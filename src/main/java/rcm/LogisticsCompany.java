package rcm;

import java.util.LinkedList;

public class LogisticsCompany {

    private int id;
    private String name;
    private String address;
    private String refPerson;
    private String email;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogisticsCompany other = (LogisticsCompany) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    LinkedList<Container> containers;

    public LogisticsCompany(String name, String address, String refPerson, String email) {
        super();
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
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
