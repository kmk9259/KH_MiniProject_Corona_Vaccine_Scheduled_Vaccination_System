package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mini.kh1.corona.controller.user.AddJoin;
import mini.kh1.corona.controller.user.AddSignup;
import mini.kh1.corona.model.vo.user.JoinList;
import mini.kh1.corona.model.vo.user.SignupList;
import mini.kh1.corona.model.vo.user.User;

public class ModifyPage extends JFrame implements ActionListener {

	SignupList signuplist = new SignupList();

	public static boolean loginSession = true;
	LoginPage loginpage;
	public static int sessionNum;

	private User u = new User();

	private JFrame frame = new JFrame();
	private Container container = getContentPane();

	// 레이블
	private JLabel userLabel = new JLabel("아이디");
	private JLabel passwordLabel = new JLabel("비밀번호");
	private JLabel nameLabel = new JLabel("이름");
	private JLabel ssnLabel = new JLabel("주민 번호");
	private JLabel emailLabel = new JLabel("이메일");

	// 필드부
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JTextField nameField = new JTextField();
	private JTextField ssnField = new JTextField();
	private JTextField emailField = new JTextField();

	private JButton modiButton = new JButton("수정 완료");
	private JButton backButton = new JButton("이전으로");
	private JButton removeButton = new JButton("회원탈퇴");

	// 체크박스
	private JCheckBox showPassword = new JCheckBox("보이기");

	// 사용자가 입력한 필드에 값을 받아오기 위한 변수
	private String id = userTextField.getText();
	private String password = passwordField.getText();
	private String name = nameField.getText();
	private String ssn = ssnField.getText();
	private String email = emailField.getText();

	private final JLabel label = new JLabel("마이 페이지");

	//
	AddSignup addsignup = new AddSignup();
	AddJoin addjoin = new AddJoin();

	static List newuser = new ArrayList<User>(); // 담아줄 새 회원 리스트
	public static JoinList temp = new JoinList();
	//
	private Image image;

	public ModifyPage() {

		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setTitle("로그인 페이지");
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("./image//image1.PNG"));
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
		container.setLocation(0, 0);

		container.setSize(900, 600);
		container.setLayout(null);
		container.setVisible(true);// 첫 패널만 일단 보이게 해놓음

		frame.getContentPane().add(container); // 패널로 컴포넌트를 감싸 놓음.
		label.setForeground(new Color(25, 25, 112));

		// 타이틀 폰트 세팅
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 28));
		label.setBounds(262, 48, 376, 75);

		// 아이디 컨테이너
		container.add(label);
		userLabel.setForeground(new Color(25, 25, 112));
		userLabel.setBounds(274, 150, 100, 30);
		container.add(userLabel);
		userLabel.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 18));
		passwordLabel.setForeground(new Color(25, 25, 112));

		// 비밀번호 컨테이너
		passwordLabel.setBounds(274, 200, 100, 30);
		container.add(passwordLabel);
		passwordLabel.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 18));

		// 이름 컨테이너
		container.add(label);
		nameLabel.setForeground(new Color(25, 25, 112));
		nameLabel.setBounds(274, 250, 100, 30);
		container.add(nameLabel);
		nameLabel.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 18));
		ssnLabel.setForeground(new Color(25, 25, 112));

		// 주민번호 컨테이너
		ssnLabel.setBounds(274, 300, 100, 30);
		container.add(ssnLabel);
		ssnLabel.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 18));
		emailLabel.setForeground(new Color(25, 25, 112));

		// 이메일 컨테이너
		emailLabel.setBounds(274, 350, 100, 30);
		container.add(emailLabel);
		emailLabel.setFont(new Font("Noto Sans CJK KR Medium", Font.BOLD, 18));

		// 사용자 텍스트 필드
		userTextField.setBounds(378, 150, 150, 30);
		container.add(userTextField);

		// 비밀번호 텍스트 필드
		passwordField.setBounds(378, 201, 150, 30);
		container.add(passwordField);

		// 이름 텍스트 필드
		nameField = new JTextField();
		nameField.setBounds(378, 252, 150, 30);
		container.add(nameField);

		ssnField = new JTextField();
		ssnField.setBounds(378, 303, 150, 30);
		container.add(ssnField);

		emailField = new JTextField();
		emailField.setBounds(378, 351, 150, 30);
		container.add(emailField);

		// 수정완료 버튼
		modiButton.setBounds(378, 420, 150, 40);
		modiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		container.add(modiButton);

		// 이전으로 버튼
		backButton.setBounds(27, 28, 150, 40);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		container.add(backButton);

		// 비밀번호 보이기
		showPassword.setBounds(548, 200, 150, 30);
		container.add(showPassword);

		// 회원탈퇴

		removeButton.setBounds(378, 477, 150, 40);
		removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		container.add(removeButton);

		// 버튼 리슨어
		modiButton.addActionListener(this);
		backButton.addActionListener(this);
		showPassword.addActionListener(this);
		removeButton.addActionListener(this);

		// ===========불러온 사용자 정보가 필드에 보여짐

		userTextField.setText(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getId());
		userTextField.setEditable(false);
		passwordField.setText(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getPassword());
		nameField.setText(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getName());
		nameField.setEditable(false);
		ssnField.setText(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getSsn());
		ssnField.setEditable(false);
		emailField.setText(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getEmail());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == modiButton) {

			// ===========새로운 필드값 작성, 버튼 누르면 각 인덱스열에 새 값으로 저장! ===== //

			password = passwordField.getText();
			email = emailField.getText();

			InsertPage.temp.getJoinlist().get(loginpage.sessionNum).setPassword(password);
			InsertPage.temp.getJoinlist().get(loginpage.sessionNum).setEmail(email);

			new MainMenu().MFrame.setVisible(true);
			frame.setVisible(false);
		}

		// 비번 토글
		if (e.getSource() == showPassword) {
			if (showPassword.isSelected()) { // 비밀번호 보이기 토글 세팅
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*'); // 비밀번호 감추기
			}
		}

		// ====================이전 버튼==========================

		if (e.getSource() == backButton) {

			LoginPage.loginSession = false; // 세션종료
			frame.setVisible(false);
			frame.dispose();

			//new LoginPage();
			new MainMenu().MFrame.setVisible(true);


		}

		if (e.getSource() == removeButton) {

			InsertPage.temp.getJoinlist().remove(loginpage.sessionNum);
			LoginPage.loginSession = false; // 세션종료
			frame.setVisible(false);
			frame.dispose();
			new LoginPage();

		}

	}
}
