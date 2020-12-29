
package CRS.controllers;

import CRS.classes.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSectionViewController implements Initializable {

    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TextField textField_1;
    @FXML TextField textField_2;
    @FXML ComboBox<String> comboBox_1;
    @FXML ComboBox<String> comboBox_2;

    public void go_back(){
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/AdminView.fxml"));
            Parent root = fxmlLoader.load();
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



    public void handler(ActionEvent event) throws SQLException {
        if(event.getSource() == button_2){
            String name = textField_1.getText();
            int seats = 0;
            String capacity = textField_2.getText();
            String course = (String) comboBox_1.getValue();
            String teacher = (String) comboBox_2.getValue();
            seats = Integer.parseInt(capacity);
            Section section = new Section(name, course, teacher, seats);
            if (section.save_in_db()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Section Added");
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
//        String[] teachers;
        ObservableList<String> teachers = FXCollections.observableArrayList();
//        String[] courses;
        ObservableList<String> courses = FXCollections.observableArrayList();
        String query_1 = "select * from teacher";
        String query_2 = "select * from course";
        Conn con = new Conn();
        try {
            ResultSet result = con.s.executeQuery(query_1);
            ResultSetMetaData rsmetadata = result.getMetaData();
            int cols = rsmetadata.getColumnCount();

//            teachers = new String[cols];
            int j = 0;
            while (result.next()) {
                teachers.add(result.getString("name"));
//                teachers[j] = result.getString("name");
//                j++;
            }

//            courses = new String[cols];
            result = con.s.executeQuery(query_2);
            rsmetadata = result.getMetaData();
            cols = rsmetadata.getColumnCount();
            j = 0;
            while (result.next()) {
//                courses[j] = result.getString("name");
                courses.add(result.getString("name"));
//                System.out.println(courses.get(j));
//                j++;
            }
            comboBox_1.setItems(courses);
            comboBox_2.setItems(teachers);
        }catch(Exception e){}

    }
}
