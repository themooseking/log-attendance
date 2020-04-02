package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import entities.*;

public class DB_Absence {
	private Connection connection;

	public DB_Absence(Connection connection) {
			this.connection = connection;
		}
	
	/***********************************
	 * CREATE
	 ***********************************/

	public void createAbsence(Absence absence) {
		try {
			String sql = "INSERT INTO absence VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(absence.getDate()));
			statement.setInt(2, absence.getStudent().getStudentId());
			statement.setInt(3, absence.getCourse().getCourseId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/***********************************
	 * READ
	 ***********************************/

	public ArrayList<Absence> getAllAbsence(ArrayList<Student> studentList, ArrayList<Course> courseList) {
		ArrayList<Absence> absenceList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM absence";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Date date = resultSet.getDate("absence_date");
				LocalDate localDate = date.toLocalDate();
				
				Student student = null;
				for (int i = 0; i < studentList.size(); i++) {
					if (studentList.get(i).getStudentId() == resultSet.getInt("student_id")) {
						student = studentList.get(i);
						break;
					}
				}
				
				Course course = null;
				for (int i = 0; i < courseList.size(); i++) {
					if (courseList.get(i).getCourseId() == resultSet.getInt("course_id")) {
						course = courseList.get(i);
						break;
					}
				}

				Absence absence = new Absence(localDate, student, course);

				absenceList.add(absence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return absenceList;
	}
	
	public ArrayList<Absence> getAbsenceByCourse(ArrayList<Student> studentList, Course course) {
		ArrayList<Absence> absenceList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM absence WHERE course_id=?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, course.getCourseId());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Date date = resultSet.getDate("absence_date");
				LocalDate localDate = date.toLocalDate();
				
				Student student = null;
				for (int i = 0; i < studentList.size(); i++) {
					if (studentList.get(i).getStudentId() == resultSet.getInt("student_id")) {
						student = studentList.get(i);
						break;
					}
				}

				Absence absence = new Absence(localDate, student, course);

				absenceList.add(absence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return absenceList;
	}
}
