package com.ehc;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ehc.bean.patientRecBean;
import com.mysql.jdbc.Connection;
public class AddPrescription 
{
	public static boolean add(patientRecBean rec, String patient_id)
	{
	PreparedStatement ps;
	String med1 = rec.getMed1();
	String dosage1 = rec.getDosage1();
	String m1 = rec.getM1();
	String a1 = rec.getA1();
	String e1 = rec.getE1();
	String duration1 = rec.getDuration1();
	String med2 = rec.getMed2();
	String dosage2 = rec.getDosage2();
	String m2 = rec.getM2();
	String a2 = rec.getA2();
	String e2 = rec.getE2();
	String duration2 = rec.getDuration2();
	String med3 = rec.getMed3();
	String dosage3 = rec.getDosage3();
	String m3 = rec.getM3();
	String a3 = rec.getA3();
	String e3 = rec.getE3();
	String duration3 = rec.getDuration3();
	String med4 = rec.getMed4();
	String dosage4 = rec.getDosage4();
	String m4 = rec.getM4();
	String a4 = rec.getA4();
	String e4 = rec.getE4();
	String duration4 = rec.getDuration4();
	String med5 = rec.getMed5();
	String dosage5 = rec.getDosage5();
	String m5 = rec.getM5();
	String a5 = rec.getA5();
	String e5 = rec.getE5();
	String duration5 = rec.getDuration5();
	int pid = Integer.parseInt(patient_id);
	
	String pattern = "dd/MM/yyyy";
	 String today =new SimpleDateFormat(pattern).format(new Date());
	 today = today.toString();
	 
	boolean pres = false;
	try {
		Connection connection = GetConnection.getCon();
		String query = "insert into prescription(med1,dosage1,m1,a1,e1,duration1,"
				+ "med2,dosage2,m2,a2,e2,duration2,"
				+ "med3,dosage3,m3,a3,e3,duration3,"
				+ "med4,dosage4,m4,a4,e4,duration4,"
				+ "med5,dosage5,m5,a5,e5,duration5, "
				+ "patient_id,date)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ORDER BY date Desc";
		ps = connection.prepareStatement(query);
		
		ps.setString(1, med1);
		ps.setString(2, dosage1);
		ps.setString(3, m1);
		ps.setString(4, a1);
		ps.setString(5, e1);
		ps.setString(6, duration1);
		ps.setString(7, med2);
		ps.setString(8, dosage2);
		ps.setString(9, m2);
		ps.setString(10, a2);
		ps.setString(11, e2);
		ps.setString(12, duration2);
		ps.setString(13, med3);
		ps.setString(14, dosage3);
		ps.setString(15, m3);
		ps.setString(16, a3);
		ps.setString(17, e3);
		ps.setString(18, duration3);
		ps.setString(19, med4);
		ps.setString(20, dosage4);
		ps.setString(21, m4);
		ps.setString(22, a4);
		ps.setString(23, e4);
		ps.setString(24, duration4);
		ps.setString(25, med5);
		ps.setString(26, dosage5);
		ps.setString(27, m5);
		ps.setString(28, a5);
		ps.setString(29, e5);
		ps.setString(30, duration5);
		ps.setInt(31, pid);
		ps.setString(32, today);
		System.out.println(query);
		System.out.println(pid);
		
		ps.executeUpdate();
		pres=true;
			
	}
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("Exception - " + e.getMessage());
	}
	return pres;
	}
}
