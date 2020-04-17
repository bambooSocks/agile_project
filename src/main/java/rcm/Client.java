package rcm;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@DiscriminatorValue("C")
public class Client extends User {

    private List<Journey> journeyList;
    private LogisticsCompany company;
    @Column
    private int companyId;

    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String regexName = "^[A-Z]+([a-z]*)+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    /**
     * Client constructor
     * 
     * @param name      Name of the client
     * @param address   Address of the client
     * @param refPerson Reference person of the client
     * @param email     Email of the client
     * @throws SQLException
     */
    public Client(String name, String address, String refPerson, String email, String password) {
        super(name, address, refPerson, email, password);
        journeyList = new LinkedList<Journey>();
        id = IdGenerator.getInstance().getId(GroupIdType.CLIENT); 
    }

    public void assignCompany(LogisticsCompany company) {
        this.company = company;
        companyId = company.getId();
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

    /**
     * Method for validating client information
     * 
     * @param name      Name to validate
     * @param address   Address to validate
     * @param refPerson Reference person to validate
     * @param email     Email to validate
     * @return boolean for if the information is valid
     */
    public static boolean validInfo(String name, String address, String refPerson, String email, String password) {
        Matcher matcherName = Pattern.compile(regexName).matcher(name);
        Matcher matcherEmail = Pattern.compile(regexEmail).matcher(email);
        Matcher matcherRefPerson = Pattern.compile(regexName).matcher(refPerson);
        if (name.length() <= 25 && matcherName.matches() && matcherEmail.matches() && matcherRefPerson.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for updating client information
     * 
     * @param newName      Optional new name of the client
     * @param newAddress   Optional new address of the client
     * @param newRefPerson Optional new reference person of the client
     * @param newEmail     Optional new email of the client
     * @return boolean for if the information was updated
     */
    public boolean updateInfo(String newName, String newAddress, String newRefPerson, String newEmail,
            String newPassword) {
        if (validInfo(newName, newAddress, newRefPerson, newEmail, newPassword)) {
            name = newName;
            address = newAddress;
            refPerson = newRefPerson;
            email = newEmail;
            password = Password.SHA1_Hasher(newPassword);
            return true;
        } else {
            return false;
        }

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
     * @return List of Journeys belonging to the clientS
     */
    public List<Journey> getJourneyList() {
        return journeyList;
    }

}
