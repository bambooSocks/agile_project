package rcm.unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rcm.Client;
import rcm.LogisticsCompany;

public class LogisticsCompanyTest {

    LogisticsCompany company1, company2;
    Client client1, client2;

    @Before
    public void init() {
        company1 = new LogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com");
        client1 = company1.createClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com");
        company2 = new LogisticsCompany("Hamburg Sud", "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany",
                "Dr. Arnt Vespermann", "info@hamburgsud-line.com");
        client2 = company2.createClient("Chiquita", "1855 Griffin Rd. Miami, Florida", "Carmen Rodriguez",
                "bananas@chiquita.com");
    }

    @Test
    public void TestWrongClientCreatingJourney() {
        assertEquals(null, company1.createJourney(client2, "Nordhavn", "Rotterdam", "remoulade"));
        assertNotEquals(null, company1.createJourney(client1, "Nordhavn", "Rotterdam", "remoulade"));
    }

}
