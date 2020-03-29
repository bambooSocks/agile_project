package rcm;

import java.util.LinkedList;

public class LogisticsCompany extends User {

    LinkedList<Container> containers;

    public LogisticsCompany(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        containers = new LinkedList<Container>();
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

}
