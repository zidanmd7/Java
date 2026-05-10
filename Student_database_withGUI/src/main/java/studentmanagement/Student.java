package studentmanagement;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private String grade;

    public Student(int id, String firstName, String lastName, String email, String course, String grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.grade = grade;
    }

    public Student(String firstName, String lastName, String email, String course, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }
}