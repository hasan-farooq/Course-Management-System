
package CRS.controllers;

import CRS.classes.Admin;
import CRS.classes.Course;
import CRS.classes.Section;
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSectionViewController extends Application implements Initializable {

    @FXML Stage stage_1;
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML Scene scene_1;
    @FXML TextField textField_1;
    @FXML TextField textField_2;
    @FXML ComboBox comboBox_1;
    @FXML ComboBox comboBox_2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage_1 = new Stage();
        stage_1 = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/AddSectionView.fxml"));
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
            int seats = 0;
            String capacity = textField_2.getText();
            String course = (String) comboBox_1.getValue();
            String teacher = (String) comboBox_2.getValue();
            seats = Integer.parseInt(capacity);
            Section section = new Section(name, course, teacher, seats);
            Admin new_admin = new Admin();
            if (section.save_in_db()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Section Added");
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
        String[] teachers;
        String[] courses;
        String query_1 = "select * from teacher";
        String query_2 = "select * from course";
        Conn con = new Conn();
        try {
            ResultSet result = con.s.executeQuery(query_1);
            ResultSetMetaData rsmetadata = result.getMetaData();
            int cols = rsmetadata.getColumnCount();

            teachers = new String[cols];
            int j = 0;
            while (result.next()) {
                teachers[j] = result.getString("name");
                j++;
            }

            courses = new String[cols];
            result = con.s.executeQuery(query_2);
            rsmetadata = result.getMetaData();
            cols = rsmetadata.getColumnCount();
            j = 0;
            while (result.next()) {
                courses[j] = result.getString("name");
                j++;
            }
//        comboBox_1.setStyle("-fx-text-inner-color: white");
        ObservableList<String> list = FXCollections.observableArrayList(courses);
        comboBox_1.setItems(list);
        list = FXCollections.observableArrayList(teachers);
        comboBox_2.setItems(list);
        }catch(Exception e){}

    }
}
