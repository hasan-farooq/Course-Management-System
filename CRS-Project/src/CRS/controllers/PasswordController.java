
package CRS.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.ResourceBundle;

public class PasswordController {
    @FXML private Button button_1;
    @FXML PasswordField passwordField_1;
    @FXML PasswordField passwordField_2;
    @FXML ComboBox<String> comboBox_1;
    String user, username;

    public void initialize(String user, String username){
        this.user = user;
        this.username = username;
    }

    public void handler(ActionEvent event){
        String old_password = passwordField_1.getText();
        String new_password = passwordField_2.getText();
        String query = "select * from login where user = '"+user+"' and username = '"+username+"' "
                + "and password = '"+old_password+"'";
        Conn c = new Conn();

        try{
            ResultSet result = c.s.executeQuery(query);
            if(event.getSource() == button_1) {
                if (result.next()) {
                    String query_1 = "update login set password = '"+new_password+"' where "
                            + "user = '"+user+"' and username = '"+username+"' "
                            + "and password = '"+old_password+"'";
                    Conn con = new Conn();
                    con.s.executeUpdate(query_1);
                    AlertBox.alert("Password Changed");
                } else { AlertBox.alert("Invalid Credentials"); }
            }
        }
        catch(Exception e){AlertBox.alert("Error");}
        Stage stage = (Stage) button_1.getScene().getWindow();
        stage.close();
    }
}
