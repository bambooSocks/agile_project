package rcm.model;

import java.util.List;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Journey implements Comparable<Journey> {
    private int id;
    private boolean started = false;
    private boolean ended = false;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private String originPort;
    private String destinationPort;
    private String content;

    private Container container;
    private Client client;

    private List<ContainerStatus> history;

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
        id = IdGenerator.getInstance().getId(GroupIdType.JOURNEY);
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

    public String getOriginPort() {
        return originPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public String getContent() {
        return content;
    }

    public Client getClient() {
        return client;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        return false;
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

}
