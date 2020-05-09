# Remote Container Management System - Group H
## Usage
This software was implemented for a final project assignment for course  [02160 Agile Object-oriented Software Development](https://kurser.dtu.dk/course/2019-2020/02160 "course link"). The development of the project was wholy based on agile principles, meaning the Maven Build-in tool along with its dependencies is used for automatic testing and continuous integration. 

This purpose of this software is to create a common point interaction between logistics companies and their clients. The services which are intended to be used are shipping cargo from one location to the other (an example of a such logistic company could be A. P. Moller-Maersk). By using this software the clients will have the possibility to requests journeys for shipping their cargo and the logistics companies will be able to administer and monitor a container within a journey. As an extra functionality to the system, we allowed multiple logistics company to register for our system. this was mostly sue to the fact that we consider our software a real product which could be used by multiple buyers.

## Implemented Features:

The features requested for the project are implemented in sub-features and they are fully tested with acceptable coverage. The main features are: 

* M1: Clients management: The system allows logistics companies to register new clients based on name, address, reference person and e-mail; it allows clients to update their information and it allows logistics companies to find clients based on this type of information criteria.

* M2: Journeys management: a client can register containers for journeys by entering origin port, destination port and content. A journey ID is created automatically and the assigned logistics company can decide when to start and end the journey based on container availability. 

* M3: Container Status tracking: Each journey involves a container, but a container can be used in several journeys. For each container involved in the journey the following internal status is tracked: temperature (Celsius), humidity (%) and atm. pressure (atm). The logistics company can add these measurements to the system and the client is able to retrieve them. In the UI the internal status is presented on the panel using graphs created with jfreechart dependencies, alongside with a table showing the tracking of the location. (the logistics company must enter a status alongside with a location).

* M4: User Interface: It is possible to interact with the system using Java Swing as the GUI for this project. This can be straightforwardly accessed through the main executable file. Another way to run this project is by running the MainView.java as a normal Java Application. This can be found under: src/main/java under the rcm.ui package.  For an overview of how the GUI works please see the UML diagram which can be found under LOCATION.

These additional features have also been implemented to add extra value to the system:
* O1: Keep track of evolution: the history of each container and all of th journeys it went on is being tracked. This is mostly done through lists and database storage.

* O2: User management: This allows segregation of different features according to the user roles. For example, clients can see their journeys only, but have the option to share journey details with other clients. The logistics company has access to see everything concerning their clients only. 

* O4: Persistency Layer: The data is stored using a persistence layer. A Repository is created under src/main/java > rcm.repository which can be extended to be used by multiple persistency layers. For this  
project the SQLite repository was used as the implementation. 

## Design Decisions & Project Issues:
* A jurney can only have one container. Since it was not specifically written in the porject requirements, this choice was made due to time constraints and YAGNI principles.

* There is a lack of interfaces for the model, apart from the Repository in the Persistency Layer. This is mostly due to the part it was not considered to be necessary for the scope of this project. The number of classes is limited and the functionalities are mostly segregated according to the user type. Should one extend the project and create an admin user role, then the User class could be made as an interface.

## Files Included Within This Project:
* src/main/java
     * rcm.config : contanins FakeData.java for testing purposes and filling up the UI in advance, thus creating a better display.
     * rcm.model: model for running the application: 
          * Application.java. *Note:* communicates with the Repository interface. For more details regarding how these classes are interconnected to each other please check our UML Diagram located under LOCATION
          * Client.java, 
          * Container.java 
          * ContainerStatus.java
          * Journey.java 
          * LogisticsCompany.java 
          * User.java 
          * WorngInputException.java. 

    * rcm.repository: Repository.java and SqliteRepository constitute the Persistency Layer mentioned above in O4.
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
    * rcm.ui.info: basic info panels for clients and containers - access permitted for the logistics company only
    * rcm.ui.journey: view panels for Journey and graphs showing the internal container status for each Journey.
    * rcm.ui.popup: various pop-ups for user-defined actions: eg.: entering a status, creating a journey etc.
    * rcm.ui.tables: table panels for showing journeys, shared journeys (for the client and logistics company), client and container tables (only for the logistics company). 



