package studentmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student student) {
        String sql = """
                INSERT INTO students (first_name, last_name, email, course, grade)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());
            pstmt.setString(5, student.getGrade());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Add student error: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("grade")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("View students error: " + e.getMessage());
        }

        return students;
    }

    public void updateStudent(Student student) {
        String sql = """
                UPDATE students
                SET first_name = ?, last_name = ?, email = ?, course = ?, grade = ?
                WHERE id = ?
                """;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());
            pstmt.setString(5, student.getGrade());
            pstmt.setInt(6, student.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Update student error: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Delete student error: " + e.getMessage());
        }
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT * FROM students
                WHERE first_name LIKE ?
                   OR last_name LIKE ?
                   OR email LIKE ?
                   OR course LIKE ?
                   OR grade LIKE ?
                """;

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchText = "%" + keyword + "%";

            pstmt.setString(1, searchText);
            pstmt.setString(2, searchText);
            pstmt.setString(3, searchText);
            pstmt.setString(4, searchText);
            pstmt.setString(5, searchText);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("grade")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Search student error: " + e.getMessage());
        }

        return students;
    }
}