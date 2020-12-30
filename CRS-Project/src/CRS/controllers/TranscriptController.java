
package CRS.controllers;

import CRS.classes.Registration;
import CRS.classes.Student;
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
import javafx.stage.Window;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TranscriptController implements Initializable {

    private final Boolean flag = true;
    public class Object{
        private String marks, course, section, grade;
        public Object(String course, String sec, String marks, String grade){
            this.marks = marks;
            this.grade = grade;
            this.course = course;
            this.section = sec;
        }
        public String getMarks(){return marks;}
        public String getGrade(){return grade;}
        public String getCourse(){return course;}
        public String getSection(){return section;}
    }
    @FXML Button button_1;
    @FXML Button button_2;
    @FXML TableView<Object> table;
    @FXML TableColumn<Object,String> course;
    @FXML TableColumn<Object,String> marks;
    @FXML TableColumn<Object,String> grade;
    @FXML TableColumn<Object,String> section;

    String username;
    ObservableList<Object> objects = FXCollections.observableArrayList();
    int rows = 0;

    public void go_back() {
        Scene scene = button_2.getScene();
        Stage stage = (Stage) button_2.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CRS/views/StudentView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ((Stage) stage).setTitle("Student View");
            ((Stage) stage).setScene(new Scene(root, 642, 392));
            ((Stage) stage).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conn con = new Conn();
        Registration registration;
        File file = new File("student_login.txt");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        username = reader.nextLine();
        Student obj = new Student(username);
        ArrayList<Registration> regs = obj.see_transcript();
        for (int i = 0; i < regs.size(); i++) {

            objects.add(new Object(regs.get(i).get_course(), regs.get(i).get_section(),
                    String.valueOf(regs.get(i).get_marks()), regs.get(i).get_grade()));
        }
//        String query = "select * from registration where student like '"+username+"'";
//        try {
//            ResultSet result = con.s.executeQuery(query);
////            System.out.println("Hello  " + username);
//            while(result.next()){
//                objects.add(new Object(
//                        result.getString("course"), result.getString("section"),
//                        result.getString("marks"), result.getString("grade")));
//                rows++;
//            }
//        } catch (Exception e) {
//            System.out.println("Couldn't load Transcript");
//        }

        section.setCellValueFactory(new PropertyValueFactory<Object,String>("section"));
        course.setCellValueFactory(new PropertyValueFactory<Object,String>("course"));
        marks.setCellValueFactory(new PropertyValueFactory<Object,String>("marks"));
        grade.setCellValueFactory(new PropertyValueFactory<Object,String>("grade"));
        table.setItems(objects);
//        System.out.println("Rows : " + rows);
    }

    public void handler(ActionEvent event) throws IOException {
        if(event.getSource() == button_2) {
            go_back();
        }
    }
}
