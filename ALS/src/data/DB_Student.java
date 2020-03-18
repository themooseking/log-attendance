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

	public ArrayList<Student> getStudents() {
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
}
