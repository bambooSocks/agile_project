package rcm;

import java.sql.SQLException;

public class Container {
    private int id;
    private LogisticsCompany company;

    public Container(LogisticsCompany company) throws SQLException {
        id = IdGenerator.getInstance().getId(GroupIdType.CONTAINER);
        this.company = company;
        company.addContainer(this);
        company.addAvailableContainer(this);
        Database.save(company.getId(),1);
    }

    public LogisticsCompany getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

}
