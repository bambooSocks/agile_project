package rcm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private LogisticsCompany company;
    @OneToMany(cascade = CascadeType.ALL)
    private LinkedList<Journey> journeyList;

    public Container() {

    }

    /**
     * Constructor for container
     * 
     * @param company The logistics company owning the container
     * @implNote should be only called from Logistics Company class
     */
    public Container(LogisticsCompany company) {
        this.company = company;
        journeyList = new LinkedList<Journey>();
    }

    /**
     * Getter for company owning the container
     * 
     * @return LogisticsComapany that owns the container
     */
    public LogisticsCompany getCompany() {
        return company;
    }

    /**
     * Checks whether a container is available at given time
     * 
     * @param timestamp LocalDateTime of the time stamp to be checked
     * @return boolean of whether the container is available
     */
    public boolean isAvailable(LocalDateTime timestamp) {
        if (journeyList.isEmpty()) {
            return true;
        } else {
            Collections.sort(journeyList);
            Journey lastJourney = journeyList.getLast();
            return timestamp != null && lastJourney.isEnded() && lastJourney.getEndTimestamp().isBefore(timestamp);
        }
    }

    /**
     * Adds a journey to the journey list of the container
     * 
     * @param journey Journey to be added to the list
     */
    public void addJourney(Journey journey) {
        journeyList.add(journey);
    }

    /**
     * Getter for the journey list
     * 
     * @return LinkedList of Journey of the journey list
     */
    public LinkedList<Journey> getJourneyList() {
        return journeyList;
    }

    /**
     * Getter for the id
     * 
     * @return int id for Container
     */
    public int getId() {
        return id;
    }

    /**
     * Method to search journeys using destination
     * 
     * @param destination Destination to be searched for
     * @return a list of journeys with the required destination
     */
    public List<Journey> searchJourneyByDestination(String destination) {
        String regexSearch = "(" + destination + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return journeyList.stream().filter(j -> (pattern.matcher(j.getDestinationPort())).find())
                .collect(Collectors.toList());
    }

    /**
     * Method to search journeys using Origin
     * 
     * @param origin Origin to be searched for
     * @return a list of journeys with the required origin
     */
    public List<Journey> searchJourneyByOrigin(String origin) {
        String regexSearch = "(" + origin + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return journeyList.stream().filter(j -> (pattern.matcher(j.getOriginPort())).find())
                .collect(Collectors.toList());
    }

    /**
     * Method to search journeys by container contents
     * 
     * @param content Container contents to be searched for
     * @return a list of journeys with the required container contents
     */
    public List<Journey> searchJourneyByContent(String content) {
        String regexSearch = "(" + content + ")";
        Pattern pattern = Pattern.compile(regexSearch, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return journeyList.stream().filter(j -> (pattern.matcher(j.getContent())).find()).collect(Collectors.toList());
    }

    /**
     * Method to search journeys by id
     * 
     * @param id Journey id to be searched for
     * @return a list of journeys with the required id
     */
    public List<Journey> searchJourneyById(String id) {
        return journeyList.stream().filter(j -> Integer.toString(j.getId()).equals(id)).collect(Collectors.toList());
    }

    public List<ContainerStatus> requestStatus(Journey journey) {
        if (journey != null && journeyList.contains(journey)) {
            return journey.getStatus();
        } else {
            return null;
        }
    }
}
