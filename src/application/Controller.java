package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    static HashMap<Integer, Register> register = new HashMap<>();

    @FXML
    private Text txt;

    @FXML
    private TextField Password;

    @FXML
    private TextField Username;

    @FXML
    private Label regSafecheck;

    @FXML
    private Label foundPet;

    @FXML
    private TextField Name;

    @FXML
    private TextField Breed;

    @FXML
    private TextField Age;

    @FXML
    private TextField PetID;

    @FXML
    private ImageView cat;

    @FXML
    private TableColumn<pets, String> col_breed;

    @FXML
    private TableColumn<pets, Integer> col_id;

    @FXML
    private TableColumn<pets, String> col_description;

    @FXML
    private TableView<pets> table_pets;

    ObservableList<pets> listM;

    String nameofthefile;

    private void openWindow(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage newStage = new Stage();
        Scene scene = new Scene(root);

        Main mainApp = new Main();
        mainApp.setStageIconAndTitle(newStage, "Pet Adoption Tracker", "cat2.jpg");

        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void openLoginPage() throws IOException {
        openWindow("Login.fxml");
    }

    @FXML
    void openMainAdmin() throws IOException {
        openWindow("MainAdmin.fxml");
    }

    @FXML
    public void openPetDetails() throws IOException {
        openWindow("PetDetails.fxml");
    }

    @FXML
    public void openDashboard() throws IOException {
        openWindow("Dashboard.fxml");
    }

    @FXML
    public void openAvailability() throws IOException {
        openWindow("Availability.fxml");
    }

    @FXML
    public void openAddingNewPet() throws IOException {
        openWindow("AddingNewPet.fxml");
    }

    @FXML
    public void displayPet() {
        String idString = PetID.getText();
        int petID;

        try {
            petID = Integer.parseInt(idString);

            String db_emer = "kitcats";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_emer, "root", "Via2023+4");

            String query = "SELECT breed, description FROM pets WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, petID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String breed = rs.getString("breed");
                String description = rs.getString("description");

                foundPet.setText("Breed: " + breed + "\nDescription: " + description);
                
            } else {
                foundPet.setText("Pet with ID " + petID + " cannot be found in the system :(");

            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (NumberFormatException e) {
            foundPet.setText("Error: Invalid pet ID format!");
        } catch (SQLException e) {
            foundPet.setText("Error: Database error!");
            e.printStackTrace();
        }
    }

    @FXML
    public void checkAvailability() {
        String idString = PetID.getText();
        int petID;

        try {
            petID = Integer.parseInt(idString);

            String db_emer = "kitcats";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_emer, "root", "Via2023+4");

            String query = "SELECT * FROM pets WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, petID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                foundPet.setText("This pet is not adopted :(");
                cat.setImage(new Image(getClass().getResourceAsStream("moon.jpg")));
            } else {
                foundPet.setText("This pet is adopted :)");
                cat.setImage(new Image(getClass().getResourceAsStream("cosmo.jpg")));
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (NumberFormatException e) {
            foundPet.setText("Error: Invalid pet ID format!");
        } catch (SQLException e) {
            foundPet.setText("Error: Database error!");
            e.printStackTrace();
        }
    }
}
