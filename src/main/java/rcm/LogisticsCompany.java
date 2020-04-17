package rcm;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.function.Predicate;
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
     * @param password
     */
    public LogisticsCompany(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
        super(name, address, refPerson, email, password);
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

    private Set<Client> applyFilter(Predicate<Client> p) {
        return clients.stream().filter(p).collect(Collectors.toSet());
    }

    public Set<Client> searchByName(String name) {
        return applyFilter(c -> c.getName().equals(name));
    }

    public Set<Client> searchByEmail(String email) {
        return applyFilter(c -> c.getEmail().equals(email));
    }

    public Set<Client> searchByHashKey(String hashKey) {
        return applyFilter(c -> c.getPassword().equals(hashKey));
    }

    public boolean logInStatus(String email, String password) {
        Set<Client> emails = searchByEmail(email);
        String hashKey = Password.SHA1_Hasher(password);
        if (emails.isEmpty()) {
            return false;
        } else {
            Set<Client> passed = emails.stream().filter(c -> c.getPassword().equals(hashKey))
                    .collect(Collectors.toSet());
            if (passed.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public Client createClient(String name, String address, String refPerson, String email, String password) {
        try {
            Client c = new Client(name, address, refPerson, email, password);
            addClient(c);
            c.assignCompany(this);
            return c;
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
            return null;
        }

//        if (validateSomeName(name) && validateSomeName(refPerson) && validateEmail(email)
//                && validatePassword(password)) {
//            Client c = new Client(name, address, refPerson, email, password);
//            addClient(c);
//            c.assignCompany(this);
//            return c;
//        } else {
//            return null;
//        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Journey createJourney(Client client, String originPort, String destinationPort, String content) {
        if (clients.contains(client) && !getAvailableContainers().isEmpty()) {
            Container container = getAvailableContainers().pop();
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

}
