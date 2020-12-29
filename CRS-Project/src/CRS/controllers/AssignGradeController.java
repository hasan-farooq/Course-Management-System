
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

public class AssignGradeController{

    public class Object{
        private String marks;
        private String grade;
        private String student;
        public Object(String name, String marks){
            this.grade = null;
            this.marks = marks;
            student = name;
        }
        public String getGrade(){return grade;}
        public String getMarks(){return marks;}
        public String getStudent(){return student;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Object> table;
    @FXML TableColumn<Object,String> student;
    @FXML TableColumn<Object, String> marks;
    @FXML TableColumn<Object, String> grade;

    String course_1;
    String section_1;
    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;


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
                objects.add(new Object(result.getString("student"),result.getString("marks")));
                rows++;
            }
        } catch (Exception e) {
            System.out.println("Couldn't load section");
        }

        student.setCellValueFactory(new PropertyValueFactory<Object,String>("student"));
        marks.setCellValueFactory(new PropertyValueFactory<Object,String>("marks"));
        grade.setCellValueFactory(new PropertyValueFactory<Object,String>("grade"));
        table.setItems(objects);

        table.setEditable(true);
        grade.setCellFactory(TextFieldTableCell.forTableColumn());
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

    public void on_edit(TableColumn.CellEditEvent<Object,String> e_grade){
        Object obj = table.getSelectionModel().getSelectedItem();
        obj.grade = e_grade.getNewValue();
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2) {
            ArrayList<String> students = new ArrayList<String>();
            ArrayList<String> grades = new ArrayList<String>();
            for(int i = 0; i < objects.size(); i++) {
                grades.add(objects.get(i).getGrade());
                students.add(objects.get(i).getStudent());
                System.out.println("Grade : " + objects.get(i).getGrade());
            }

            Teacher.assign_grades(students,grades,course_1,section_1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Grades Assigned");
            alert.showAndWait();
            go_back();
        }
        else if(event.getSource() == button_3) {
            go_back();
        }
    }
}
