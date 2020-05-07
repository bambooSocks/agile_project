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
    Repository repo,repo2;
    Application app,app2;
    LogisticsCompany lc1,lc3;
    Client cl1, cl2,cl4;
    Journey j1,j4;
    Container c1,c3;
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
        cl1 = app.createNewClient("DTU", "Linde Alle 2", "Tom", "tomisashortname@dtu.dk", "Tom123456");
        cl2 = app.createNewClient("Novo Nordisk", "Lyngbyvej 56", "Linea Hansen", "linea@novo.dk", "Password12345");
        c1 = app.createNewContainer();
        app.logInUser("tomisashortname@dtu.dk", "Tom123456");
        timestamp = LocalDateTime.of(2020, 4, 16, 15, 0);
        j1 = app.requestNewJourney("Copenhagen", "New York", "robots");
        app.logInUser("peter@maersk.dk", "Password12345");
        app.startJourney(j1.getId(), timestamp);
        timestamp2 = LocalDateTime.of(2020, 4, 22, 15, 0);
        cs1 = new ContainerStatus(timestamp2, 35.0, 20.0, 101.0, "Copenhagen");
    }

    @Test
    public void testDatabase() throws IOException, WrongInputException {

        app.logInUser("peter@maersk.dk", "Password12345");
        boolean success = app.enterNewContainerStatus(j1.getId(), cs1);
        assertTrue(success);
        repo = new SqliteRepository();

        Container dbContainer = repo.readContainer(c1.getId());
        assertEquals(c1.getId(), dbContainer.getId());
        dbClient = repo.readClient(cl1.getEmail());
        LogisticsCompany dbUser3 = repo.readLogisticsCompany(lc1.getEmail());
        assertEquals("Maersk", dbUser3.getName());
        Journey dbJourney = repo.readJourney(j1.getId());

        assertEquals("robots", dbJourney.getContent());
        ContainerStatus dbStatus = dbJourney.getStatus().get(0);
        assertEquals(35.0, dbStatus.getTemperature(), 0.001);

    }

    @Test
    public void testUpdate() throws IOException, WrongInputException {
        dbClient = repo.readClient(cl1.getEmail());
        app.logInUser("tomisashortname@dtu.dk", "Tom123456");
        dbClient.updateEmail("christianhansen@maersk.dk");
        repo.updateCompany(lc1);
        assertEquals("christianhansen@maersk.dk", repo.readClient(cl1.getEmail()).getEmail());
    }

    @Test
    public void testreadAllCompanies() throws IOException, WrongInputException {
        LogisticsCompany lc2 = app.createNewLogisticsCompany("Doprava", "Jedlova 21", "Petr Zeleny", "zelda@novo.dk",
                "Password12345");
        List<LogisticsCompany> list = repo.readAllLogisticsCompanies();
        assertTrue(list.contains(lc2));
        assertTrue(list.contains(lc1));
    }

    @Test
    public void testSharedJourney() throws IOException, WrongInputException {
        Client cl3 = app.createNewClient("Brambora", "Lesova 3", "Linea Hansen", "linea2@novo.dk", "Password12345");
        cl1.shareJourney(cl2, j1);
        assertTrue(cl2.getSharedJourneyList().contains(j1));
        assertFalse(cl3.getSharedJourneyList().contains(j1));
    }
    
    @Test
    public void testRepo()throws IOException, WrongInputException {
        lc3 = repo.readLogisticsCompany("someone@somehwere.dk");
        assertEquals(null,lc3);
        c3 = repo.readContainer(-123);
        assertEquals(null,c3);
        cl4 = repo.readClient("whoistheclient@idontknow.dk");
        assertEquals(null,cl4);
        j4 = repo.readJourney(-34574);
        assertEquals(null,j4);        
    }
    
}
