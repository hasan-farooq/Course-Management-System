
package CRS.controllers;

import CRS.classes.Admin;
import CRS.classes.Course;
import CRS.classes.Evaluation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEvaluationViewController{

    @FXML Stage stage_1;
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
//    @FXML Scene scene_1;
    @FXML TextField textField_1;
    @FXML TextField textField_2;
    @FXML ComboBox comboBox_1;

    String course_1;
    String section_1;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        stage_1 = new Stage();
//        stage_1 = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/AddEvaluationView.fxml"));
//        stage_1.setTitle("Admin View");
//        scene_1 = new Scene(root, 642, 392);
//        stage_1.setScene(scene_1);
//        stage_1.show();
//    }

    public void go_back(){
        Scene scene = button_2.getScene();
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/TeacherView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Admin View");
            ((Stage) stage).setScene(new Scene(root, 642, 392));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        launch(args);
//    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2){
            int marks = Integer.parseInt(textField_1.getText());
            int weightage = Integer.parseInt(textField_2.getText());
            String type = (String) comboBox_1.getValue();
            String gender = "";

            Evaluation evaluation = new Evaluation(type, marks, weightage);

            if (evaluation.save_in_db(course_1,section_1)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Evaluation Added");
                alert.showAndWait();
                go_back();
            }
        }
        else if(event.getSource() == button_3) {
            go_back();
        }
    }

    public void initialize(String course, String section){
        course_1 = course;
        section_1 = section;
        String[] users = {"Quiz", "Assignment", "Exam"};
//        comboBox_1.setStyle("-fx-text-inner-color: white");
        ObservableList<String> list = FXCollections.observableArrayList(users);
        comboBox_1.setItems(list);
    }

}
