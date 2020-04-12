package rcm;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.function.Predicate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class LogisticsCompany extends User {

    List<Container> containers;
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
        clients = new HashSet<Client>();
        id = IdGenerator.getInstance().getId(GroupIdType.COMPANY);
    }

    /**
     * Getter for a list of all available containers
     * 
     * @return List of Container filtered by availability
     */
    public List<Container> getAllAvailableContainers() {
        return containers.stream().filter(c -> c.isAvailable()).collect(Collectors.toList());
    }

    /**
     * Getter for a single available container
     * 
     * @return An available container or null if all containers are allocated
     */
    public Container getAvailableContainer() {
        List<Container> available = getAllAvailableContainers();
        if (available.isEmpty()) {
            return null;
        } else {
            Container container = available.get(0);
            return container;
        }
    }

    /**
     * Sets the given container available
     * 
     * @param container The container to be set available
     */
    public void setAvailableContainer(Container container) {
        container.setAvailable(true);
    }

    public void setAllocatedContainer(Container container) {
        container.setAvailable(false);
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

    /**
     * Creates a journey for a given client and links them together
     * 
     * @param client          The client requesting a new journey
     * @param originPort      The origin port of the journey
     * @param destinationPort The destination port of the journey
     * @param content         The content of the container transported in the
     *                        journey
     * @return null if all containers are allocated or the client is not of the
     *         logistics company creating the journey
     */
    public Journey createJourney(Client client, String originPort, String destinationPort, String content) {
        Container container = getAvailableContainer();
        if (clients.contains(client) && container != null) {
            Journey journey = new Journey(originPort, destinationPort, content, container, client);
            client.addJourney(journey);
            return journey;
        } else {
            return null;
        }
    }

    /**
     * Creates a container and links it together with the company
     * 
     * @return A created container
     */
    public Container createContainer() {
        Container container = new Container(this);
        addContainer(container);
        setAvailableContainer(container);
        return container;
    }

    /**
     * Enters a new container status to the given journey
     * 
     * @param status  The status to be entered
     * @param journey The journey that the status should be entered to
     * @return Boolean of whether the container status was entered successfully
     */
    public boolean enterStatus(ContainerStatus status, Journey journey) {
        if (journey != null && journey.getCompany().equals(this) && journey.isStarted()
                && journey.getStartTimestamp().isBefore(status.getTimestamp()) && !journey.isEnded()) {
            journey.addStatus(status);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Starts a given journey with given time stamp
     * 
     * @param journey   The journey to be stared
     * @param timestamp The time stamp to be set as starting time stamp of the
     *                  journey
     * @return Boolean of whether the journey was started successfully
     */
    public boolean startJourney(Journey journey, LocalDateTime timestamp) {
        if (journey != null && !journey.isStarted()) {
            journey.setStartTimestamp(timestamp);
            journey.setStarted();
            setAllocatedContainer(journey.getContainer());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether a container is allocated
     * 
     * @param container The container to be checked
     * @return Boolean of whether container is allocated
     */
    public boolean isAllocated(Container container) {
        return !container.isAvailable();
    }

    public boolean endJourney(Journey journey, LocalDateTime timestamp) {
        if (journey != null && !journey.isEnded() && journey.isStarted() && journey.isValidEndTimestamp(timestamp)) {
            journey.setEndTimestamp(timestamp);
            journey.setEnded();
            setAvailableContainer(journey.getContainer());
            return true;
        } else {
            return false;
        }
    }

    public boolean isAvailable(Container container) {
        return container.isAvailable();
    }

}
