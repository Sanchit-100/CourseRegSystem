import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    ArrayList<Course> registeredCourses = new ArrayList<>();
    Integer semester;
    Integer curr_date;

    public Student(String email, String pass, Integer semester) {
        super(email, pass);
        this.semester=semester;
    }

    public void menu() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter today's date: ");
        Integer curr_date=Integer.parseInt(s.nextLine());
        this.curr_date=curr_date;
        while(true){
            System.out.println("1. View available Courses");
            System.out.println("2. Register for Courses");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop Courses");
            System.out.println("6. Submit Complaints");
            System.out.println("7. View Complaints");
            System.out.println("8. Submit feedback");
            System.out.println("9. Exit");


            int choice = Integer.parseInt(s.nextLine());

            if(choice==1){
                viewAvailableCourses();

            }
            else if(choice==2){
                registerForCourses();

            }
            else if(choice==3){
                viewSchedule();

            }
            else if(choice==4){
                viewGrades();

            }
            else if(choice==5){
                dropCourses();

            }
            else if(choice==6){
                submitComplaints();

            }
            else if(choice==7){
                viewComplaints();

            }
            else if(choice==8){
                submitFeedback();
            }
            else if(choice==9) break;
            else{
                System.out.println("Invalid choice");
            }


        }

    }

    private void viewAvailableCourses() {
        for (Course c : Records.courses) {
            if(c.semester.equals(semester)){
                System.out.println("Course Code: "+c.course_code);
                System.out.println("Course Name: " + c.name);
                System.out.println("Professor: " + c.professor);
                System.out.println("Credits: "+c.credits);
                System.out.println("Pre-req: "+c.pre_req);
                System.out.println("Schedule: " + c.schedule);
                System.out.println();
            }

        }
    }

    public void viewGrades() {
        for (Course c : registeredCourses) {
            String grade = c.getGrade(email);
            System.out.println("Course: " + c.name + " | Grade: " + grade);
        }
    }

    public void viewComplaints() {
        System.out.println("Viewing complaints...");
        for (Complaint complaint : Records.complaints) {
            if (complaint.studentEmail.equals(this.email)) {
                System.out.println("Description: " + complaint.description);
                System.out.println("Status: " + complaint.status);
                System.out.println("Remarks: " + complaint.remarks);
                System.out.println();
            }
        }
    }


    private void registerForCourses() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code to register: ");
        String courseCode = s.nextLine();
        for (Course c : Records.courses) {
            if (c.course_code.equals(courseCode) && c.semester.equals(semester)) {
                try {
                    if (c.isFull()) {
                        throw new CourseFullException("Course is already full.");
                    }
                    c.enrollStudent();
                    registeredCourses.add(c);
                    System.out.println("Course registered successfully.");
                    return;
                } catch (CourseFullException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
        System.out.println("Course not found or not available in the current semester.");
    }



    private void viewSchedule() {
        for (Course c : registeredCourses) {
            System.out.println("Course Name: " + c.name);
            System.out.println("Schedule: " + c.schedule);
            System.out.println();
        }
    }


    private void dropCourses() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code to drop: ");
        String courseCode = s.nextLine();
        Course courseToRemove = null;
        for (Course c : registeredCourses) {
            if (c.course_code.equals(courseCode)) {
                courseToRemove = c;
                break;
            }
        }
        if (courseToRemove != null) {
            try {
                if (isDropDeadlinePassed(courseToRemove)) {
                    throw new DropDeadlinePassedException("Cannot drop course after the deadline.");
                }
                registeredCourses.remove(courseToRemove);
                courseToRemove.dropStudent();
                System.out.println("Course dropped successfully.");
            } catch (DropDeadlinePassedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private boolean isDropDeadlinePassed(Course course_to_remove) {

        if(course_to_remove.drop_date>=this.curr_date) return false;
        return true;
    }


    private void submitComplaints() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your complaint: ");
        String description = s.nextLine();
        Complaint complaint = new Complaint(this.email, description);
        Records.complaints.add(complaint);
        System.out.println("Complaint submitted successfully.");
    }

    public void submitFeedback() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code: ");
        String courseCode = s.nextLine();
        System.out.println("Enter numeric rating (1-5): ");
        int rating = Integer.parseInt(s.nextLine());
        System.out.println("Enter textual feedback: ");
        String comment = s.nextLine();

        Feedback<Integer> numericFeedback = new Feedback<>(this.email, courseCode, rating);
        Feedback<String> textualFeedback = new Feedback<>(this.email, courseCode, comment);

        Records.numericFeedbacks.add(numericFeedback);
        Records.textualFeedbacks.add(textualFeedback);

        System.out.println("Feedback submitted successfully.");
    }

}
