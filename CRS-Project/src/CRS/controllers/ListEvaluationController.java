
package CRS.controllers;

import CRS.classes.Registration;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ListEvaluationController{

    public class Object{
        private CheckBox select;
        private String eval;
        private String num;
        private String weight, marks;
        public Object(String eval,String n, String marks, String weight){
            this.select = new CheckBox();
            this.eval = eval;
            this.num = n;
            this.marks = marks;
            this.weight = weight;
        }
        public CheckBox getSelect(){return select;}
        public String getEval(){return eval;}
        public String getNum(){return num;}
        public String getMarks(){return marks;}
        public  String getWeight(){return  weight;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Object> table;
    String course;
    @FXML TableColumn<Object,CheckBox> select;
    @FXML TableColumn<Object, String> eval;
    @FXML TableColumn<Object, String> num;
    @FXML TableColumn<Object, String> mark;
    @FXML TableColumn<Object, String> weight;
    String section;

    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;


    public void go_back(){
        Scene scene = button_2.getScene();
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/TeacherView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Teacher View");
            ((Stage) stage).setScene(new Scene(root, 642, 392));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void populate_table(String course, String section) {
        Conn con = new Conn();
        this.section = section;
        this.course = course;
        String query = "select * from evaluation where section like '"+section+"' " +
                " and course like '"+course+"'";
        try {
            int rand = 0;
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                rand++;
                objects.add(new Object(
                        result.getString("type"),(""+rand),result.getString("marks"),
                        result.getString("weightage")));
                rows++;
            }
        } catch (Exception e) {
            System.out.println("Couldn't load section");
        }

        eval.setCellValueFactory(new PropertyValueFactory<Object,String>("eval"));
        num.setCellValueFactory(new PropertyValueFactory<Object,String>("num"));
        mark.setCellValueFactory(new PropertyValueFactory<Object,String>("marks"));
        weight.setCellValueFactory(new PropertyValueFactory<Object,String>("weight"));
        select.setCellValueFactory(new PropertyValueFactory<Object,CheckBox>("select"));
        table.setItems(objects);
        System.out.println("Rows : " + rows);
    }

    public void handler(ActionEvent event) throws IOException {
        if(event.getSource() == button_2) {
            int count = 0;
            String total = null;
            String weight = null;
            for(int i = 0; i < objects.size(); i++){
                if(objects.get(i).getSelect().isSelected()){
                    count++;
                    total = objects.get(i).getMarks();
                    weight = objects.get(i).getWeight();
                }
            }
            if (count == 1){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/MarkEvaluationView.fxml"));
                Parent root = fxmlLoader.load();
                MarkEvaluationController obj = fxmlLoader.getController();
                obj.populate_table(this.course, this.section,Integer.parseInt(total),Integer.parseInt(weight));
                Scene scene = button_1.getScene();
                Stage stage = (Stage) button_1.getScene().getWindow();
                stage.setTitle("Teacher View");
                Scene scene_1 = new Scene(root, 642, 392);
                stage.setScene(scene_1);
                stage.show();
            }
            else{
                System.out.println("Error");
            }
        }
        else if (event.getSource()==button_3) {
            go_back();
        }
    }
}

