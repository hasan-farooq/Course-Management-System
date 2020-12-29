
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

public class LoginController implements Initializable {
    @FXML private Button button_1;
    @FXML TextField textField_1;
    @FXML PasswordField passwordField_1;
    @FXML ComboBox<String> comboBox_1;

    public void go_back(){
        Scene scene = button_1.getScene();
        Stage stage = (Stage) button_1.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/Login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Sign In");
            ((Stage) stage).setScene(new Scene(root, 300, 275));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void login_button(ActionEvent event){
        String user = comboBox_1.getValue();
        String username = textField_1.getText();
        String password = passwordField_1.getText();
        System.out.println("User : " + user);
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        String query = "select * from login where user = '"+user+"' and username = '"+username+"' "
                + "and password = '"+password+"'";
        Conn c = new Conn();
        try{
            ResultSet result = c.s.executeQuery(query);
            if (user == "Admin" && result.next() ){
                try {
                    FileWriter writer = new FileWriter("admin_login.txt");
                    writer.write(username);
                    writer.close();
                    Stage stage_1 = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/CRS/views/AdminView.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage_1.setTitle("Admin View");
                    Stage stage = (Stage) button_1.getScene().getWindow();
                    stage.close();

                    stage_1.setScene(new Scene(root, 642, 392));
                    stage_1.show();

                } catch (IOException ex) {
                    System.out.println("Couldn't Write.");
                }
            }
            else if (user == "Teacher" && result.next() ){
                try {
                    FileWriter writer = new FileWriter("teacher_login.txt");
                    writer.write(username);
                    writer.close();
                    Stage stage_1 = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/CRS/views/TeacherView.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage_1.setTitle("Teacher View");
                    stage_1.setScene(new Scene(root, 642, 392));
                    Stage stage = (Stage) button_1.getScene().getWindow();
                    stage.close();
                    stage_1.show();
                } catch (IOException ex) {
                    System.out.println("Couldn't Write.");
                }
            }
            else if (user == "Student" && result.next() ){
                try {
                    FileWriter writer = new FileWriter("student_login.txt");
                    writer.write(username);
                    writer.close();
                    Stage stage_1 = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/CRS/views/StudentView.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage_1.setTitle("Student View");
                    stage_1.setScene(new Scene(root, 642, 392));
                    Stage stage = (Stage) button_1.getScene().getWindow();
                    stage.close();
                    stage_1.show();
                } catch (IOException ex) {
                    System.out.println("Couldn't Write.");
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Credentials");
                alert.showAndWait();
                go_back();
            }
        }
        catch(Exception e){System.out.println("Error");}
//        Stage stage = (Stage) button_1.getScene().getWindow();
//        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] users = {"Teacher", "Student", "Admin"};
        comboBox_1.setStyle("-fx-text-inner-color: white");
        ObservableList<String> list = FXCollections.observableArrayList(users);
        comboBox_1.setItems(list);
    }
}
