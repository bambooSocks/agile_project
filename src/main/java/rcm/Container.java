package rcm;

public class Container {
    private int id;
    private LogisticsCompany company;

    public Container(LogisticsCompany company) {
        id = IdGenerator.getInstance().getId(GroupIdType.CONTAINER);
        this.company = company;
        company.addContainer(this);
        company.addAvailableContainer(this);
    }

    public LogisticsCompany getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

}
