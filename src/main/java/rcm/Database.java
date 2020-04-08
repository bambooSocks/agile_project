package rcm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

    private Connection conn = null;

    public void connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/" + "Database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createNewTable(String fileName) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n" + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n" + "    capacity real\n" + ");";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
