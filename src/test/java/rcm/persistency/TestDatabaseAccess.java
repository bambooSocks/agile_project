package rcm.persistency;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import rcm.Container;
import rcm.LogisticsCompany;
import rcm.Journey;
import rcm.ContainerStatus;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class TestDatabaseAccess {
	
	@Test
	public void testDatabase() throws IOException {
        /*
         * Repository db = new SqliteRepository(); db.clearDatabase(); // Remove all
         * users from the database to get a fresh database for testing LogisticsCompany
         * user = new LogisticsCompany("Maersk","linde alle","Peter",
         * "kjh@ldhjf.cz","kjh"); db.createUser(user); db = new SqliteRepository();
         * LogisticsCompany dbUser = db.readUser(user.getId());
         * assertEquals(user.getId(),dbUser.getId());
         * assertEquals(user.getName(),dbUser.getName());
         * assertEquals(user.getEmail(),dbUser.getEmail());
         */
	    
	       Repository db = new SqliteRepository();
	       db.clearDatabase(); // Remove all users from the database to get a fresh database for testing
	       //Container user = new Container();
	       //Container user2 = new Container();
	       //db.createContainer(user);
	       //db.createContainer(user2);
	       db = new SqliteRepository();
	       //Container dbUser = db.readContainer(user.getId());
	       //Container dbUser2 = db.readContainer(user2.getId());
	       //assertEquals(user.getId(),dbUser.getId());
	       //assertEquals(user2.getId(),dbUser2.getId());
	}

}
