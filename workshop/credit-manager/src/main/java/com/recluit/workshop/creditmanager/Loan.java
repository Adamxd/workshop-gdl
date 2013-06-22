package com.recluit.workshop.creditmanager;
public class Loan {
	
	private String bank_code;
	private String rfc;
	private String name;
	private String address;
	private String amount;
	private String date;
	
	

	public Loan(String bank_code, String rfc, String name, String address,
			String amount, String date, String qualification, String active) {
		super();
		this.bank_code = bank_code;
		this.rfc = rfc;
		this.name = name;
		this.address = address;
		this.amount = amount;
		this.date = date;
		this.qualification = qualification;
		this.active = active;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	private String qualification;
	private String active;
}
