package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author dipanshu
 *Reader class that always run a thread in background to read the data
 */
public class Reader implements Runnable  {
	private int port=3008;
	public Reader(Socket socket) {
		// TODO Auto-generated constructor stub
		//port=socket.getPort();
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * use a runnable thread in background to initiate a server client talk
	 */
	@Override
	public void run() {
		try{
		 ServerSocket listener = new ServerSocket(); // don't bind just yet
		 listener.setReuseAddress(true);
		 listener.bind(new InetSocketAddress(12346)); // can bind with reuse= true
		 while (true) {
             Socket socket = listener.accept();
             BufferedReader sIn = null;
 		     sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 		     String response;
		     response = sIn.readLine();
		     System.out.println("Message Recived " +response);
		     socket.close();
         }
		}
		catch(IOException e){
	         e.printStackTrace();
	    }
		
	}

}
