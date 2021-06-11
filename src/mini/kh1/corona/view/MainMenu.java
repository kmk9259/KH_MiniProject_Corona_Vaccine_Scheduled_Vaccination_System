package mini.kh1.corona.view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mini.kh1.corona.controller.view_booking.GoToView;
import mini.kh1.corona.model.vo.Chatbot;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.ChatBot.ChatBotFrame;

public class MainMenu {

	JPanel selectHospital = new SelectHospital();
	JPanel chatBotpanel = ChatBotFrame.chatPanel;

	static JFrame MFrame = new JFrame();
	static JPanel mainPanel = new JPanel(); // 메인 패널은 자주 호출될 것이므로 일단 static->직접 호출가능하도록

	boolean isBooker = false;

	JButton bookButton = new JButton("예약하기"); // 예약하기 버튼
	JButton chackBookButton = new JButton("예약조회"); // 예약조회 버튼
	JButton myPageButton = new JButton("MyPage"); // 마이페이지 버튼
	JButton chatbotButton = new JButton("챗봇 FAQ"); // 챗봇 버튼
	JButton logoutButton = new JButton("로그아웃"); // 로그아웃 버튼

	public MainMenu() {

		MFrame.setTitle("백신 예약 프로그램");
		MFrame.setSize(900, 600);
		MFrame.setResizable(false); // 화면 크기 조절 잠금
		MFrame.setLocationRelativeTo(null);
		MFrame.setVisible(true);
		MFrame.setDefaultCloseOperation(MFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(null);
		mainPanel.setVisible(true);

		MFrame.add(mainPanel); // 메인 프레임에 메인 패널 추가

		// final : 상수 static

		// 예약하기 버튼 설정
		bookButton.setBounds(180, 280, 200, 50);
		bookButton.setVisible(true);
		bookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList bookerList = SelectHospital.bookerlist;

				for (int i = 0; i < bookerList.size(); i++) {
					if (InsertPage.temp.getJoinlist().get(LoginPage.sessionNum).getName().equals(((User) bookerList.get(i)).getName())) {
						isBooker = true;
						break;
					}

				}

				if (isBooker == false) {
					mainPanel.setVisible(false); // 예약버튼 누르면 메인화면은 안보임
					MFrame.add(Notice.NOTICE); // 메인 프레임에 예약 전 공지사항 추가
//			MFrame.add(selectHospital); //메인 프레임에 병원(지역)선택 화면 추가

					Notice.NOTICE.setVisible(true);
//			selectHospital.setVisible(true); //병원(지역)선택 화면 노출
				} else {
					JOptionPane.showMessageDialog(null, "이미 예약하셨습니다.");

				}

			}
		});
		mainPanel.add(bookButton); // 메인패널에 예약버튼 추가

		// 예약조회 버튼 설정
		chackBookButton.setBounds(470, 280, 200, 50);
		chackBookButton.setVisible(true);
		chackBookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 예약조회 버튼 클릭 시 이벤트 구현

				GoToView gtv = new GoToView();

				int i = gtv.go(); // 신청인 수, 예약 여부 가져와야 함
				// 사용자가 신청한 병원의 백신갯수, 예약자리스트에 사용자가 있는지
				if (i == 0) { // 예약 완료, 마감 되었을때
					mainPanel.setVisible(false);
					MFrame.add(CheckBookPanel.checkBookPanel);
					CheckBookPanel.checkBookPanel.setVisible(true);
				} else if (i == 2) {// 예약하지 않았을 때
					JOptionPane.showMessageDialog(null, "접종예약을 하지 않으셨습니다.\n예약하기 먼저 진행해주세요.");
				} else { // 예약이 마감되지 않았을 때
					JOptionPane.showMessageDialog(null, "예약이 마감되지 않았습니다.");
				}

			}
		});
		mainPanel.add(chackBookButton); // 메인패널에 예약조회 버튼 추가

		// 마이페이지 버튼 설정
		myPageButton.setBounds(180, 380, 200, 50);
		myPageButton.setVisible(true);
		myPageButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				new ModifyPage();
				MFrame.setVisible(false);
				// 마이페이지 버튼 클릭 시 이벤트 구현
			}
		});
		mainPanel.add(myPageButton);// 메인패널에 마이페이지 버튼 추가

		// 챗봇 버튼 설정
		chatbotButton.setBounds(470, 380, 200, 50);
		chatbotButton.setVisible(true);
		chatbotButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				mainPanel.setVisible(false); // 예약버튼 누르면 메인화면은 안보임
				MFrame.add(chatBotpanel); // 메인 프레임에 병원(지역)선택 화면 추가
				new Chatbot(8500);
				chatBotpanel.setVisible(true);

			}
		});
		mainPanel.add(chatbotButton);// 메인패널에 챗봇버튼 추가

		// 로그아웃 버튼 설정
		logoutButton.setBounds(680, 480, 150, 35);
		logoutButton.setVisible(true);
		logoutButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				LoginPage.loginSession = false; // 세션종료
				mainPanel.setVisible(false);
				MFrame.dispose();
				new LoginPage();

			}
		});
		mainPanel.add(logoutButton); // 메인 패널에 로그아웃 버튼 추가

	}

}