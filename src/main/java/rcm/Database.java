package rcm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection conn = null;

    public void connect() throws SQLException {

        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/" + "Database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            throw new SQLException("Not connected");
        }

    }

    public void disconnect() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new SQLException("Not disconnected");
        }
    }

    public void createNewTables() {

        // SQL statement for creating a new table
        String logisticsCompany = "CREATE TABLE IF NOT EXISTS LogisticsCompanies (\n"
                + "    logisticsCompanyId integer PRIMARY KEY,\n" + "    name text NOT NULL UNIQUE,\n"
                + "   address text NOT NULL,\n" + "    refPerson text NOT NULL,\n" + "    email text NOT NULL\n" + ");";
        String client = "CREATE TABLE IF NOT EXISTS Clients (\n" + "    clientId integer PRIMARY KEY,\n"
                + "    name text NOT NULL UNIQUE,\n" + "   address text NOT NULL,\n" + "    refPerson text NOT NULL,\n"
                + "    email text NOT NULL,\n" + "logisticsCompanyId integer NOT NULL,\n"
                + "FOREIGN KEY (logisticsCompanyId) REFERENCES LogisticsCompanies(logisticsCompanyId)\n" + ");";
        String container = "CREATE TABLE IF NOT EXISTS Containers (\n" + "    containerId integer PRIMARY KEY,\n"
                + "    location text NOT NULL,\n" + "   availability integer NOT NULL,\n"
                + "logisticsCompanyId integer NOT NULL,\n"
                + "FOREIGN KEY (logisticsCompanyId) REFERENCES LogisticsCompanies(logisticsCompanyId)\n" + ");";
        String journey = "CREATE TABLE IF NOT EXISTS Journeys (\n" + "    journeyId integer PRIMARY KEY,\n"
                + "    originPort text NOT NULL UNIQUE,\n" + "   destinationPort text NOT NULL,\n"
                + "    content text NOT NULL,\n" + "    containerId integer NOT NULL,\n"
                + "    location text NOT NULL,\n" + "    logisticsCompanyId integer NOT NULL,\n"
                + "FOREIGN KEY (containerId) REFERENCES Containers(containerId),\n "
                + "FOREIGN KEY (logisticsCompanyId) REFERENCES LogisticsCompanies(logisticsCompanyId)\n" + ");";
        String containerStatus = "CREATE TABLE IF NOT EXISTS ContainersStatus (\n" + "   temperature real NOT NULL,\n"
                + "   humidity real NOT NULL,\n" + "atmPressure real NOT NULL,\n" + "journeyId integer NOT NULL,\n"
                + "FOREIGN KEY (journeyId) REFERENCES Journeys(journeyId)\n" + ");";
        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(logisticsCompany);
            stmt.execute(client);
            stmt.execute(container);
            stmt.execute(journey);
            stmt.execute(containerStatus);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.connect();
        db.createNewTables();
    }

}
