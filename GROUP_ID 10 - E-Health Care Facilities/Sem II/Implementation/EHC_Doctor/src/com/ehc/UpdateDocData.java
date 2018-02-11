package com.ehc;

import java.sql.PreparedStatement;
import com.ehc.bean.UpdateDoctor;
import com.mysql.jdbc.Connection;

public class UpdateDocData {

	public static boolean update(UpdateDoctor doc1, String username) {
		PreparedStatement ps;
		PreparedStatement ps1;
		String name = doc1.getName();
		String address = doc1.getAddress();
		String gender = doc1.getGender();
		String hospital = doc1.getHospital();
		String email = doc1.getEmail();
		String mobile = doc1.getMobile();
		String dob = doc1.getDob();
		String speciality = doc1.getSpeciality();

		boolean user1 = false;

		try {
			Connection connection = GetConnection.getCon();
			String query = "update doctor set name = ?,address = ?,dob = ? ,gender = ?,email = ?,hospital = ?,speciality = ?,mobile = ? WHERE username = ?";
			String query1 = "update appointment set name = ? WHERE username = ?";
			ps = connection.prepareStatement(query);
			
			ps1 = connection.prepareStatement(query1);
			ps1.setString(1, name);
			ps1.setString(2, username);
			
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, dob);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, hospital);
			ps.setString(7, speciality);
			ps.setString(8, mobile);
			ps.setString(9, username);
			
			System.out.println(query);
			System.out.println(doc1);

			ps.executeUpdate();

			user1 = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e.getMessage());
		}
		return user1;

	}
}
