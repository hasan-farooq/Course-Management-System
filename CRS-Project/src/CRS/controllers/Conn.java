

package CRS.controllers;

//import com.mysql.cj.xdevapi.Statement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
//import java.sql.*;
import javax.swing.JOptionPane;


public class Conn {
    Connection c;
    Statement s;

    public Conn(){
        try{
//            JOptionPane.showMessageDialog(null, "Invalid-conn");
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///ms","proj","");

            s = c.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid-connection");
//            System.exit(5);
            e.printStackTrace();
        }
    }
}