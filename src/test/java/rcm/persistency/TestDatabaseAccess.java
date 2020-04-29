package rcm.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class TestDatabaseAccess {
    Repository repo;
    Application app;
    LogisticsCompany lc1;
    Client cl1, cl2;
    Journey j1;
    Container c1;
    LocalDateTime timestamp, timestamp2;
    ContainerStatus cs1;
    Client dbClient;

    @Before
    public void init() throws WrongInputException, IOException {
        repo = new SqliteRepository();
        app = new Application(repo);
        repo.clearDatabase(); // Remove all users from the database to get a fresh database for testing

        lc1 = app.createNewLogisticsCompany("Maersk", "Linde Alle", "Peter", "peter@maersk.dk", "Password12345");
        app.logInUser("peter@maersk.dk", "Password12345");
        cl1 = app.createNewClient("DTU", "Linde Alle 2", "Tom", "tom@dtu.dk", "Tom123456");
        cl2 = app.createNewClient("Novo Nordisk", "Lyngbyvej 56", "Linea Hansen", "linea@novo.dk", "Password12345");
        c1 = app.createNewContainer();
        app.logInUser("tom@dtu.dk", "Tom123456");
        timestamp = LocalDateTime.of(2019, 4, 22, 15, 0);
        j1 = app.requestNewJourney("Copenhagen", "New York", "robots", timestamp);

        timestamp2 = LocalDateTime.of(2020, 4, 22, 15, 0);
        cs1 = new ContainerStatus(timestamp2, 35.0, 20.0, 101.0, "Copenhagen");

    }

    @Test
    public void testDatabase() throws IOException, WrongInputException {

        app.logInUser("peter@maersk.dk", "Password12345");
        boolean success = app.enterNewContainerStatus(j1, cs1);

        repo = new SqliteRepository();

        Container dbContainer = repo.readContainer(c1.getId());
        dbClient = repo.readClient(cl1.getEmail());
        LogisticsCompany dbUser3 = repo.readLogisticsCompany(lc1.getEmail());
        Journey dbJourney = repo.readJourney(j1.getId());
        // TODO: group2 do you need this mili variable?
        long mili = new Date().getTime();
        ContainerStatus dbStatus = dbJourney.getStatus().get(0);

        assertEquals("Maersk", dbUser3.getName());

        assertEquals("DTU", dbClient.getName());

        assertEquals(c1.getId(), dbContainer.getId());

        assertEquals("robots", dbJourney.getContent());

        assertEquals(35.0, dbStatus.getTemperature(), 0.001);

        assertTrue(success);

    }

    @Test
    public void testUpdate() throws IOException, WrongInputException {
        dbClient = repo.readClient(cl1.getEmail());
        dbClient.updateEmail("client@maersk.dk");
        repo.updateCompany(lc1);
        assertEquals("client@maersk.dk", repo.readClient(cl1.getEmail()).getEmail());

    }
    
    
    @Test
    public void testreadAllCompanies() throws IOException, WrongInputException {
        LogisticsCompany lc2 = new LogisticsCompany("Doprava", "Jedlova 21", "Petr Zeleny", "zelda@novo.dk", "Password12345");
        List <LogisticsCompany> list = repo.readAllLogisticsCompanies();
        assertTrue(list.contains(lc2)&&list.contains(lc1));

    }
    
    
    
    @Test
    public void testSharedJourney() throws IOException, WrongInputException {
        Client cl3 = lc1.createClient("Brambora", "Lesova 3", "Linea Hansen", "linea2@novo.dk", "Password12345");
//        cl2.shareClientData(cl1.getEmail(),cl2.getEmail());
        assertTrue(cl2.getSharedJourneyList().contains(j1));
        assertFalse(cl3.getSharedJourneyList().contains(j1));

    }

}
