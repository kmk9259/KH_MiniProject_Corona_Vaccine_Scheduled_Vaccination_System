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

import mini.kh1.corona.view.ChatBot.ChatBotView;

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
			//runCMD();
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


//	public void runCMD()
//	{
//		Runtime re = Runtime.getRuntime();
//		Process p;
//		try {
//			p=re.exec("cmd /c netstat -ano  | findstr 8500");
//			InputStream in = p.getInputStream();
//			InputStreamReader isr = new InputStreamReader(in);
//			//System.out.println(isr.getEncoding()+"d");
//			
//			BufferedReader br = new BufferedReader(isr);
//			String line;
//			while((line = br.readLine())!=null)
//			{
//				//System.out.println(line+"자르기 전");
//				if(line.contains("0:8500"))
//				{
//					line = line.substring(70, 75);	//0~3까지 
//					//line = line.trim();
//					System.out.println(line);
//					p=re.exec("cmd /c taskkill /f /pid"+line);
//				}
//				
//			}
//			in.close();
//		}catch (IOException e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		
//	}


}
