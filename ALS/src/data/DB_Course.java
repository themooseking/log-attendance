package data;

import java.sql.*;
import java.util.ArrayList;
import entities.*;

public class DB_Course {
	private Connection connection;

	public DB_Course(Connection connection) {
		this.connection = connection;
	}

	/***********************************
	 * READ
	 ***********************************/

	public ArrayList<Course> getCourses() {
		ArrayList<Course> courseList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int courseId = resultSet.getInt("id");
				String courseName = resultSet.getString("course_name");
				int semesterNo = resultSet.getInt("semester_no");
				int educatorId = resultSet.getInt("educator_id");

				Course course = new Course(courseId, courseName, semesterNo, educatorId);

				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courseList;
	}
}
