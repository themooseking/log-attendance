package data;

import java.util.ArrayList;
import java.sql.*;
import entities.*;

public class DB_Educator {
	private Connection connection;

	public DB_Educator(Connection connection) {
			this.connection = connection;
		}

	/***********************************
	 * READ
	 ***********************************/
	
	public ArrayList<Educator> getAllEducators(int id) {
		ArrayList<Educator> educatorList = new ArrayList<>();

		
		try {
			String sql = "SELECT * FROM educator";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int educatorId = resultSet.getInt("id");
				String educatorName = resultSet.getString("educator_name");

				Educator educator = new Educator(educatorId, educatorName);

				educatorList.add(educator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return educatorList;
	}
}
