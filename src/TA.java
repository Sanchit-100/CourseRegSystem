import java.util.Scanner;
public class TA extends Student {

    public TA(String email, String pass, Integer semester) {
        super(email, pass, semester);
    }

    @Override
    public void menu() {
        while(true){
            System.out.println("1. View Courses");
            System.out.println("2. View Grades");
            System.out.println("3. Manage Student Grades");
            System.out.println("4. Exit");

            Scanner s = new Scanner(System.in);
            int choice = Integer.parseInt(s.nextLine());

            if(choice==1){
                viewCourses();
            }
            else if(choice==2){
                viewGrades();
            }
            else if(choice==3){
                manageStudentGrades();
            }
            else if(choice==4){
                break;
            }

        }

    }

    private void viewCourses() {
        for (Course c : Records.courses) {
            if (c.TA.equals(this.email)) {
                System.out.println("Course Name: " + c.name);
                System.out.println("Credits: " + c.credits);
                System.out.println("Pre-requisites: " + c.pre_req);
                System.out.println("Schedule: " + c.schedule);
                System.out.println();
            }
        }
    }

    private void manageStudentGrades() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course name: ");
        String courseCode = s.nextLine();
        Course course = null;
        for (Course c : Records.courses) {
            if (c.name.equals(courseCode) && c.professor.equals(this.email)) {
                course = c;
                break;
            }
        }
        if (course != null) {
            System.out.println("Enter student email: ");
            String studentEmail = s.nextLine();
            System.out.println("Enter grade: ");
            String grade = s.nextLine();
            course.assignGrade(studentEmail, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Course not found or you do not have permission to manage this course.");
        }
    }
}

