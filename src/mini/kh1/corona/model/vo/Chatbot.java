package mini.kh1.corona.model.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	//cmd
	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;

	public Chatbot() {
		try {
			address = InetAddress.getByName("192.168.1.1");
			
			socket= new DatagramSocket(8500);
			
			//cmd에서 netstat -n pid 번호
			//taskkill /f /pid pid번호
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BindException e) {
			//runCMD();
			new ChatBotView();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}