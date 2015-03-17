package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	/**
	 * Main to run the client setup
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice;
		String userName="";
		System.out.println("Enter Choice 1.Register 2.Send Message");
		Scanner scanner = new Scanner(System.in);
		choice=scanner.nextInt();
		scanner.nextLine();
		Socket soc = intializeClient(); //intitalize the reader side at client
		
		while(choice >0 && choice <5){
			switch(choice){
				case 1 	: 	System.out.println("Enter UserName");
							userName=scanner.nextLine();
							Register reg=new Register(userName);
							System.out.println("UserRegister "+ reg.register(soc));
							break;
				case 2  :	if(!userName.equals("")){
								Login log=new Login(userName);
								log.chat(soc);
							}
							
							break;
	    
			}
			System.out.println("Enter Choice");
			choice=scanner.nextInt();
			scanner.nextLine();
		}
		
	}

	/**
	 * Initialize the reader thread and set up writer socket
	 * @return soc at which client can write from
	 */
	private static Socket intializeClient() {
		Socket soc = new Socket();
		try {
			soc.connect(new InetSocketAddress("localhost" , 5011));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reader readRunnable = new Reader(soc); 
		Thread readThread = new Thread(readRunnable);
		readThread.start();
		return soc;
	}

}
