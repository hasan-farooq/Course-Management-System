
package CRS.classes;

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
}
