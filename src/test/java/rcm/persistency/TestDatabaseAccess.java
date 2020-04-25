package rcm.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class TestDatabaseAccess {

    @Test
    public void testDatabase() throws IOException, WrongInputException {

        Repository db = new SqliteRepository();
        db.clearDatabase(); // Remove all users from the database to get a fresh database for testing
        LogisticsCompany lc1 = new LogisticsCompany(db,"Maersk", "Linde Alle", "Peter", "peter@maersk.dk", "Peter12345");
        
        Client cl1 = lc1.createClient("DTU", "Linde Alle 2", "Tom", "tom@dtu.dk", "Tom123456");
        Container c1= lc1.createContainer();
        Journey j1 = lc1.createJourney(cl1, "Copenhagen", "New York", "robots");
        LocalDateTime timestamp = LocalDateTime.of(2019, 4, 22, 15, 0);
        lc1.startJourney(j1, timestamp);
        
        LocalDateTime timestamp2 = LocalDateTime.of(2020, 4, 22, 15, 0);
        ContainerStatus cs1 = new ContainerStatus(timestamp2, 35.0, 20.0, 101.0, "Copenhagen");

        boolean success = lc1.enterStatus(cs1, j1);

        db = new SqliteRepository();

        Container dbUser1 = db.readContainer(c1.getId());
        Client dbUser2 = db.readClient(cl1.getId());
        LogisticsCompany dbUser3 = db.readLogisticsCompany(lc1.getId());
        Journey dbUser4 = db.readJourney(j1.getId());
        long mili = new Date().getTime();
        ContainerStatus dbStatus = dbUser4.getHistory().get(0);
        

        assertEquals("Maersk", dbUser3.getName());

        assertEquals("DTU", dbUser2.getName());

        assertEquals(c1.getId(), dbUser1.getId());

        assertEquals("robots", dbUser4.getContent());
        
        assertEquals(35.0, dbStatus.getTemperature(),0.001);
        
        
        assertTrue(success);

    }

}
