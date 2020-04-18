package rcm.unitTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import rcm.Client;
import rcm.Journey;
import rcm.LogisticsCompany;


public class JourneyTest {

    LogisticsCompany company;
    Client client;
    Journey journey;
    
    @Before
    public void init() {
        company = new LogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com");
        client = company.createClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen", "info@novonordisk.com");
        journey = company.createJourney(client, "Rotterdam", "Los Angeles", "tobacco");
    }
    
    @Test
    public void testHashCode() {
//        fail("Not yet implemented");
    }

    @Test
    public void testGetCompany() {
        assertEquals(null, journey.getCompany());
        company.createContainer();
        company.startJourney(journey, LocalDateTime.of(2020, 3, 13, 4, 20));
        assertEquals(company, journey.getCompany());
    }

    @Test
    public void testEqualsObject() {
//        fail("Not yet implemented");
    }

}
