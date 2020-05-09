package rcm.unitTest;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class ClientTest {
    LogisticsCompany company1;
    Client client1;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        Application app = new Application(repo);
        company1 = app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou",
                "info@maersk.com", "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        client1 = app.createNewClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
    }

    @Test
    public void testShareJourney() {
        assertFalse(client1.shareJourney(null, null));
        assertFalse(client1.shareJourney(client1, null));
    }
}
