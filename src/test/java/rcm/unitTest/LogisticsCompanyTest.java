package rcm.unitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class LogisticsCompanyTest {

    private Application app;
    private LogisticsCompany company1;
    private Client client1;

    @Before
    public void init() throws WrongInputException, IOException {
        Repository repo = new SqliteRepository();
        repo.clearDatabase();
        app = new Application(repo);
        company1 = app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 1098 Koebenhavn K", "Soeren Skou",
                "info@maersk.com", "Agile123");
        app.createNewLogisticsCompany("Hamburg Sud", "Willy-Brandt-Strasse 59, 20457 Hamburg, Germany",
                "Dr. Arnt Vespermann", "info@hamburgsud-line.com", "Agile123");
        app.logInUser("info@maersk.com", "Agile123");
        client1 = app.createNewClient("Novo Nordisk", "Novo Alle, 2880 Bagsvaerd", "Lars Fruergaard Joergensen",
                "info@novonordisk.com", "Agile123");
        app.logInUser("info@hamburgsud-line.com", "Agile123");
        app.createNewClient("Chiquita", "1855 Griffin Rd. Miami, Florida", "Carmen Rodriguez", "bananas@chiquita.com",
                "Agile123");
    }

    @Test
    public void TestWrongClientCreatingJourney() throws IOException, WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        assertEquals(null, app.requestNewJourney("Nordhavn", "Rotterdam", "remoulade"));
        app.logOut();
    }

    @Test
    public void testSearchClientById() throws WrongInputException {
        app.logInUser("info@maersk.com", "Agile123");
        assertTrue(company1.searchClientById(Integer.toString(client1.getId())).contains(client1));
        app.logOut();
    }
}
