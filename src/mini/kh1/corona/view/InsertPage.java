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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.AddJoin;
import mini.kh1.corona.controller.user.AddSignup;
import mini.kh1.corona.model.vo.user.JoinList;
import mini.kh1.corona.model.vo.user.User;

public class InsertPage extends JFrame implements ActionListener {

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

	private JButton insertButton = new JButton("회원가입");
	private JButton backButton = new JButton("이전으로");
	private JButton dupliButton = new JButton("중복확인");

	private final JLabel label = new JLabel("회원가입");

	private String id = userTextField.getText();
	private String pw = passwordField.getText();
	private String name = nameField.getText();
	private String ssn = ssnField.getText();
	private String email = emailField.getText();

	private Image image;

	//
	AddSignup addsignup = new AddSignup();
	AddJoin addjoin = new AddJoin();

	static List newuser = new ArrayList<User>(); // 담아줄 새 회원 리스트
	public static JoinList temp = new JoinList();

	//
	public InsertPage() {

		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("./image//image1.PNG"));
			frame.setIconImage(ImageIO.read(new File("./image//frameicon3.png")));
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
		label.setFont(new Font("Nanum Gothic", Font.BOLD, 28));
		label.setBounds(262, 48, 376, 75);

		// 아이디 컨테이너
		container.add(label);
		userLabel.setForeground(new Color(25, 25, 112));
		userLabel.setBounds(274, 162, 100, 30);
		container.add(userLabel);
		userLabel.setFont(new Font("Nanum Gothic", Font.BOLD, 18));
		passwordLabel.setForeground(new Color(25, 25, 112));

		// 비밀번호 컨테이너
		passwordLabel.setBounds(274, 212, 100, 30);
		container.add(passwordLabel);
		passwordLabel.setFont(new Font("Nanum Gothic", Font.BOLD, 18));

		// 이름 컨테이너
		container.add(label);
		nameLabel.setForeground(new Color(25, 25, 112));
		nameLabel.setBounds(274, 262, 100, 30);
		container.add(nameLabel);
		nameLabel.setFont(new Font("Nanum Gothic", Font.BOLD, 18));
		ssnLabel.setForeground(new Color(25, 25, 112));

		// 주민번호 컨테이너
		ssnLabel.setBounds(274, 312, 100, 30);
		container.add(ssnLabel);
		ssnLabel.setFont(new Font("Nanum Gothic", Font.BOLD, 18));

		// 이메일 컨테이너
		emailLabel.setForeground(new Color(25, 25, 112));
		emailLabel.setBounds(274, 362, 100, 30);
		container.add(emailLabel);
		emailLabel.setFont(new Font("Nanum Gothic", Font.BOLD, 18));

		// 사용자 텍스트 필드 + 텍스트 홀딩
		// userTextField.setText("ID");
		userTextField.setBounds(378, 162, 150, 30);
		container.add(userTextField);
		userTextField.setForeground(Color.LIGHT_GRAY);
		userTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// userTextField.setText("");
				userTextField.setForeground(Color.black);

			}
		});

		// 비밀번호 텍스트 필드 + 텍스트 홀딩
		passwordField.setBounds(378, 213, 150, 30);
		passwordField.setText("password");
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setText("");
				passwordField.setForeground(Color.black);
			}
		});
		container.add(passwordField);

		// 이름 텍스트 필드 + 텍스트 홀딩
		nameField = new JTextField();
		nameField.setForeground(Color.LIGHT_GRAY);
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				nameField.setText("");
				nameField.setForeground(Color.black);
			}
		});
		nameField.setText("이름");
		nameField.setBounds(378, 264, 150, 30);
		container.add(nameField);

		// 주민번호 텍스트 필드 + 텍스트 홀딩
		ssnField = new JTextField();
		ssnField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ssnField.setText("");
				ssnField.setForeground(Color.black);
			}
		});
		ssnField.setBounds(378, 315, 150, 30);
		ssnField.setForeground(Color.LIGHT_GRAY);
		ssnField.setText("주민번호");
		container.add(ssnField);

		// 이메일 텍스트 필드 + 텍스트 홀딩
		emailField = new JTextField();
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				emailField.setText("");
				emailField.setForeground(Color.black);
			}
		});
		emailField.setText("email");
		emailField.setForeground(Color.LIGHT_GRAY);
		emailField.setBounds(378, 363, 150, 30);
		container.add(emailField);

		// 회원 가입 버튼
		insertButton.setBounds(378, 433, 150, 40);
		insertButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		insertButton.setForeground(Color.WHITE);
		insertButton.setOpaque(true);
		insertButton.setBackground(Color.BLACK);
		container.add(insertButton);

		// 이전으로 버튼
		backButton.setBounds(27, 28, 150, 40);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		backButton.setForeground(Color.WHITE);
		backButton.setOpaque(true);
		backButton.setBackground(Color.BLACK);
		backButton.setFocusPainted(false);
		container.add(backButton);

		// 중복확인 버튼
		dupliButton.setBounds(558, 157, 150, 40);
		dupliButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// ######버튼 커서 변경
		dupliButton.setForeground(Color.WHITE);
		dupliButton.setOpaque(true);
		dupliButton.setBackground(Color.BLACK);
		container.add(dupliButton);

		// 버튼 리슨어
		insertButton.addActionListener(this);
		dupliButton.addActionListener(this);
		backButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {

			id = userTextField.getText();
			pw = passwordField.getText();
			name = nameField.getText();
			ssn = ssnField.getText();
			email = emailField.getText();

			// 값이 빈 곳이 있으면 가입이 되지 않게~
			if (id.length() == 0 || pw.length() == 0 || name.length() == 0 || ssn.length() == 0
					|| email.length() == 0) {
				JOptionPane.showMessageDialog(this, "정보를 모두 입력해 주세요");

			} else if (ssn.length() < 13) {
				JOptionPane.showMessageDialog(this, "주민등록번호의 자릿수가 틀립니다.");
				ssnField.setText("");
			} else if (!email.contains("@")) {
				JOptionPane.showMessageDialog(this, "이메일 형식이 올바르지 않습니다.");
				emailField.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "가입 되었습니다.");

				addsignup.adduser(id, pw, name, ssn, email); // 회원가입
				newuser = addsignup.addsignup();
				temp = addjoin.setjoin(newuser);
				Reservation r = new Reservation();
//	            r.orderAge();
//	            r.cutPeople();
				new LoginPage(); // 빈 곳 없이 값이 입력 되면 로그인 할 수 있는 페이지로 이동
				frame.setVisible(false);

			}

		} else if (e.getSource() == dupliButton) { // 중복이면 필드부 클리어, 아니면 성공해서 입력할 수 있다.
			
			dupliCheckID();

		} else if (e.getSource() == backButton) {
			new LoginPage(); // 클릭시, 로그인 화면 으로 돌아감
			frame.setVisible(false);
		}
	}

	// 중복확인 메소드
	public void dupliCheckID() {
		id = userTextField.getText();
		int result = 0;

		for (int i = 0; i < InsertPage.temp.getJoinlist().size(); i++) { // 전체 회원 인덱스를 돌며 확인하기 위해
			System.out.println();
			if (InsertPage.temp.getJoinlist().get(i).getId().equals(id)) {
				result = 1;
				break;
			} else {
				result = 2;
			}

		}
		if (result == 1) {
			JOptionPane.showMessageDialog(this, "중복된 아이디 입니다.");
			userTextField.setText("");
			result = 0;
		} else if (result == 2) {
			JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다.");
			userTextField.setText("");
			result = 0;
		}

	}

}