package com.ehc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ehc.bean.Appointment;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GetConfirmedAppointment {
	public static  Appointment todayAppt[] = new Appointment[10];
	public static  Appointment confirmedAppt[] = new Appointment[10];
	
	
	public static int getConfirmedCount(String doc_id)
	{
		
		Statement stmt;
		ResultSet rs=null;
		int confirm_count = 0;
		String c = "Confirmed";
		int id = Integer.parseInt(doc_id);
		try {
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM appointment WHERE doctor_id = '"+ id + "' AND status ='"+c+"'";
//			System.out.println(query);
			rs = stmt.executeQuery(query);
			//System.out.println(query);
			while(rs.next()){
				confirm_count++;
			
				System.out.println(confirm_count);
			}
		}
	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("confirm_count="+confirm_count);
		return confirm_count;
	}
	
	
	public static int getTodaysCount(String doc_id)
	{
		
		Statement stmt;
		ResultSet rs=null;
		int today_count = 0;
		String c = "Confirmed";
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	
    	/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	System.out.println("TIme = "+ sdf.format(cal.getTime()) );
    	String time = sdf.toString();
	*/
    	String pattern = "dd/MM/yyyy";
		 String today =new SimpleDateFormat(pattern).format(new Date());
		int id = Integer.parseInt(doc_id);
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); 
		
		String time;
		 today = today.toString();
		
		try {
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM appointment WHERE doctor_id = '"+ id + "' AND appointment_date = '"+today+"' AND status = '"+c+"'";
			rs = stmt.executeQuery(query);
			System.out.println(query);
			
			while(rs.next()){
				
				time = rs.getString("time");
				System.out.println(time);
				
				Date d = dateFormat.parse(time);
				System.out.println("compare time : "+ date.compareTo(d));
				//System.out.println((rs.getString("status")).compareTo("Confirmed"));
				
				if(today.compareTo(rs.getString("appointment_date"))==0)
						{
							today_count++;
						}
			//System.out.println("today_count="+today_count);
			}
		}
	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
		System.out.println("today_count="+today_count);
		return today_count;
	}
	
	
	public static Appointment getConfirmApptointment(String doctor_id){
			
			
			Statement stmt;
			ResultSet rs=null;
			String c = "Confirmed";
			int i=0,j=0;
			
			 String pattern = "dd/MM/yyyy";
			 String today =new SimpleDateFormat(pattern).format(new Date());
			 System.out.println("Todays date="+today);
			 
			 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 
			 Appointment appointment=null;
			 
			
			 try {
				
				Connection connection = GetConnection.getCon();
				stmt = (Statement) connection.createStatement();
				String query = "SELECT * FROM appointment WHERE doctor_id = '"+ doctor_id + "' AND status = '"+c+"'";
//				System.out.println(query);
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
					
					/*String appt_date = rs.getString("time");
					Date d = dateFormat.parse(appt_date);
					System.out.println("comparing confirmed dates:"+appt_date.compareTo(today));*/
					confirmedAppt[j]=appointment;
					j++;
			  } 
			}
				catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
			
			return appointment;

		
		}

	
	
	public static Appointment getTodayApptointment(String doctor_id){
		
		
		Statement stmt;
		ResultSet rs=null;
		String c = "Confirmed";
		int i=0,j=0;
		 String pattern = "dd/MM/yyyy";
		 String today =new SimpleDateFormat(pattern).format(new Date());
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			
		 Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	//System.out.println("TIme = "+ sdf.format(cal.getTime()) );
	    	String time = sdf.toString();
	    	
		 System.out.println("Todays date="+today);
		Appointment today_appointment=null;
		try {
			
			Connection connection = GetConnection.getCon();
			stmt = (Statement) connection.createStatement();
			String query = "SELECT * FROM appointment WHERE doctor_id = '"+ doctor_id + "' AND status = '"+c+"' ORDER BY time ASC";
//			System.out.println(query);
			rs = stmt.executeQuery(query);
			while(rs.next()){		
				
				
				time = rs.getString("time");
				Date d = dateFormat.parse(time);
				System.out.println("compare timing : "+ date.compareTo(d));
				
				today_appointment = new Appointment();
				today_appointment.setAppointment_id(Integer.parseInt(rs.getString("appointment_id")));
				today_appointment.setDoctor_id(Integer.parseInt(rs.getString("doctor_id")));
				today_appointment.setPatient_id(Integer.parseInt(rs.getString("patient_id")));
				today_appointment.setPatient_name(rs.getString("patient_name"));
				today_appointment.setSpeciality(rs.getString("speciality"));
				today_appointment.setAppointment_date(rs.getString("appointment_date"));
				today_appointment.setTime(rs.getString("time"));
				today_appointment.setStatus(rs.getString("status"));

//				System.out.println("appointment ="+ appointment);
				System.out.println("compare="+time.compareTo(rs.getString("time")));
				if(today.compareTo(rs.getString("appointment_date"))==0 && date.compareTo(d) <= 1)
						{
						//&& time.compareTo(rs.getString("time")) <= 0
							todayAppt[i]=today_appointment;
							i++;
						}
		
		  } 
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return today_appointment;

	
	}
	public static Appointment getTodaysAppt(String x)
	{
		int n = Integer.parseInt(x);

		Appointment data = new Appointment ();
		data = todayAppt[n];
//		System.out.println("data="+data);
		return data;

	}
	public static Appointment getConfirmed(String x)
	{
		int n = Integer.parseInt(x);

		Appointment data = new Appointment ();
		data = confirmedAppt[n];
//		System.out.println("data="+data);
		return data;

	}
}
