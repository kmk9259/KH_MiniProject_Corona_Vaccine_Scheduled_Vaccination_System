package mini.kh1.corona.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mini.kh1.corona.controller.user.UserList;

public class ChatBotFrame extends JPanel implements ActionListener{
	private JTextArea chatting;
	private JTextField input;
	
	static JPanel chatPanel = new JPanel();
	private DatagramSocket socket;
	private DatagramPacket packet;
	private InetAddress address;
	
	
	

	public ChatBotFrame(InetAddress address, DatagramSocket socket) {
		this.address = address;
		this.socket = socket;
		
		input = new JTextField(60);
		chatting = new JTextArea(25, 70); // was 15,50
		JScrollPane scroll = new JScrollPane(chatting, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//chatting.setForeground(Color.red);
		chatting.setLineWrap(true);
		chatting.setWrapStyleWord(true);
		chatting.setEditable(false);
		

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		
		chatPanel.add(scroll);
		chatPanel.add(input);
		chatPanel.add(sendButton);
		menual();
		
		chatPanel.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = input.getText();
		byte[] buffer = str.getBytes(); // udp 방식은 byte로
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, 8500);

		try {
			socket.send(sendPacket);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		faq(str);
		input.setText("");
		input.requestFocus();
		input.selectAll();
		
	}
	public void process() {
		while (true) {
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			
			try {
				socket.receive(packet);
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	public void menual()
	{
		//chatting.append(+"님, 안녕하세요 :)\n\n");
		chatting.append("궁금한 사항을 입력해주세요.\n");
		chatting.append("/FAQ\n");
		chatting.append("/메인화면\n");
		
		chatting.append("======================================================"+
				"========================================================\n");
		
	}

	
	public void faq(String str)
	{
		if(str.equals("/faq") || str.equals("/FAQ")) 
		{ 
			
			chatting.append("FAQ (번호를 골라주세요)\n\n");
			chatting.append("1. 백신의 보호 기능은 얼마나 오래 지속 됩니까?\n");
			chatting.append("2. 백신을 맞은 후 나타나는 가장 일반적인 부작용은 무엇입니까?\n\n");
		}
		else if(str.equals("/1") ||str.equals("1") || str.equals("1번")) 
		{
			chatting.append("===============================================Question No.1===================================================\n");
			chatting.append("1. 백신의 보호 기능은 얼마나 오래 지속 됩니까?\n\n"+
			"백신 접종자의 면역력이 얼마나 오래 지속되는지에 대한 정보는 없습니다.\n"+
			"우리가 아는 것은 COVID-19가 많은 사람에게 매우 심각한 질병과 사망을 초래했다는 것입니다.\n"+
			"COVID-19 백신을 접종하는 것이 더 안전한 선택입니다.\n"+
			"자연 면역과 백신 유도 면역에 관해 더 많이 알아내기 위해 전문가들이 연구 중입니다.\nCDC는 새로운 증거가 확보되는 대로 계속해서 일반에 공개할 것입니다.\n");
			
		}
		else if(str.equals("/2") ||str.equals("2") || str.equals("2번")) 
		{
			chatting.append("===============================================Question No.2===================================================\n");
			chatting.append("2. 백신을 맞은 후 나타나는 가장 일반적인 부작용은 무엇입니까?\n\n"
			+"백신을 접종하고 나면 신체가 보호 기능을 구축하고 있다는 정상적인 신호인 부작용이 나타날 수 있습니다.\n"
			+"일반적인 부작용은 주사 맞은 팔의 통증, 발적, 부기 그리고 몸살, 두통, 근육통, 오한, 발열, 메스꺼움입니다.\n"
			+"이러한 부작용은 일상 활동에 지장을 주기도 하지만, 며칠 내에 사라질 것입니다.\n");
		}
		else if(str.equals("/메인화면") ||str.equals("메인화면"))
		{
			chatPanel.setVisible(false); 
			MainMenu.mainPanel.setVisible(true);
		}
		
		
	}

}
