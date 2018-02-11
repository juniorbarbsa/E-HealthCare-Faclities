package com.ehc;

import java.sql.PreparedStatement;

import com.ehc.bean.Appointment;
import com.mysql.jdbc.Connection;

public class UpdateAppointment {
	public static boolean update(Appointment app, String apptID) {
		PreparedStatement ps;
		int id = Integer.parseInt(apptID);
		boolean user1 = false;
		String status = app.getStatus();
		try {
			Connection connection = GetConnection.getCon();
			String query = "update appointment set status =? WHERE appointment_id = ?";

			ps = connection.prepareStatement(query);

			
			ps.setString(1,status);
			ps.setInt(2,id);
			
			System.out.println(query);
			System.out.println(app);

			ps.executeUpdate();

			user1 = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception - " + e.getMessage());
		}
		return user1;

	}
}
