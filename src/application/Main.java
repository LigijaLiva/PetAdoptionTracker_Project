package application;

// Import statements for JavaFX classes and utilities.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class for the Pet Adoption Tracker application.
 * This class serves as the entry point for the JavaFX application.
 */
public class Main extends Application {

    /**
     * The main entry point for JavaFX applications. 
     * Initializes and displays the primary stage with the user interface.
     * 
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main FXML file, which defines the UI layout.
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

            // Create a Scene using the loaded FXML layout and apply a CSS stylesheet.
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Set the stage title and icon, then display it.
            setStageIconAndTitle(primaryStage, "Pet Adoption Tracker", "cat2.jpg");
            primaryStage.setScene(scene);

            // Show the primary stage (application window).
            primaryStage.show();
        } catch (Exception e) {
            // Print the stack trace in case of an exception (e.g., file not found).
            e.printStackTrace();
        }
    }

    /**
     * Helper method to set the icon and title for a given stage.
     * 
     * @param stage    The stage for which the title and icon will be set.
     * @param title    The title to be displayed on the stage window.
     * @param iconPath The path to the icon image resource.
     */
    public void setStageIconAndTitle(Stage stage, String title, String iconPath) {
        // Load the icon image from the resource path and set it to the stage.
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        stage.getIcons().add(icon);

        // Set the title of the stage.
        stage.setTitle(title);
    }

    /**
     * The main method, serving as the application's entry point.
     * This method launches the JavaFX application.
     * 
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application.
        launch(args);
    }
}
