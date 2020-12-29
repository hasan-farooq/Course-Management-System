
package CRS.controllers;

import CRS.classes.Registration;
import CRS.classes.Teacher;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TakeAttendanceViewController{

    public class Object{
        private CheckBox select;
        private String student;
        public Object(String name){
            this.select = new CheckBox();
            student = name;
        }
        public CheckBox getSelect(){return select;}
        public String getStudent(){return student;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Object> table;
    @FXML TableColumn<Object,String> student;
    @FXML TableColumn<Object,CheckBox> select;

    String course_1;
    String section_1;
    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/TakeAttendanceView.fxml"));
//        primaryStage.setTitle("Teacher View");
//        Scene scene_1 = new Scene(root, 642, 392);
//        primaryStage.setScene(scene_1);
//        primaryStage.show();
//    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Conn con = new Conn();
//        String name = "noob";
//        String query = "select * from registration where section like '"+0+"' " +
//                " and course like '"+0+"'";
//        try {
//            ResultSet result = con.s.executeQuery(query);
//            while(result.next()){
//                objects.add(new Object(result.getString("student")));
//                rows++;
//            }
//        } catch (Exception e) {
//            System.out.println("Couldn't load section");
//        }
//
//        student.setCellValueFactory(new PropertyValueFactory<Object,String>("student"));
//        select.setCellValueFactory(new PropertyValueFactory<Object,CheckBox>("select"));
//
//        table.setItems(objects);
//        System.out.println("Rows : " + rows);
////        System.out.println(objects_2.get(rows-1).add.isSelected());
//    }

    public void populate_table(String course, String section) {
        Conn con = new Conn();
        course_1 = course;
        section_1 = section;
        String name = "noob";
        String query = "select * from registration where section like '"+section+"' " +
                " and course like '"+course+"'";
        try {
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                objects.add(new Object(result.getString("student")));
                rows++;
            }
        } catch (Exception e) {
            System.out.println("Couldn't load section");
        }

        student.setCellValueFactory(new PropertyValueFactory<Object,String>("student"));
        select.setCellValueFactory(new PropertyValueFactory<Object,CheckBox>("select"));

        table.setItems(objects);
        System.out.println("Rows : " + rows);
    }
    public void go_back(){
        Scene scene = button_2.getScene();
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/TeacherView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Student View");
            ((Stage) stage).setScene(new Scene(root, 642, 392));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2) {
            ArrayList<String> students = new ArrayList<String>();
            ArrayList<Boolean> status = new ArrayList<Boolean>();
            for(int i = 0; i < objects.size(); i++){
                students.add(objects.get(i).getStudent());
                if(objects.get(i).select.isSelected()) {
                    status.add(true);
                }
                else{
                    status.add(false);
                }
            }

            Teacher.mark_attendance(students,status,course_1,section_1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Done");
            alert.showAndWait();
            go_back();
        }
        else if(event.getSource() == button_3) {
            go_back();
        }
    }
}
