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

    // Static data structure to store registers for the application
    static HashMap<Integer, Register> register = new HashMap<>();

    // FXML UI components, linked to the scene elements in the FXML files
    @FXML private Text txt;
    @FXML private TextField Password;
    @FXML private TextField Username;
    @FXML private Label regSafecheck;
    @FXML private Label foundPet;
    @FXML private TextField Name;
    @FXML private TextField Breed;
    @FXML private TextField Age;
    @FXML private TextField PetID;
    @FXML private ImageView cat;
    @FXML private TableColumn<pets, String> col_breed;
    @FXML private TableColumn<pets, Integer> col_id;
    @FXML private TableColumn<pets, String> col_description;
    @FXML private TableView<pets> table_pets;

    // Observable list for managing pet table data
    ObservableList<pets> listM;

    // Variable to store file name (used elsewhere in the code)
    String nameofthefile;

    /**
     * Opens a new window specified by the given FXML file.
     * @param fxmlFile the FXML file to load
     * @throws IOException if the FXML file cannot be loaded
     */
    private void openWindow(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage newStage = new Stage();
        Scene scene = new Scene(root);

        // Set the stage icon and title for better user experience
        Main mainApp = new Main();
        mainApp.setStageIconAndTitle(newStage, "Pet Adoption Tracker", "cat2.jpg");

        newStage.setScene(scene);
        newStage.show();
    }

    // Event handlers for navigating between different application views
    @FXML public void openLoginPage() throws IOException {
        openWindow("Login.fxml");
    }

    @FXML void openMainAdmin() throws IOException {
        openWindow("MainAdmin.fxml");
    }

    @FXML public void openPetDetails() throws IOException {
        openWindow("PetDetails.fxml");
    }

    @FXML public void openDashboard() throws IOException {
        openWindow("Dashboard.fxml");
    }

    @FXML public void openAvailability() throws IOException {
        openWindow("Availability.fxml");
    }

    @FXML public void openAddingNewPet() throws IOException {
        openWindow("AddingNewPet.fxml");
    }

    /**
     * Displays details of a pet based on the provided ID.
     * Queries the database for pet information and updates the UI accordingly.
     */
    @FXML
    public void displayPet() {
        String idString = PetID.getText();
        int petID;

        try {
            // Parse the pet ID and establish a database connection
            petID = Integer.parseInt(idString);
            String db_emer = "kitcats";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_emer, "root", "Via2023+4");

            // Prepare and execute the SQL query
            String query = "SELECT breed, description FROM pets WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, petID);
            ResultSet rs = pstmt.executeQuery();

            // Display the pet details if found
            if (rs.next()) {
                String breed = rs.getString("breed");
                String description = rs.getString("description");
                foundPet.setText("Breed: " + breed + "\nDescription: " + description);
            } else {
                foundPet.setText("Pet with ID " + petID + " cannot be found in the system :(");
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();

        } catch (NumberFormatException e) {
            // Handle invalid ID format
            foundPet.setText("Error: Invalid pet ID format!");
        } catch (SQLException e) {
            // Handle database connection or query errors
            foundPet.setText("Error: Database error!");
            e.printStackTrace();
        }
    }

    /**
     * Checks if a pet with the given ID is available (not adopted).
     * Updates the UI with availability status and displays a corresponding image.
     */
    @FXML
    public void checkAvailability() {
        String idString = PetID.getText();
        int petID;

        try {
            // Parse the pet ID and establish a database connection
            petID = Integer.parseInt(idString);
            String db_emer = "kitcats";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_emer, "root", "Via2023+4");

            // Prepare and execute the SQL query
            String query = "SELECT * FROM pets WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, petID);
            ResultSet rs = pstmt.executeQuery();

            // Update the UI based on pet availability
            if (rs.next()) {
                foundPet.setText("This pet is not adopted :(");
                cat.setImage(new Image(getClass().getResourceAsStream("moon.jpg")));
            } else {
                foundPet.setText("This pet is adopted :)");
                cat.setImage(new Image(getClass().getResourceAsStream("cosmo.jpg")));
            }

            // Close resources
            rs.close();
            pstmt.close();
            conn.close();

        } catch (NumberFormatException e) {
            // Handle invalid ID format
            foundPet.setText("Error: Invalid pet ID format!");
        } catch (SQLException e) {
            // Handle database connection or query errors
            foundPet.setText("Error: Database error!");
            e.printStackTrace();
        }
    }
}
