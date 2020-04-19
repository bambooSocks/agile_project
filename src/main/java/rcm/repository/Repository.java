package rcm.repository;

import java.io.IOException;

import rcm.Container;
import rcm.LogisticsCompany;

public interface Repository {
/*
	void createUser(LogisticsCompany po) throws IOException;

	LogisticsCompany readUser(int i) throws IOException;
	*/
	
	void createContainer(Container po) throws IOException;

   Container readContainer(int i) throws IOException;
	
	void clearDatabase();

}