package mini.kh1.corona.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mini.kh1.corona.model.vo.user.User;
import java.awt.Color;

public class LoginPage extends JFrame implements ActionListener {

	private JFrame frame = new JFrame();
	private Container container = getContentPane();
	public static boolean loginSession = false;	//로그인 세션 유지함을 위함
	public static int sessionNum=0;

	// 레이블
	private JLabel userLabel = new JLabel("아이디");
	private JLabel passwordLabel = new JLabel("비밀번호");

	// 필드부
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();

	// 버튼
	private JButton loginButton = new JButton("로그인");
	private JButton insertButton = new JButton("회원가입");

	// 체크박스
	private JCheckBox showPassword = new JCheckBox("보이기");

	// 메인 타이틀
	private final JPanel panel = new JPanel();
	private final JLabel label = new JLabel("코로나 백신 예약 프로그램");
	
	private Image image;
	
	public LoginPage() {

		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setTitle("로그인 페이지");
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("./image//background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		container = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Dimension d = container.getSize();
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}
			
		};

		container.setSize(900, 600);
		container.setLayout(null);
		container.setVisible(true);// 첫 패널만 일단 보이게 해놓음

		frame.getContentPane().add(container); // 패널로 컴포넌트를 감싸 놓음.
		label.setForeground(new Color(25, 25, 112));

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("NanumGothic", Font.BOLD, 30));
		label.setBounds(262, 120, 376, 75);

		container.add(label);

		//panel.setBounds(304, 233, 292, 199);

		container.add(panel);
		container.setLayout(null);
		userLabel.setForeground(new Color(25, 25, 112));

		userLabel.setFont(new Font("NanumGothic", Font.BOLD, 18));
		userLabel.setBounds(311, 222, 100, 30);
		container.add(userLabel);
		passwordLabel.setForeground(new Color(25, 25, 112));

		passwordLabel.setFont(new Font("NanumGothic", Font.BOLD, 18));
		passwordLabel.setBounds(311, 268, 100, 30);
		container.add(passwordLabel);

		userTextField.setBounds(426, 222, 150, 30);
		container.add(userTextField);

		passwordField.setBounds(426, 268, 150, 30);
		container.add(passwordField);
		showPassword.setBackground(Color.WHITE);

		showPassword.setBounds(426, 300, 150, 30);
		container.add(showPassword);

		loginButton.setBounds(313, 384, 100, 30);

		container.add(loginButton);

		// insertButton
		insertButton.setBounds(478, 384, 100, 30);
		container.add(insertButton);

		// 버튼 이벤트
		loginButton.addActionListener(this);
		showPassword.addActionListener(this);
		insertButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) 
		{
			String userText = userTextField.getText();
			String pwdText = passwordField.getText();
			loginSession = true;
			loginSuccess(userText, pwdText);
			
		}
		if (e.getSource() == insertButton) 
		{
			new InsertPage(); // 클릭시, 회원가입 창으로 넘어가게
			frame.setVisible(false);
		}
		if (e.getSource() == showPassword) {
			if (showPassword.isSelected()) { // 비밀번호 보이기 토글 세팅
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*'); // 비밀번호 감추기
			}
		}

	}// action
	public void loginSuccess(String userText, String pwdText)
	{
		
		//기존 가입된 회원 + 새로 가입한 회원 리스트를 받아옴
		List<User> uList = new ArrayList<User>();
		uList = InsertPage.temp.getJoinlist();

		int result = 0;

		for (int i = 0; i < uList.size(); i++) {

			String inputId = uList.get(i).getId();
			String inputPw = uList.get(i).getPassword();

			if (inputId.equals(userText) && inputPw.equals(pwdText)) {
				result = 1;
				sessionNum = i;
				break;
			} else {// else문 있어도 되고 없어도 됨
				result = 0;
			}

		}
		
		System.out.println("result값 : " + result);
		//result 가 1 이면 로그인 성공
		if (result == 1) {

			JOptionPane.showMessageDialog(this, "로그인에 성공했습니다.");
			new MainMenu();
			frame.setVisible(false);
			loginSession = true;
		//입력한 값이 관리자 아이디와 비밀번호면 result 를 2 로 바꿔서 관리자 로그인이 되게 함
		}else if(userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("12345")) {
			result = 2;
		}
		
		//result 가  0 이면 로그인 실패
		if (result == 0) {

			JOptionPane.showMessageDialog(this, "로그인에 실패했습니다.");
		}		
		
		//result 가 2 이면 관리자 로그인 성공
		if (result == 2) 
		{
			JOptionPane.showMessageDialog(this, "관리자 로그인에 성공했습니다."); // 성공 메세지!!
			try {
				new ManagerView();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setVisible(false);
		}

	
	}
	public void setsn(int sNo) {
	      sessionNum = sNo;
	   }

	   public static int getsn() {
	      return sessionNum;
	   }
	

	
}
