package com.ehc;

import java.sql.PreparedStatement;
import com.ehc.bean.Doctor;
import com.mysql.jdbc.Connection;

public class SubmitDoctor {

	public static boolean submit(Doctor doc) {

		// ResultSet rs;
		// Statement stmt;
		// boolean exists = false;

		PreparedStatement ps;
		String name = doc.getName();
		String address = doc.getAddress();
		String gender = doc.getGender();
		String hospital = doc.getHospital();
		String email = doc.getEmail();
		String mobile = doc.getMobile();
		String dob = doc.getDob();
		String speciality = doc.getSpeciality();
		String username = doc.getUsername();
		String password = doc.getPassword();
		boolean newuser = false;
		
		try {
			Connection connection = GetConnection.getCon();
			String query = "insert into doctor(name,address,dob,gender,email,hospital,speciality,mobile,username,password) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, dob);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, hospital);
			ps.setString(7, speciality);
			ps.setString(8, mobile);
			ps.setString(9, username);
			ps.setString(10, password);

			System.out.println(query);
			System.out.println(doc);

			ps.executeUpdate();

			newuser = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e.getMessage());
		}
		return newuser;

	}
}