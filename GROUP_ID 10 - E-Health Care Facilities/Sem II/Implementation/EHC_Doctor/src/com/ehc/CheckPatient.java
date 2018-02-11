package com.ehc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ehc.bean.Patientuser;

public class CheckPatient {
	public static boolean check(Patientuser patient) {
		ResultSet rs;
		Statement stmt;
		boolean exists = false;
		try {

			Connection connection = GetConnection.getCon();
			stmt = connection.createStatement();
			String query = "SELECT * FROM person_info WHERE uname = '"
					+ patient.getUname() + "' AND password = '"
					+ patient.getPassword() + "'";
			rs = stmt.executeQuery(query);
			System.out.println(query);
			/*System.out.println("Result - " + rs.getString("uname") + " "
					+ rs.getString("password"));*/

			if (rs.next()) {
				
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e.getMessage());
		}

		return exists;
	}
}
