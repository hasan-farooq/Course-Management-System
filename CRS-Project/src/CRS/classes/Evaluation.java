
package CRS.classes;

public class Evaluation {
    String type;
    int marks, weightage;

    public Evaluation(String type, int marks, int weightage){
        this.marks = marks;
        this.weightage = weightage;
        this.type = type;
    }
    public void add_marks(int marks){ this.marks = marks;}
    public void add_weightage(int weightage){this.weightage = weightage;}
    public boolean save_in_db(String course, String section){
        Conn con = new Conn();
//        System.out.println(course+section+type+marks+weightage);
        String query1 = "insert into evaluation values('"+course+"','"+section+"','"+type+"',"
                + "'"+marks+"','"+weightage+"')";
        try{
            con.s.executeUpdate(query1);   //executes the query
            return true;
        } catch (Exception excp) {
            System.out.println("Error Occurred while Adding Evaluation");
            return false;
        }
    }
}
