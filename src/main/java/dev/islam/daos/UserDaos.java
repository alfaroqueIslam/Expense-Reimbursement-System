package dev.islam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.islam.models.Request;
import dev.islam.models.User;
import dev.islam.util.ConnectionUtil;

public class UserDaos {
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> pendList = new ArrayList<>();
		String selectQuery = "SELECT * FROM APP_USER";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User temp = new User();
				temp.setUserName(rs.getString("EMAIL"));
				temp.setPassword(rs.getString("PASSWORD"));
				temp.setUserType(rs.getString("USER_TYPE"));
				temp.setUserId(rs.getInt("USER_ID"));
				temp.setFirstName(rs.getString("FIRST_NAME"));
				temp.setLastName(rs.getString("LAST_NAME"));
				pendList.add(temp);
			}
			return pendList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendList;
	}

}
