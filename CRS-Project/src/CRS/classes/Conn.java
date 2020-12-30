

package CRS.classes;

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