package model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import controller.Reader;


import View.ClientGUI;


public class Client {

	/**
	 * Main to run the client setup
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice;
		String userName="";
		Socket soc = new Socket();
		Reader readRunnable = new Reader(soc);
		intializeClient(readRunnable,soc); //intitalize the reader side at client
		ClientGUI clientView=new ClientGUI(soc);
		while(true){
			String data=readRunnable.getData();
			if(data!=""){
				//update GUI
				System.out.println("non null");
				clientView.printRecvMsg(data);
			}
		}
		/*
		System.out.println("Enter Choice 1.Register 2.Send Message");
		Scanner scanner = new Scanner(System.in);
		choice=scanner.nextInt();
		scanner.nextLine();
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
		*/
		
	}

	/**
	 * Initialize the reader thread and set up writer socket
	 * @return soc at which client can write from
	 */
	private static void intializeClient(Reader readRunnable,Socket soc) {
		try {
			soc.connect(new InetSocketAddress("localhost" , 5011));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread readThread = new Thread(readRunnable);
		readThread.start();
		
	}

}
