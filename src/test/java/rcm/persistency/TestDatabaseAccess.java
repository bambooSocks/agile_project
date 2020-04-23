package rcm.persistency;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

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
        LogisticsCompany lc1 = new LogisticsCompany("Maersk", "Linde Alle", "Peter", "peter@maersk.dk", "Peter12345");
        Container c1 = new Container(lc1);
        Client cl1 = new Client("DTU", "Linde Alle 2", "Tom", "tom@dtu.dk", "Tom123456");
        Journey j1 = new Journey("Copenhagen", "New York", "robots", cl1);
        LocalDateTime timestamp = LocalDateTime.of(2020, 4, 22, 15, 0);
        ContainerStatus cs1 = new ContainerStatus(timestamp, 35.0, 20.0, 101.0, "Copenhagen");

        db.createLogisticsCompany(lc1);
        db.createContainer(c1);
        db.createClient(cl1);
        db.createJourney(j1);
        db.createContainerStatus(cs1);

        db = new SqliteRepository();

        Container dbUser1 = db.readContainer(c1.getId());
        Client dbUser2 = db.readClient(cl1.getId());
        LogisticsCompany dbUser3 = db.readLogisticsCompany(lc1.getId());
        Journey dbUser4 = db.readJourney(j1.getId());

        assertEquals("Maersk", dbUser3.getName());

        assertEquals("DTU", dbUser2.getName());

        assertEquals(c1.getId(), dbUser1.getId());

        assertEquals("robots", dbUser4.getContent());

    }

}
