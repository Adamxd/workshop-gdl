package com.recluit.workshop.communication.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Connect {
	
	private BufferedReader reader;
	private String result="";
	private boolean done = false;
	
	public String establishConnection(String msg){
		try{
			done = false;
			Socket s = new Socket("127.0.0.1",3550);
			InputStreamReader stream = new InputStreamReader(s.getInputStream());
			reader = new BufferedReader(stream);
			Thread t = new Thread(new IncomingMessageReader());
			t.start();
			OutputStream out = s.getOutputStream();
			//out.write('\n');
			out.write(msg.getBytes());
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
