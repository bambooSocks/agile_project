package rcm;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class LogisticsCompany extends User {

    LinkedList<Container> containers;
    LinkedList<Container> availableContainers;
    
    
    public LinkedList<Container> getAvailableContainers() {
        return availableContainers;
    }
    
    public void addAvailableContainer(Container container) {
        availableContainers.add(container);
    }

    Set<Client> clients;

    public Set<Client> getClients() {
        return clients;
    }
    

    public LogisticsCompany(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        containers = new LinkedList<Container>();
        availableContainers = new LinkedList<Container>();
        clients = new HashSet<Client>();
    }

    public Response updateLocation(Container container,String newLocation) {
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

    public boolean searchClient(String parameter) {
        for (Client search : clients) {
            if (parameter.equals(search.getName()) || parameter.equals(search.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public Client searchResult(String parameter) { // this needs to be fixed (try to do without)
        for (Client search : clients) {
            if (parameter.equals(search.getName()) || parameter.equals(search.getEmail())) {
                return search;
            }
        }
        return null;
    }

    public boolean addClient(Client client) {
            clients.add(client);
            return true;

    }
    
    public void addContainer() {
        Container container = new Container(this);
        addContainer(container);
        addAvailableContainer(container);
    }
    
}
