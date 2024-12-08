package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_Admin {

    @FXML
    private Text txt;
    
    @FXML
    private TextField Password;

    @FXML
    private TextField Username;
	
    @FXML
    public void Login() {
        DBConnection object = new DBConnection();
        Connection objectConnection = null;
        PreparedStatement loginQuery = null;
        ResultSet resultSet = null;

        try {
            objectConnection = object.connect();
            String sql = "select * from admins where username = ? and password = ?";
            loginQuery = objectConnection.prepareStatement(sql);
            loginQuery.setString(1, Username.getText());
            loginQuery.setString(2, Password.getText());
            resultSet = loginQuery.executeQuery();

            if (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAdmin.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) Username.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                txt.setText("Wrong email or password!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            txt.setText("Failed to log in.");
        } catch (IOException e) {
            txt.setText("Failed to load fxml.");
        } finally {
            try { 
            	if (resultSet != null) resultSet.close(); 
            	if (loginQuery != null) loginQuery.close();
            	if (objectConnection != null) objectConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
