import java.util.ArrayList;

public class Records {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Professor> professors = new ArrayList<>();
    static ArrayList<TA> tas = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Complaint> complaints = new ArrayList<>();
    static ArrayList<Feedback<Integer>> numericFeedbacks = new ArrayList<>();
    static ArrayList<Feedback<String>> textualFeedbacks = new ArrayList<>();

    // 1. Student 2. Prof 3. Admin 4. TA
    public void addUser(User s, int role) {
        if (role == 1) students.add((Student) s);
        else if (role == 2) professors.add((Professor) s);
        else if (role == 3) admins.add((Admin) s);
        else if (role == 4) tas.add((TA) s);
    }

    public int authenticate(String email, String pass, int role) {
        int n = -1;
        if (role == 1) {
            for (User s : students) {
                if (s.email.equals(email) && s.pass.equals(pass)) n = students.indexOf(s);
            }
        } else if (role == 2) {
            for (User s : professors) {
                if (s.email.equals(email) && s.pass.equals(pass)) n = professors.indexOf(s);
            }
        } else if (role == 3) {
            for (User s : admins) {
                if (s.email.equals(email) && s.pass.equals(pass)) n = admins.indexOf(s);
            }
        } else if (role == 4) {
            for (User s : tas) {
                if (s.email.equals(email) && s.pass.equals(pass)) n = tas.indexOf(s);
            }
        }
        return n; // The index of the found user
    }
}
