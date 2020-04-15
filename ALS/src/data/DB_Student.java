package data;

import java.sql.*;
import java.util.ArrayList;
import entities.*;

public class DB_Student {

	private Connection connection;

	public DB_Student(Connection connection) {
		this.connection = connection;
	}

	/***********************************
	 * CREATE
	 ***********************************/

	public void createStudent(Student student) {
		try {
			String sql = "INSERT INTO student VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setInt(3, student.getSemesterNo());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/***********************************
	 * READ
	 ***********************************/

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> studentList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM student";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int studentId = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int semesterNo = resultSet.getInt("semester_no");

				Student student = new Student(studentId, firstName, lastName, semesterNo);

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;
	}

	public ArrayList<Student> getStudentsByCourse(Course course) {
		ArrayList<Student> studentList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM student WHERE semester_no=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, course.getSemesterNo());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				int studentId = resultSet.getInt("id");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				int semesterNo = resultSet.getInt("semester_no");

				Student student = new Student(studentId, firstName, lastName, semesterNo);

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;
	}
	
    public ArrayList<Student> getStudentsBySemester(int semesterNo) {
        ArrayList<Student> studentList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM student WHERE semester_no=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, semesterNo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int studentId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");

                Student student = new Student(studentId, firstName, lastName, semesterNo);

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

	/***********************************
	 * DELETE
	 ***********************************/

	public void deleteStudent(Student student) {
		try {
			String sql = "DELETE FROM student WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, student.getStudentId());

			if (statement.executeUpdate() == 0) {
				System.out.println("No student to be deleted!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
