package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);

            setStageIconAndTitle(primaryStage, "Pet Adoption Tracker", "cat2.jpg");

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStageIconAndTitle(Stage stage, String title, String iconPath) {
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        stage.getIcons().add(icon);
        stage.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
