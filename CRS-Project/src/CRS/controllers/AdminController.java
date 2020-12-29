
package CRS.controllers;

import CRS.classes.Admin;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileWriter;
import java.io.IOException;

public class AdminController {

    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML Button button_4;
    @FXML Button button_5;
    @FXML Button button_6;
    @FXML Button button_7;

    public void go_back(){
        Scene scene = button_2.getScene();
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AdminView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Admin View");
            ((Stage) stage).setScene(new Scene(root, 642, 392));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/AdminView.fxml"));
//        primaryStage.setTitle("Admin View");
//        primaryStage.setScene(new Scene(root, 642, 392));
//        primaryStage.show();
//    }


    public void handler(ActionEvent event){
        if(event.getSource() == button_1) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AddTeacherView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Admin View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == button_2) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AddCourseView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Admin View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == button_3) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AddStudentView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Admin View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(event.getSource() == button_4) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AddSectionView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Admin View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(event.getSource() == button_5){
            Admin obj = new Admin();
            obj.open_registration();
        }
        else if(event.getSource() == button_6){
            Admin obj = new Admin();
            obj.close_registration();
        }
        else if(event.getSource() == button_7){
            AlertBox.alert("Good Bye !");
            System.exit(0);
        }
    }

}
