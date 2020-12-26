package CRS.classes;

public class Teacher {
    private String name, contact, id, program;

    public Teacher(String name, String cont, String id, String prg){
        this.name = name;
        this.id = id;
        this.program = prg;
        this.contact = cont;
    }
    public boolean save_in_db(){
        Conn con = new Conn();
        String query1 = "insert into teacher values('"+name+"','"+contact+"','"+id+"',"
                + "'"+program+"')";
        try {
            con.s.executeUpdate(query1);   //executes the query
            return true;
        }catch (Exception e){
            System.out.println("Error Occurred while Adding Teacher");
            return false;
        }

    }

}
