package rcm;

import java.util.LinkedList;

public class Client extends User {

    private LinkedList<Journey> journeyList;

    public Client(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        journeyList = new LinkedList<Journey>();
    }

    public void addJourney(Journey journey) {
        this.journeyList.add(journey);
    }

    public Response filter(String destination) {
        Response response = new Response(231, "Successful filtering");
        return response;
    }
    
    public boolean updateProfile(String newName, String newAddress, String newRefPerson, String newEmail) {
        name = newName;
        address = newAddress;
        refPerson = newRefPerson;
        email = newEmail;
        return true;
    }

}
