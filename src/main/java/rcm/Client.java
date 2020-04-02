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

    public LinkedList<Journey> searchByDestination(String destination) {
        LinkedList<Journey> filtered = new LinkedList<Journey>();
        for (Journey j : journeyList) {
            if (j.getDestinationPort() == destination) {
                filtered.add(j);
            }
        }
        return filtered;
    }

    public LinkedList<Journey> searchByOrigin(String origin) {
        LinkedList<Journey> filtered = new LinkedList<Journey>();
        for (Journey j : journeyList) {
            if (j.getOriginPort() == origin) {
                filtered.add(j);
            }
        }
        return filtered;
    }

    public LinkedList<Journey> searchByContent(String content) {
        LinkedList<Journey> filtered = new LinkedList<Journey>();
        for (Journey j : journeyList) {
            if (j.getContent() == content) {
                filtered.add(j);
            }
        }
        return filtered;
    }

    public boolean updateProfile(String newName, String newAddress, String newRefPerson, String newEmail) {
        name = newName;
        address = newAddress;
        refPerson = newRefPerson;
        email = newEmail;
        return true;
    }

}
