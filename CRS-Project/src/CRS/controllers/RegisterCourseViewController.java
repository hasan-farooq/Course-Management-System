
package CRS.controllers;

import CRS.classes.Registration;
import CRS.classes.Section;
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

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RegisterCourseViewController extends Application implements Initializable {

    public class checkbox{
        private CheckBox add;
        private CheckBox drop;
        public checkbox(){
            this.add = new CheckBox();
            this.drop = new CheckBox();
        }
        public CheckBox getAdd(){return add;}
        public CheckBox getDrop(){return drop;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML Button button_3;
    @FXML TableView<Section> table;
    @FXML TableView<checkbox> table_2;
    @FXML TableColumn<Section,String> course;
    @FXML TableColumn<Section,String> teacher;
    @FXML TableColumn<Section,String> section;
    @FXML TableColumn<Section,Integer> seats;
    @FXML TableColumn<checkbox, CheckBox> col_add;
    @FXML TableColumn<checkbox, CheckBox> col_drop;

    ObservableList<Section> objects = FXCollections.observableArrayList();
    ObservableList<checkbox> objects_2 = FXCollections.observableArrayList();
    int rows = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/CRS/views/RegisterCourseView.fxml"));
        primaryStage.setTitle("Student View");
        Scene scene_1 = new Scene(root, 642, 392);
        primaryStage.setScene(scene_1);
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conn con = new Conn();
        String query = "select * from section where seats > 0";
        try {
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                objects.add(new Section(
                        result.getString("name"),result.getString("course"),
                        result.getString("teacher"), result.getInt("seats") ));
                objects_2.add(new checkbox());
                rows++;
            }
        } catch (Exception e) {
            System.out.println("Couldn't load section");
        }

        section.setCellValueFactory(new PropertyValueFactory<Section,String>("name"));
        course.setCellValueFactory(new PropertyValueFactory<Section,String>("course"));
        teacher.setCellValueFactory(new PropertyValueFactory<Section,String>("teacher"));
        seats.setCellValueFactory(new PropertyValueFactory<Section,Integer>("seats"));
        col_add.setCellValueFactory(new PropertyValueFactory<checkbox,CheckBox>("add"));
        col_drop.setCellValueFactory(new PropertyValueFactory<checkbox,CheckBox>("drop"));

        table.setItems(objects);
        table_2.setItems(objects_2);
        System.out.println("Rows : " + rows);
//        System.out.println(objects_2.get(rows-1).add.isSelected());
    }

    public void handler(ActionEvent event){
        if(event.getSource() == button_2) {
            Registration registration;
            File file = new File("student_login.txt");
            Scanner reader = null;
            try {
                reader = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String username = reader.nextLine();
            reader.close();
            for(int i = 0; i < rows; i++){
                if(objects_2.get(i).add.isSelected()){
                    registration = new Registration(username, objects.get(i).getName(),
                            objects.get(i).getCourse());
                    registration.insert_in_db();
                }
                else if(objects_2.get(i).drop.isSelected()){
                    registration = new Registration(username, objects.get(i).getName(),
                            objects.get(i).getCourse());
                    registration.delete_in_db();
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Registered");
            alert.showAndWait();
            Stage stage = (Stage) button_3.getScene().getWindow();
            stage.close();
        }
        else if(event.getSource() == button_3) {
        }
    }
}
