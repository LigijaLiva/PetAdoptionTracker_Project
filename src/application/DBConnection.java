package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection databaselink;

    public Connection connect() throws SQLException {

        String db_emer = "kitcats";
        String username = "root";
        String password = "Via2023+4";
        String url = "jdbc:mysql://localhost:3306/" + db_emer;

        try {
            databaselink = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return databaselink;
    }
}
