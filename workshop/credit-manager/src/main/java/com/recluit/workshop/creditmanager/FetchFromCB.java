package com.recluit.workshop.creditmanager;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import com.recluit.workshop.creditmanager.restclient.RestClient;

public class FetchFromCB {
	
	public static ArrayList<Loan> fetch(String rfc, ArrayList<Loan> array) throws Exception{
		RestClient client = new RestClient();
		String lines[];
		String fields[];
		lines = client.connect("searchrfc/",rfc).split("\n");
		System.out.println("lines: "+lines.length);
		for(String line:lines){
			if (line.equals("EOT") || line.equals("Not Found"))
				break;
			System.out.println("linea: "+line);
			try{
			fields = line.split("\\|");
			}
			catch(PatternSyntaxException e){
				System.out.println("error: regex");
				fields = null;
			}
			System.out.println("fields: "+fields.length);
			System.out.println(fields[0]);
			System.out.println(fields[1]);
			System.out.println(fields[2]);
			System.out.println(fields[3]);
			System.out.println(fields[4]);
			System.out.println(fields[5]);
			array.add(new Loan(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7]));
		}
		return array;
	}
}
