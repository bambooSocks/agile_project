package rcm;

public class Container {
    private int id;
    private LogisticsCompany company;


    public Container(int id, LogisticsCompany company) {
        this.id = id;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public LogisticsCompany getCompany() {
        return company;
    }

}
