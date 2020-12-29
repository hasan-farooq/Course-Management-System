
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

public class TeacherViewController implements Initializable {

    private final Boolean flag = true;
    public class Object{
        private final CheckBox select;
        private final String course;
        private final String section;
        public Object(String course,String sec){
            this.select = new CheckBox();
            this.course = course;
            this.section = sec;
        }
        public CheckBox getSelect(){return select;}
        public String getCourse(){return course;}
        public String getSection(){return section;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Object> table;
    @FXML TableColumn<Object,String> course;
    @FXML TableColumn<Object,CheckBox> select;
    @FXML TableColumn<Object,String> section;
    @FXML MenuBar menuBar;
    @FXML Menu menu1;
    @FXML Menu menu2;
    @FXML MenuItem item1;
    @FXML MenuItem item2;

    String username;
    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;

    //    @Override
//    public void start(Stage primaryStage) throws Exception {
//        if(flag) {
//            Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/TeacherView.fxml"));
//            primaryStage.setTitle("Teacher View");
//            Scene scene_1 = new Scene(root, 642, 392);
//            primaryStage.setScene(scene_1);
//            primaryStage.show();
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conn con = new Conn();
        Registration registration;
        File file = new File("teacher_login.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        username = reader.nextLine();
        String temp = username;

        if(temp.length() != 0){
            temp = temp.substring(0, 1).toUpperCase() + temp.substring(1)+ "'s ";
        }
        menu1.setText(temp+"Profile");

        String query = "select * from section where teacher like '"+username+"'";
        try {
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                objects.add(new Object(
                        result.getString("course"),result.getString("name")));
                rows++;
            }
        } catch (Exception e) {
            System.out.println("Couldn't load section");
        }

        section.setCellValueFactory(new PropertyValueFactory<Object,String>("section"));
        course.setCellValueFactory(new PropertyValueFactory<Object,String>("course"));
        select.setCellValueFactory(new PropertyValueFactory<Object,CheckBox>("select"));
        table.setItems(objects);
        System.out.println("Rows : " + rows);
    }

    public void handler(ActionEvent event) throws IOException {
        if(event.getSource() == button_2) {
            String course_s = null, section_s = null;
            int count = 0;
            for(int i = 0; i < objects.size(); i++){
                if(objects.get(i).getSelect().isSelected()){
                    course_s = objects.get(i).getCourse();
                    section_s = objects.get(i).getSection();
                    count++;
                }
            }
            if (count == 1){

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/TakeAttendanceView.fxml"));
                Parent root = fxmlLoader.load();
                TakeAttendanceViewController obj = fxmlLoader.getController();
                obj.populate_table(course_s, section_s);
                Scene scene = button_1.getScene();
                Stage stage = (Stage) button_1.getScene().getWindow();
                stage.setTitle("Teacher View");
                Scene scene_1 = new Scene(root, 642, 392);
                stage.setScene(scene_1);
                stage.show();
            }
            else{
                AlertBox.alert("Error");
            }
        }
        else if (event.getSource()==button_3) {
            String course_s = null, section_s = null;
            int count = 0;
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).getSelect().isSelected()) {
                    course_s = objects.get(i).getCourse();
                    section_s = objects.get(i).getSection();
                    count++;
                }
            }
            if (count == 1) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/EvaluationView.fxml"));
                Parent root = fxmlLoader.load();
                EvaluationController obj = fxmlLoader.getController();
                obj.initialize(course_s, section_s);
                Scene scene = button_1.getScene();
                Stage stage = (Stage) button_1.getScene().getWindow();
                stage.setTitle("Teacher View");
                Scene scene_1 = new Scene(root, 515, 274);
                stage.setScene(scene_1);
                stage.show();
            }
            else{
                AlertBox.alert("Error");
            }
        }
        else if(event.getSource() == item1){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/PasswordView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage new_stage = new Stage();
            PasswordController obj = fxmlLoader.getController();
            obj.initialize("Teacher",username);
            ((Stage) new_stage).setTitle("Teacher View");
            ((Stage) new_stage).setScene(new Scene(root, 308, 266));
            ((Stage) new_stage).show();
        }
        else if(event.getSource() == item2){
            AlertBox.alert("Good Bye !");
            System.exit(0);
        }
    }
}
