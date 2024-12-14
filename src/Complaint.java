public class Complaint {
    String studentEmail;
    String description;
    String status;
    String remarks;

    public Complaint(String studentEmail, String description) {
        this.studentEmail = studentEmail;
        this.description = description;
        this.status = "Pending";
        this.remarks = "";
    }

    public void resolve(String remarks) {
        if (this.status.equals("Resolved")) {
            System.out.println("Complaint is already resolved.");
        } else {
            this.status = "Resolved";
            this.remarks = remarks;
        }
    }

}
