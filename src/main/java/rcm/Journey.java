package rcm;

import java.sql.SQLException;
import java.util.LinkedList;

public class Journey {
    private int id;
    private String originPort;
    private String destinationPort;
    private String content;
    private Container container;
    private Client client;

    private LinkedList<ContainerStatus> history;
    private LinkedList<Location> locationHistory;

    public Journey(String originPort, String destinationPort, String content, Container container, Client client) {
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.content = content;
        this.container = container;
        client.addJourney(this);
        this.client = client;
        history = new LinkedList<ContainerStatus>();
        locationHistory = new LinkedList<Location>();
        id = IdGenerator.getInstance().getId(GroupIdType.JOURNEY);
    }

    public int getID() {
        return id;
    }

    public LogisticsCompany getCompany() {
        return (container == null) ? null : container.getCompany();
    }

    public boolean addStatus(ContainerStatus status, LogisticsCompany company) throws SQLException {
        if (company.equals(getCompany())) {
            history.add(status);
            Database.save(status.getTemperature(), status.getHumidity(), status.getAtmPressure(), id);

            return true;
        } else {
            return false;
        }
    }

    public boolean addLocation(Location location, LogisticsCompany company) throws SQLException {
        if (company.equals(getCompany())) {
            locationHistory.add(location);
            return true;
        } else {
            return false;
        }
    }

    public LinkedList<ContainerStatus> getStatus(Client client1) {
        return (client1.equals(client)) ? history : null;
    }

    public boolean containsStatus(ContainerStatus status) {
        return history.contains(status);
    }
    
    public boolean containsLocation(Location location) {
        return locationHistory.contains(location);
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

}
