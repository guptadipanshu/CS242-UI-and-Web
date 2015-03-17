package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Login {
	private String clientName;
	
	/**
	 * Store client name
	 * @param name
	 */
	public Login(String name) {
		clientName=name;
	}
	public String getAllClient(Socket soc){
		BufferedReader sIn = null;
		PrintWriter sOut = null;
		String response="";
		try{
			sOut = new PrintWriter( soc.getOutputStream(), true);
			sIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		    String message = "REQUEST,"+ clientName+","+soc.getLocalPort();
		    sOut.println( message ); 
		    response = sIn.readLine();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	    return response;
	}
	/**
	 * Initialize a thread to write in background
	 * @param soc write socket
	 */
	public void chat(Socket soc,String recName,String msg,String msgType){
		
		Writer writeRunnable = new Writer(clientName,soc,recName,msg,msgType); 
		Thread writeThread = new Thread(writeRunnable);
		writeThread.start();
		try {
			writeThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 
}
