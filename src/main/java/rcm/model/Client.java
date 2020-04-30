package rcm.model;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private List<Journey> journeyList;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Journey> sharedJourneyList;

    @ManyToOne
    private LogisticsCompany company;

    @SuppressWarnings("unused")
    private Client() {
        super();
    }

    /**
     * Client constructor
     * 
     * @param name      Name of the client
     * @param address   Address of the client
     * @param refPerson Reference person of the client
     * @param email     Email of the client
     * @param password  Password of the client
     * @throws WrongInputException
     */
    public Client(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
        super(name, address, refPerson, email, password);
        journeyList = new LinkedList<Journey>();
        sharedJourneyList = new LinkedList<Journey>();
    }

    /**
     * Method to assign a logistics company to a client
     * 
     * @param company The logistics company to be assigned
     */
    public void assignCompany(LogisticsCompany company) {
        this.company = company;
    }

    /**
     * Method to assign a journey to a client
     * 
     * @param journey The journey to be assigned
     */
    public void addJourney(Journey journey) {
        journeyList.add(journey);
    }

    /**
     * Method to search journeys using destination
     * 
     * @param destination Destination to be searched for
     * @return a list of journeys with the required destination
     */
    public List<Journey> journeySearchByDestination(String destination) {
        return journeyList.stream().filter(j -> j.getDestinationPort().equals(destination))
                .collect(Collectors.toList());
    }

    /**
     * Method to search journeys using Origin
     * 
     * @param origin Origin to be searched for
     * @return a list of journeys with the required origin
     */
    public List<Journey> journeySearchByOrigin(String origin) {
        return journeyList.stream().filter(j -> j.getOriginPort().equals(origin)).collect(Collectors.toList());
    }

    /**
     * Method to search journeys by container contents
     * 
     * @param content Container contents to be searched for
     * @return a list of journeys with the required container contents
     */
    public List<Journey> journeySearchByContent(String content) {
        return journeyList.stream().filter(j -> j.getContent().equals(content)).collect(Collectors.toList());
    }

    /**
     * Method to search journeys by id
     * 
     * @param id Journey id to be searched for
     * @return a list of journeys with the required id
     */
    public List<Journey> journeySearchById(String id) {
        return journeyList.stream().filter(j -> Integer.toString(j.getId()).equals(id)).collect(Collectors.toList());
    }

    /**
     * Method to view the data of a client
     * 
     * @param loggedIn boolean representing the log-in status of the first client
     * @param email1   Email of the first client
     * @param email2   Email of the second client
     * @return a list of journeys of the second client
     */
    public List<Journey> viewClientData(String email1, String email2) {
        if (email1.equals(email2)) {
            LinkedList<Client> cl = new LinkedList<Client>();
            cl.addAll(company.searchClientByEmail(email2));
            if (cl.isEmpty()) {
                return null;
            } else {
                return cl.pop().getJourneyList();
            }
        } else {
            return null;
        }
    }

    /**
     * Method to share a journey with another client
     * 
     * @param client  Client the journey is shared with
     * @param journey Journey to be shared with the client
     * @return true if journey is successfully shared, otherwise false
     */
    public boolean shareJourney(Client client, Journey journey) {
        if (client != null && journey != null) {
            client.addSharedJourney(journey);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to add a shared journey to the list of shared journeys
     * 
     * @param journey Journey to be added to the list of shared journeys
     */
    public void addSharedJourney(Journey journey) {
        sharedJourneyList.add(journey);
    }

    /**
     * Method to search shared journeys using destination
     * 
     * @param destination Destination to be searched for
     * @return a list of shared journeys with the required destination
     */
    public List<Journey> sharedJourneySearchByDestination(String destination) {
        return sharedJourneyList.stream().filter(j -> j.getDestinationPort().equals(destination))
                .collect(Collectors.toList());
    }

    /**
     * Method to search shared journeys using Origin
     * 
     * @param origin Origin to be searched for
     * @return a list of shared journeys with the required origin
     */
    public List<Journey> sharedJourneySearchByOrigin(String origin) {
        return sharedJourneyList.stream().filter(j -> j.getOriginPort().equals(origin)).collect(Collectors.toList());
    }

    /**
     * Method to search shared journeys by container contents
     * 
     * @param content Container contents to be searched for
     * @return a list of shared journeys with the required container contents
     */
    public List<Journey> sharedJourneySearchByContent(String content) {
        return sharedJourneyList.stream().filter(j -> j.getContent().equals(content)).collect(Collectors.toList());
    }

    /**
     * Method to search shared journeys by id
     * 
     * @param id Journey id to be searched for
     * @return a list of shared journeys with the required id
     */
    public List<Journey> sharedJourneySearchById(String id) {
        return sharedJourneyList.stream().filter(j -> Integer.toString(j.getId()).equals(id))
                .collect(Collectors.toList());
    }

    /**
     * Requests the history of container statuses from journey
     * 
     * @param journey Journey providing the status
     * @return List of container statuses of the given journey
     */
    public List<ContainerStatus> requestStatus(Journey journey) {
        if (journey.getClient().equals(this)) {
            return journey.getStatus();
        } else {
            return null;
        }
    }

    /**
     * Getter for list of journeys
     * 
     * @return List of Journeys belonging to the client
     */
    public List<Journey> getJourneyList() {
        return journeyList;
    }

    /**
     * Getter for List of shared Journeys belonging to the client
     * 
     * @return List of shared Journeys belonging to the client
     */
    public List<Journey> getSharedJourneyList() {
        return sharedJourneyList;
    }

}
