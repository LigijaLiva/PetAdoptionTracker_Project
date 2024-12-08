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

    // Izveido AppConfig objektu, lai nolasītu konfigurācijas datus
    private AppConfig appConfig;

    public DBConnection() {
        appConfig = new AppConfig();  // Inicializē AppConfig objektu
        appConfig.loadConfig("config.properties");  // Ielādē konfigurāciju no faila
    }

    /**
     * Establishes a connection to the database.
     * @return Connection object representing the database connection.
     * @throws SQLException if a database access error occurs.
     */
    public Connection connect() throws SQLException {
        // Iegūstam konfigurāciju no AppConfig
        String url = appConfig.getDBUrl();
        String username = appConfig.getDBUsername();
        String password = appConfig.getDBPassword();

        // Mēģinām izveidot savienojumu ar datu bāzi
        try {
            databaselink = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();  // Ja rodas kļūda, parādām tās izsistēmu
            throw ex;  // Atkārtoti izsistam kļūdu
        }

        // Atgriežam izveidoto savienojumu
        return databaselink;
    }
}
