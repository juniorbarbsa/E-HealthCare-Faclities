package com.ehc.bean;

public class Patientuser {
	String uname;
	String password;

	public Patientuser() {
		// TODO Auto-generated constructor stub
	}

	public Patientuser(String uname, String password) {
		super();
		this.uname = uname;
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Patientuser [uname=" + uname + ", password=" + password + "]";
	}

}