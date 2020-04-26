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
     * 
     */
    public Application(Repository repo) {
        this.repo = repo;
        // TODO: for the Group2
//      system = loadFromDB();

        // begin temporary
        system = new LinkedList<>();
        try {
            createNewLogisticsCompany("Maersk", "Kbh", "someone", "info@maersk.com", "bigShip123");
        } catch (WrongInputException | IOException e) {
            e.printStackTrace();
        }
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
     * @throws WrongInputException
     * @throws IOException
     */
    private void createNewLogisticsCompany(String name, String address, String refPerson, String email, String password)
            throws WrongInputException, IOException {
        LogisticsCompany c = new LogisticsCompany(name, address, refPerson, email, password);
        repo.createLogisticsCompany(c);
        system.add(c);
    }

    /**
     * Creates a client and adds it to the system and database
     * 
     * @param name      Name of the client
     * @param address   Address of the client
     * @param refPerson Reference person of the client
     * @param email     Email of the client
     * @param password  Password for the account of the client
     * @throws IOException
     * @throws WrongInputException
     */
    public void createNewClient(String name, String address, String refPerson, String email, String password)
            throws IOException, WrongInputException {
        Client c = loggedInCompany.createClient(name, address, refPerson, email, password);
        repo.createClient(c);
    }

    /**
     * Creates a container and adds it to the system and database
     * 
     * @throws IOException
     */
    public void createNewContainer() throws IOException {
        Container c = loggedInCompany.createContainer();
        repo.createContainer(c);
    }

    /**
     * Requests a new journey adds it to the system and database
     * 
     * @param originPort      Origin port of the journey
     * @param destinationPort Destination port of the journey
     * @param content         Content of the journey
     * @param timestamp       Time stamp when the journey should start
     * @throws IOException
     */
    public void requestNewJourney(String originPort, String destinationPort, String content, LocalDateTime timestamp)
            throws IOException {
        Journey j = loggedInClient.requestAndStartJourney(originPort, destinationPort, content, timestamp);
        repo.createJourney(j);
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
     * @throws IOException
     */
    public void enterNewContainerStatus(Journey journey, LocalDateTime timestamp, double temp, double humid,
            double atmPress, String loc) throws IOException {
        ContainerStatus status = new ContainerStatus(timestamp, temp, humid, atmPress, loc);
        loggedInCompany.enterStatus(status, journey);
        repo.updateCompany(loggedInCompany);
    }

    /**
     * Logs in a user by email and password
     * 
     * @param email    Email of the user to be logged in
     * @param password Password of the user to be logged in
     * @throws WrongInputException
     */
    @SuppressWarnings("unchecked")
    public void logInUser(String email, char[] password) throws WrongInputException {
        String pswd = new String(password);

        for (LogisticsCompany c : system) {
            if (c.getEmail().equals(email)) {
                if (User.SHA1_Hasher(pswd).equals(c.getPassword())) {
                    loggedInCompany = c;
                    loggedInClient = null;
                    support.firePropertyChange("companyLoggedIn", null, null);
                } else {
                    throw new WrongInputException("Your password is incorrect");
                }
            } else {
                Set<Client> cs = c.searchByEmail(email);
                if (!cs.isEmpty()) {
                    Client cl = ((LinkedList<Client>) cs).pop();
                    if (User.SHA1_Hasher(pswd).equals(cl.getPassword())) {
                        loggedInClient = cl;
                        loggedInCompany = null;
                        support.firePropertyChange("clientLoggedIn", null, null);
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
    //TODO: Adrienne: switch to use shared journeys
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

    
    
}
