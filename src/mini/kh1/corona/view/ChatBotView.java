package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mini.kh1.corona.model.vo.Chatbot;

public class ChatBotView extends JPanel implements ActionListener{
	private static JTextArea chatting;
	private JTextField input;
	private static DatagramSocket socket;
	private static DatagramPacket packet;
	private static InetAddress address;
	
	LoginPage loginpage;
	
	private Image image;
	
	public ChatBotView() {
		Chatbot c = new Chatbot();
		this.address = c.address;
		this.socket = c.socket;
		setVisible(false);
		setLayout(null);

		input = new JTextField(60);
		input.setBounds(88, 510, 627, 31);
		chatting = new JTextArea(30, 70); // was 15,50
		chatting.setEditable(false);
		chatting.setSize(748, 428);
		chatting.setLocation(70, 56);
		//chatting.setBounds(600, 700, 500, 40);
		
		JScrollPane scroll = new JScrollPane(chatting, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setSize(717, 413);
		scroll.setLocation(88, 80);

		chatting.setLineWrap(true);
		chatting.setWrapStyleWord(true);
		chatting.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));//######글씨체 변경
		scroll.setVisible(true);


		JButton sendButton = new JButton("Send");
		sendButton.setSize(92, 31);
		sendButton.setLocation(713, 510);
		sendButton.addActionListener(this);
		sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		sendButton.setForeground(Color.WHITE);
		sendButton.setOpaque(true);
		sendButton.setBackground(Color.BLACK);
		sendButton.setFocusPainted(false);
		
		add(scroll);
		add(input);
		add(sendButton);
		menual();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = input.getText();
		byte[] buffer = str.getBytes(); // udp 방식은 byte로
		// 6. DatagramPacket에 각 정보를 담고 전송 (Client -> Server)
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, address, 8500);

		try {
			socket.send(sendPacket);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		input.setFont(new Font("휴먼엑스포", Font.PLAIN, 13));//######글씨체 변경
		input.setText("");
		input.requestFocus();
		input.selectAll();
		faq(str);

	}
	public static void process() {
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
		chatting.append(" "+InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getName()+"님, 안녕하세요 :)\n\n");
		chatting.append(" "+"궁금한 사항을 입력해주세요.\n");
		chatting.append(" "+"/FAQ\n");
		chatting.append(" "+"/메인화면\n");
		
		chatting.append("=================================="+
				"=====================================================\n");	
	}

	
	public void faq(String str)
	{
		
		switch (str) {
		case "/faq" :
		case "/FAQ" :
			chatting.append("FAQ (번호를 골라주세요)\n\n");
			chatting.append("1. 백신의 보호 기능은 얼마나 오래 지속 됩니까?\n");
			chatting.append("2. 백신을 맞은 후 나타나는 가장 일반적인 부작용은 무엇입니까?\n");
			chatting.append("3. 현재 코로나에 걸린 상태에서 코로나 백신 접종을 받을 수 있습니까??\n\n");
			break;
		case "/1" :
			chatting.append("================================Question No.1==================================\n");
			chatting.append("1. 백신의 보호 기능은 얼마나 오래 지속 됩니까?\n\n"+
			"백신 접종자의 면역력이 얼마나 오래 지속되는지에 대한 정보는 없습니다.\n"+
			"우리가 아는 것은 COVID-19가 많은 사람에게 매우 심각한 질병과 사망을 초래했다는 것입니다.\n"+
			"COVID-19 백신을 접종하는 것이 더 안전한 선택입니다.\n"+
			"자연 면역과 백신 유도 면역에 관해 더 많이 알아내기 위해 전문가들이 연구 중입니다.\nCDC는 새로운 증거가 확보되는 대로 계속해서 일반에 공개할 것입니다.\n");
			break;
		case "/2" :
			chatting.append("=================================Question No.2==================================\n");
			chatting.append("2. 백신을 맞은 후 나타나는 가장 일반적인 부작용은 무엇입니까?\n\n"
			+"백신을 접종하고 나면 신체가 보호 기능을 구축하고 있다는 정상적인 신호인 부작용이 나타날 수 있습니다.\n"
			+"일반적인 부작용은 주사 맞은 팔의 통증, 발적, 부기 그리고 몸살, 두통, 근육통, 오한, 발열, 메스꺼움입니다.\n"
			+"이러한 부작용은 일상 활동에 지장을 주기도 하지만, 며칠 내에 사라질 것입니다.\n");
			break;
		case "/3" :
			chatting.append("=================================Question No.3===================================\n");
			chatting.append("3. 현재 코로나에 걸린 상태에서 코로나 백신 접종을 받을 수 있습니까??\n\n"
			+"아니요. 증상이 있는 COVID-19 감염자는 질병에서 회복되고 격리 해제 기준을 충족할 때까지 백신 접종을 연기해야 합니다.\n"
			+"무증상 감염자도 기준을 충족할 때까지 기다린 후 백신 접종을 받아야 합니다. \n"
			+"이 지침은 2차 백신 접종을 받기 전에 COVID-19에 걸리는 사람에게도 적용됩니다.\n");
			break;
			
		case "/메인화면" :
			socket.close();
			setVisible(false); 
			MainMenu.mainPanel.setVisible(true);
			break;
		default :
			chatting.append("잘못 입력 하셨습니다.\n");
			break;
			
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		try {
			image = ImageIO.read(new File("./image//image1.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}
}