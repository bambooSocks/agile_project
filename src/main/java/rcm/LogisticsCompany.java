package rcm;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class LogisticsCompany extends User {

    LinkedList<Container> containers;
    Set<Client> clients;

    public LogisticsCompany(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        containers = new LinkedList<Container>();
        clients = new HashSet<Client>();
    }

    public Response updateLocation(Container container) {

        if (this.containers.contains(container)) {

            // TO DO implement user input for x and y
            Double x = 1212.0;
            Double y = 2313.0;
            container.setLocation(x, y);

            return new Response(402, "Location changed");
        } else {
            return new Response(333, "Location not changed");
        }
    }

    public void addToList(Container container) {
        containers.add(container);
    }
    
    public boolean searchProfiles(String parameter) {
        for(Client search : clients) {
            if (parameter.equals(search.getName()) || parameter.equals(search.getEmail())) {
                return true;
            }
        }
        return false;
    }
    
    public Client searchResult(String parameter) {
        for(Client search : clients) {
            if (parameter.equals(search.getName()) || parameter.equals(search.getEmail())) {
                return search;
            }
        }
        return null;
    }
    
    public boolean addProfile(Client client) {
        if (this.clients.contains(client)) {
            return false;
//            return new Response(567, "This client already exists");
        } else {
            clients.add(client);
            return true;
//            return new Response(568, "New client profile has been created");
        }
    }

}
