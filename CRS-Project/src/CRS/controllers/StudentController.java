
package CRS.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StudentController implements Initializable {

    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML Button button_4;
    @FXML MenuBar menuBar;
    @FXML Menu menu1;
    @FXML Menu menu2;
    @FXML MenuItem item1;
    @FXML MenuItem item2;
    String username;

    public void handler(ActionEvent event) throws IOException {
        if(event.getSource() == button_1) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/RegisterCourseView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Admin View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == item1){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/PasswordView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage new_stage = new Stage();
            PasswordController obj = fxmlLoader.getController();
            obj.initialize("Student",username);
            ((Stage) new_stage).setTitle("Student View");
            ((Stage) new_stage).setScene(new Scene(root, 308, 266));
            ((Stage) new_stage).show();
        }
        else if(event.getSource() == item2){
            AlertBox.alert("Good Bye !");
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("student_login.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.username = reader.nextLine();
        reader.close();
        String temp = username;

        if(temp.length() != 0){
            temp = temp.substring(0, 1).toUpperCase() + temp.substring(1)+ "'s ";
        }
        menu1.setText(temp+"Profile");
    }
}
