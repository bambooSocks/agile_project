package rcm.persistency;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Test;

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
    LogisticsCompany lc1,lc2;
    Client cl1,cl2,cl3,cl4;
    Journey j1,j2,j3,j4,j5,j6,j7,j8;
    
    
    
    LocalDateTime timestamp,timestamp2;
    ContainerStatus cs1;
    
    @Test
    public void testData() throws WrongInputException, IOException {
        
        Repository db = new SqliteRepository();
        db.clearDatabase();
        
        lc1 = new LogisticsCompany(db, "Agrofert", "Rendebanen 6D, 6000 Kolding", "Peter Hansen", "peter@3plogistics.dk","Password12345");
        lc2 = new LogisticsCompany(db, "Maersk", "Esplanaden 50, 6000 Kolding", "Cristian Hansen", "christiam@3maersk.dk","Password12345");
        cl1 = lc1.createClient("CBS", "Byhojen 2", "Tom Hanks", "tom@cbs.dk", "Password12345");
        cl2 = lc1.createClient("Novozymes", "Smorumvej 43", "William Andersen", "linea@novozymes.dk","Password12345");
        cl3 = lc2.createClient("DTU", "Tekniksevej 43", "Pablo Escobar", "linea@dtu.dk","Password12345");
        cl4 = lc2.createClient("Netto", "Ondrovej 43", "Oscar Vinci", "dave@netto.dk","Password12345");
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc1.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        lc2.createContainer();
        
        
        j1 = lc1.createJourney(cl1, "Copenhagen", "New York", "bananas");
        j2 = lc1.createJourney(cl2, "Pearl Harbor", "Tokyo", "robots");
        j3 = lc1.createJourney(cl1, "Rotterdam", "London", "cocaine");
        j4 = lc1.createJourney(cl2, "Hong Kong", "New York", "people");
        j5 = lc2.createJourney(cl3, "Los Angeles", "Rio de Janeiro", "furniture");
        j6 = lc2.createJourney(cl4, "Copenhagen", "Cairo", "cars");
        j7 = lc2.createJourney(cl3, "Rome", "Beijing", "toys");
        j8 = lc2.createJourney(cl4, "New Dili", "New York", "steel");
        
        
       

        lc1.startJourney(j1, LocalDateTime.of(2019, 4, 22, 15, 0));
        lc1.startJourney(j2, LocalDateTime.of(2019, 4, 23, 15, 0));
        lc1.startJourney(j3, LocalDateTime.of(2019, 4, 24, 15, 0));
        lc1.startJourney(j4, LocalDateTime.of(2019, 4, 25, 15, 0));
        lc2.startJourney(j5, LocalDateTime.of(2019, 4, 26, 15, 0));
        lc2.startJourney(j6, LocalDateTime.of(2019, 4, 28, 15, 0));
        lc2.startJourney(j7, LocalDateTime.of(2019, 4, 28, 15, 0));
        
        
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Copenhagen"),j1);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "New York"),j1);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Atlantic Ocean"),j1);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 20, 15, 0), 35.0, 5.0, 90.0, "Pacific Ocean"),j2);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 30, 15, 0), 0.0, 89.0, 101.0, "Indian Ocean"),j3);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 5, 22, 19, 32), -35.0, 20.0, 101.0, "San Diego"),j3);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "Wakanda"),j4);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Atlantida"),j4);
        lc1.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 12.0, 132.0, "Copenhagen"),j4);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 05, 15, 0), 35.0, 20.0, 91.0, "Prague"),j5);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 45.0, 20.0, 101.0, "Moscow"),j5);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 3.0, 32.0, 121.0, "Red Sea"),j5);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 5.0, 20.0, 111.0, "Dead Sea"),j7);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 24.0, 111.0, "Black Sea"),j7);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 25, 15, 0), 35.0, 20.0, 108.0, "Copenhagen"),j7);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 26, 15, 0), 35.0, 24.0, 101.0, "North Pole"),j7);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 35.0, 20.0, 121.0, "Antarctida"),j7);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 6.0, 106.0, "Copenhagen"),j6);
        lc2.enterStatus(new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 35.0, 5.0, 107.0, "Copenhagen"),j6);
        
        
        
        
    }
   
}
