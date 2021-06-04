package mini.kh1.corona.model.vo;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import mini.kh1.corona.view.ChatBotFrame;

public class Chatbot {
	private ChatBotFrame f;
	private DatagramSocket socket;
	private InetAddress address;
	private final int PORT;

	public Chatbot(int port) {
		this.PORT = port;
		try {
			address = InetAddress.getByName("192.168.1.5");
			socket= new DatagramSocket(port);
			//cmd에서 netstat -n pid 번호
			//taskkill /f /pid 
			f=new ChatBotFrame(address,socket);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ChatBotFrame getFrame()
	{
		return f;
	}

}
