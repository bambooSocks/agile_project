package rcm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Connection conn = null;

    public static Connection connect() throws SQLException {

        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/" + "Database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            throw new SQLException("Not connected");
        }

    }

    public static void disconnect() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new SQLException("Not disconnected");
        }
    }

    public static void createNewTables() {

        // SQL statement for creating a new table
        String logisticsCompany = "CREATE TABLE IF NOT EXISTS LogisticsCompanies (\n"
                + "    logisticsCompanyId integer PRIMARY KEY,\n" + "    name text NOT NULL,\n"
                + "   address text NOT NULL,\n" + "    refPerson text NOT NULL,\n" + "    email text NOT NULL\n" + ");";
        String client = "CREATE TABLE IF NOT EXISTS Clients (\n" + "    clientId integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n" + "   address text NOT NULL,\n" + "    refPerson text NOT NULL,\n"
                + "    email text NOT NULL,\n" + "logisticsCompanyId integer NOT NULL,\n"
                + "FOREIGN KEY (logisticsCompanyId) REFERENCES LogisticsCompanies(logisticsCompanyId)\n" + ");";
        String container = "CREATE TABLE IF NOT EXISTS Containers (\n" + "    containerId integer PRIMARY KEY,\n"
                + "   availability integer NOT NULL,\n" + "logisticsCompanyId integer NOT NULL,\n"
                + "FOREIGN KEY (logisticsCompanyId) REFERENCES LogisticsCompanies(logisticsCompanyId)\n" + ");";
        String journey = "CREATE TABLE IF NOT EXISTS Journeys (\n" + "    journeyId integer PRIMARY KEY,\n"
                + "    originPort text NOT NULL,\n" + "   destinationPort text NOT NULL,\n"
                + "    content text NOT NULL,\n" + "    clientId integer NOT NULL,\n"
                + "    containerId integer NOT NULL,\n"
                + "FOREIGN KEY (containerId) REFERENCES Containers(containerId),\n "
                + "FOREIGN KEY (clientId) REFERENCES Clients(clientId)\n" + ");";
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // logistics company
    public static void save(String name, String address, String refPerson, String email) throws SQLException {
        String sql = "INSERT INTO LogisticsCompanies(name,address,refPerson,email) VALUES(?,?,?,?)";
        Database.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, refPerson);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // container
    public static void save(int logisticsCompanyId, int availability) throws SQLException {
        String sql = "INSERT INTO Containers(logisticsCompanyId,availability) VALUES(?,?)";
        Database.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, logisticsCompanyId);
            pstmt.setInt(2, availability);
            pstmt.executeUpdate();
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // client
    public static void save(String name, String address, String refPerson, String email, int logisticsCompanyId)
            throws SQLException {
        String sql = "INSERT INTO Clients(name,address,refPerson,email,logisticsCompanyId) VALUES(?,?,?,?,?)";
        Database.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, refPerson);
            pstmt.setString(4, email);
            pstmt.setInt(5, logisticsCompanyId);
            pstmt.executeUpdate();
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // journey
    public static void save(String originPort, String destinationPort, String content, int clientId, int containerId)
            throws SQLException {
        String sql = "INSERT INTO Journeys(originPort,destinationPort,content,clientId,containerId) VALUES(?,?,?,?,?)";
        Database.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, originPort);
            pstmt.setString(2, destinationPort);
            pstmt.setString(3, content);
            pstmt.setInt(4, clientId);
            pstmt.setInt(5, containerId);
            pstmt.executeUpdate();
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // container status
    public static void save(double temperature, double humidity, double atmPressure, int journeyId)
            throws SQLException {
        String sql = "INSERT INTO ContainersStatus(temperature,humidity,atmPressure,journeyId) VALUES(?,?,?,?)";
        Database.connect();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, temperature);
            pstmt.setDouble(2, humidity);
            pstmt.setDouble(3, atmPressure);
            pstmt.setInt(4, journeyId);
            pstmt.executeUpdate();
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        Database.connect();
        Database.createNewTables();
    }

}
