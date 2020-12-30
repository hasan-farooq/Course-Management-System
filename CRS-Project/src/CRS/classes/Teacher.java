package CRS.classes;

import java.util.ArrayList;

public class Teacher {
    private String name, contact, id, program;

    public Teacher(String name, String cont, String id, String prg){
        this.name = name;
        this.id = id;
        this.program = prg;
        this.contact = cont;
    }

    static public void mark_attendance(ArrayList<String> students,ArrayList<Boolean> status,String course,String section){
        Conn con = new Conn();
        for (int i = 0; i < students.size(); i++){
            if(status.get(i) == false) {
                String query = "update registration set attendance = attendance + 1 where " +
                        "course like '" + course + "' and section like '" + section + "' and student like " +
                        "'" + students.get(i) + "'";
                try {
                    con.s.executeUpdate(query);
                } catch (Exception e) {
                    System.out.println("Could not update Attendance");
                }
            }
        }
    }

    static public void mark_evaluation(ArrayList<String> students,ArrayList<Integer> marks,String course,
                                       String section,int total, int weight){
        Conn con = new Conn();
        double weight_d,marks_d,total_d;
        for (int i = 0; i < students.size(); i++){
//            System.out.println("W : "+weight + " M : " + marks.get(i) + " T: "+total);
            weight_d = weight;
            total_d = total;
            marks_d = marks.get(i);
            double temp = (marks_d/total_d)*weight_d;
//            System.out.println("Weight-D : " +temp);
            int temp_i = (int) Math.round(temp);
//            System.out.println("Weight-I : " +temp_i);
            String query = "update registration set marks = marks + " +temp_i+ "  where " +
                    "course like '" + course + "' and section like '" + section + "' and student like " +
                    "'" + students.get(i) + "'";
            try {
//                System.out.println("Marks : " + marks.get(i));
                con.s.executeUpdate(query);
            } catch (Exception e) {
                System.out.println("Could not update Marks");
            }
        }
    }

    static public void assign_grades(ArrayList<String> students,ArrayList<String> grades,
                                     String course, String section){
        Conn con = new Conn();
        for (int i = 0; i < students.size(); i++){
            String query = "update registration set grade = '"+grades.get(i)+"'  where " +
                    "course like '" + course + "' and section like '" + section + "' and student like " +
                    "'" + students.get(i) + "'";
            try {
//                System.out.println("Marks : " + marks.get(i));
                con.s.executeUpdate(query);
            } catch (Exception e) {
                System.out.println("Could not update Grade");
            }
        }
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
