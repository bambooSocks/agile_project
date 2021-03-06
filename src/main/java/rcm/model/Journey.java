package rcm.model;

import java.util.List;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Journey implements Comparable<Journey> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private boolean started = false;
    @Column
    private boolean ended = false;
    @Column(nullable = true)
    private LocalDateTime startTimestamp;
    @Column(nullable = true)
    private LocalDateTime endTimestamp;

    @Column
    private String originPort;
    @Column
    private String destinationPort;
    @Column
    private String content;

    @ManyToOne
    private Container container;
    @ManyToOne
    private Client client;

    @ElementCollection
    private List<ContainerStatus> history;

    @SuppressWarnings("unused")
    private Journey() {

    }

    /**
     * Journey constructor
     * 
     * @param originPort      The name of the origin port
     * @param destinationPort The name of the destination port
     * @param content         The content of the container being transported in the
     *                        journey
     * @param client          The client that the journey belongs to
     * @implNote should be only called from Logistics Company
     */
    public Journey(String originPort, String destinationPort, String content, Client client) {
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.content = content;
        this.client = client;
        history = new LinkedList<ContainerStatus>();
    }

    /**
     * Getter for the company that the journey is associated with
     * 
     * @return null if the container is missing otherwise the LogisticsCompany that
     *         owns the container used in the journey
     */
    public LogisticsCompany getCompany() {
        return (container == null) ? null : container.getCompany();
    }

    /**
     * Getter for the Container Status history
     * 
     * @return List of Container Statuses containing all accumulated container
     *         statuses
     */
    public List<ContainerStatus> getStatus() {
        return history;
    }

    /**
     * Checks for whether a status belongs to the history of container statuses of
     * the journey
     * 
     * @param status The Container Status to check for
     * @return Boolean of whether the status is in the history
     */
    public boolean containsStatus(ContainerStatus status) {
        return history.contains(status);
    }

    /**
     * Getter for the origin port
     * 
     * @return origin port of the journey
     */
    public String getOriginPort() {
        return originPort;
    }

    /**
     * Getter for the destination port
     * 
     * @return destination port of the journey
     */
    public String getDestinationPort() {
        return destinationPort;
    }

    /**
     * Getter for the contents
     * 
     * @return contents of the journey
     */
    public String getContent() {
        return content;
    }

    /**
     * Getter for the client
     * 
     * @return client of the journey
     */
    public Client getClient() {
        return client;
    }

    /**
     * Getter of the container
     * 
     * @return container of the journey
     */
    public Container getContainer() {
        return container;
    }

    /**
     * Method to find a container using its id
     * 
     * @param id of the container to be found
     * @return true if the container exists, otherwise false
     */
    public boolean checkContainerById(int container_id) {
        if (container != null && container.getId() == container_id) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setter for container
     * 
     * @param container for the journey
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * Adds a new status to the history
     * 
     * @param status The Container Status to be added to the history
     */
    public void addStatus(ContainerStatus status) {
        history.add(status);
    }

    /**
     * Getter for the started flag
     * 
     * @return Boolean of whether the journey has been started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Sets the journey to started
     */
    public void setStarted() {
        this.started = true;
    }

    /**
     * Getter for the ended flag
     * 
     * @return Boolean of whether the journey has been ended
     */
    public boolean isEnded() {
        return ended;
    }

    /**
     * Sets the journey to ended
     */
    public void setEnded() {
        this.ended = true;
    }

    /**
     * Getter for the Journey's starting time stamp
     * 
     * @return LocalDateTime denoting the journey's starting time stamp
     */
    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Setter for the Journey's starting time stamp
     * 
     * @param timestamp LocalDateTime that the journey's starting time stamp should
     *                  be set to
     */
    public void setStartTimestamp(LocalDateTime timestamp) {
        startTimestamp = timestamp;
    }

    /**
     * Getter for the Journey's ending time stamp
     * 
     * @return LocalDateTime denoting the journey's ending time stamp
     */
    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * Setter for the Journey's ending time stamp
     * 
     * @param timestamp LocalDateTime that the journey's ending time stamp should be
     *                  set to
     */
    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    /**
     * Checks whether the given time stamp can be used as an ending time stamp for
     * the journey
     * 
     * @param timestamp The time stamp that should be checked
     * @return Boolean of whether the time stamp can be used as an ending time stamp
     */
    public boolean isValidEndTimestamp(LocalDateTime timestamp) {
        return history.stream().allMatch(s -> s.getTimestamp().isBefore(timestamp))
                && startTimestamp.isBefore(timestamp);
    }

    /**
     * Override of the compareTo from the Comparable interface
     * 
     * @implNote Since the Journey lists should be able to be sorted by start time
     *           stamp.
     */
    @Override
    public int compareTo(Journey o) {
        return startTimestamp.compareTo(o.getStartTimestamp());
    }

    /**
     * Getter for the id
     * 
     * @return int id for Journey
     */
    public int getId() {
        return id;
    }
}