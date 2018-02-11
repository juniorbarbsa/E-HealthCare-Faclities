package com.ehc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ehc.bean.Doctor;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GetDocData {
	
	public static Doctor getData(String username){
		
		Doctor doctor = new Doctor();
		Statement stmt;
		ResultSet rs=null;

		try {
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM doctor WHERE username = '"+ username + "'";
			
			rs = stmt.executeQuery(query);

			while(rs.next()){
//				System.out.println(rs.getInt("id"));
				
				doctor.setName(rs.getString("name"));
				doctor.setAddress(rs.getString("address"));
				doctor.setDob(rs.getString("dob"));
				doctor.setEmail(rs.getString("email"));
				doctor.setGender(rs.getString("gender"));
				doctor.setHospital(rs.getString("hospital"));
				doctor.setMobile(rs.getString("mobile"));
				doctor.setSpeciality(rs.getString("speciality"));
				doctor.setId(Integer.parseInt(rs.getString("id")));
				System.out.println("doc id:"+Integer.parseInt(rs.getString("id")));
			}
			/*doctor.setName(rs.getString(0));
			doctor.setAddress(rs.getString(1));*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doctor;
	
	}
}

