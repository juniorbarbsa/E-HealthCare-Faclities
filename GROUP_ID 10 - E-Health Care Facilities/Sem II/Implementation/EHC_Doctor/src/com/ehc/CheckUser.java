package com.ehc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ehc.bean.User;

public class CheckUser {

	public static boolean check(User user) {
		ResultSet rs;
		Statement stmt;
		boolean exists = false;
		try {
			
			
			Connection connection = GetConnection.getCon();
			stmt = connection.createStatement();
			String query = "SELECT * FROM doctor WHERE username = '"
					+ user.getUsername() + "' AND password = '"
					+ user.getPassword() + "'";
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
//				System.out.println("Result - " + rs.getString("uname") + " " + rs.getString("password"));
			
			if(rs.next()){
				
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e.getMessage());
		}
		
		return exists;
	}
}
