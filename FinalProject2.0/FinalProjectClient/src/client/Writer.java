package client;

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

	private String clientName;
	private Scanner scanner;
	private Socket soc;
	public Writer(String name,Socket socket) {
		// TODO Auto-generated constructor stub
		clientName=name;
		scanner = new Scanner(System.in);
		soc=socket;
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
		    String message = "REQUEST,"+ clientName+","+soc.getLocalPort();
		    sOut.println( message );
		    String response;
		    response = sIn.readLine();
		    System.out.println("Select Name " +response);
		    String recvName=scanner.nextLine();
		    System.out.println("Enter Message for " +response);
		    String recvMsg=scanner.nextLine();
		    message = "PUT,"+clientName+","+recvName+","+recvMsg+","+soc.getLocalPort();
		    sOut.println( message );
		   
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
	}
