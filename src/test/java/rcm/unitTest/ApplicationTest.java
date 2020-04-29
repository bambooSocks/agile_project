package rcm.unitTest;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class ApplicationTest {

    private Application app;
    private String propertyName;
    private Journey journey;
    private Client client, client2;
    private Container container;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        app = new Application(repo);

        app.createNewLogisticsCompany("Maersk", "Kbh", "Someone", "info@maersk.com", "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        container = app.createNewContainer();
        client = app.createNewClient("Novo Nordisk", "Kbh", "Someone else", "info@novonordisk.com", "Object123");
        client2 = app.createNewClient("Dole", "Florida", "Someone completely different", "dole@bananas.com",
                "Object123");
        app.logInUser("info@novonordisk.com", "Object123");
        journey = app.requestNewJourney("Copenhagen", "Rotterdam", "medical tools",
                LocalDateTime.of(2020, 3, 13, 4, 20));
        app.shareJourney(client2, journey);
        app.logOut();
    }

    @Test
    public void testAddObserver() throws WrongInputException {
        app.addObserver(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                propertyName = evt.getPropertyName();
            }
        });
        app.logInUser("info@maersk.com", "Agile123");
        assertEquals("companyLoggedIn", propertyName);
        app.logInUser("info@novonordisk.com", "Object123");
        assertEquals("clientLoggedIn", propertyName);
        app.logOut();
        assertEquals("userLoggedOut", propertyName);
    }

    @Test
    public void testEnterNewContainerStatusJourneyLocalDateTimeDoubleDoubleDoubleString()
            throws WrongInputException, IOException {
        app.logInUser("info@maersk.com", "Agile123");
        app.enterNewContainerStatus(journey, LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01, "Copenhagen");
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        assertTrue(journey.containsStatus(status));
        app.logOut();
    }

    @Test
    public void testSearchForClients() throws WrongInputException, IOException {
        app.logInUser("info@maersk.com", "Agile123");
        Client client1 = client;
        assertTrue(app.searchForClients("Novo Nordisk").contains(client1));
        assertTrue(app.searchForClients("Kbh").contains(client1));
        assertTrue(app.searchForClients("Someone else").contains(client1));
        assertTrue(app.searchForClients("info@novonordisk.com").contains(client1));
        assertTrue(app.searchForClients(Integer.toString(client1.getId())).contains(client1));
        app.logOut();
    }

    @Test
    public void testSearchForJourneys() throws WrongInputException {
        app.logInUser("info@novonordisk.com", "Object123");
        Journey journey1 = journey;
        assertTrue(app.searchForJourneys("Copenhagen").contains(journey1));
        assertTrue(app.searchForJourneys("Rotterdam").contains(journey1));
        assertTrue(app.searchForJourneys("medical tools").contains(journey1));
        assertTrue(app.searchForJourneys(Integer.toString(journey1.getId())).contains(journey1));
        app.logOut();
    }

    @Test
    public void testSearchForSharedJourneys() throws WrongInputException {
        app.logInUser("dole@bananas.com", "Object123");
        assertEquals(app.requestSharedJourneys(), app.searchForSharedJourneys("Copenhagen"));
        assertEquals(app.requestSharedJourneys(), app.searchForSharedJourneys("Rotterdam"));
        assertEquals(app.requestSharedJourneys(), app.searchForSharedJourneys("medical tools"));
        assertEquals(app.requestSharedJourneys(), app.searchForSharedJourneys(Integer.toString(journey.getId())));
        app.logOut();
    }

    @Test
    public void testRequestStatus() throws WrongInputException, IOException {
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        app.logInUser("info@maersk.com", "Agile123");
        app.enterNewContainerStatus(journey, status);
        app.logInUser("info@novonordisk.com", "Object123");
        assertNotEquals(null, app.requestStatus(journey));
        assertTrue(app.requestStatus(journey).contains(status));
        app.logOut();
        assertEquals(null, app.requestStatus(journey));
    }

    @Test
    public void testRequestClients() throws WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        assertNotEquals(null, app.requestClients());
        assertTrue(app.requestClients().contains(client));
        app.logOut();
        assertEquals(null, app.requestClients());
    }

    @Test
    public void testRequestContainers() throws WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        assertNotEquals(null, app.requestContainers());
        assertTrue(app.requestContainers().contains(container));
        app.logOut();
        assertEquals(null, app.requestContainers());
    }

    @Test
    public void testRequestJourneys() throws WrongInputException {
        app.logInUser("info@novonordisk.com", "Object123");
        assertNotEquals(null, app.requestJourneys());
        assertTrue(app.requestJourneys().contains(journey));
        app.logOut();
        assertEquals(null, app.requestJourneys());
    }

    @Test
    public void testRequestSharedJourneys() throws IOException, WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        Client client2 = app.createNewClient("Chiquita", "USA", "Someone", "bananas@chiquita.com", "Object123");
        assertFalse(app.shareJourney(client2, journey));
        app.logInUser("info@novonordisk.com", "Object123");
        assertTrue(app.shareJourney(client2, journey));
        app.logInUser("bananas@chiquita.com", "Object123");
        assertNotEquals(null, app.requestSharedJourneys());
        assertTrue(app.requestSharedJourneys().contains(journey));
        app.logOut();
        assertEquals(null, app.requestSharedJourneys());
    }

}
