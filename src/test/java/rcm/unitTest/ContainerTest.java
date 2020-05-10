package rcm.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Container;
import rcm.model.Journey;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class ContainerTest {

    private Application app;
    private Journey journey, journey2;
    private Container container;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        app = new Application(repo);
        app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com",
                "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        container = app.createNewContainer();
        app.createNewClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        app.logInUser("info@novonordisk.com", "Agile123");
        journey = app.requestNewJourney("Rotterdam", "Los Angeles", "tobacco");
        journey2 = app.requestNewJourney("Frankfurt", "El Paso", "wine");
        app.logInUser("info@maersk.com", "Agile123");
        app.startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20));
        app.logOut();
    }

    @Test
    public void testSearchJourneyById() {
        assertTrue(container.searchJourneyById(Integer.toString(journey.getId())).contains(journey));
    }

    @Test
    public void testRequestStatus() {
        assertEquals(container.requestStatus(journey), journey.getStatus());
        assertEquals(container.requestStatus(journey2), null);
    }
}
