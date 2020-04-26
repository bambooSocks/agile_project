package rcm.unitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Client;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class JourneyTest {

    LogisticsCompany company;
    Client client;
    Journey journey;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository db = new SqliteRepository();
        db.clearDatabase();
        company = new LogisticsCompany(db, "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com", "Agile123");
        client = company.createClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        journey = company.createJourney(client, "Rotterdam", "Los Angeles", "tobacco");
    }

    @Test
    public void testHashCode() {
        Journey j2 = journey;
        assertTrue(journey.equals(j2) && j2.equals(journey));
        assertTrue(journey.hashCode() == j2.hashCode());
    }

    @Test
    public void testGetCompany() throws IOException {
        assertEquals(null, journey.getCompany());
        company.createContainer();
        company.startJourney(journey, LocalDateTime.of(2020, 3, 13, 4, 20));
        assertEquals(company, journey.getCompany());
    }

    @Test
    public void testEqualsObject() throws IOException {
        assertFalse(journey.equals(null));
        assertFalse(journey.equals(client));
        Journey j2 = company.createJourney(client, "Lima", "Hamburg", "bananas");
        assertFalse(journey.equals(j2));
        assertTrue(journey.equals(journey));
    }

}
