package rcm.config;

import java.io.IOException;
import java.time.LocalDateTime;

import rcm.model.Application;
import rcm.model.Client;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.WrongInputException;

public class FakeData {
    public static void setupFakeApp(Application app) throws WrongInputException, IOException {
        // Company 1
        app.createNewLogisticsCompany("Agrofert", "Rendebanen 6D, 6000 Kolding", "Peter Hansen", "peter@agrofert.dk",
                "Password12345");
        app.logInUser("peter@agrofert.dk", "Password12345");
        Client c1 = app.createNewClient("CBS", "Byhojen 2", "Tom Hanks", "tom@cbs.dk", "Password12345");
        Client c2 = app.createNewClient("Novozymes", "Smorumvej 43", "William Andersen", "william@novozymes.dk",
                "Password12345");
        Client c3 = app.createNewClient("Stark Industries", "New York", "Tony Stark", "IamIronMan@stark.com",
                "Password12345");
        Client c4 = app.createNewClient("Federation Supply", "USS Enterprise", "Admiral Picard",
                "EarlGreyHot@shaceship.com", "Password12345");
        Client c5 = app.createNewClient("Ramasjang", "Copenhagen", "Hr. Skaeg", "IliketoSing@beard.com",
                "Password12345");
        Client c6 = app.createNewClient("Lon Lon Ranch", "Hyrule", "Link", "TriforceOfCourage@hyrule.com",
                "Password12345");
        Client c7 = app.createNewClient("Gringotts", "Diagon Alley", "Griphook", "PointyTeeth@bank.com",
                "Password12345");
        for (int i = 0; i < 23; i++) {
            app.createNewContainer();
        }

        app.logInUser("tom@cbs.dk", "Password12345");
        Journey c1j1 = app.requestNewJourney("Copenhagen", "New York", "bananas");
        Journey c1j2 = app.requestNewJourney("Rotterdam", "London", "cocaine");
        app.shareJourney(c2.getId(), c1j1.getId());
        app.shareJourney(c4.getId(), c1j1.getId());

        app.logInUser("william@novozymes.dk", "Password12345");
        Journey c2j1 = app.requestNewJourney("Pearl Harbor", "Tokyo", "robots");
        Journey c2j2 = app.requestNewJourney("Hong Kong", "New York", "people");
        app.shareJourney(c1.getId(), c2j1.getId());
        app.shareJourney(c6.getId(), c2j1.getId());

        app.logInUser("IamIronMan@stark.com", "Password12345");
        Journey c3j1 = app.requestNewJourney("Wakanda", "New York", "Vibranium");
        Journey c3j2 = app.requestNewJourney("China", "New York", "Snacks");
        Journey c3j3 = app.requestNewJourney("Mexico", "New York", "Booze");
        app.requestNewJourney("New York", "Wakanda", "Prototypes");
        app.shareJourney(c4.getId(), c3j2.getId());
        app.shareJourney(c5.getId(), c3j2.getId());

        app.logInUser("EarlGreyHot@shaceship.com", "Password12345");
        Journey c4j1 = app.requestNewJourney("The Enterprise", "Qo'noS", "Tribbles");
        Journey c4j2 = app.requestNewJourney("La Barre", "The Enterprise", "Wine");
        app.requestNewJourney("Omicron Theta", "The Enterprise", "Android Parts");
        app.shareJourney(c5.getId(), c4j2.getId());
        app.shareJourney(c3.getId(), c4j2.getId());

        app.logInUser("IliketoSing@beard.com", "Password12345");
        Journey c5j1 = app.requestNewJourney("California", "Copenhagen", "Beard Combs");
        Journey c5j2 = app.requestNewJourney("Paris", "Copenhagen", "Hot Air balloon");
        app.requestNewJourney("China", "Copenhagen", "Red Suits");
        app.shareJourney(c6.getId(), c5j2.getId());
        app.shareJourney(c7.getId(), c5j2.getId());

        app.logInUser("TriforceOfCourage@hyrule.com", "Password12345");
        Journey c6j1 = app.requestNewJourney("Lon Lon Ranch", "Castle Town", "Milk");
        Journey c6j2 = app.requestNewJourney("Lon Lon Ranch", "Termina", "Cuccos");
        Journey c6j3 = app.requestNewJourney("Kakariko", "Lon Lon Ranch", "Apples");
        app.requestNewJourney("Eldin Province", "Lon Lon Ranch", "Saddles");
        app.shareJourney(c7.getId(), c6j2.getId());
        app.shareJourney(c3.getId(), c6j2.getId());

        app.logInUser("PointyTeeth@bank.com", "Password12345");
        Journey c7j1 = app.requestNewJourney("Diagon Alley", "Ministry of Magic", "Paperwork");
        Journey c7j2 = app.requestNewJourney("Kiev", "Diagon Alley", "Ukrainian Ironbelly");
        Journey c7j3 = app.requestNewJourney("Egypt", "Diagon Alley", "Parchment");
        app.requestNewJourney("Machu Picchu", "Diagon Alley", "Gold");
        app.shareJourney(c1.getId(), c7j2.getId());
        app.shareJourney(c4.getId(), c7j2.getId());

        // Company 2
        app.createNewLogisticsCompany("Maersk", "Esplanaden 50, 6000 Kolding", "Cristian Hansen",
                "christian@3maersk.dk", "Password12345");
        app.logInUser("christian@3maersk.dk", "Password12345");
        Client cC1 = app.createNewClient("DTU", "Tekniksevej 43", "Pablo Escobar", "pablo@dtu.dk", "Password12345");
        Client cC2 = app.createNewClient("Netto", "Ondrovej 43", "Oscar Vinci", "oscar@netto.dk", "Password12345");
        Client cC3 = app.createNewClient("Shire Supply", "Bag End", "Samwise Gamgee", "gardener@shire.com",
                "Password12345");
        Client cC4 = app.createNewClient("Seuss Supply", "Who-ville", "Dr. Seuss", "redFishBlueFish@books.com",
                "Password12345");
        Client cC5 = app.createNewClient("Goods For U", "Serenity", "Malcolm Reynolds", "brownCoats@fringe.com",
                "Password12345");
        for (int i = 0; i < 20; i++) {
            app.createNewContainer();
        }

        app.logInUser("pablo@dtu.dk", "Password12345");
        Journey cC1J1 = app.requestNewJourney("Los Angeles", "Rio de Janeiro", "furniture");
        Journey cC1J2 = app.requestNewJourney("Rome", "Beijing", "toys");
        app.shareJourney(cC2.getId(), cC1J1.getId());
        app.shareJourney(cC3.getId(), cC1J1.getId());
        app.shareJourney(cC4.getId(), cC1J1.getId());

        app.logInUser("oscar@netto.dk", "Password12345");
        Journey cC2J1 = app.requestNewJourney("Copenhagen", "Cairo", "cars");
        app.requestNewJourney("New Dili", "New York", "steel");
        app.shareJourney(cC3.getId(), cC2J1.getId());
        app.shareJourney(cC4.getId(), cC2J1.getId());
        app.shareJourney(cC5.getId(), cC2J1.getId());

        app.logInUser("gardener@shire.com", "Password12345");
        Journey cC3J1 = app.requestNewJourney("Southfarthing", "Isenguard", "pipeweed");
        Journey cC3J2 = app.requestNewJourney("Shire", "Mordor", "The One Ring");
        Journey cC3J3 = app.requestNewJourney("Northfarthing", "Green Dragon Inn", "beer");
        app.requestNewJourney("Beorn's Hall", "Eastfarthing", "honey");
        app.shareJourney(cC2.getId(), cC3J1.getId());
        app.shareJourney(cC4.getId(), cC3J1.getId());
        app.shareJourney(cC5.getId(), cC3J1.getId());
        app.shareJourney(cC1.getId(), cC3J2.getId());
        app.shareJourney(cC2.getId(), cC3J2.getId());
        app.shareJourney(cC4.getId(), cC3J2.getId());

        app.logInUser("redFishBlueFish@books.com", "Password12345");
        Journey cC4J1 = app.requestNewJourney("The Jungle of Nool", "Who-ville", "rare who roast beast");
        Journey cC4J2 = app.requestNewJourney("Thneedville", "Sam I am", "green eggs and ham");
        app.requestNewJourney("Mulberry Street", "Beaches", "Sneetches");
        app.shareJourney(cC2.getId(), cC4J2.getId());
        app.shareJourney(cC3.getId(), cC4J2.getId());
        app.shareJourney(cC5.getId(), cC4J2.getId());

        app.logInUser("brownCoats@fringe.com", "Password12345");
        Journey cC5J1 = app.requestNewJourney("Ariel", "Haven", "fugitives");
        app.requestNewJourney("Jiangyin", "Beaumonde", "stolen goods");
        app.shareJourney(cC1.getId(), cC5J1.getId());
        app.shareJourney(cC2.getId(), cC5J1.getId());
        app.shareJourney(cC3.getId(), cC5J1.getId());

        // Company 1 Journeys
        app.logInUser("peter@agrofert.dk", "Password12345");
        app.startJourney(c1j1.getId(), LocalDateTime.of(2019, 4, 22, 15, 0));
        app.startJourney(c1j2.getId(), LocalDateTime.of(2019, 4, 24, 15, 0));
        app.startJourney(c2j1.getId(), LocalDateTime.of(2019, 4, 23, 15, 0));
        app.startJourney(c2j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c3j1.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c3j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c3j3.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c4j1.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c4j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c5j1.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c5j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c6j1.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c6j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c6j3.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c7j1.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c7j2.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));
        app.startJourney(c7j3.getId(), LocalDateTime.of(2019, 4, 25, 15, 0));

