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
    }
    
    public void addJourney(Journey journey) {
        this.journeyList.add(journey);
    }
    

    public Response filter(String destination) {
        return null;
       
    }



}
