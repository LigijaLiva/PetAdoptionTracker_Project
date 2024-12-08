package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Izveido DatabaseMigrator objektu un migrē datu bāzi
        DatabaseMigrator migrator = new DatabaseMigrator();
        migrator.migrateDatabase();  // Migrācijas izpilde

        // Attēla parādīšana
        ImageView imageView = new ImageView();
        Image image = new Image("/images/cat2.jpg");
        imageView.setImage(image);

        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Pet Adoption Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