        app.enterNewContainerStatus(c1j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Copenhagen"));
        app.enterNewContainerStatus(c1j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "New York"));
        app.enterNewContainerStatus(c1j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Atlantic Ocean"));
        app.enterNewContainerStatus(c1j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 30, 15, 0), 0.0, 89.0, 101.0, "Indian Ocean"));
        app.enterNewContainerStatus(c1j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 5, 22, 19, 32), -35.0, 20.0, 101.0, "San Diego"));

        app.enterNewContainerStatus(c2j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 20, 15, 0), 35.0, 5.0, 90.0, "Pacific Ocean"));
        app.enterNewContainerStatus(c2j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "Wakanda"));
        app.enterNewContainerStatus(c2j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Atlantida"));
        app.enterNewContainerStatus(c2j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 12.0, 132.0, "Copenhagen"));

        app.enterNewContainerStatus(c3j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Port Louis"));
        app.enterNewContainerStatus(c3j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "Cape Town"));
        app.enterNewContainerStatus(c3j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Atlantic Ocean"));
        app.enterNewContainerStatus(c3j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 30, 15, 0), 0.0, 89.0, 101.0, "Hawaii"));
        app.enterNewContainerStatus(c3j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 5, 22, 19, 32), -35.0, 20.0, 101.0, "Panama"));
        app.enterNewContainerStatus(c3j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "Gulf of Mexico"));
        app.enterNewContainerStatus(c3j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Florida"));
        app.enterNewContainerStatus(c3j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 12.0, 132.0, "Alantic Ocean"));

        app.enterNewContainerStatus(c4j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 20, 15, 0), 35.0, 5.0, 90.0, "Deep Space Nine"));
        app.enterNewContainerStatus(c4j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "San Francisco"));
        app.enterNewContainerStatus(c4j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "SpaceDock"));
        app.enterNewContainerStatus(c4j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 12.0, 132.0, "Deep Space Nine"));

        app.enterNewContainerStatus(c5j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Mexico"));
        app.enterNewContainerStatus(c5j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "New York"));
        app.enterNewContainerStatus(c5j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Germany"));
        app.enterNewContainerStatus(c5j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "London"));
        app.enterNewContainerStatus(c5j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Germany"));

        app.enterNewContainerStatus(c6j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 30, 15, 0), 0.0, 89.0, 101.0, "Hyrule Field"));
        app.enterNewContainerStatus(c6j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 5, 22, 19, 32), -35.0, 20.0, 101.0, "North Hyrule Field"));
        app.enterNewContainerStatus(c6j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Hyrule Field"));
        app.enterNewContainerStatus(c6j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "Lost Forest"));
        app.enterNewContainerStatus(c6j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Southern Swamp"));
        app.enterNewContainerStatus(c6j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "Castle Town"));
        app.enterNewContainerStatus(c6j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Hyrule Field"));

        app.enterNewContainerStatus(c7j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 30, 15, 0), 0.0, 89.0, 101.0, "Leaky Cauldron"));
        app.enterNewContainerStatus(c7j1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 5, 22, 19, 32), -35.0, 20.0, 101.0, "Whitehall"));
        app.enterNewContainerStatus(c7j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Black Sea"));
        app.enterNewContainerStatus(c7j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "Mediterranean Sea"));
        app.enterNewContainerStatus(c7j2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "English Channel"));
        app.enterNewContainerStatus(c7j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0, 101.0, "Italy"));
        app.enterNewContainerStatus(c7j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "France"));
        app.enterNewContainerStatus(c7j3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 12.0, 132.0, "English Channel"));

        // Company 2 Journeys
        app.logInUser("christian@3maersk.dk", "Password12345");
        app.startJourney(cC1J1.getId(), LocalDateTime.of(2019, 4, 26, 15, 0));
        app.startJourney(cC1J2.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC2J1.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC3J1.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC3J2.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC3J3.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC4J1.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC4J2.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));
        app.startJourney(cC5J1.getId(), LocalDateTime.of(2019, 4, 28, 15, 0));

        app.enterNewContainerStatus(cC1J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 05, 15, 0), 35.0, 20.0, 91.0, "Prague"));
        app.enterNewContainerStatus(cC1J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 45.0, 20.0, 101.0, "Moscow"));
        app.enterNewContainerStatus(cC1J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 3.0, 32.0, 121.0, "Red Sea"));
        app.enterNewContainerStatus(cC1J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 5.0, 20.0, 111.0, "Dead Sea"));
        app.enterNewContainerStatus(cC1J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 24.0, 111.0, "Black Sea"));
        app.enterNewContainerStatus(cC1J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 25, 15, 0), 35.0, 20.0, 108.0, "Copenhagen"));
        app.enterNewContainerStatus(cC1J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 26, 15, 0), 35.0, 24.0, 101.0, "North Pole"));
        app.enterNewContainerStatus(cC1J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 35.0, 20.0, 121.0, "Antarctida"));

        app.enterNewContainerStatus(cC2J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 6.0, 106.0, "Copenhagen"));
        app.enterNewContainerStatus(cC2J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 35.0, 5.0, 107.0, "Copenhagen"));

        app.enterNewContainerStatus(cC3J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 05, 15, 0), 35.0, 20.0, 91.0, "Bree"));
        app.enterNewContainerStatus(cC3J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 45.0, 20.0, 101.0, "Greenway"));
        app.enterNewContainerStatus(cC3J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 3.0, 32.0, 121.0, "Tharbad"));
        app.enterNewContainerStatus(cC3J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 5.0, 20.0, 111.0, "Amon Sul"));
        app.enterNewContainerStatus(cC3J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 35.0, 24.0, 111.0, "Rivendale"));
        app.enterNewContainerStatus(cC3J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 25, 15, 0), 35.0, 20.0, 108.0, "Moria"));
        app.enterNewContainerStatus(cC3J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 26, 15, 0), 35.0, 24.0, 101.0, "Lorien"));
        app.enterNewContainerStatus(cC3J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 35.0, 20.0, 121.0, "Fangorn"));
        app.enterNewContainerStatus(cC3J3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 05, 15, 0), 35.0, 20.0, 91.0, "Nobottle"));
        app.enterNewContainerStatus(cC3J3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 45.0, 20.0, 101.0, "Hobbiton"));
        app.enterNewContainerStatus(cC3J3.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 27, 15, 0), 3.0, 32.0, 121.0, "Bywater"));

        app.enterNewContainerStatus(cC4J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 22, 15, 0), 35.0, 20.0, 103.0, "Prarie of Prax"));
        app.enterNewContainerStatus(cC4J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 39.0, 24.0, 102.0, "Weehawken"));
        app.enterNewContainerStatus(cC4J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 24, 15, 0), 12.0, 20.0, 121.0, "Mount Crumpit"));
        app.enterNewContainerStatus(cC4J2.getId(), new ContainerStatus(LocalDateTime.of(2020, 4, 22, 17, 0), -35.0, 2.0,
                101.0, "Street of the Lifted Lorax"));
        app.enterNewContainerStatus(cC4J2.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 53.0, 2.0, 101.0, "Sala-ma-Sond"));

        app.enterNewContainerStatus(cC5J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 05, 15, 0), 35.0, 20.0, 91.0, "The Core"));
        app.enterNewContainerStatus(cC5J1.getId(),
                new ContainerStatus(LocalDateTime.of(2020, 4, 23, 15, 0), 45.0, 20.0, 101.0, "Border Planets"));

        app.endJourney(cC3J3.getId(), LocalDateTime.of(2019, 5, 25, 15, 0));
        app.endJourney(cC4J2.getId(), LocalDateTime.of(2019, 5, 25, 15, 0));
        
        app.logOut();
        System.out.println("Database Setup");
    }
}
