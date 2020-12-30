
package CRS.classes;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Student {
    private ArrayList<Registration> registrations;
    private String name, id, gender, program, phone;

    public Student(String name, String id, String gen, String prg, String ph){
        this.name = name;
        this.id = id;
        this.gender = gen;
        this.program = prg;
        this.phone = ph;
        this.registrations = new ArrayList<Registration>();
    }
    public Student(String username){
        this.name = username;
    }
    public void register_course(String course, String section) throws FileNotFoundException {
        this.registrations.add(new Registration(this.name, section, course));
    }
    public void drop_course(String course, String sec){
        for(int i = 0; i < registrations.size(); i++){
            if(registrations.get(i).get_section() == sec
                    && registrations.get(i).get_course() == course && registrations.get(i).get_student() == name){
                registrations.remove(i);
                return;
            }
        }
    }
    public String see_grade(String course, String sec){
        Registration temp = Registration.search_registration(registrations,course,sec);
        if (temp != null) {
            if (temp.get_grade() == null) {
                return "Not Assigned Yet";
            } else {
                return temp.get_grade();
            }
        }
        else{
            return "Registration not Found";
        }
    }
    public boolean save_in_db(){
        Conn con = new Conn();
        String query1 = "insert into student values('"+name+"','"+id+"','"+gender+"',"
                + "'"+program+"','"+phone+"')";
        try{
            con.s.executeUpdate(query1);   //executes the query
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding Student");
            return false;
        }
    }
    public ArrayList<Registration> see_transcript(){
        ArrayList<Registration> regs = new ArrayList<Registration>();
        Conn con = new Conn();
        String query = "select * from registration where student like '"+name+"'";
        try {
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                regs.add(new Registration(name, result.getString("section"),
                        result.getString("course"), result.getString("grade"),
                        result.getString("marks"), result.getString("attendance")));
            }
        } catch (Exception e) {
            System.out.println("Couldn't load Transcript");
        }
        return regs;
    }
    public ArrayList<Registration> see_attendance(){
        ArrayList<Registration> regs = new ArrayList<Registration>();
        Conn con = new Conn();
        String query = "select * from registration where student like '"+name+"'";
        try {
            ResultSet result = con.s.executeQuery(query);
            while(result.next()){
                regs.add(new Registration(name, result.getString("section"),
                        result.getString("course"), result.getString("grade"),
                        result.getString("marks"), result.getString("attendance")));
            }
        } catch (Exception e) {
            System.out.println("Couldn't load Transcript");
        }
        return regs;
    }

}
