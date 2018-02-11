package com.ehc.bean;

public class Appointment {
int appointment_id;
int doctor_id;
int patient_id;
String patient_name;
String speciality;
String appointment_date;
String time;
String status;

public Appointment() {
	// TODO Auto-generated constructor stub
}

public Appointment(int appointment_id, int doctor_id, int patient_id,
		String patient_name, String speciality, String appointment_date,
		String time, String status) {
	super();
	this.appointment_id = appointment_id;
	this.doctor_id = doctor_id;
	this.patient_id = patient_id;
	this.patient_name = patient_name;
	this.speciality = speciality;
	this.appointment_date = appointment_date;
	this.time = time;
	this.status = status;
}

public int getAppointment_id() {
	return appointment_id;
}

public void setAppointment_id(int appointment_id) {
	this.appointment_id = appointment_id;
}

public int getDoctor_id() {
	return doctor_id;
}

public void setDoctor_id(int doctor_id) {
	this.doctor_id = doctor_id;
}

public int getPatient_id() {
	return patient_id;
}

public void setPatient_id(int patient_id) {
	this.patient_id = patient_id;
}

public String getPatient_name() {
	return patient_name;
}

public void setPatient_name(String patient_name) {
	this.patient_name = patient_name;
}

public String getSpeciality() {
	return speciality;
}

public void setSpeciality(String speciality) {
	this.speciality = speciality;
}

public String getAppointment_date() {
	return appointment_date;
}

public void setAppointment_date(String appointment_date) {
	this.appointment_date = appointment_date;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

@Override
public String toString() {
	return "Appointment [appointment_id=" + appointment_id + ", doctor_id="
			+ doctor_id + ", patient_id=" + patient_id + ", patient_name="
			+ patient_name + ", speciality=" + speciality
			+ ", appointment_date=" + appointment_date + ", time=" + time
			+ ", status=" + status + "]";
}


}
