package com.ehc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ehc.bean.Appointment;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class AppointmentCount {
	
	public static int getData(String doc_id)
	{
		
		Statement stmt;
		ResultSet rs=null;
		int count = 0;
		int id = Integer.parseInt(doc_id);
		try {
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM appointment WHERE doctor_id = '"+ id + "' AND status IS NULL";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			System.out.println(query);
			while(rs.next()){
				count++;
			}
		}
	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count="+count);
		return count;
	}

}
