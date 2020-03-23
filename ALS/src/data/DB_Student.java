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
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, course.getSemesterNo());

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
}
