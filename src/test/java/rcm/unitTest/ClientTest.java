package rcm.unitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Journey;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class ClientTest {
    private Application app;
    private Client client1, client2;
    private Journey journey;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        app = new Application(repo);
        app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com",
                "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        client1 = app.createNewClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        client2 = app.createNewClient("Seuss Supply", "Prarie of Prax", "The Lorax", "Iliketrees@books.com",
                "Agile123");
        app.logInUser("info@novonordisk.com", "Agile123");
        journey = app.requestNewJourney("Copenhagen", "Rotterdam", "medical tools");
        app.shareJourney(client2.getId(), journey.getId());
    }

    @Test
    public void testShareJourney() {
        assertFalse(client1.shareJourney(null, null));
        assertFalse(client1.shareJourney(client1, null));
    }

    @Test
    public void testSearchJourneyById() {
        assertTrue(client1.searchJourneyById(Integer.toString(journey.getId())).contains(journey));
    }

    @Test
    public void testSearchSharedJourneyById() {
        assertTrue(client2.searchSharedJourneyById(Integer.toString(journey.getId())).contains(journey));
    }
}
