package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Register {
	
	private String clientName;
	
	
	/**
	 * Initialize the name
	 * @param name: of the client
	 */
	public Register(String name) {
		// TODO Auto-generated constructor stub
		clientName=name;
	}
	/**
	 * Register the client with the server
	 * @param soc socket at which client writes to server
	 * @return
	 */
	public boolean register(Socket soc){
		try
	      {
			PrintWriter sOut = null;
		    BufferedReader sIn = null;
		    sIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		    sOut = new PrintWriter( soc.getOutputStream(), true);
		    
		    String message = "Register,"+ clientName+","+"12346";
		    sOut.println( message ); //make request to server
		    
		    String response;
		    response = sIn.readLine();//wait for ack
		    System.out.println(response); 
		   
		    return true;
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	         return false;
	      }
		
	}
}
