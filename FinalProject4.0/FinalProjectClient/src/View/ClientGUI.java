package View;

import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import controller.Login;
import controller.Register;


public class ClientGUI extends JFrame implements ActionListener
{
	  JPanel pane = new JPanel();
	  JButton registerBtn=new JButton("REGISTER");
	  JButton chatBtn=new JButton("CHAT");
	  JButton historyBtn=new JButton("HISTORY");
	  JLabel registerText = new JLabel("");
	  JRadioButton  radioBtn[]=new JRadioButton[20];
	  TextField userName;
	  JTextArea chatText;
	  Socket socket;
	  String name;
	  // the frame constructor method
	  /**
	   * Constructor of the GUI view
	 * @param soc socket address for the GUI 
	 */
	public ClientGUI(Socket soc) { 
		super("My Simple Frame"); 
		setBounds(100,100,500,500);
	    socket=soc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container con = this.getContentPane(); // inherit main frame
	    con.add(pane); // add the panel to frame
	    // customize panel here
	    ButtonGroup group = new ButtonGroup();
	    for(int i=0;i<20;i++)
	    {	radioBtn[i]=new JRadioButton("OFFLINE");
	    	group.add(radioBtn[i]);
	    	radioBtn[i].addActionListener(this);
	    	pane.add(radioBtn[i]);
	    	
	    }
	    chatText=new JTextArea(5,25);
	    userName=new TextField("Name",30);
	    registerBtn.addActionListener(this);
	    chatBtn.addActionListener(this);
	    chatBtn.setLocation(0, 200);
	    historyBtn.addActionListener(this);
	    pane.add(registerBtn);
	    pane.add(registerText);
	    pane.add(userName);
	    pane.add(chatBtn);
	    
	    
	    
	    
	    
	    setVisible(true); // display this frame
	  }
	  
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		if (source == registerBtn){
			register();
	    }
		chatPress(source);
		historyChat(source);
		
	}
	/**
	 * Get the history from server
	 * @param source button 
	 */
	private void historyChat(Object source) {
		if(source== historyBtn && name !=""){
			for(int i=0;i<20;i++){
				if(radioBtn[i].isSelected() && !radioBtn[i].getText().equals("OFFLINE")){ 
					String message=userName.getText();
					String reciver=radioBtn[i].getText();
					Login log=new Login(name);
					log.chat(socket, reciver, message,"GET");
					break;
				}
			}
		}
	}
	/**
	 * Start a P2P chat with server
	 * @param source : chat button
	 */
	private void chatPress(Object source) {
		if(source== chatBtn && name !=""){
			setUpClients();
			for(int i=0;i<20;i++){
				if(radioBtn[i].isSelected() && !radioBtn[i].getText().equals("OFFLINE")){ 
					String message=userName.getText();
					String reciver=radioBtn[i].getText();
					Login log=new Login(name);
					log.chat(socket, reciver, message,"PUT");
					break;
				}
			}
		}
	}

	/**
	 *Get all the client info from controller 
	 */
	private void setUpClients() {
		Login log=new Login(name);
		String allClient=log.getAllClient(socket);
		int i=0;
		
		for (String retval: allClient.split("-")){
		     System.out.println("got "+retval);
		     radioBtn[i].setText(retval);
		     i++;
		  }
	}

	/**
	 * Function to register the client
	 * Call the controller
	 */
	private void register() {
		name=userName.getText();
		Register reg=new Register(name);
		registerText.setText("Trying Registration!");
		boolean message=reg.register(socket);
		registerMsg(message);
		if(message==true){
			registerText.setText("Registerd "+name);
			pane.remove(registerBtn);
			userName.setText("Enter Message");
			pane.add(chatBtn);
			pane.add(chatText);
			pane.add(historyBtn);
			setUpClients();
		}
		else
			registerText.setText("RETRY !");
	}
	
	public void registerMsg(boolean message){
		JOptionPane.showMessageDialog(null,"Register="+message,"Message Dialog",
									JOptionPane.PLAIN_MESSAGE);
		setVisible(true);  // show something
	}

	/**
	 * Called from the model CLient.java to update the view
	 * @param data
	 */
	public void printRecvMsg(String data) {
		// TODO Auto-generated method stub
		if(data!=null){
			String text=chatText.getText();
			text=text+"\n"+data;
			chatText.setText(text);
		}
	}
	
}
