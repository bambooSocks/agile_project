package rcm;

import java.util.LinkedList;

public class Client {

    private int id;
    private String name;
    private String address;
    private String refPerson;
    private String email;

    private LinkedList<Journey> journeyList;

    public Client(String name, String address, String refPerson, String email) {
        super();
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
        journeyList = new LinkedList<Journey>();
    }

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
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public void addJourney(Journey journey) {
        this.journeyList.add(journey);
    }

    public Response filter(String destination) {
        Response response = new Response(231, "Successful filtering");
        return response;

    }

}
