package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles the database connection for the application.
 * It provides a method to establish a connection to the MySQL database.
 */
public class DBConnection {

    // Static variable to hold the database connection object
    public static Connection databaselink;

    // Izveido AppConfig objektu, lai nolasītu konfigurācijas datus
    private AppConfig appConfig;

    // Izveido Logger objektu žurnālu rakstīšanai
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    public DBConnection() {
        appConfig = new AppConfig();  // Inicializē AppConfig objektu
        appConfig.loadConfig("config.properties");  // Ielādē konfigurāciju no faila

        // Logojam, ka konfigurācija ir ielādēta
        logger.log(Level.INFO, "Konfigurācija ielādēta no config.properties");
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

        // Logojam, ka sākam mēģināt izveidot savienojumu ar datu bāzi
        logger.log(Level.INFO, "Mēģinām izveidot savienojumu ar datu bāzi: {0}", url);

        try {
            // Mēģinām izveidot savienojumu ar datu bāzi
            databaselink = DriverManager.getConnection(url, username, password);

            // Ja savienojums veiksmīgi izveidots, logojam šo notikumu
            logger.log(Level.INFO, "Savienojums ar datu bāzi veiksmīgi izveidots!");
        } catch (SQLException ex) {
            // Ja rodas kļūda, logojam kļūdu līmeni
            logger.log(Level.SEVERE, "Kļūda, mēģinot izveidot savienojumu ar datu bāzi", ex);
            throw ex;  // Atkārtoti izsistam kļūdu
        }

        // Atgriežam izveidoto savienojumu
        return databaselink;
    }
}
