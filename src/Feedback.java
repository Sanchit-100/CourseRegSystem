public class Feedback<T> {
    private String studentEmail;
    private String courseCode;
    private T feedback;

    public Feedback(String studentEmail, String courseCode, T feedback) {
        this.studentEmail = studentEmail;
        this.courseCode = courseCode;
        this.feedback = feedback;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public T getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "studentEmail='" + studentEmail + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", feedback=" + feedback +
                '}';
    }
}
