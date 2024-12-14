import java.util.HashMap;

public class Course {
    String course_code;
    int maxCapacity;
    int currentEnrollment;
    String name;
    String professor;
    Integer credits;
    String pre_req;
    String TA;
    String schedule;
    Integer drop_date;
    Integer semester;
    HashMap<String, String> studentGrades;

    public Course(String course_code, String name, String professor, Integer credits, String pre_req, String schedule, Integer semester, Integer maxCapacity, String TA){
        this.name=name;
        this.professor=professor;
        this.schedule=schedule;
        this.course_code=course_code;
        this.TA=TA;
        this.credits=credits;
        this.pre_req=pre_req;
        this.studentGrades = new HashMap<>();
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
        this.semester=semester;
        this.drop_date = 15;
    }

    public void assignGrade(String studentEmail, String grade) {
        studentGrades.put(studentEmail, grade);
    }

    public String getGrade(String studentEmail) {
        return studentGrades.getOrDefault(studentEmail, "No grade assigned");
    }

    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }

    public void enrollStudent() {
        if (!isFull()) {
            currentEnrollment++;
        }
    }

    public void dropStudent() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }
}
