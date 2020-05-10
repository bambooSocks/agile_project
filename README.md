# Remote Container Management System - Group H
## Purpose
This software was implemented for a final project assignment for course  [02160 Agile Object-oriented Software Development](https://kurser.dtu.dk/course/2019-2020/02160 "course link"). The development of the project was almost entirely based on agile principles, meaning the Maven Build-in tool along with its dependencies is used for automatic testing and continuous integration. 

The purpose of this software is to create a common point of interaction between logistics companies and their clients. The services which are intended to be used are shipping cargo from one location to another (an example of such a logistic company could be A. P. Moller-Maersk). By using this software the clients will have the possibility to requests journeys for shipping their cargo and the logistics companies will be able to administer and monitor a container within a journey. As an extra functionality to the system, we allowed multiple logistics companies to register for our system. This was mostly due to the fact that we treat our software as a usable product, which could have multiple buyers.

## Implemented Features:

The features requested for the project are implemented in sub-features and they are fully tested with acceptable coverage. The main features are: 

* M1: Clients management: The system allows logistics companies to register new clients based on name, address, reference person and e-mail; it allows clients to update their information and it allows logistics companies to find clients based on this type of information criteria.

* M2: Journeys management: a client can register containers for journeys by entering origin port, destination port and content. A journey ID is created automatically and the assigned logistics company can decide when to start and end the journey based on the container availability. 

* M3: Container Status tracking: Each journey involves a container, but a container can be used in several journeys. For each container involved in the journey the following internal status is tracked: temperature (Celsius), humidity (%) and atm. pressure (atm). The logistics company can add these measurements to the system and the client is able to access them. In the UI the internal status is presented on the panel using graphs created with jfreechart dependencies, alongside with a table showing the tracking of the location. (in our system the logistics company must enter a status alongside with a location).

* M4: User Interface: It is possible to interact with the system using Java Swing as the GUI for this project. This can be straightforwardly accessed through the main executable file. Another way to run this project is by running the MainView.java as a normal Java Application. This can be found under: src/main/java under the rcm.ui package.  For an overview of how the GUI works please see the UML diagram which can be found under *docs: UI-Diagram.pdf*.

These additional features have also been implemented to add extra value to the system:
* O1: Keep track of evolution: the history of each container and all of the journeys it went on is being tracked. This is mostly done through lists and database storage.

* O2: User management: This allows segregation of different features according to the user roles. For example, clients can see their journeys only, but have the option to share journey details with other clients. The logistics company has access to see everything concerning their clients only. 

* O4: Persistency Layer: The data is stored using a persistence layer. A Repository is created under src/main/java > rcm.repository, which can be extended to be used by multiple persistence layers. For this  
project the SQLite repository was used as the implementation. 

## Design Decisions & Project Issues:
* A journey can only have one container. Since it was not specifically written in the project requirements, this choice was made due to time constraints and YAGNI principles.

* There is a lack of interfaces for the model, apart from the Repository for the database. This is mostly due to the fact that it was not considered to be necessary for the scope of this project. The number of classes is limited and the functionalities are mostly segregated according to the user type. Should one extend the project and create an admin user role, then the User class could be possibly made as an interface.

 * It might be that our UI system is highly coupled, however it was decided to design it in this way in order to give it a consistent appearance (for example the top bar is similar for the main panels). Also we have kept the model (Application.java in our case) separated from the UI. This allows further implementation of the system on other user interfaces, if required in the future.

## Files Included Within This Project:
* **JPA Content** n XML file for the persistence layer
* **src/main/java**
     * rcm.config : contains FakeData.java which has pre-filled data: initially for testing purposes, but later used also in the presentation of the final product to up the UI display in advance, thus creating a better look.
     * rcm.model: model for running the application and the correspondent classes. Each class has methods defined for its sole purpose (e.g.: a client can request a Journey object to be created but only the logistics company can add a starting and ending timestamp).
          * Application.java.: communicates with the Repository interface. For more details regarding how these classes are interconnected to each other please check our UML Diagram located under *docs: UML.pdf*
          * Client.java, 
          * Container.java 
          * ContainerStatus.java
          * Journey.java 
          * LogisticsCompany.java 
          * User.java 
          * WorngInputException.java. 

    * rcm.repository: Repository.java and SqliteRepository constitute the Persistency Layer mentioned above in O4. For an overview of how this is implemented please see the Database Diagram under *docs: Database-Diagram.pdf*
    * rcm.ui: main GUI parent classes:
          * MainView: most important. Application and Repository are run through it. 
         * BaseMenuBar.java
         * BaseTopBar.java
         * BaseView.java
         * ClientTabView.java
         * CompanyTabView.java
         * LogInView.java
         * MenuBarClient.java
         * MenuBarCompany.java
         * UpdatetablePanel.java
    * rcm.ui.info: basic info panels for clients and containers - view access is permitted only for the logistics company
    * rcm.ui.journey: view panels for Journey and graphs showing the internal container status for each Journey.
    * rcm.ui.popup: various pop-ups for user-defined actions: e.g.: entering a status, creating a journey etc.
    * rcm.ui.tables: table panels for showing journeys, shared journeys (for the client and logistics company), client and container tables (only for the logistics company). 
* **src/main/resources**: used for implementing the Gherkin Scenarios based on the customer stories. Each feature is subdivided into sub-features representing different scenarios. It was chosen to use the Gherkin language in order to implement automated testing through Cucumber (installed as a Maven dependency).
* **src/main/testing**:
     * rcm: FullSuite.java is a class which is run for automated unit testing and coverage: to be run as a JUnit test 
     * rcm.cucumber
         * CucumberTest.java : to be run as a JUnit test. However we recommend running the FullSuite instead, which checks for the coverage as well and for the additionally written unit tests (found in rcm.unitTest)
         * M1.java: step implementation for M1
         * M2.java: step implementation for M2
         * M3.java -''-
         * O1.java -''-
         * O2.java -''-
         * SharedObjectHolder.java: contains the common objects to used for the step implementations of the features above
         * SharedStepMethods.java: contains the shared @Given step methods that these features have in common 
     * rcm.persistency:
         * TestDatabaseAccess.java: for testing the database access: run as JUnit test (covered by running FullSuite.java)
         * TestDB2.java: for setting up the initial database.: run as JUnit test (also covered by running Fullsuite.java)
     * rcm.unitTest: contains additional unit tests which are not covered by the scenarios. These tests also have the purpose of increasing the coverage. The purpose of the following testing classes are self-explanatory by the name:
         * ApplicationTest.java
         * ClientTest.java
         * ContainerStatusTest.java
         * JourneyTest.java
         * LogisticsCompanyTest.java
* **JRE System Library**: contains the jar files which we need for running our system
* **Maven Dependencies** lots of Maven Dependencies, but worth mentioning are Cucumber (for testing), Hibernate and SQLite (for the database) 
* **docs**: used to store our UML diagrams for the database, the UI and Model.
* **rcm**: contains a structure of the used classes outside of the packages
* **target**: mostly contains the annotations for generated sources and generated-test-sources. Also includes the status of the Maven compiler plugin. Another folder is the surefire-reports which are reports generated by Maven once the tests are run.
* **CIrules.md**: contains the rules all team members have agreed one for continuous integration. This enables working in an agile manner and having a runnable system each time someone is pushing code.
* **pom.xml**: used for installing the Maven dependencies. This file can be run according to several Maven Configuration, depending on the user's intent.
* **Database.db**: automatically gets created when you run the pre-filled database.
* this **README.md** file: glad you are reading it!
     
## Running the Executable File:

