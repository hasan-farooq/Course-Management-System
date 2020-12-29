
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MarkEvaluationController{

    public class Object{
        private String marks;
        private String student;
        public Object(String name){
            this.marks = null;
            student = name;
        }
        public String getMarks(){return marks;}
        public String getStudent(){return student;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Object> table;
    @FXML TableColumn<Object,String> student;
    @FXML TableColumn<Object, String> marks;

    String course_1;
    int total,weight;
    String section_1;
    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/MarkEvaluationView.fxml"));
//        primaryStage.setTitle("Teacher View");
//        Scene scene_1 = new Scene(root, 642, 392);
//        primaryStage.setScene(scene_1);
//        primaryStage.show();
//    }

    public void populate_table(String course, String section, int total, int weight) {
        Conn con = new Conn();
        this.total = total;
        this.weight = weight;
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
        marks.setCellValueFactory(new PropertyValueFactory<Object,String>("marks"));
        table.setItems(objects);

        table.setEditable(true);
        marks.setCellFactory(TextFieldTableCell.forTableColumn());
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

    public void on_edit(TableColumn.CellEditEvent<Object,String> e_marks){
        Object obj = table.getSelectionModel().getSelectedItem();
        obj.marks = e_marks.getNewValue();
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2) {
            ArrayList<String> students = new ArrayList<String>();
            ArrayList<Integer> s_marks = new ArrayList<Integer>();
            for(int i = 0; i < objects.size(); i++) {
                int temp = Integer.parseInt(objects.get(i).getMarks());
                s_marks.add(temp);
                students.add(objects.get(i).getStudent());
//                System.out.println("Student : " + objects.get(i).getStudent() + "| Weight : " + weight);
            }

            System.out.println(total+" "+weight);
            Teacher.mark_evaluation(students,s_marks,course_1,section_1,total,weight);

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
