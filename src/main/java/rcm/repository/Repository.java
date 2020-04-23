package rcm.repository;

import java.io.IOException;

import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;

public interface Repository {
    
	void createLogisticsCompany(LogisticsCompany po) throws IOException;

	LogisticsCompany readLogisticsCompany(int i) throws IOException;
	
	
	void createContainer(Container po) throws IOException;

    Container readContainer(int i) throws IOException;
    
    
    void createClient(Client po) throws IOException;

    Client readClient(int i) throws IOException;
    
    
    void createContainerStatus(ContainerStatus po) throws IOException;

    ContainerStatus readContainerStatus(int i) throws IOException;
    
    
    void createJourney(Journey po) throws IOException;

    Journey readJourney(int i) throws IOException;
	
	void clearDatabase();

}