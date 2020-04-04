package rcm;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Client extends User {

    private List<Journey> journeyList;

    public Client(String name, String address, String refPerson, String email) {
        super(name, address, refPerson, email);
        journeyList = new LinkedList<Journey>();
    }

    public void addJourney(Journey journey) {
        this.journeyList.add(journey);
    }

    private List<Journey> applyFilter(Predicate<Journey> p) {
        return journeyList.stream().filter(p).collect(Collectors.toList());
    }
    
    public List<Journey> searchByDestination(String destination) {
        return applyFilter(j -> j.getDestinationPort().equals(destination));
    }

    public List<Journey> searchByOrigin(String origin) {
        return applyFilter(j -> j.getOriginPort().equals(origin));
    }

    public List<Journey> searchByContent(String content) {
        return applyFilter(j -> j.getContent().equals(content));
    }

    public boolean updateClient(String newName, String newAddress, String newRefPerson, String newEmail) {
        name = newName;
        address = newAddress;
        refPerson = newRefPerson;
        email = newEmail;
        return true;
    }


    public boolean closeButton() {
        // TODO Auto-generated method stub
        return true;
        
    }
    
    public Response requestJourney(String originPort, String destinationPort, String content,LogisticsCompany logisticsCompany) {
        if (logisticsCompany.getClients().contains(this) && !logisticsCompany.getAvailableContainers().isEmpty()) {
            Container container = logisticsCompany.getAvailableContainers().pop();
            Journey journey = new Journey(originPort, destinationPort, content, container, this);
        return Response.SUCCESS;
        }
        else {
            return Response.JOURNEY_NOT_CREATED;
        }
    }
    
}
