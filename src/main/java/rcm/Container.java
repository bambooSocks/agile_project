package rcm;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;

public class Container {
    private int id;
    private LogisticsCompany company;
    private String location;
    private LinkedList<Journey> journeyList;

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

    public void setLocation(String newLocation) {
        location = newLocation;
        
    }

    public String getLocation() {
        return location;
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

}
