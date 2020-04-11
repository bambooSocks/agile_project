package rcm;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.function.Predicate;
import java.util.Calendar;
import java.util.HashSet;

public class LogisticsCompany extends User {

    LinkedList<Container> containers;
    LinkedList<Container> availableContainers;
    Set<Client> clients;

    /**
     * Logistics Company constructor
     * 
     * @param name      Name of the logistics company
     * @param address   Address of the logistics company
     * @param refPerson Reference person of the logistics company
     * @param email     Email of the logistics company
     */
    public LogisticsCompany(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        containers = new LinkedList<Container>();
        availableContainers = new LinkedList<Container>();
        clients = new HashSet<Client>();
        id = IdGenerator.getInstance().getId(GroupIdType.COMPANY);
    }

    public LinkedList<Container> getAvailableContainers() {
        return availableContainers;
    }

    public void addAvailableContainer(Container container) {
        availableContainers.add(container);
    }

    /**
     * Getter for client set
     * 
     * @return a set of clients
     */
    public Set<Client> getClients() {
        return clients;
    }

    public Response updateLocation(Container container, String newLocation) {
        if (this.containers.contains(container)) {
            container.setLocation(newLocation);

            return Response.SUCCESS;
        } else {
            return Response.LOCATION_NOT_CHANGED;
        }
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    /**
     * Filter method for client set
     * 
     * @param p Search criteria
     * @return set of clients that meet filter requirements
     */
    private Set<Client> applyFilter(Predicate<Client> p) {
        return clients.stream().filter(p).collect(Collectors.toSet());
    }

    /**
     * Method to search by client name
     * 
     * @param name Name of the client
     * @return filter method with appropriate search criteria
     */
    public Set<Client> searchByName(String name) {
        return applyFilter(c -> c.getName().equals(name));
    }

    /**
     * Method to search by client email
     * 
     * @param email Email of the client
     * @return filter method with appropriate search criteria
     */
    public Set<Client> searchByEmail(String email) {
        return applyFilter(c -> c.getEmail().equals(email));
    }

    /**
     * Method for creating a client
     * 
     * @param name      Name of the client
     * @param address   Address of the client
     * @param refPerson Reference person of the client
     * @param email     Email of the client
     * @return either valid created client or null
     */
    public Client createClient(String name, String address, String refPerson, String email) {
        if (Client.validInfo(name, address, refPerson, email)) {
            Client c = new Client(name, address, refPerson, email);
            addClient(c);
            c.assignCompany(this);
            return c;
        } else {
            return null;
        }
    }

    /**
     * Method to add a client to the client set
     * 
     * @param client Client to be added to the client set
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    public Journey createJourney(Client client, String originPort, String destinationPort, String content) {
        if (clients.contains(client) && !getAvailableContainers().isEmpty()) {
            Container container = availableContainers.pop();
            return new Journey(originPort, destinationPort, content, container, client);
        } else {
            return null;
        }
    }

    public Container createContainer() {
        Container container = new Container(this);
        addContainer(container);
        addAvailableContainer(container);
        return container;
    }

    public boolean enterStatus(ContainerStatus status, Journey journey) {
        if (journey != null && journey.getCompany().equals(this)) {
            journey.addStatus(status);
            return true;
        } else {
            return false;
        }
    }

    public boolean startJourney(Journey journey, Calendar timestamp) {
        if (journey != null && !journey.isStarted()) {
            journey.setStartTimestamp(timestamp);
            journey.setStarted(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean isAllocated(Container container) {
        return !availableContainers.contains(container);
    }

}
