package rcm;

import java.util.LinkedList;

public class Client extends User {

    private LinkedList<Journey> journeyList;
    private LinkedList<Client> clientList; //TODO: maybe remove

    public Client(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        clientList = new LinkedList<Client>();
        addProfile(this);
        journeyList = new LinkedList<Journey>();
    }

    public void addJourney(Journey journey) {
        this.journeyList.add(journey);
    }

    public Response filter(String destination) {
        Response response = new Response(231, "Successful filtering");
        return response;
    }

    public boolean searchProfiles(String name) {
        return clientList.contains(name);
    }

    public void addProfile(Client client) {
        this.clientList.add(client);
    }

}
