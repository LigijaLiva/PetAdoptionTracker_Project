package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles the database connection for the application.
 * It provides a method to establish a connection to the MySQL database.
 */
public class DBConnection {

    // Static variable to hold the database connection object
    public static Connection databaselink;

    /**
     * Establishes a connection to the database.
     * @return Connection object representing the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public Connection connect() throws SQLException {

        // Database details:
        // The name of the database, username, password, and URL for connection.
        String db_emer = "kitcats"; // Name of the database
        String username = "root";  // Username for the database
        String password = "Via2023+4"; // Password for the database
        String url = "jdbc:mysql://localhost:3306/" + db_emer; // Connection URL

        // Attempting to establish the database connection
        try {
            databaselink = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            // If an error occurs, print the stack trace and rethrow the exception
            ex.printStackTrace();
            throw ex;
        }

        // Returning the established connection
        return databaselink;
    }
}
