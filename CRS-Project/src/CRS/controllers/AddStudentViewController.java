
package CRS.controllers;

import CRS.classes.Admin;
import CRS.classes.Student;
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

public class AddStudentViewController implements Initializable{

    private @FXML Stage stage_1;
    private @FXML Button button_1;
    private @FXML RadioButton radioButton_2;
    private @FXML Button button_2;
    private @FXML RadioButton radioButton_1;
    private @FXML Scene scene_1;
    private @FXML TextField textField_1;
    private @FXML TextField textField_2;
    private @FXML TextField textField_3;
    private @FXML ComboBox comboBox_1;
    private @FXML Button button_3;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        stage_1 = new Stage();
//        stage_1 = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/AddStudentView.fxml"));
//        stage_1.setTitle("Admin View");
//        scene_1 = new Scene(root, 642, 392);
//        stage_1.setScene(scene_1);
//        stage_1.show();
//    }

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

    public void handler(ActionEvent event){
        if(event.getSource() == button_2) {
            String name = textField_1.getText();
            String contact = textField_3.getText();
            String id = textField_2.getText();
            String program = (String) comboBox_1.getValue();
            String gender = "";
            if (radioButton_1.isSelected()) {
                gender = "Female";
            } else if (radioButton_2.isSelected()) {
                gender = "Male";
            }
            Student new_student = new Student(name, id, gender, program, contact);
            Admin new_admin = new Admin();
            if (new_student.save_in_db() &&
                    new_admin.add_user_db("Student",name,"haha")) {
                //Alert box
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Student Added");
                alert.showAndWait();
                go_back();
            }
        }
        else if(event.getSource() == button_3) {
            go_back();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] users = {"CS", "SE", "DS"};
//        comboBox_1.setStyle("-fx-text-inner-color: white");
        ObservableList<String> list = FXCollections.observableArrayList(users);
        comboBox_1.setItems(list);
        AddStudentViewController n = new AddStudentViewController();
    }
}
