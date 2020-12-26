
package CRS.controllers;

import CRS.classes.Admin;
import CRS.classes.Course;
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

import java.net.URL;
import java.util.ResourceBundle;

public class AddCourseViewController extends Application implements Initializable {

    @FXML Stage stage_1;
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML RadioButton radioButton_1;
    @FXML RadioButton radioButton_2;
    @FXML Scene scene_1;
    @FXML TextField textField_1;
    @FXML TextField textField_2;
    @FXML TextField textField_3;
    @FXML ComboBox comboBox_1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage_1 = new Stage();
        stage_1 = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/AddCourseView.fxml"));
        stage_1.setTitle("Admin View");
        scene_1 = new Scene(root, 642, 392);
        stage_1.setScene(scene_1);
        stage_1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2){
            String name = textField_1.getText();
            int hours = 0;
            String id = textField_2.getText();
            String program = (String) comboBox_1.getValue();
            String gender = "";
            if(radioButton_1.isSelected()){
                hours = 3;
            } else if (radioButton_2.isSelected()) {
                hours = 1;
            }
            Course course = new Course(name, id, program, hours);
            Admin new_admin = new Admin();
            if (course.save_in_db()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Course Added");
                alert.showAndWait();
                Stage stage = (Stage) button_3.getScene().getWindow();
                stage.close();
            }
        }
        else if(event.getSource()==button_3){

            Stage stage = (Stage) button_3.getScene().getWindow();
            stage.close();
//            stage_1.getScene().getWindow();
//            stage_1.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] users = {"CS", "SE", "DS"};
//        comboBox_1.setStyle("-fx-text-inner-color: white");
        ObservableList<String> list = FXCollections.observableArrayList(users);
        comboBox_1.setItems(list);
    }
}
