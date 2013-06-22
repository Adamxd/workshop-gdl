package com.recluit.workshop.creditmanager;

import java.sql.*;

import com.recluit.workshop.creditmanager.db.DBConnect;

public class CreateLoan extends ExampleSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rfc;
	private String fname;
	private String lname;
	private String address;
	private String amount;
	
	
	public void validate(){
		if(rfc.length() <10 ){
			addFieldError("rfc","RFC must contain 10 characters");
		}
		
		if(Float.parseFloat(amount) < 0){
			addFieldError("amount","The amount can not be less than 0");
		}
	}
	
	public String execute(){
		DBConnect c = new DBConnect();
		Connection con = c.connectToOracle();
		Statement stmt = null;
		
		try{
			stmt = con.createStatement();
			String query = "insert into loans values(loanid.nextval,'"+rfc+"','"+amount+"', 'NORMAL',sysdate+365, 'Y')";
			stmt.executeUpdate(query);
			System.out.println("Value Inserted");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt != null)
					stmt.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		return "index";
	}
	
	
	
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
