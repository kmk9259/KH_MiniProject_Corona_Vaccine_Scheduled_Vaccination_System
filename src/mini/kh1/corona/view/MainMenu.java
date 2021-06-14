package mini.kh1.corona.view;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mini.kh1.corona.controller.reservation.sendMail.MailNotification;
import mini.kh1.corona.controller.view_booking.GoToView;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.ChatBot.ChatBotView;

public class MainMenu {
	MailNotification mail = new MailNotification();

	JPanel selectHospital = new SelectHospital();
	

	public static JFrame MFrame = new JFrame();
	public static JPanel mainPanel; // 메인 패널은 자주 호출될 것이므로 일단 static->직접 호출가능하도록

	boolean isBooker = false;

	JButton bookButton = new JButton("예약하기"); // 예약하기 버튼
	JButton chackBookButton = new JButton("예약조회"); // 예약조회 버튼
	JButton myPageButton = new JButton("MyPage"); // 마이페이지 버튼
	JButton chatbotButton = new JButton("챗봇 FAQ"); // 챗봇 버튼
	JButton logoutButton = new JButton("로그아웃"); // 로그아웃 버튼
	
	private Image image;

	public MainMenu() {
		
		MFrame.setTitle("백신 예약 프로그램");
		MFrame.setSize(900, 600);
		MFrame.setResizable(false); // 화면 크기 조절 잠금
		MFrame.setLocationRelativeTo(null);
		MFrame.setVisible(true);
		MFrame.setDefaultCloseOperation(MFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("./image//image1.PNG"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mainPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Dimension d = mainPanel.getSize();
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}
			
		};
		
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);

		MFrame.add(mainPanel); // 메인 프레임에 메인 패널 추가

		// final : 상수 static

		// 예약하기 버튼 설정
		bookButton.setBounds(180, 280, 200, 50);
		bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
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
		chackBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		chackBookButton.setVisible(true);
		chackBookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// 예약조회 버튼 클릭 시 이벤트 구현

				GoToView gtv = new GoToView();

				int i = gtv.go(); // 신청인 수, 예약 여부 가져와야 함
				// 사용자가 신청한 병원의 백신갯수, 예약자리스트에 사용자가 있는지
				mail.mailText();
				
				if (i == 0) { // 예약 완료, 마감 되었을때
					JPanel checkBookPanel = new CheckBookPanel();
					mainPanel.setVisible(false);
					MFrame.add(checkBookPanel);
					checkBookPanel.setVisible(true);
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
		myPageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
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
		chatbotButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		chatbotButton.setVisible(true);
		chatbotButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				JPanel chatBotpanel = new ChatBotView();
				mainPanel.setVisible(false); // 예약버튼 누르면 메인화면은 안보임
				MFrame.add(chatBotpanel); // 메인 프레임에 병원(지역)선택 화면 추가
				chatBotpanel.setVisible(true);
				
				
				

			}
		});
		mainPanel.add(chatbotButton);// 메인패널에 챗봇버튼 추가

		// 로그아웃 버튼 설정
		logoutButton.setBounds(680, 480, 150, 35);
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		logoutButton.setVisible(true);
		logoutButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				LoginPage.loginSession = false; // 세션종료
				JOptionPane.showMessageDialog(null, InsertPage.temp.getJoinlist().get(LoginPage.sessionNum).getName()+"님 로그아웃 되셨습니다.");
				mainPanel.setVisible(false);
				
				MFrame.dispose();
				
				new LoginPage();

			}
		});
		mainPanel.add(logoutButton); // 메인 패널에 로그아웃 버튼 추가
	}

}