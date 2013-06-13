package com.recluit.workshop.communication.adapter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.Socket;


@Path("/searchrfc")
public class FetchFromCB {
	
	private BufferedReader reader;
	private String result="";
	private boolean done = false;
	
	@GET
	@Path("/{rfc}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String establishConnection(@PathParam("rfc") String rfc){
		try{
			done = false;
			Socket s = new Socket("127.0.0.1",3550);
			InputStreamReader stream = new InputStreamReader(s.getInputStream());
			reader = new BufferedReader(stream);
			Thread t = new Thread(new IncomingMessageReader());
			t.start();
			OutputStream out = s.getOutputStream();
			//out.write('\n');
			rfc="f"+rfc;
			out.write(rfc.getBytes());
			out.flush();
			while(!done){
				System.out.println("waiting...");
			}
			System.out.println("about send result: "+result);
			s.close();
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
					if(line.equals("EOT")){
						done=true;
						return;
					}
					result = result + line +"\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}


