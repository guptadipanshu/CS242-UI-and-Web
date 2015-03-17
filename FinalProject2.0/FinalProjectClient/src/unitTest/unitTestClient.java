package unitTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.Test;

import client.Register;
import static org.junit.Assert.*;

public class unitTestClient {
	@Test		
	public void testRegister() {
		Socket soc = new Socket();
		try {
			soc.connect(new InetSocketAddress("localhost" , 5011));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Register reg=new Register("ABCA");
		assertEquals(true,reg.register(soc));
	}
	@Test		
	public void testDoubleRegister() {
		Socket soc = new Socket();
		try {
			soc.connect(new InetSocketAddress("localhost" , 5011));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Register reg=new Register("ABCA");
		reg.register(soc);
		assertEquals(true,reg.register(soc));
	}
	@Test		
	public void testWrite() {
		Socket soc = new Socket();
		try {
			soc.connect(new InetSocketAddress("localhost" , 5011));
			Register reg=new Register("ABCA");
			reg.register(soc);
			PrintWriter sOut = null;
		    BufferedReader sIn = null;
		    sIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		    sOut = new PrintWriter( soc.getOutputStream(), true);
		    sOut.println("REQUEST,"+ "ABCA"+","+soc.getLocalPort());
		    String response = sIn.readLine();
		    assertEquals("ABCA",response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
