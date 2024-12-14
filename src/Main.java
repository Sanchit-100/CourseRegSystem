import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static Records records = new Records();


    public static void main(String args[]) {
        while (true) {
            System.out.println("Welcome to the Course Registration System");
            System.out.println("1. Login\n2. Register\n3. Exit ");

            String ch1 = s.nextLine();
            if (ch1.equals("1")) login();
            else if (ch1.equals("2")) register();
            else if (ch1.equals("3")) break;
            else System.out.println("Error... Enter Valid value");
        }
    }

    private static void login() {
        while (true) {
            System.out.println("LOGIN as");
            System.out.println("1. Student\n2. Professor\n3. Admin\n4. TA\n5. Exit");
            String role = s.nextLine();
            int roleInt = Integer.parseInt(role);
            if (roleInt == 5) break;
            System.out.println("Enter mail: ");
            String mail = s.nextLine();
            System.out.println("Enter password: ");
            String password = s.nextLine();
            try {
                int userIndex = records.authenticate(mail, password, roleInt);
                if (userIndex != -1) {
                    System.out.println("Login successful!");
                    navigateToMenu(roleInt, userIndex);
                } else {
                    throw new InvalidLoginException("InvalidLoginException.");
                }
            } catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void register() {
        System.out.println("1. Student\n2. Professor\n3. Admin\n4. TA");
        String role = s.nextLine();
        System.out.println("Enter mail: ");
        String mail = s.nextLine();
        System.out.println("Enter password: ");
        String password = s.nextLine();
        Integer roleInt = Integer.parseInt(role);

        User newUser;
        if (roleInt == 1) {
            System.out.println("Enter semester: ");
            Integer sem = Integer.parseInt(s.nextLine());
            newUser = new Student(mail, password, sem);
        } else if (roleInt == 2) {
            newUser = new Professor(mail, password, records.courses);
        } else if (roleInt == 3) {
            newUser = new Admin(mail, password, records.courses, records.students, records.professors);
        } else if (roleInt == 4) {
            System.out.println("Enter semester: ");
            Integer sem = Integer.parseInt(s.nextLine());
            newUser = new TA(mail, password, sem);
        } else {
            System.out.println("Invalid role.");
            return;
        }
        records.addUser(newUser, roleInt);
        System.out.println("Registration successful!");
        login();
    }

    private static void navigateToMenu(int role, int userIndex) {
        if (role == 1) {
            Student student = records.students.get(userIndex);
            student.menu();
        } else if (role == 2) {
            Professor professor = records.professors.get(userIndex);
            professor.menu();
        } else if (role == 3) {
            Admin admin = records.admins.get(userIndex);
            admin.menu();
        } else if (role == 4) {
            TA ta = records.tas.get(userIndex);
            ta.menu();
        }
    }
}
