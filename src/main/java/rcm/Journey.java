package rcm;

import java.util.List;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Journey {
    private int id;
    private boolean started = false;
    private LocalDateTime startTimestamp;
    private String originPort;
    private String destinationPort;
    private String content;

    private Container container;
    private Client client;

    private List<ContainerStatus> history;

    public Journey(String originPort, String destinationPort, String content, Container container, Client client) {
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.content = content;
        this.container = container;
        client.addJourney(this);
        this.client = client;
        history = new LinkedList<ContainerStatus>();
        id = IdGenerator.getInstance().getId(GroupIdType.JOURNEY);
    }

    public int getID() {
        return id;
    }

    public LogisticsCompany getCompany() {
        return (container == null) ? null : container.getCompany();
    }

    public List<ContainerStatus> getStatus() {
        return history;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Journey other = (Journey) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public Client getClient() {
        return client;
    }

    public void addStatus(ContainerStatus status) {
        history.add(status);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime timestamp) {
        startTimestamp = timestamp;
    }

}
