package mini.kh1.corona.model.vo;

import java.net.BindException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import mini.kh1.corona.view.ChatBotView;

public class Chatbot {
	private ChatBotView f;
	public static DatagramSocket socket;
	public static InetAddress address;
	int port=8500;

	public Chatbot() {

		try {
			address = InetAddress.getByName("192.168.1.5");
			socket= new DatagramSocket(port);
			//cmd에서 netstat -n pid 번호
			//taskkill /f /pid pid번호
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
