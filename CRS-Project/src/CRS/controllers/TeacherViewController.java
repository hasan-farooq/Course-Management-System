
package CRS.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherViewController extends Application {

    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML Button button_4;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/TeacherView.fxml"));
        primaryStage.setTitle("Teacher View");
        primaryStage.setScene(new Scene(root, 642, 392));
        primaryStage.show();
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_1) {
//            new AddTeacherViewController();
            Stage stage_1 = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRS/views/AddStudentView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage_1.setTitle("Admin View");
            stage_1.setScene(new Scene(root, 642, 392));
            stage_1.show();
        }
        else if(event.getSource() == button_2) {
            Stage stage_1 = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRS/views/AddCourseView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage_1.setTitle("Admin View");
            stage_1.setScene(new Scene(root, 642, 392));
            stage_1.show();
        }
        else if(event.getSource() == button_3) {
            Stage stage_1 = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRS/views/AddStudentView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage_1.setTitle("Admin View");
            stage_1.setScene(new Scene(root, 642, 392));
            stage_1.show();
        }
        else if(event.getSource() == button_4) {
            Stage stage_1 = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/CRS/views/AddSectionView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage_1.setTitle("Admin View");
            stage_1.setScene(new Scene(root, 642, 392));
            stage_1.show();        }
    }

}
