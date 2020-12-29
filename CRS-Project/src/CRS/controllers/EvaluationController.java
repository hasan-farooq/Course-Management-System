
package CRS.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class EvaluationController {

    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;

    String course_1,section_1;

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
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/EvaluationView.fxml"));
//        primaryStage.setTitle("Admin View");
//        primaryStage.setScene(new Scene(root, 515, 274));
//        primaryStage.show();
//    }

    public void initialize(String course, String section) {
        course_1 = course;
        section_1 = section;
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_1) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AddEvaluationView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                AddEvaluationViewController obj = fxmlLoader.getController();
                obj.initialize(course_1, section_1);
                ((Stage) stage).setTitle("Teacher View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(event.getSource() == button_2) {
            Scene scene = button_2.getScene();
            Stage stage = (Stage) button_2.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/ListEvaluationView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Teacher View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
                ListEvaluationController obj = fxmlLoader.getController();
                obj.populate_table(course_1, section_1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(event.getSource() == button_3) {
            Scene scene = button_1.getScene();
            Stage stage = (Stage) button_1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AssignGradeView.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                ((Stage) stage).setTitle("Teacher View");
                ((Stage) stage).setScene(new Scene(root, 642, 392));
                ((Stage) stage).show();
                AssignGradeController obj = fxmlLoader.getController();
                obj.populate_table(course_1, section_1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
