package com.ehc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class GetConnection {

	public static Connection getCon() throws SQLException{
		Connection connection = null;
		
		
			
			System.out.println("Conneting to Database...");
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ehc", "root", "");
//				connection = (Connection) DriverManager.getConnection("jdbc:mysql://10.0.1.33:3306/ehealthcare", "root", "");
			//82.197.130.20
				//connection = (Connection) DriverManager.getConnection("jdbc:mysql://82.197.130.20:3306/1651880_kj", "1651880_kj", "ehc123456");							
				//"jdbc:mysql://mysql.hostinger.in/u614930526_ehc"+ "user=u614930526_ehc&password=ehealthcare"
				//connection = (Connection) DriverManager.getConnection("jdbc:mysql://mysql.hostinger.in:3306/u614930526_ehc?user=u614930526_ehc&password=ehealthcare");
				//connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ehealthcare", "root", "");
				System.out.println("Connection Successful");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			return connection;
			
	}
	
}
