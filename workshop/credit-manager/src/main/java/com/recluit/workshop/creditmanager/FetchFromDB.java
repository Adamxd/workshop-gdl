package com.recluit.workshop.creditmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.recluit.workshop.creditmanager.db.DBConnect;

public class FetchFromDB {
	static String bank_code= "200"; 
	
	public static void fetch(String rfc, ArrayList<Loan> array) throws Exception{
		DBConnect db = new DBConnect();
		Connection con = db.connectToOracle();
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			String query = "select "+bank_code+", loans.rfc, customer.fname||' '||customer.lname AS name, customer.address, loans.loan_amount, loans.expiration_date, loans.qualification, loans.status from loans join customer on loans.rfc = customer.rfc where customer.rfc = '"+rfc+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				array.add(new Loan(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
		}
		catch (SQLException  e){
			System.out.println("SQL ERROR");
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			try{
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static boolean isCustomer (String rfc){
		DBConnect db = new DBConnect();
		Connection con = db.connectToOracle();
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			String query = "select rfc from customer where rfc = '"+rfc+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				return true;
			}
		}
		catch (SQLException  e){
			System.out.println("SQL ERROR");
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			try{
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
