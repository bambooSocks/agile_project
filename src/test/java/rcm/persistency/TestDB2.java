package rcm.persistency;

import java.io.IOException;
import java.time.LocalDateTime;

import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;
import rcm.model.WrongInputException;
import rcm.repository.Repository;
import rcm.repository.SqliteRepository;

public class TestDB2 {
    Repository db;
    LogisticsCompany lc1;
    Client cl1,cl2;
    Journey j1;
    Container c1;
    LocalDateTime timestamp,timestamp2;
    ContainerStatus cs1;
    Client dbClient;
    
    
    public void testData() throws WrongInputException, IOException {
        
        Repository db = new SqliteRepository();
        db.clearDatabase();
        
        lc1 = new LogisticsCompany(db, "3P Logistics A/S", "Rendebanen 6D, 6000 Kolding", "Peter Hansen", "peter@3plogistics.dk","Password12345");
        cl1 = lc1.createClient("CBS", "Byhojen 2", "Tom Hanks", "tom@cbs.dk", "Tom123456");
        cl2 = lc1.createClient("Novo Enzymes", "Smorumvej 43", "Linea Hansen", "linea@novoenzymes.dk","Password12345");
        c1 = lc1.createContainer();
        j1 = lc1.createJourney(cl1, "Copenhagen", "New York", "robots");
    }
   
}
