package data;

import java.sql.*;
import java.util.ArrayList;
import entities.*;

public class DB_Absence {
	private Connection connection;

	public DB_Absence(Connection connection) {
			this.connection = connection;
		}

	/***********************************
	 * READ
	 ***********************************/

	public ArrayList<Absence> getAbsence(ArrayList<Student> studentList, ArrayList<Course> courseList) {
		ArrayList<Absence> absenceList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM goals";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Student student = null;
				for (int i = 0; i < studentList.size(); i++) {
					if (studentList.get(i).getStudentId() == resultSet.getInt("id")) {
						student = studentList.get(i);
						break;
					}
				}
				
				Course course = null;
				for (int i = 0; i < courseList.size(); i++) {
					if (courseList.get(i).getCourseId() == resultSet.getInt("id")) {
						course = courseList.get(i);
						break;
					}
				}

				Date date = resultSet.getDate("absence_date");

				Absence absence = new Absence(date, student, );

				absenceList.add(absence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return absenceList;
	}
}
