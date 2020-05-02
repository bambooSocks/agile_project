package rcm.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class LogisticsCompany extends User {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Container> containers;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Client> clients;

    @SuppressWarnings("unused")
    private LogisticsCompany() {
        super();
    }

    /**
     * Logistics Company constructor
     * 
     * @param name      Name of the logistics company
     * @param address   Address of the logistics company
     * @param refPerson Reference person of the logistics company
     * @param email     Email of the logistics company
     * @param password  Password of the logistics company
     * @throws WrongInputException
     */
    public LogisticsCompany(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
        super(name, address, refPerson, email, password);
        containers = new LinkedList<Container>();
        clients = new HashSet<Client>();
    }

    /**
     * Getter for a list of all available containers
     * 
     * @return List of Container filtered by availability
     */
    public List<Container> getAllAvailableContainers(LocalDateTime timestamp) {
        return containers.stream().filter(c -> c.isAvailable(timestamp)).collect(Collectors.toList());
    }

    /**
     * Getter for a single available container
     * 
     * @return An available container or null if all containers are allocated
     */
    public Container getAvailableContainer(LocalDateTime timestamp) {
        List<Container> available = getAllAvailableContainers(timestamp);
        if (available.isEmpty()) {
            return null;
        } else {
            Container container = available.get(0);
            return container;
        }
    }

    /**
     * Getter for the client set of a logistics company
     * 
     * @return Set of all clients of a logistics company
     */
    public Set<Client> getClients() {
        return clients;
    }

    /**
     * Adds a container to the list of all containers associated with the company
     * 
     * @param container The container to be added to the list
     */
    public void addContainer(Container container) {
        containers.add(container);
    }

    /**
     * Filter method for client set
     * 
     * @param p Search criteria
     * @return Set of clients that meet filter requirements
     */
    private Set<Client> applyClientFilter(Predicate<Client> p) {
        return clients.stream().filter(p).collect(Collectors.toSet());
    }

    /**
     * Method to search clients by name
     * 
     * @param name Search criteria
     * @return Set of clients that have a matching name
     */
    public Set<Client> searchClientByName(String name) {
        String regexSearch = "(" + name + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return applyClientFilter(c -> (pattern.matcher(c.getName())).find());
    }

    /**
     * Method to search clients by address
     * 
     * @param address Search criteria
     * @return Set of clients that have a matching address
     */
    public Set<Client> searchClientByAddress(String address) {
        String regexSearch = "(" + address + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return applyClientFilter(c -> (pattern.matcher(c.getAddress())).find());
    }

    /**
     * Method to search clients by reference person
     * 
     * @param refPerson Search criteria
     * @return Set of clients that have a matching reference person
     */
    public Set<Client> searchClientByRefPerson(String refPerson) {
        String regexSearch = "(" + refPerson + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return applyClientFilter(c -> (pattern.matcher(c.getRefPerson())).find());
    }

    /**
     * Method to search clients by email
     * 
     * @param email Search criteria
     * @return Set of clients that have a matching email
     */
    public Set<Client> searchClientByEmail(String email) {
        String regexSearch = "(" + email + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return applyClientFilter(c -> (pattern.matcher(c.getEmail())).find());
    }

    /**
     * Method to search clients by id
     * 
     * @param id Search criteria
     * @return Set of clients that have a matching id
     */
    public Set<Client> searchClientById(String id) {
        return applyClientFilter(c -> Integer.toString(c.getId()).equals(id));
    }

    /**
     * Method to add a client to the client set
     * 
     * @param client Client to be added to the client set
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    /**
     * Getter for the containers of the logistics company
     * 
     * @return List of Containers
     */
    public List<Container> getContainers() {
        return containers;
    }
}
