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
	 * CREATE
	 ***********************************/

//	public void createCourse(Course course) {
//		try {
//			String sql = "INSERT INTO absence VALUES (?, ?, ?)";
//
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, course.getCourseName());
//			statement.setInt(2, course.getSemesterNo());
//			statement.setInt(3, course.getEducator().getEducatorName());
//			statement.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

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

	public ArrayList<Course> getCoursesByEducatorId(int id) {
		ArrayList<Course> courseList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM course WHERE educator_id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int courseId = resultSet.getInt("id");
				String courseName = resultSet.getString("course_name");
				int semesterNo = resultSet.getInt("semester_no");
				int educatorId = id;

				Course course = new Course(courseId, courseName, semesterNo, educatorId);

				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courseList;
	}

	/***********************************
	 * DELETE
	 ***********************************/

	public void deleteCourse(Course course) {
		try {
			String sql = "DELETE FROM course WHERE id=?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, course.getCourseId());

			if (statement.executeUpdate() == 0) {
				System.out.println("No course to be deleted!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
