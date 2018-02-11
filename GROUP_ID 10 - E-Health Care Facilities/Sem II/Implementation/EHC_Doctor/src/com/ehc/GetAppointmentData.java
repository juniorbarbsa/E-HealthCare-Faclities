package com.ehc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ehc.bean.Appointment;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class GetAppointmentData {
	public static  Appointment appt[] = new Appointment[5];
public static Appointment getData(String doctor_id){
		
		
		Statement stmt;
		ResultSet rs=null;
		int i=0;
		Appointment appointment=null;
		try {
			
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM appointment WHERE doctor_id = '"+ doctor_id + "' AND status IS NULL";
//			System.out.println(query);
			rs = stmt.executeQuery(query);
			while(rs.next()){		
				
				
				appointment = new Appointment();
				appointment.setAppointment_id(Integer.parseInt(rs.getString("appointment_id")));
				appointment.setDoctor_id(Integer.parseInt(rs.getString("doctor_id")));
				appointment.setPatient_id(Integer.parseInt(rs.getString("patient_id")));
				appointment.setPatient_name(rs.getString("patient_name"));
				appointment.setSpeciality(rs.getString("speciality"));
				appointment.setAppointment_date(rs.getString("appointment_date"));
				appointment.setTime(rs.getString("time"));
				appointment.setStatus(rs.getString("status"));
//				System.out.println(query);
				System.out.println("appointment ="+ appointment);
//				System.out.println("i="+i);
				appt[i]=appointment;
//				System.out.println("appt ="+ appt[i]);
				i++;
		  } 
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		System.out.println("aapt= ");
//		for (int j =0 ;j<i;j++)
//		System.out.println(appt[j]);
		
		return appointment;

	
	}

public static Appointment Data(String x)
{
	int n = Integer.parseInt(x);
	/*System.out.println("n="+n);
	System.out.println("appt[n]="+appt[n]);*/
	Appointment data = new Appointment ();
	data = appt[n];
//	System.out.println("data="+data);
	return data;

}
}
