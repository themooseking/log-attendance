package data;

import java.sql.*;
import java.util.ArrayList;

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
				Team scoringTeam = null;
				for (int i = 0; i < teamList.size(); i++) {
					if (teamList.get(i).getTeamId() == resultSet.getInt("scoringteam")) {
						scoringTeam = teamList.get(i);
						break;
					}
				}

				MatchTime matchTime = new MatchTime(resultSet.getInt("matchtime"));
				int matchId = resultSet.getInt("matchid");

				Absence absence = new Absence(scoringTeam, matchTime, matchId);

				absenceList.add(absence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return absenceList;
	}
}
