
package CRS.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Registration {
    private String student, section, course, grade;
    private int marks, attendance;

    public Registration(String std,String sec,String course){
        this.student = std;
        this.section = sec;
        this.course = course;
        this.grade = "I";
        this.marks = 0;
        this.attendance = 0;
    }
    public Registration(String std,String sec,String course,String grade,
                        String marks, String attendance){
        this.student = std;
        this.section = sec;
        this.course = course;
        this.grade = grade;
        this.marks = Integer.parseInt(marks);
        this.attendance = Integer.parseInt(attendance);
    }
    public Registration(Registration reg){
        this.student = reg.student;
        this.section = reg.section;
        this.course = reg.course;
        this.grade = reg.grade;
        this.marks = reg.marks;
        this.attendance = reg.attendance;
    }
    static public boolean is_open() throws FileNotFoundException {
        File file = new File("registration.txt");
        Scanner reader = new Scanner(file);
        String status = reader.nextLine();
        reader.close();
        if (status.equals("close")){
            return false;
        }
        else{
            return true;
        }
    }
    public String get_grade(){
        return this.grade;
    }
    public int get_marks(){
        return this.marks;
    }
    public int get_attendance(){
        return this.attendance;
    }
    public String get_section(){
        return this.section;
    }
    public String get_course(){ return this.course; }
    public String get_student(){
        return this.grade;
    }
    static public Registration search_registration(ArrayList<Registration> registrations, String course, String sec){
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).course == course && registrations.get(i).section == sec) {
                return registrations.get(i);
            }
        }
        return null;
    }
    public boolean insert_in_db(){
        Conn con = new Conn();
        String query = "insert into registration values('"+this.student+"','"+course+"',"
                + "'"+section+"','"+grade+"','"+0+"','"+0+"')";
        String query_2 = "update section set seats = seats - 1 where "
                + "course like '"+course+"' and name like '"+section+"'";
        try{
            con.s.executeUpdate(query);   //executes the query
            con.s.executeUpdate(query_2);
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding Registration");
            return false;
        }
    }
    public boolean delete_in_db(){
        Conn con = new Conn();
        String query = "delete from registration where student like '"+this.student+"' and course like '"+course+"' " +
                "and section like '"+section+"'";
        String query_2 = "update section set seats = seats + 1 where "
                + "course like '"+course+"' and name like '"+section+"'";
        try{
            con.s.executeUpdate(query);   //executes the query
            con.s.executeUpdate(query_2);
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Registration");
            return false;
        }
    }
    public void print(){
        System.out.println("Name : " + student);
        System.out.println("Course : " + course);
        System.out.println("Teacher : " + section);
        System.out.println("Seats : " + grade);
    }

}
