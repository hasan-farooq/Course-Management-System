
package CRS.classes;

import java.util.ArrayList;

public class Registration {
    private String student, section, course, grade;

    public Registration(String std,String sec,String course){
        this.student = std;
        this.section = sec;
        this.course = course;
        this.grade = null;
    }
    public Registration(Registration reg){
        this.student = reg.student;
        this.section = reg.section;
        this.course = reg.course;
        this.grade = reg.grade;
    }
    public String get_grade(){
        return this.grade;
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
                + "'"+section+"','"+grade+"','"+0+"')";
        try{
            con.s.executeUpdate(query);   //executes the query
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
        try{
            con.s.executeUpdate(query);   //executes the query
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
