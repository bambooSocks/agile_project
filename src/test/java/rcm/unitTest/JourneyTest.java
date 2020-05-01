package rcm.unitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class JourneyTest {

    Application app;
    LogisticsCompany company;
    Client client;
    Journey journey;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        app = new Application(repo);
        repo.clearDatabase();
        company = app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou",
                "info@maersk.com", "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        client = app.createNewClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        app.logInUser("info@novonordisk.com", "Agile123");
        journey = app.requestNewJourney("Rotterdam", "Los Angeles", "tobacco");
    }

    @Test
    public void testGetCompany() throws IOException, WrongInputException {
        assertEquals(null, journey.getCompany());
        app.logInUser("info@maersk.com", "Agile123");
        app.createNewContainer();
        company.startJourney(journey, LocalDateTime.of(2020, 3, 13, 4, 20));
        assertEquals(company, journey.getCompany());
    }

}
