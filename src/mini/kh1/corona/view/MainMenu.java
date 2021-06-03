package mini.kh1.corona.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

	JFrame MFrame = new JFrame();
	JPanel mainPanel = new JPanel();

	JButton bookButton = new JButton("예약하기"); // 예약하기 버튼
	JButton chackBookButton = new JButton("예약조회"); // 예약조회 버튼
	JButton myPageButton = new JButton("MyPage"); // 마이페이지 버튼
	JButton chatbotButton = new JButton("챗봇 FAQ"); // 챗봇 버튼
	JButton logoutButton = new JButton("로그아웃"); // 로그아웃 버튼

	public MainMenu() {

		MFrame.setTitle("지역 선택");
		MFrame.setSize(900, 600);
		MFrame.setResizable(false);
		MFrame.setLocationRelativeTo(null);
		MFrame.setVisible(true);
		MFrame.setDefaultCloseOperation(MFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(null);
		mainPanel.setVisible(true);

		
		//예약하기 버튼 설정
		bookButton.setBounds(180, 280, 200, 50);
		bookButton.setVisible(true);
		bookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 예약하기 버튼 클릭 시 이벤트 구현

			}
		});
		mainPanel.add(bookButton); //메인패널에 예약버튼 추가

		
		//예약조회 버튼 설정
		chackBookButton.setBounds(470, 280, 200, 50);
		chackBookButton.setVisible(true);
		chackBookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 예약조회 버튼 클릭 시 이벤트 구현
			}
		});
		mainPanel.add(chackBookButton); //메인패널에 예약조회 버튼 추가

		
		//마이페이지 버튼 설정
		myPageButton.setBounds(180, 380, 200, 50);
		myPageButton.setVisible(true);
		myPageButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 마이페이지 버튼 클릭 시 이벤트 구현
			}
		});
		mainPanel.add(myPageButton);//메인패널에 마이페이지 버튼 추가

		//챗봇 버튼 설정
		chatbotButton.setBounds(470, 380, 200, 50);
		chatbotButton.setVisible(true);
		chatbotButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 챗봇 버튼 클릭 시 이벤트 구현
			}
		});
		mainPanel.add(chatbotButton);//메인패널에 챗봇버튼 추가

		//로그아웃 버튼 설정
		logoutButton.setBounds(680, 480, 150, 35);
		logoutButton.setVisible(true);
		logoutButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 로그아웃 버튼 클릭 시 이벤트 구현
			}
		});
		mainPanel.add(logoutButton); // 메인 패널에 로그아웃 버튼 추가

		MFrame.add(mainPanel); //메인 프레임에 메인 패널 추가

	}

}
