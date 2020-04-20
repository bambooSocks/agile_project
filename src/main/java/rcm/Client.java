package rcm;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.sql.SQLException;
import java.util.LinkedList;


@Entity
@DiscriminatorValue("C")
public class Client extends User {
    @OneToMany(cascade=CascadeType.ALL)
    private List<Journey> journeyList;
    @ManyToOne
    private LogisticsCompany company;

    public Client(String name, String address, String refPerson, String email, String password)
            throws WrongInputException {
     */
     * @throws SQLException
     * @param email     Email of the client
     * @param refPerson Reference person of the client
     * @param address   Address of the client
     * @param name      Name of the client
     * 
    /**
     * Client constructor
    }
        super();
    {
    private Client()
    

        super(name, address, refPerson, email, password);
        journeyList = new LinkedList<Journey>();
        id = IdGenerator.getInstance().getId(GroupIdType.CLIENT); 
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

    public List<Journey> viewClientData(boolean loggedIn, String email1, String email2, boolean access) {
        if ((email1.equals(email2) || access) && loggedIn) {
            LinkedList<Client> cl = new LinkedList<Client>();
            cl.addAll(company.searchByEmail(email2));
            if (cl.isEmpty()) {
                return null;
            } else {
                return cl.pop().getJourneyList();
            }
        } else {
            return null;
        }
    }

    public boolean closeButton() {
        // TODO Auto-generated method stub
        return true;
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
     * Requests the company to create a journey
     * 
     * @param originPort      the origin port of the journey
     * @param destinationPort the destination port of the journey
     * @param content         content of the container in the journey
     * @param timestamp       time stamp of the journey start
     * @return Response.SUCCESS for journey created and added to journeyList
     *         JOURNEY_NOT_STARTED for failing to start journey. The journey is
     *         added to the journeyList. 
     *         JOURNEY_NOT_CREATED for failing to create journey
     * @throws SQLException
     * @implNote This method only works if the client is assigned to a company
     */
    public Response requestJourney(String originPort, String destinationPort, String content, LocalDateTime timestamp) {
        Journey journey = company.createJourney(this, originPort, destinationPort, content);
        if (journey != null) {
            if (company.startJourney(journey, timestamp)) {
                return Response.SUCCESS;
            } else {
                return Response.JOURNEY_NOT_STARTED;
            }
        } else {
            return Response.JOURNEY_NOT_CREATED;
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
}
