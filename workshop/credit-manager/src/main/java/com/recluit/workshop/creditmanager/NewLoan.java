package com.recluit.workshop.creditmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.recluit.workshop.creditmanager.db.DBConnect;
import com.recluit.workshop.creditmanager.restclient.RestClient;

public class NewLoan {
	
	public static boolean isElegible(String rfc){
		short qualification = 2;
		short loans = 1;
		String q; //use to store the qualification while looping through the result set
		DBConnect db = new DBConnect();
		Connection con = db.connectToOracle();
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			String query = "select qualification from loans where rfc ='"+rfc+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				loans++;
				q = rs.getString(1);
				if(q.equals("VERY GOOD")){
					qualification +=3;
				}
				else if(q.equals("NORMAL")){
					qualification += 2;
				}
				else {
					qualification += 1;
				}
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
		if (loans == 1){
			RestClient client = new RestClient();
			q = client.connect("qualification/",rfc);
			if(q.equals("VERY GOOD")){
				qualification =3;
			}
			else if(q.equals("NORMAL") || q.equals("Not Found")){
				qualification = 2;
			}
			else {
				qualification = 1;
			}
		}
		else{
			qualification = (short) (qualification / loans);
		}
		if (qualification > 1)
			return true;
		return false;
		
	}
}
