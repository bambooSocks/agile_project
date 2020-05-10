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
    private ContainerStatus status;
    private boolean test;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        app = new Application(repo);

        test = false;

        app.createNewLogisticsCompany("Maersk", "Kbh", "Someone", "info@maersk.com", "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        container = app.createNewContainer();
        client = app.createNewClient("Novo Nordisk", "Kbh", "Someone else", "info@novonordisk.com", "Object123");
        client2 = app.createNewClient("Dole", "Florida", "Someone completely different", "dole@bananas.com",
                "Object123");
        app.logInUser("info@novonordisk.com", "Object123");
        journey = app.requestNewJourney("Copenhagen", "Rotterdam", "medical tools");
        app.shareJourney(client2.getId(), journey.getId());
        app.logInUser("info@maersk.com", "Agile123");
        app.startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20));
        status = new ContainerStatus(LocalDateTime.of(2020, 3, 13, 4, 25), 12.0, 80.0, 1.01, "Copenhagen");
        app.enterNewContainerStatus(journey.getId(), status);
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
        app.logOut();
    }

    @Test
    public void testSearchForClients() throws WrongInputException, IOException {
        app.logInUser("info@maersk.com", "Agile123");
        Client client1 = client;
        assertFalse(app.searchForClients("Novo Nordisk", false, false, false, false).contains(client1));
        assertTrue(app.searchForClients("Novo Nordisk").contains(client1));
        assertTrue(app.searchForClients("Kbh").contains(client1));
        assertTrue(app.searchForClients("Someone else").contains(client1));
        assertTrue(app.searchForClients("info@novonordisk.com").contains(client1));
        app.logOut();
    }

    @Test
    public void testEnterNewContainerStatusJourneyLocalDateTimeDoubleDoubleDoubleString()
            throws WrongInputException, IOException {
        app.logInUser("info@maersk.com", "Agile123");
        app.startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20));
        app.enterNewContainerStatus(journey.getId(), LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        app.logInUser("info@novonordisk.com", "Object123");
        assertTrue(app.requestStatus(journey.getId()).contains(status));
        assertTrue(journey.containsStatus(status));
        app.logOut();
    }

    @Test
    public void testEnterNewContainerStatusJourneyStatus() throws WrongInputException, IOException {
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        app.startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20));
        app.endJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 5, 20));
        app.enterNewContainerStatus(journey.getId(), status);
        assertEquals(null, app.requestStatus(journey.getId()));
    }

    @Test
    public void testSearchForJourneys() throws WrongInputException {
        app.logInUser("info@novonordisk.com", "Object123");
        Journey journey1 = journey;
        assertFalse(app.searchForJourneys("Copenhagen", false, false, false).contains(journey1));
        assertTrue(app.searchForJourneys("Copenhagen").contains(journey1));
        assertTrue(app.searchForJourneys("Rotterdam").contains(journey1));
        assertTrue(app.searchForJourneys("medical tools").contains(journey1));
        app.logOut();
    }

    @Test
    public void testSearchForSharedJourneys() throws WrongInputException {
        app.logInUser("dole@bananas.com", "Object123");
        assertFalse(app.searchForSharedJourneys("Copenhagen", false, false, false).contains(journey));
        assertTrue(app.searchForSharedJourneys("Copenhagen").contains(journey));
        assertTrue(app.searchForSharedJourneys("Rotterdam").contains(journey));
        assertTrue(app.searchForSharedJourneys("medical tools").contains(journey));
        app.logOut();
    }

    @Test
    public void testRequestStatus() throws WrongInputException, IOException {
        ContainerStatus status = new ContainerStatus(LocalDateTime.of(2020, 3, 14, 4, 20), 13.5, 75.0, 1.01,
                "Copenhagen");
        app.logInUser("info@maersk.com", "Agile123");
        app.startJourney(journey.getId(), LocalDateTime.of(2020, 3, 13, 4, 20));
        app.enterNewContainerStatus(journey.getId(), status);
        app.logInUser("info@novonordisk.com", "Object123");
        assertNotEquals(null, app.requestStatus(journey.getId()));
        assertTrue(app.requestStatus(journey.getId()).contains(status));
        app.logOut();
        assertEquals(null, app.requestStatus(journey.getId()));
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
        Client client2 = app.createNewClient("Chiquita", "USA", "Someone", "bananas@chiquita.com", "Object123");
        assertEquals(null, client2);
        app.logInUser("info@maersk.com", "Agile123");
        client2 = app.createNewClient("Chiquita", "USA", "Someone", "bananas@chiquita.com", "Object123");
        assertFalse(app.shareJourney(client2.getId(), journey.getId()));
        app.logInUser("info@novonordisk.com", "Object123");
        assertTrue(app.shareJourney(client2.getId(), journey.getId()));
        app.logInUser("bananas@chiquita.com", "Object123");
        assertNotEquals(null, app.requestSharedJourneys());
        assertTrue(app.requestSharedJourneys().contains(journey));
        app.logOut();
        assertEquals(null, app.requestSharedJourneys());
    }

    @Test
    public void testSearchForClientsJourneysIdQuery() throws WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        assertTrue(app.searchForClientsJourneys(client.getId(), "Copenhagen").contains(journey));
        app.logOut();
    }

    @Test(expected = WrongInputException.class)
    public void testWrongInputForCompany() throws WrongInputException, IOException {
        app.createNewLogisticsCompany("wrongname", "@|!^", "wn", "thisatthatdotcom", "weakpswd");
    }

    @Test(expected = WrongInputException.class)
    public void testWrongPasswordForComapny() throws WrongInputException {
        app.logInUser("info@maersk.com", "wrongpassword");
    }

    @Test
    public void testFireChange() {

        app.addObserver(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                case "setTrue":
                    test = true;
                    break;
                case "set":
                    test = (boolean) evt.getNewValue();
                    break;
                }
            }
        });

        app.fireChange("setTrue");
        assertTrue(test);
        app.fireChange("set", false);
        assertFalse(test);
    }

    @Test
    public void testGettersByID() throws WrongInputException {
        assertEquals(null, app.getClientById(-1));
        assertEquals(null, app.getJourneyById(-1));
        app.logInUser("info@maersk.com", "Agile123");
        assertEquals(null, app.getContainerById(-1));
    }

    @Test
    public void testRequestingJourney() throws WrongInputException {
        assertEquals(null, app.requestClientsStatus(client.getId(), journey.getId()));
        assertEquals(null, app.requestContainersStatus(container.getId(), journey.getId()));
        app.logInUser("info@maersk.com", "Agile123");
        assertEquals(null, app.requestClientsStatus(-1, journey.getId()));
        assertEquals(null, app.requestContainersStatus(-1, journey.getId()));
        assertTrue(app.requestClientsStatus(client.getId(), journey.getId()).contains(status));
        assertTrue(app.requestContainersStatus(container.getId(), journey.getId()).contains(status));
        assertTrue(app.requestClientsJourneys(client.getId()).contains(journey));
        assertFalse(app.requestClientsJourneys(-1).contains(journey));
        assertTrue(app.requestContainersJourneys(container.getId()).contains(journey));
    }

    @Test
    public void testAdditionalSearch() throws WrongInputException {
        assertEquals(client.getId(), (int) app.searchClientIdByEmail("info@novonordisk.com"));
        assertEquals(null, app.searchClientIdByEmail("wrong@email.com"));
        app.logInUser("info@maersk.com", "Agile123");
        assertTrue(app.searchForContainersJourneys(container.getId(), "Copenhagen").contains(journey));
    }
    
}
