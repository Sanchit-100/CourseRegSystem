import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    ArrayList<Course> courses;
    ArrayList<Student> students;
    ArrayList<Professor> professors;
    static Scanner s = new Scanner(System.in);

    public Admin(String email, String pass, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
        super(email, pass);
        this.courses = courses;
        this.students = students;
        this.professors = professors;
    }

    public void menu() {
        while(true){
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign Professors");
            System.out.println("4. Handle Complaints");
            System.out.println("5. Assign Grades");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(s.nextLine());
            if (choice == 6) {
                break;
            } else if (choice == 1) {
                manageCourseCatalog();
            } else if (choice == 2) {
                manageStudentRecords();
            } else if (choice == 3) {
                assignProfessors();
            } else if (choice == 4) {
                handleComplaints();
            } else if (choice == 5) {
                assignGrades();
            } else {
                System.out.println("Invalid choice.");
            }

        }

    }

    public void manageCourseCatalog() {
        System.out.println("1. Add Course\n2. Delete Course\n3. Update Course");
        int choice = Integer.parseInt(s.nextLine());

        switch (choice) {
            case 1:
                addCourse();
                break;
            case 2:
                deleteCourse();
                break;
            case 3:
                updateCourse();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addCourse() {
        System.out.println("Enter course code: ");
        String code = s.nextLine();
        System.out.println("Enter course title: ");
        String name = s.nextLine();
        System.out.println("Enter course professor: ");
        String professor = s.nextLine();
        System.out.println("Enter semester: ");
        Integer semester = Integer.parseInt(s.nextLine());
        System.out.println("Enter course credits: ");
        String credit = s.nextLine();
        Integer credits=Integer.parseInt(credit);
        System.out.println("Enter course pre_req: ");
        String  pre_req= s.nextLine();
        System.out.println("Enter schedule: ");
        String schedule = s.nextLine();
        System.out.println("Enter TA mail: ");
        String TA_mail = s.nextLine();
        System.out.println("Enter max_capacity: ");
        Integer max_cap = Integer.parseInt(s.nextLine());
        Course newCourse = new Course(code, name, professor, credits, pre_req, schedule,semester, max_cap, TA_mail);
        courses.add(newCourse);
        System.out.println("Course added successfully.");
    }

    private void deleteCourse() {
        System.out.println("Enter course name to delete: ");
        String name = s.nextLine();
        Course courseToRemove = null;
        for (Course c : courses) {
            if (c.name.equals(name)) {
                courseToRemove = c;
                break;
            }
        }
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void updateCourse() {
        System.out.println("Enter course code to update: ");
        String code = s.nextLine();
        Course courseToUpdate = null;
        for (Course c : courses) {
            if (c.course_code.equals(code)) {
                courseToUpdate = c;
                break;
            }
        }
        if (courseToUpdate != null) {
            System.out.println("1. Update Title\n2. Update Professor\n3. Update Credits\n4. Update Pre-requisites\n5. Update Schedule");
            int detail_no = Integer.parseInt(s.nextLine());
            System.out.println("Enter new value: ");
            String new_val = s.nextLine();
            switch (detail_no) {
                case 1:
                    courseToUpdate.name = new_val;
                    break;
                case 2:
                    courseToUpdate.professor = new_val;
                    break;
                case 3:
                    courseToUpdate.credits = Integer.parseInt(new_val);
                    break;
                case 4:
                    courseToUpdate.pre_req = new_val;
                    break;
                case 5:
                    courseToUpdate.schedule = new_val;
                    break;

                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void assignGrades() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter course code: ");
        String courseCode = s.nextLine();
        Course course = null;
        for (Course c : courses) {
            if (c.course_code.equals(courseCode)) {
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
            System.out.println("Course not found.");
        }
    }


    public void manageStudentRecords() {
        System.out.println("1. Add Student\n2. Remove Student\n3. Update Student");
        int choice = Integer.parseInt(s.nextLine());

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                removeStudent();
                break;
            case 3:
                updateStudent();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addStudent() {
        System.out.println("Enter student email: ");
        String email = s.nextLine();
        System.out.println("Enter student semester: ");
        Integer sem = Integer.parseInt(s.nextLine());
        System.out.println("Enter student password: ");
        String password = s.nextLine();
        Student newStudent = new Student(email, password,sem);
        students.add(newStudent);
        System.out.println("Student added successfully.");
    }

    private void removeStudent() {
        System.out.println("Enter student email to remove: ");
        String email = s.nextLine();
        Student studentToRemove = null;
        for (Student s : students) {
            if (s.email.equals(email)) {
                studentToRemove = s;
                break;
            }
        }
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void updateStudent() {
        System.out.println("Enter student email to update: ");
        String email = s.nextLine();
        Student studentToUpdate = null;
        for (Student s : students) {
            if (s.email.equals(email)) {
                studentToUpdate = s;
                break;
            }
        }
        if (studentToUpdate != null) {
            System.out.println("Enter new password: ");
            String newPassword = s.nextLine();
            studentToUpdate.pass = newPassword;
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void assignProfessors() {
        System.out.println("Enter course name to assign professor: ");
        String courseName = s.nextLine();
        System.out.println("Enter professor email: ");
        String professorEmail = s.nextLine();
        Course courseToAssign = null;
        for (Course c : courses) {
            if (c.name.equals(courseName)) {
                courseToAssign = c;
                break;
            }
        }
        if (courseToAssign != null) {
            courseToAssign.professor = professorEmail;
            System.out.println("Professor assigned successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void handleComplaints() {
        System.out.println("Handling complaints...");
        for (Complaint complaint : Records.complaints) {
            System.out.println("Complaint from: " + complaint.studentEmail);
            System.out.println("Description: " + complaint.description);
            System.out.println("Status: " + complaint.status);
            System.out.println("Remarks: " + complaint.remarks);
            if (complaint.status.equals("Pending")) {
                System.out.println("1. Resolve Complaint\n2. Skip");
                int choice = Integer.parseInt(s.nextLine());
                if (choice == 1) {
                    System.out.println("Enter remarks: ");
                    String remarks = s.nextLine();
                    complaint.resolve(remarks);
                    System.out.println("Complaint resolved.");
                }
            } else {
                System.out.println("This complaint is already resolved.");
            }
        }
    }


}
