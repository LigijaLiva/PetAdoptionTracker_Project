package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class Controller_Dashboard {

    // Katra reģistra glabāšanai tiek izmantots HashMap
    static HashMap<Integer, Register> register = new HashMap<>();
    
    // FXML anotācijas - sasaista Java objektus ar GUI komponentiem
    @FXML
    private TableColumn<pets, String> col_breed;

    @FXML
    private TableColumn<pets, Integer> col_id;

    @FXML
    private TableColumn<pets, String> col_description;

    @FXML
    private TableView<pets> table_pets;
    
    @FXML
    private TextField txtDesc;
    
    @FXML
    private TextField txtBreed;
    
    @FXML
    private Text txt;
    
    // Sākotnējās inicializācijas metode, tiek izsaukta, kad GUI ir ielādēts
    public void initialize() throws SQLException {
        // Sasaista tabulas kolonnas ar "pets" klases īpašībām
        col_id.setCellValueFactory(new PropertyValueFactory<pets, Integer>("id"));
        col_breed.setCellValueFactory(new PropertyValueFactory<pets, String>("breed"));
        col_description.setCellValueFactory(new PropertyValueFactory<pets, String>("description"));
        
        // Aizpilda tabulu ar datiem no datubāzes
        addinformationin();
    }

    // Metode datu ielādei no datubāzes un pievienošanai tabulai
    public void addinformationin() throws SQLException {
        // Izveido sarakstu, kas atbalsta JavaFX tabulas sasaisti
        ObservableList<pets> listofclients = FXCollections.observableArrayList();    	
    	
        // Izveido datubāzes savienojumu un izpilda SQL vaicājumu
        DBConnection object = new DBConnection();
        Connection objectConnection = object.connect();
        Statement state = objectConnection.createStatement();
        ResultSet result = state.executeQuery("select * from pets");
        
        // Apstrādā rezultātus un pievieno tos sarakstam
        while(result.next()) {
            listofclients.add(new pets(result.getInt("id"), result.getString("breed"), result.getString("description")));
        }
        
        // Atjauno tabulas datus
        table_pets.setItems(listofclients);    	
    }

    // Metode jaunu ierakstu pievienošanai datubāzei
    public void Add() throws SQLException {
        try {
            // Pārbauda, vai ievades lauki ir aizpildīti
            if (txtBreed.getText().isEmpty() || txtDesc.getText().isEmpty()) {
                txt.setText("Please fill in all fields before adding a pet.");
                return;
            }

            // Izveido datubāzes savienojumu un sagatavoto vaicājumu
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            PreparedStatement addquery = objectConnection.prepareStatement("insert into pets (breed, description) values (?, ?)");
            
            // Pievieno vērtības no teksta laukiem
            addquery.setString(1, txtBreed.getText());
            addquery.setString(2, txtDesc.getText());
            addquery.execute(); 

            // Atjauno tabulas datus un notīra ievades laukus
            addinformationin(); 
            Clean();
            txt.setText("Pet added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            txt.setText("Error occurred while adding pet!");
        }
    }

    // Metode ierakstu dzēšanai no datubāzes
    public void Delete() {
        try {
            // Pārbauda, vai tabulā ir izvēlēts ieraksts
            if (!isTableSelected()) {
                return;
            }

            // Izveido datubāzes savienojumu un sagatavoto dzēšanas vaicājumu
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            int id = table_pets.getSelectionModel().getSelectedItem().getId();
            PreparedStatement state = objectConnection.prepareStatement("delete from pets where id = ?");
            state.setInt(1, id);
            state.executeUpdate();

            // Atjauno tabulas datus un notīra ievades laukus
            addinformationin();
            Clean();
        } catch (SQLException e) {
            txt.setText("Couldn't remove pet!");
        }
    }

    // Metode ierakstu atjaunināšanai datubāzē
    public void Update() {
        try {
            // Pārbauda, vai tabulā ir izvēlēts ieraksts
            if (!isTableSelected()) {
                return;
            }

            // Izveido datubāzes savienojumu un sagatavoto atjaunināšanas vaicājumu
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            PreparedStatement state = objectConnection.prepareStatement("update pets set breed=?, description=? where id=?");

            // Pievieno vērtības no teksta laukiem un ieraksta ID
            state.setString(1, txtBreed.getText());
            state.setString(2, txtDesc.getText());
            state.setInt(3, table_pets.getSelectionModel().getSelectedItem().getId());
            state.executeUpdate();

            // Atjauno tabulas datus un notīra ievades laukus
            addinformationin();
            Clean();            
        } catch (SQLException e) {
            txt.setText("Couldn't update pet!");
        }
    }

    // Palīgmetode ievades lauku notīrīšanai
    public void Clean() {
        txtBreed.setText(null);
        txtDesc.setText(null);
    }

    // Metode teksta lauku aizpildīšanai ar tabulas izvēlētā ieraksta datiem
    public void UpdateTextFields() {
        pets pets = table_pets.getSelectionModel().getSelectedItem();
        txtBreed.setText(String.valueOf(pets.getBreed()));
        txtDesc.setText(String.valueOf(pets.getDescription())); 
    }

    // Palīgmetode, lai pārbaudītu, vai tabulā ir izvēlēts ieraksts
    private boolean isTableSelected() {
        if (table_pets.getSelectionModel().getSelectedItem() == null) {
            txt.setText("Please select a pet from the table.");
            return false;
        }
        return true;
    }
}
