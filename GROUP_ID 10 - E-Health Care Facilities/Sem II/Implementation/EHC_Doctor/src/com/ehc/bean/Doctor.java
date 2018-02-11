package com.ehc.bean;



public class Doctor {

	String name;
	String address;
	String gender;
	String hospital;
	String email;
	String mobile;
	String dob;
	String speciality;
	String username;
	String password;
	int id;
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	public Doctor(String name, String address, String gender, String hospital,
			String email, String mobile, String dob, String speciality,
			String username, String password, int id) {
		super();
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.hospital = hospital;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.speciality = speciality;
		this.username = username;
		this.password = password;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Doctor [name=" + name + ", address=" + address + ", gender="
				+ gender + ", hospital=" + hospital + ", email=" + email
				+ ", mobile=" + mobile + ", dob=" + dob + ", speciality="
				+ speciality + ", username=" + username + ", password="
				+ password + ", id=" + id + "]";
	}

	
	
}
	