package rcm;

public class Container {
    private int id;
    private LogisticsCompany company;
    private String location;

    public Container(LogisticsCompany company) {
        id = IdGenerator.getInstance().getId(GroupIdType.CONTAINER);
        this.company = company;
    }

    public LogisticsCompany getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

    public void setLocation(String newLocation) {
        location = newLocation;

    }

    public String getLocation() {
        return location;
    }

}
