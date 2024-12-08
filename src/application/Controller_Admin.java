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

    // FXML anotācijas, lai sasaistītu šos elementus ar FXML failu
    @FXML
    private Text txt; // Parāda paziņojumus, piemēram, kļūdas tekstus lietotājam
    
    @FXML
    private TextField Password; // Teksta lauks, kur lietotājs ievada paroli

    @FXML
    private TextField Username; // Teksta lauks, kur lietotājs ievada lietotājvārdu
	
    // Login metode, kas tiek izsaukta, kad lietotājs nospiež pogu pieslēgties
    @FXML
    public void Login() {
        // Objekti datu bāzes savienojumam un vaicājumu izpildei
        DBConnection object = new DBConnection();
        Connection objectConnection = null;
        PreparedStatement loginQuery = null;
        ResultSet resultSet = null;

        try {
            // Izveido datu bāzes savienojumu
            objectConnection = object.connect();
            
            // SQL vaicājums, lai pārbaudītu, vai lietotājvārds un parole ir pareizi
            String sql = "select * from admins where username = ? and password = ?";
            loginQuery = objectConnection.prepareStatement(sql);
            
            // Uzstāda lietotāja ievadītās vērtības SQL vaicājumā
            loginQuery.setString(1, Username.getText());
            loginQuery.setString(2, Password.getText());
            
            // Izpilda SQL vaicājumu un iegūst rezultātu
            resultSet = loginQuery.executeQuery();

            // Pārbauda, vai rezultātu kopa nav tukša (tas nozīmē, ka dati ir pareizi)
            if (resultSet.next()) {
                // Ielādē galveno administrācijas skatu, ja pieteikšanās ir veiksmīga
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAdmin.fxml"));
                Parent root = loader.load();

                // Uzstāda jauno skatu un parāda to pašreizējā logā
                Scene scene = new Scene(root);
                Stage stage = (Stage) Username.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                // Parāda kļūdas paziņojumu, ja lietotājvārds vai parole ir nepareizi
                txt.setText("Wrong email or password!");
            }

        } catch (SQLException e) {
            // Apstrādā datu bāzes savienojuma vai vaicājuma kļūdas
            e.printStackTrace();
            txt.setText("Failed to log in.");
        } catch (IOException e) {
            // Apstrādā FXML ielādes kļūdas
            txt.setText("Failed to load fxml.");
        } finally {
            // Pārliecinās, ka visi resursi tiek pareizi atbrīvoti
            try { 
            	if (resultSet != null) resultSet.close(); // Aizver rezultātu kopu
            	if (loginQuery != null) loginQuery.close(); // Aizver vaicājumu
            	if (objectConnection != null) objectConnection.close(); // Aizver datu bāzes savienojumu
            } catch (SQLException e) {
                e.printStackTrace(); // Izdrukā kļūdu, ja resursu atbrīvošana neizdodas
            }
        }
    }
}
