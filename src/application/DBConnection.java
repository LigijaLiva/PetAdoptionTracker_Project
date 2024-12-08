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

    // Izveidojam ConfigLoader objektu
    private ConfigLoader configLoader;

    public DBConnection() {
        configLoader = new ConfigLoader(); // Inicializējam ConfigLoader
        configLoader.loadConfig("config.properties"); // Ielādējam konfigurāciju no faila
    }

    /**
     * Establishes a connection to the database.
     * @return Connection object representing the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public Connection connect() throws SQLException {
        // Iegūstam konfigurāciju datus no ConfigLoader
        String url = configLoader.getDBUrl();
        String username = configLoader.getDBUsername();
        String password = configLoader.getDBPassword();

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
