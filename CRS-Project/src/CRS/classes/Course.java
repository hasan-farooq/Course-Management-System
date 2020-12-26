
package CRS.classes;

public class Course {
    private String name, code, program;
    private int hours;

    public Course(String name, String code, String prg, int hr){
        this.name = name;
        this.code = code;
        this.program = prg;
        this.hours = hr;
    }

    public boolean save_in_db(){
        Conn con = new Conn();
        String query1 = "insert into course values('"+name+"','"+code+"','"+hours+"',"
                + "'"+program+"')";
        try{
            con.s.executeUpdate(query1);   //executes the query
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding Course");
            return false;
        }
    }
}
