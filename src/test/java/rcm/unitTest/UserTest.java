package rcm.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import rcm.model.Client;
import rcm.model.LogisticsCompany;
import rcm.model.User;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class UserTest {
    Throwable exception1, exception2, exception3, exception4;

    @Before
    public void init() {
        Repository db = new SqliteRepository();
        db.clearDatabase();
        exception1 = assertThrows(WrongInputException.class,
                () -> new User("Novo Nordisk", "a", "Lars Fruergaard Joergensen", "info@novonordisk.com", "pass"));
        exception2 = assertThrows(WrongInputException.class, () -> new LogisticsCompany(db, "Novo Nordisk",
                "Novo Alle, 2880 Bagsvaerd", "r", "info.com", "Agile123"));
        exception3 = assertThrows(WrongInputException.class,
                () -> new Client("n", "Novo Alle, 2880 Bagsvaerd", "^", "@novonordisk.com", "Agile123"));
        exception4 = assertThrows(NullPointerException.class, () -> User.SHA1_Hasher(null));
    }

    @Test
    public void testUserConstructor() {
        assertEquals("Please correct the following input: address password", exception1.getMessage());
        assertEquals("Please correct the following input: reference person email", exception2.getMessage());
        assertEquals("Please correct the following input: name reference person email", exception3.getMessage());
    }

    @Test
    public void testSHA1_Hasher() {
        assertNotEquals(null, exception4);
    }

}
