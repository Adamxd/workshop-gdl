package com.recluit.workshop.communication.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.Socket;


@Path("/hello")
public class FetchFromCB {
	
	BufferedReader reader;
	String result="";
	boolean done = false;
	
	@GET
	@Path("/{rfc}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String establishConnection(@PathParam("rfc") String rfc){
		try{
			Socket s = new Socket("127.0.0.1",3550);
			InputStreamReader stream = new InputStreamReader(s.getInputStream());
			reader = new BufferedReader(stream);
			Thread t = new Thread(new IncomingMessageReader());
			t.start();
			OutputStream out = s.getOutputStream();
			//out.write('\n');
			out.write(rfc.getBytes());
			out.flush();
			System.out.println("about send result: "+result);
			return result;
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return "ERROR asda";
	}

	
	class IncomingMessageReader implements Runnable{

		public void run() {
			String line = null;
			try {
					while((line = reader.readLine())!= null){
					System.out.println("incoming message : " + line);
					result = result + line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}


