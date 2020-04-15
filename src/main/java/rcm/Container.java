package rcm;

import java.util.LinkedList;

public class Container {
    private int id;
    private boolean available = true;
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

    /**
     * Getter for Container ID
     * 
     * @return Integer denoting the Container ID
     */
    public int getId() {
        return id;
    }

    public void setLocation(String newLocation) {
        location = newLocation;

    }

    public String getLocation() {
        return location;
    }

    /**
     * Getter for availability flag
     * 
     * @return Boolean of whether the container is available or not
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Setter for availability flag
     * 
     * @param available Boolean value the flag should be set to
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addJourney(Journey journey) {
        journeyList.add(journey);
    }

    public LinkedList<Journey> getJourneyList() {
        return journeyList;
    }

}
