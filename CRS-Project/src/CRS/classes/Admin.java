
package CRS.classes;

import CRS.controllers.AlertBox;

import java.io.FileWriter;
import java.io.IOException;

public class Admin {
    public boolean add_user_db(String user,String name,String passw){
        Conn con = new Conn();
        String query1 = "insert into login values('"+user+"','"+name+"','"+passw+"')";
        try{
            con.s.executeUpdate(query1);   //executes the query
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding User");
            return false;
        }
    }
    public void open_registration(){
        try {
            FileWriter writer = new FileWriter("registration.txt");
            writer.write("open");
            writer.close();
            AlertBox.alert("Registration Opened");
        } catch (IOException ex) {
            AlertBox.alert("Error");
        }
    }
    public void close_registration(){
        try {
            FileWriter writer = new FileWriter("registration.txt");
            writer.write("close");
            writer.close();
            AlertBox.alert("Registration Closed");
        } catch (IOException ex) {
            AlertBox.alert("Error");
        }
    }
}
