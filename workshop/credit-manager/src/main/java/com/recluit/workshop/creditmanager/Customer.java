package com.recluit.workshop.creditmanager;

public class Customer {
	private String rfc;
	private String fname;
	private String lname;
	private String address;
	private String salary;
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public Customer(String rfc, String fname, String lname, String address,
			String salary) {
		this.rfc = rfc;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.salary = salary;
	}
	
	
	
}
