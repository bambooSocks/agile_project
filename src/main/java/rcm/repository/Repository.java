package rcm.repository;

import java.io.IOException;
import java.time.LocalDateTime;

import rcm.model.Client;
import rcm.model.Container;
import rcm.model.ContainerStatus;
import rcm.model.Journey;
import rcm.model.LogisticsCompany;

public interface Repository {

    void clearDatabase();

    /**
     * Method to create the logistics company in the database
     * 
     * @param po LogisticsCompany to be added to the database
     */
    void createLogisticsCompany(LogisticsCompany po) throws IOException;

    /**
     * Method to retrieve the LogisticsCompany object from the database
     * 
     * @param key representing the email of the client
     * 
     * @return LogisticsCompany object
     */
    LogisticsCompany readLogisticsCompany(String key) throws IOException;

    /**
     * Method to create the container in the database
     * 
     * @param po Container to be added to the database
     */
    void createContainer(Container po) throws IOException;

    /**
     * Method to retrieve the Container object from the database
     * 
     * @param i representing the id of the container
     * 
     * @return Container object
     */
    Container readContainer(int i) throws IOException;

    /**
     * Method to create the client in the database
     * 
     * @param po Client to be added to the database
     */
    void createClient(Client po) throws IOException;

    /**
     * Method to retrieve the Client object from the database
     * 
     * @param key representing the email of the client
     * 
     * @return Client object
     */
    Client readClient(String key) throws IOException;

    /**
     * Method to create the journey in the database
     * 
     * @param po Journey to be added to the database
     */
    void createJourney(Journey po) throws IOException;

    /**
     * Method to retrieve the Journey object from the database
     * 
     * @param i representing the id of the journey
     * 
     * @return Journey object
     */
    Journey readJourney(int i) throws IOException;

    /**
     * Method to update the logistics company object in the database
     * 
     * @param logisticsCompany LogisticsCompany to be updated
     */
    void updateCompany(LogisticsCompany logisticsCompany);

}