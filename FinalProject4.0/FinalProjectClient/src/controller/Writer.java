package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author dipanshu
 *Writer calss to write data to server
 */
public class Writer implements Runnable {

	private String clientName,recvName,recvMsg,msgType;
	private Scanner scanner;
	private Socket soc;
	public Writer(String name,Socket socket,String recName,String msg,String mgType) {
		// TODO Auto-generated constructor stub
		clientName=name;
		scanner = new Scanner(System.in);
		soc=socket;
		recvName=recName;
		recvMsg=msg;
		msgType=mgType;
	}
	@Override
	public void run() {
		writeData();
	}
	/**
	 * Write the data to server
	 */
	private void writeData() {
		try
	      {
			PrintWriter sOut = null;
		    BufferedReader sIn = null;
		    sIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		    sOut = new PrintWriter( soc.getOutputStream(), true);
		    String message;
		    //String response;
		    //response = sIn.readLine();
		    //System.out.println("Select Name " +response);
		    //String recvName=scanner.nextLine();
		    //System.out.println("Enter Message for " +response);
		    //String recvMsg=scanner.nextLine();
		    message = msgType+","+clientName+","+recvName+","+recvMsg+","+soc.getLocalPort();
		    sOut.println( message );
		   
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
	}
