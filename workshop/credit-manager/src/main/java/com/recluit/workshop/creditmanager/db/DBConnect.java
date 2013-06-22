package com.recluit.workshop.creditmanager.db;

import java.sql.*;
public class DBConnect {
	private Connection conn;
	
	public Connection connectToOracle(){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xD","system","91211003");
			System.out.println("Connection established");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
}