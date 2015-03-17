package client;

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
	
	/**
	 * Initialize a thread to write in background
	 * @param soc write socket
	 */
	public void chat(Socket soc){
		Writer writeRunnable = new Writer(clientName,soc); 
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
