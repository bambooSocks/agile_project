package rcm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Client extends User {

    private List<Journey> journeyList;
    private LogisticsCompany company;

    public Client(String name, String address, String refPerson, String email, String password) throws WrongInputException {
        super(name, address, refPerson, email, password);
        journeyList = new LinkedList<Journey>();
        id = IdGenerator.getInstance().getId(GroupIdType.CLIENT);
    }

    public List<Journey> getJourneyList() {
        return journeyList;
    }

    public void assignCompany(LogisticsCompany company) {
        this.company = company;
    }

    public void addJourney(Journey journey) {
        journeyList.add(journey);
    }

    public List<Journey> searchByDestination(String destination) {
        return journeyList.stream().filter(j -> j.getDestinationPort().equals(destination))
                .collect(Collectors.toList());
    }

    public List<Journey> searchByOrigin(String origin) {
        return journeyList.stream().filter(j -> j.getOriginPort().equals(origin)).collect(Collectors.toList());
    }

    public List<Journey> searchByContent(String content) {
        return journeyList.stream().filter(j -> j.getContent().equals(content)).collect(Collectors.toList());
    }

    public List<Journey> viewData(boolean loggedIn, String email1, String email2, boolean access) {
        if ((email1 == email2 || access) && loggedIn) {
//            makesure its not empty
//            return company.searchByEmail(email2).getJourneyList();
            return null;
        } else {
            return null;
        }
    }

    public boolean closeButton() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * Method which requests the company to create a journey
     * 
     * @param originPort      the origin port of the journey
     * @param destinationPort the destination port of the journey
     * @param content         content of the container in the journey
     * @return Response.SUCCESS for journey created and added to journeyList
     *         JOURNEY_NOT_CREATED for failing to create journey
     * @implNote This method only works if the client is assigned to a company
     */
    public Response requestJourney(String originPort, String destinationPort, String content) {
        if (company.createJourney(this, originPort, destinationPort, content) != null) {
            return Response.SUCCESS;
        } else {
            return Response.JOURNEY_NOT_CREATED;
        }
    }

}
