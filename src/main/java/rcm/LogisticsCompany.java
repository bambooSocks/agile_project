package rcm;

import java.util.HashSet;
import java.util.LinkedList;

public class LogisticsCompany extends User {

    LinkedList<Container> containers;
    HashSet<Client> clients;

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

    public boolean searchProfiles(String name) {
        return clients.contains(name);
    }

    public Response addProfile(Client client) {
        if (this.clients.contains(client)) {
            return new Response(567, "This client already exists");
        } else {
            clients.add(client);
            return new Response(568, "New client profile has been created");
        }
    }

}
