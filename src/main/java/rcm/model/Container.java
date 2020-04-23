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
        id = IdGenerator.getInstance().getId(GroupIdType.CONTAINER);
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
            return lastJourney.isEnded() && lastJourney.getEndTimestamp().isBefore(timestamp);
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

    public int getId() {
        return id;
    }

}
