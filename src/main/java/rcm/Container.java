package rcm;

public class Container {
    private int id;
    private LogisticsCompany company;
    private Double[] location = new Double[2];

    @Deprecated
    public Container(int id, LogisticsCompany company) {
        this.id = id;
        this.company = company;
        company.addToList(this);
    }

    public Container(LogisticsCompany company) {
        id = IdGenerator.getInstance().getId(GroupIdType.CONTAINER);
        this.company = company;
        company.addToList(this);
    }

    public LogisticsCompany getCompany() {
        return company;
    }

    public void setLocation(Double x, Double y) {
        this.location[0] = x;
        this.location[1] = y;
    }

    public int getId() {
        return id;
    }

}
