import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends User {
    ArrayList<Course> courses;

    public Professor(String email, String pass, ArrayList<Course> courses) {
        super(email, pass);
        this.courses = courses;
    }

    public void menu() {

        while(true){
            System.out.println("1. Manage Courses");
            System.out.println("2. View enrolled students");
            System.out.println("3. View feedback");
            System.out.println("4. Exit");
            Scanner s = new Scanner(System.in);
            int choice = Integer.parseInt(s.nextLine());
            if(choice==4) break;

            switch (choice) {
                case 1:
                    manageCourses();
                    break;
                case 2:
                    viewEnrolledStudents();
                    break;
                case 3:
                    viewFeedback();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private void manageCourses() {
        System.out.println("1. View Courses\n2. Update Course");
        Scanner s = new Scanner(System.in);
        int choice = Integer.parseInt(s.nextLine());

        switch (choice) {
            case 1:
                viewCourses();
                break;
            case 2:
                updateCourseDetails();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void viewCourses() {
        for (Course c : courses) {
            if (c.professor.equals(this.email)) {
                System.out.println("Course Code: " + c.course_code);
                System.out.println("Semester: "+c.semester);
                System.out.println("Course Name: " + c.name);
                System.out.println("Credits: " + c.credits);
                System.out.println("Pre-requisites: " + c.pre_req);
                System.out.println("Schedule: " + c.schedule);

                System.out.println();
            }
        }
    }


    private void updateCourseDetails() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code to update: ");
        String courseCode = s.nextLine();
        Course courseToUpdate = null;
        for (Course c : courses) {
            if (c.professor.equals(this.email) && c.course_code.equals(courseCode)) {
                courseToUpdate = c;
                break;
            }
        }
        if (courseToUpdate != null) {
            System.out.println("1. Update Pre-requisites\n2. Update Schedule");
            int detail_no = Integer.parseInt(s.nextLine());
            System.out.println("Enter new value: ");
            String new_val = s.nextLine();
            if (detail_no == 1) courseToUpdate.pre_req = new_val;
            else if (detail_no == 2) courseToUpdate.schedule = new_val;
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }


    private void viewEnrolledStudents() {
        System.out.println("Viewing enrolled students...");
        for (Course c : courses) {
            if (c.professor.equals(this.email)) {
                System.out.println("Course: " + c.name);
                for (Student student : Records.students) {
                    if (student.registeredCourses.contains(c)) {
                        System.out.println("Student Email: " + student.email);
                    }
                }
                System.out.println();
            }
        }
    }

    public void viewFeedback() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code to view feedback: ");
        String courseCode = s.nextLine();

        System.out.println("Numeric Feedback:");
        for (Feedback<Integer> feedback : Records.numericFeedbacks) {
            if (feedback.getCourseCode().equals(courseCode)) {
                System.out.println(feedback);
            }
        }

        System.out.println("Textual Feedback:");
        for (Feedback<String> feedback : Records.textualFeedbacks) {
            if (feedback.getCourseCode().equals(courseCode)) {
                System.out.println(feedback);
            }
        }
    }

}
