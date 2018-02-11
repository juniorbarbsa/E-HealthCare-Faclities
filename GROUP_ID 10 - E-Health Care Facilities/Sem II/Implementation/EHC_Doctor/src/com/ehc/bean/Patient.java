package com.ehc.bean;

public class Patient {
  String name;
  String address;
  String age;
  String bg;
  String gender;
  String dob;
  String mob;
  String email;
  int id;
  
public Patient() {
	
	// TODO Auto-generated constructor stub
}

public Patient(String name, String address, String age, String bg,
		String gender, String dob, String mob, String email, int id) {
	super();
	this.name = name;
	this.address = address;
	this.age = age;
	this.bg = bg;
	this.gender = gender;
	this.dob = dob;
	this.mob = mob;
	this.email = email;
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

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public String getBg() {
	return bg;
}

public void setBg(String bg) {
	this.bg = bg;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getMob() {
	return mob;
}

public void setMob(String mob) {
	this.mob = mob;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

@Override
public String toString() {
	return "Patient [name=" + name + ", address=" + address + ", age=" + age
			+ ", bg=" + bg + ", gender=" + gender + ", dob=" + dob + ", mob="
			+ mob + ", email=" + email + ", id=" + id + "]";
}


}
