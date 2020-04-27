package rcm.persistency;

import java.io.IOException;
import java.time.LocalDateTime;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class TestDB2 {
    Repository repo;
    Application app;
    LogisticsCompany lc1;
    Client cl1,cl2;
    Journey j1;
    Container c1;
    LocalDateTime timestamp,timestamp2;
    ContainerStatus cs1;
    Client dbClient;
    
    
    public void testData() throws WrongInputException, IOException {
        
        repo = new SqliteRepository();
        app = new Application(repo);
        repo.clearDatabase();
        
        lc1 = app.createNewLogisticsCompany("3P Logistics A/S", "Rendebanen 6D, 6000 Kolding", "Peter Hansen", "peter@3plogistics.dk","Password12345");
        app.logInUser("peter@3plogistics.dk","Password12345");
        cl1 = app.createNewClient("CBS", "Byhojen 2", "Tom Hanks", "tom@cbs.dk", "Tom123456");
        cl2 = app.createNewClient("Novo Enzymes", "Smorumvej 43", "Linea Hansen", "linea@novoenzymes.dk","Password12345");
        c1 = app.createNewContainer();
        app.logInUser("tom@cbs.dk", "Tom123456");
        j1 = app.requestNewJourney("Copenhagen", "New York", "robots", null); // if you pass null then the journey doesn't start straight away ... otherwise give it a timestamp
    }
   
}
