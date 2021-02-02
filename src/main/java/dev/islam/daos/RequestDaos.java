package dev.islam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import dev.islam.models.Request;
import dev.islam.models.User;
import dev.islam.util.ConnectionUtil;

public class RequestDaos {
	
	private static Logger log = Logger.getRootLogger();
	
	public Request newRequest(String email, double amount, String reason, int id) {
		log.info("Attempting to create new Request");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String tDate = dtf.format(now);
		String insertQuery = "INSERT INTO REQUEST (STATUS,AMOUNT,REASON,DATE_CREATED,EMAIL,USER_ID) "
				+ "VALUES ('pending'," + amount + "," + "'" + reason + "','" + tDate + "','" + email + "'," + id + ")";
		Connection connection;
		Request ra = new Request();
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.executeQuery();
			log.info("Request created");
			return ra;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ra;	
		
		
	}
	
	public Request getRequest(String date) {
		String authQuery = "SELECT * FROM REQUEST WHERE DATE_CREATED = " + "'" + date + "'";
		Request def = new Request();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(authQuery);
			ResultSet rs = statement.executeQuery();
			rs.next();
			double balance = rs.getDouble("BALANCE");
			String status = rs.getString("STATUS");
			String s2 = rs.getString("DATE_CREATED");
			int RequestId = rs.getInt("REQUEST_ID");
			String email = rs.getString("EMAIL");
			Request acc = new Request();
			acc.setAmount(balance);
			acc.setStatus(status);
			acc.setRequestId(RequestId);
			acc.setDate(s2);
			acc.setEmail(email);
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return def;
	}

	public Request getRequestE(String email) {
		String authQuery = "SELECT * FROM REQUEST WHERE EMAIL = " + "'" + email + "'";
		Request def = new Request();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(authQuery);
			ResultSet rs = statement.executeQuery();
			rs.next();
			double balance = rs.getDouble("BALANCE");
			String status = rs.getString("STATUS");
			String s2 = rs.getString("DATE_CREATED");
			int RequestId = rs.getInt("REQUEST_ID");
			String em = rs.getString("EMAIL");
			Request acc = new Request();
			acc.setAmount(balance);
			acc.setStatus(status);
			acc.setRequestId(RequestId);
			acc.setDate(s2);
			acc.setEmail(em);
			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return def;
	}
	
	public void viewRequest(String email) {
		String authQuery = "SELECT * FROM REQUEST WHERE EMAIL = " + "'" + email + "'";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(authQuery);
			ResultSet rs = statement.executeQuery();
			rs.next();
			System.out.println("Balance: $" + rs.getDouble("BALANCE"));
			System.out.println("Status: " + rs.getString("STATUS"));
			System.out.println("Date created: " + rs.getString("DATE_CREATED"));
			System.out.println("Request ID: " + rs.getInt("Request_ID"));
			System.out.println("Email: " + rs.getString("EMAIL"));
//			double balance = rs.getDouble("BALANCE");
//			String status = rs.getString("STATUS");
//			String s2 = rs.getString("DATE_CREATED");
//			int RequestId = rs.getInt("Request_ID");
//			Request acc = new Request();
//			acc.setAmount(balance);
//			acc.setStatus(status);
//			acc.setRequestId(RequestId);
//			acc.setDate(s2);
//			return acc;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkRequest(String status, String rId) {
		String authQuery = "SELECT * FROM REQUEST WHERE REQUEST_ID = " + rId;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(authQuery);
			ResultSet rs = statement.executeQuery();
			rs.next();
			if (rs.getString("STATUS").equals(status)) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
		
	public void modify(String status, String rId) {
		String updateQuery = "UPDATE REQUEST a SET a.STATUS = '"
				+ status + "' WHERE a.REQUEST_ID = " + rId;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
	public ArrayList<Request> getPendingRequests(){
		ArrayList<Request> pendList = new ArrayList<>();
		String selectQuery = "SELECT * FROM REQUEST WHERE REQUEST.STATUS = 'pending'";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			Request temp = new Request();
			while (rs.next()) {
				temp.setAmount(rs.getDouble("BALANCE"));
				temp.setStatus(rs.getString("STATUS"));
				temp.setRequestId(rs.getInt("REQUEST_ID"));
				temp.setDate(rs.getString("DATE_CREATED"));
				temp.setEmail(rs.getString("EMAIL"));
				pendList.add(temp);
			}
			return pendList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendList;
	}
	
	public ArrayList<Request> getAllRequests(){
		ArrayList<Request> pendList = new ArrayList<>();
		String selectQuery = "SELECT * FROM REQUEST";
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectQuery);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Request temp = new Request();
				temp.setAmount(rs.getDouble("AMOUNT"));
				temp.setStatus(rs.getString("STATUS"));
				temp.setRequestId(rs.getInt("REQUEST_ID"));
				temp.setDate(rs.getString("DATE_CREATED"));
				temp.setEmail(rs.getString("EMAIL"));
				temp.setReason(rs.getString("REASON"));
				temp.setUserId(rs.getInt("USER_ID"));
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
