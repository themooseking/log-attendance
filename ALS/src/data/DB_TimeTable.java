package data;

import java.sql.*;
import java.util.ArrayList;
import entities.*;

public class DB_TimeTable {
		private Connection connection;

		public DB_TimeTable(Connection connection) {
					this.connection = connection;
				}

		/***********************************
		 * READ
		 ***********************************/

		public ArrayList<Timetable> getTimeTable(ArrayList<Course> courseList) {
			ArrayList<Timetable> timeTableList = new ArrayList<>();

			try {
				String sql = "SELECT * FROM timetable";

				Statement statement = connection.createStatement();

				ResultSet resultSet = statement.executeQuery(sql);

				while (resultSet.next()) {
					String plannedDay = resultSet.getString("planned_day");
					
					Course course = null;
					for (int i = 0; i < courseList.size(); i++) {
						if (courseList.get(i).getCourseId() == resultSet.getInt("id")) {
							course = courseList.get(i);
							break;
						}
					}

					Timetable timeTable = new Timetable(plannedDay, course);

					timeTableList.add(timeTable);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return timeTableList;
		}
	}

