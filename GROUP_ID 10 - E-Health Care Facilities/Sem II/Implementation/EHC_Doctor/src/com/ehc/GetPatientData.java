package com.ehc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ehc.bean.Patient;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GetPatientData {
public static Patient getData(String patient_username){
		
		Patient patient = new Patient();
		Statement stmt;
		ResultSet rs=null;

		try {
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM person_info WHERE uname = '"+ patient_username + "'";
			
			rs = stmt.executeQuery(query);

			while(rs.next()){
//				System.out.println(rs.getInt("id"));
				
				patient.setName(rs.getString("name"));
				patient.setAddress(rs.getString("address"));
				patient.setAge(rs.getString("age"));
				patient.setBg(rs.getString("bg"));
				patient.setGender(rs.getString("gender"));
				patient.setDob(rs.getString("dob"));
				patient.setMob(rs.getString("mob"));
				patient.setEmail(rs.getString("email"));
				patient.setId(Integer.parseInt(rs.getString("id")));
				System.out.println("patient id:"+Integer.parseInt(rs.getString("id")));
				System.out.println(patient);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patient;
	
	}
}
