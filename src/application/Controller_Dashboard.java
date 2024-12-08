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

    static HashMap<Integer, Register> register = new HashMap<>();
    
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
    
    public void initialize() throws SQLException {
    	col_id.setCellValueFactory(new PropertyValueFactory<pets, Integer>("id"));
    	col_breed.setCellValueFactory(new PropertyValueFactory<pets, String>("breed"));
    	col_description.setCellValueFactory(new PropertyValueFactory<pets, String>("description"));
    	addinformationin();
    }
    public void addinformationin() throws SQLException {
        ObservableList<pets> listofclients = FXCollections.observableArrayList();    	
    	DBConnection object = new DBConnection();
    	Connection objectConnection = object.connect();
    	Statement state = objectConnection.createStatement();
    	ResultSet result = state.executeQuery("select * from pets");
        while(result.next()) {
        	listofclients.add(new pets(result.getInt("id"), result.getString("breed"), result.getString("description")));
     }
        table_pets.setItems(listofclients);    	
    }
    public void Add() throws SQLException {
        try {
            if (txtBreed.getText().isEmpty() || txtDesc.getText().isEmpty()) {
                txt.setText("Please fill in all fields before adding a pet.");
                return;
            }
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            PreparedStatement addquery = objectConnection.prepareStatement("insert into pets (breed, description) values (?, ?)");
            addquery.setString(1, txtBreed.getText());
            addquery.setString(2, txtDesc.getText());
            addquery.execute(); 
            addinformationin(); 
            Clean();
            txt.setText("Pet added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            txt.setText("Error occurred while adding pet!");
        }
    }
    public void Delete() {
        try {
            if (!isTableSelected()) {
                return;
            }
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            int id = table_pets.getSelectionModel().getSelectedItem().getId();
            PreparedStatement state = objectConnection.prepareStatement("delete from pets where id = ?");
            state.setInt(1, id);
            state.executeUpdate();
            addinformationin();
            Clean();
        } catch (SQLException e) {
            txt.setText("Couldn't remove pet!");
        }
    }
    public void Update() {
        try {
            if (!isTableSelected()) {
                return;
            }
            DBConnection object = new DBConnection();
            Connection objectConnection = object.connect();
            
            PreparedStatement state = objectConnection.prepareStatement("update pets set breed=?, description=? where id=?");

            state.setString(1, txtBreed.getText());
            state.setString(2, txtDesc.getText());
            state.setInt(3, table_pets.getSelectionModel().getSelectedItem().getId());

            state.executeUpdate();
            addinformationin();
            Clean();            
        } catch (SQLException e) {
            txt.setText("Couldn't update pet!");
        }
    }
    public void Clean() {
    	txtBreed.setText(null);
    	txtDesc.setText(null);
    }
    public void UpdateTextFields() {
        pets pets = table_pets.getSelectionModel().getSelectedItem();
        txtBreed.setText(String.valueOf(pets.getBreed()));
        txtDesc.setText(String.valueOf(pets.getDescription())); 
    }
    private boolean isTableSelected() {
        if (table_pets.getSelectionModel().getSelectedItem() == null) {
            txt.setText("Please select a pet from the table.");
            return false;
        }
        return true;
    }
}
