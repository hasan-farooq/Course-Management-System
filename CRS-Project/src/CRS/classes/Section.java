
package CRS.classes;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ResourceBundle;

public class Section {
    private String name, course, teacher;
    private int seats;

    public Section(String name, String course, String teacher, int seats){
        this.name = name;
        this.course = course;
        this.teacher = teacher;
        this.seats =seats;
    }
    public String getName(){return this.name;}
    public String getCourse(){return this.course;}
    public String getTeacher(){return this.teacher;}
    public int getSeats(){return this.seats;}
    public void print(){
        System.out.println("Name : " + name);
        System.out.println("Course : " + course);
        System.out.println("Teacher : " + teacher);
        System.out.println("Seats : " + seats);
    }
    public boolean save_in_db(){
        Conn con = new Conn();
        String query_1 = "insert into section values('"+name+"','"+course+"','"+teacher+"',"
                + "'"+seats+"')";
        try{
            con.s.executeUpdate(query_1);   //executes the query
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding Course");
            return false;
        }
    }

}
