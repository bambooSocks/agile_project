package rcm.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rcm.repository.Repository;

public class Application {

    private List<LogisticsCompany> system;
    private Repository repo;
    private PropertyChangeSupport support;
    LogisticsCompany loggedInCompany;
    Client loggedInClient;

    /**
     * Application constructor
     * 
     * @param repo Repository connected to the database
     */
    public Application(Repository repo) {
        this.repo = repo;
        support = new PropertyChangeSupport(this);
        // TODO: for the Group2
//      system = loadFromDB();

        // begin temporary
        system = new LinkedList<>();
//        try {
//            createNewLogisticsCompany("Maersk", "Kbh", "Someone", "info@maersk.com", "bigShip123");
//        } catch (WrongInputException | IOException e) {
//            e.printStackTrace();
//        }
        // end temporary
    }

    /**
     * Adds an observer to the Application
     * 
     * @param l PropertyChangeListener to be added to the support object
     */
    public void addObserver(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    /**
     * Creates a logistics company and adds it to the system and database
     * 
     * @param name      Name of the logistics company
     * @param address   Address of the logistics company
     * @param refPerson Reference person of the logistics company
     * @param email     Email of the logistics company
     * @param password  Password for the account of the logistics company
     * @return created Logistics Company
     * @throws WrongInputException
     * @throws IOException
     */
    public LogisticsCompany createNewLogisticsCompany(String name, String address, String refPerson, String email,
            String password) throws WrongInputException, IOException {
        LogisticsCompany c = new LogisticsCompany(name, address, refPerson, email, password);
        repo.createLogisticsCompany(c);
        system.add(c);
        return c;
    }

    /**
     * Creates a client and adds it to the system and database
     * 
     * @param name      Name of the client
     * @param address   Address of the client
     * @param refPerson Reference person of the client
     * @param email     Email of the client
     * @param password  Password for the account of the client
     * @return created Client
     * @throws IOException
     * @throws WrongInputException
     */
    public Client createNewClient(String name, String address, String refPerson, String email, String password)
            throws IOException, WrongInputException {
        Client c = loggedInCompany.createClient(name, address, refPerson, email, password);
        repo.createClient(c);
        return c;
    }

    /**
     * Creates a container and adds it to the system and database
     * 
     * @return created Container
     * 
     * @throws IOException
     */
    public Container createNewContainer() throws IOException {
        Container c = loggedInCompany.createContainer();
        repo.createContainer(c);
        return c;
    }

    /**
     * Requests a new journey adds it to the system and database
     * 
     * @param originPort      Origin port of the journey
     * @param destinationPort Destination port of the journey
     * @param content         Content of the journey
     * @param timestamp       Time stamp when the journey should start
     * @return requested Journey
     * @throws IOException
     */
    public Journey requestNewJourney(String originPort, String destinationPort, String content, LocalDateTime timestamp)
            throws IOException {
        Journey j = loggedInClient.requestJourney(originPort, destinationPort, content, timestamp);
        repo.createJourney(j);
        return j;
    }

    /**
     * Enters a new container status adds it to the system and database
     * 
     * @param journey   Journey the status should be added to
     * @param timestamp Time stamp of the status
     * @param temp      Temperature inside the container
     * @param humid     Humidity inside the container
     * @param atmPress  Atmospheric pressure inside the container
     * @param loc       Location of the container
     * @return Boolean of whether it was successfully added or not
     * @throws IOException
     */
    // TODO: maybe switch to Journey id not an object
    public boolean enterNewContainerStatus(Journey journey, LocalDateTime timestamp, double temp, double humid,
            double atmPress, String loc) throws IOException {
        ContainerStatus status = new ContainerStatus(timestamp, temp, humid, atmPress, loc);
        if (loggedInCompany.enterStatus(status, journey)) {
            repo.updateCompany(loggedInCompany);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enters a new container status adds it to the system and database
     * 
     * @param journey Journey the status should be added to
     * @param status  Container status of the container
     * @return Boolean of whether it was successfully added or not
     * @throws IOException
     */
    // TODO: maybe switch to Journey id not an object
    public boolean enterNewContainerStatus(Journey journey, ContainerStatus status) throws IOException {
        if (loggedInCompany.enterStatus(status, journey)) {
            repo.updateCompany(loggedInCompany);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Starts a journey with the given time stamp
     * 
     * @param journey   Journey to be started
     * @param timestamp Starting time stamp of the journey
     * @return Boolean whether the journey was successfully started
     */
    // TODO: maybe switch to Journey id not an object
    public boolean startJourney(Journey journey, LocalDateTime timestamp) {
        return loggedInCompany.startJourney(journey, timestamp);
    }

    /**
     * Ends a journey with the given time stamp
     * 
     * @param journey   Journey to be ended
     * @param timestamp Ending time stamp of the journey
     * @return Boolean whether the journey was successfully ended
     */
    // TODO: maybe switch to Journey id not an object
    public boolean endJourney(Journey journey, LocalDateTime timestamp) {
        return loggedInCompany.endJourney(journey, timestamp);
    }

    /**
     * Logs in a user by email and password
     * 
     * @param email    Email of the user to be logged in
     * @param password Password of the user to be logged in
     * @throws WrongInputException
     */
    public void logInUser(String email, String password) throws WrongInputException {
        loggedInCompany = null;
        loggedInClient = null;

        for (LogisticsCompany c : system) {
            if (c.getEmail().equals(email)) {
                if (User.SHA1_Hasher(password).equals(c.getPassword())) {
                    loggedInCompany = c;
                    support.firePropertyChange("companyLoggedIn", null, null);
                    break;
                } else {
                    throw new WrongInputException("Your password is incorrect");
                }
            } else {
                Set<Client> cs = c.searchByEmail(email);
                if (!cs.isEmpty()) {
                    Client cl = (new LinkedList<>(cs)).pop();
                    if (User.SHA1_Hasher(password).equals(cl.getPassword())) {
                        loggedInClient = cl;
                        support.firePropertyChange("clientLoggedIn", null, null);
                        break;
                    } else {
                        throw new WrongInputException("Your password is incorrect");
                    }
                }
            }
        }
        if (loggedInClient == null && loggedInCompany == null) {
            throw new WrongInputException("Email not found");
        }
    }

    /**
     * Logs out the user
     */
    public void logOut() {
        loggedInClient = null;
        loggedInCompany = null;
        support.firePropertyChange("userLoggedOut", null, null);
    }

    /**
     * Searches for clients of logged in logistics company by all parameters
     * 
     * @param query Query to be searched for
     * @return Set of clients
     */
    public Set<Client> searchForClients(String query) {
        Set<Client> results = loggedInCompany.searchByName(query);
        results.addAll(loggedInCompany.searchByAddress(query));
        results.addAll(loggedInCompany.searchByRefPerson(query));
        results.addAll(loggedInCompany.searchByEmail(query));
        try {
            int id = Integer.parseInt(query);
            // TODO: Adrienne: implement or delete
//            results.addAll(loggedInCompany.searchById(id));
        } catch (NumberFormatException e) {
        }
        return results;
    }

    // TODO: figure out if searching for containers is worth it ...

    /**
     * Searches for journeys of logged in client by all parameters
     * 
     * @param query Query to be searched for
     * @return Set of journeys
     */
    public Set<Journey> searchForJourneys(String query) {
        @SuppressWarnings("unchecked")
        Set<Journey> results = (Set<Journey>) loggedInClient.searchByOrigin(query);
        results.addAll(loggedInClient.searchByDestination(query));
        results.addAll(loggedInClient.searchByContent(query));
        try {
            int id = Integer.parseInt(query);
            // TODO: Adrienne: implement or delete
//            results.addAll(loggedInClient.searchById(id));
        } catch (NumberFormatException e) {
        }
        return results;
    }

    /**
     * Searches for shared journeys of logged in client by all parameters
     * 
     * @param query Query to be searched for
     * @return Set of shared journeys
     */
    // TODO: Adrienne: switch to use shared journeys
    public Set<Journey> searchForSharedJourneys(String query) {
        @SuppressWarnings("unchecked")
        Set<Journey> results = (Set<Journey>) loggedInClient.searchByOrigin(query);
        results.addAll(loggedInClient.searchByDestination(query));
        results.addAll(loggedInClient.searchByContent(query));
        try {
            int id = Integer.parseInt(query);
            // TODO: Adrienne: implement or delete
//            results.addAll(loggedInClient.searchById(id));
        } catch (NumberFormatException e) {
        }
        return results;
    }

    /**
     * Getter for logged in logistics company
     * 
     * @return null if no logistics company is logged in, otherwise returns the
     *         object of the company
     */
    public LogisticsCompany getLoggedInCompany() {
        return loggedInCompany;
    }

    /**
     * Getter for logged in client
     * 
     * @return null if no client is logged in, otherwise returns the object of the
     *         client
     */
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    /**
     * Requests status for given journey
     * 
     * @param journey Journey to get status history out of
     * @return List of the Container Statuses
     */
    public List<ContainerStatus> requestStatus(Journey journey) {
        if (loggedInClient != null && loggedInClient.ownsJourney(journey)) {
            return journey.getStatus();
        } else {
            return null;
        }
    }

}
