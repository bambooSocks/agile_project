package rcm.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Client;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class ClientTest {
    LogisticsCompany company1, company2;
    Client client1, client2;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository db = new SqliteRepository();
        db.clearDatabase();
        company1 = new LogisticsCompany(db, "Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou", "info@maersk.com",
                "Agile123");
        company2 = new LogisticsCompany(db, "Hamburg Sud", "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany",
                "Dr. Arnt Vespermann", "info@hamburgsud-line.com", "Agile123");
        client1 = company1.createClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        client2 = company2.createClient("Chiquita", "1855 Griffin Rd. Miami, Florida", "Carmen Rodriguez",
                "bananas@chiquita.com", "Agile123");
    }

    @Test
    public void testShareClientData() {
        assertTrue(client2.shareClientData(client1.getEmail(), client2.getEmail()).isEmpty());
        assertTrue(client2.shareClientData("blabla@email.com", "fakeemail@fake.com").isEmpty());
    }
    
    @Test
    public void testViewClientData() {
        assertEquals(null, client2.viewClientData("bla@fake.com", "bla@fake.com"));
    }
}
