package mini.kh1.corona.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mini.kh1.corona.controller.user.UserList;

public class LoginPage extends JFrame implements ActionListener {

	private JFrame frame = new JFrame();
	private Container container = getContentPane();
	public static boolean loginSession = false;	//로그인 세션 유지함을 위함
	public static int sessionNum;


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
	
	public LoginPage() {

		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setTitle("로그인 페이지");
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		container = new JPanel();

		container.setSize(900, 600);
		container.setLayout(null);
		container.setVisible(true);// 첫 패널만 일단 보이게 해놓음

		frame.getContentPane().add(container); // 패널로 컴포넌트를 감싸 놓음.

		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("NanumGothic", Font.BOLD, 30));
		label.setBounds(262, 120, 376, 75);

		container.add(label);

		panel.setBounds(304, 233, 292, 199);

		container.add(panel);
		panel.setLayout(null);

		userLabel.setFont(new Font("NanumGothic", Font.BOLD, 18));
		userLabel.setBounds(0, 0, 100, 30);
		panel.add(userLabel);

		passwordLabel.setFont(new Font("NanumGothic", Font.BOLD, 18));
		passwordLabel.setBounds(0, 46, 100, 30);
		panel.add(passwordLabel);

		userTextField.setBounds(115, 0, 150, 30);
		panel.add(userTextField);

		passwordField.setBounds(115, 46, 150, 30);
		panel.add(passwordField);

		showPassword.setBounds(115, 78, 150, 30);
		panel.add(showPassword);

		loginButton.setBounds(2, 162, 100, 30);

		panel.add(loginButton);

		// insertButton
		insertButton.setBounds(167, 162, 100, 30);
		panel.add(insertButton);

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
		for(int i=0; i< UserList.UserList().size(); i++)
		{
			
			if(UserList.UserList().get(i).getId().equals(userText) && UserList.UserList().get(i).getPassword().equals(pwdText))
			{
				JOptionPane.showMessageDialog(this, "로그인에 성공했습니다."); // 성공 메세지!!
				new MainMenu();
				frame.setVisible(false);
				loginSession=true;	//세션유지하기 위해 true 바꿈
				sessionNum=i;		//arraylist<User>의 i번째 정보를 얻어내기 위한 변수
				break;
			}
		}
		
		
		
		if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("12345")) 
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
		else if (userText.equalsIgnoreCase("test") && pwdText.equalsIgnoreCase("1234")) 
		{
			JOptionPane.showMessageDialog(this, "로그인에 성공했습니다."); // 성공 메세지!!
			new MainMenu();
			frame.setVisible(false);

		} 
		else if(userText.isEmpty()==true || pwdText.isEmpty()==true)
			JOptionPane.showMessageDialog(this, "아이디, 비밀번호가 일치 하지 않습니다.");
			
	}
	public int session()
	{
		if(loginSession==true)
			return sessionNum;	//배열의 인덱스값
		else
			return 0;
		
	}

	
}
