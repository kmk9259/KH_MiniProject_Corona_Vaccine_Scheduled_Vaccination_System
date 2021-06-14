package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

import mini.kh1.corona.controller.hospital.HospitalAddress;
import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.user.AddSample;
import mini.kh1.corona.model.vo.HospitalDetail;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.model.vo.user.User;

public class ManagerView {

	private JFrame frame = new JFrame();

	private JPanel ManagerPanel;
	private JPanel vc;
	private JPanel hs;
	private JPanel us;

	private JTable vmTable;
	private JTable hmTable;

	private Vector<HospitalVaccine> vector;
	private Vector<HospitalDetail> hdVector;

	private Object[][] vmData;
	private Object[][] hsData;

	private JScrollPane vmScrollPane;
	private JScrollPane hmScrollPane;

	private BufferedImage image;

	private Object[][] usData;

	public ManagerView() throws Exception {

		// 관리자 모드 프레임
		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setTitle("관리자모드");
		// frame.setUndecorated(true);//프레임 타이틀 바 안보이게 할 수도 있음, 쓰려면 setTitle 삭제
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 아이콘 이미지 추가

		frame.setIconImage(ImageIO.read(new File("./image//managerIcon.PNG")));

		// 관리자모드 메인 메뉴 패널

		image = ImageIO.read(new File("./image//image1.PNG"));

		ManagerPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				Dimension d = ManagerPanel.getSize();
				//System.out.println("메인패널 길이 : " + ManagerPanel.getSize());
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}

		};

		ManagerPanel.setSize(900, 600);
		ManagerPanel.setLayout(null);
		ManagerPanel.setVisible(true);// 첫 패널만 일단 보이게 해놓음

		// 이미지 크기를 조절해야 하기때문에 Image로 변환해서 크기 조절 후 다시 ImageIcon에 넣어준다.
		ImageIcon labelImage = new ImageIcon("./image//managerIcon.PNG");
		Image mainLabel = labelImage.getImage();
		Image mainLabel2 = mainLabel.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon labelImage2 = new ImageIcon(mainLabel2);

		JLabel managerMenu = new JLabel("관리자 모드", labelImage2, SwingConstants.CENTER);

		managerMenu.setBounds(456, 65, 329, 50);
		managerMenu.setFont(new Font("Nanum Gothic", Font.BOLD, 25));
		managerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		managerMenu.setForeground(Color.BLACK);
		ManagerPanel.add(managerMenu);
		// 1번 기능
		JButton vcManager = new JButton("백신 재고 관리");
		vcManager.setBounds(550, 180, 180, 60);
		vcManager.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		vcManager.setFocusPainted(false);// 버튼 테두리 없애기
		vcManager.setForeground(Color.WHITE);
		vcManager.setOpaque(true);
		vcManager.setBackground(Color.BLACK);
		vcManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ManagerPanel.add(vcManager);
		// 2번 기능
		JButton hsManager = new JButton("병원 관리");
		hsManager.setBounds(550, 280, 180, 60);
		hsManager.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		hsManager.setFocusPainted(false);
		hsManager.setForeground(Color.WHITE);
		hsManager.setOpaque(true);
		hsManager.setBackground(Color.BLACK);
		hsManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ManagerPanel.add(hsManager);
		// 3번 기능
		JButton userManager = new JButton("회원 관리");
		userManager.setBounds(550, 380, 180, 60);
		userManager.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		userManager.setFocusPainted(false);
		userManager.setForeground(Color.WHITE);
		userManager.setOpaque(true);
		userManager.setBackground(Color.BLACK);
		userManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ManagerPanel.add(userManager);
		// 4번 로그아웃 기능
		JButton managerLgOut = new JButton("로그아웃");
		managerLgOut.setBounds(760, 490, 90, 40);
		managerLgOut.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		managerLgOut.setFocusPainted(false);
		managerLgOut.setForeground(Color.WHITE);
		managerLgOut.setOpaque(true);
		managerLgOut.setBackground(Color.BLACK);
		managerLgOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ManagerPanel.add(managerLgOut);

		frame.setIconImage(ImageIO.read(new File("./image//frameicon3.PNG")));
		frame.getContentPane().add(ManagerPanel);

		// ==========================================================================

		// 1번기능 - 백신 재고 관리

		vc = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				Dimension d = vc.getSize();
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}

		};

		vc.setSize(900, 600);
		vc.setLayout(null);
		// 라벨
		JLabel vmLabel = new JLabel("백신 재고 현황");
		vmLabel.setBounds(140, 70, 120, 50);
		vmLabel.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		vmLabel.setHorizontalAlignment(SwingConstants.CENTER);// 라벨 박스에서 가운데 정렬
		vmLabel.setOpaque(true);// 라벨 배경색 바꿀 수 있게 만들어주는 기능
		vmLabel.setBackground(Color.BLACK);// 추후 변경
		vmLabel.setForeground(Color.PINK);
		vc.add(vmLabel);
		// 수량 변경 버튼
		JButton vmButton1 = new JButton("수량 변경");
		vmButton1.setBounds(140, 420, 90, 40);
		vmButton1.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		vmButton1.setFocusPainted(false);
		vmButton1.setForeground(Color.WHITE);
		vmButton1.setOpaque(true);
		vmButton1.setBackground(Color.BLACK);
		vmButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vc.add(vmButton1);
		// 이전으로 버튼
		JButton vmButton2 = new JButton("이전으로");
		vmButton2.setBounds(760, 490, 90, 40);
		vmButton2.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		vmButton2.setFocusPainted(false);
		vmButton2.setForeground(Color.WHITE);
		vmButton2.setOpaque(true);
		vmButton2.setBackground(Color.BLACK);
		vmButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vc.add(vmButton2);

		JButton vmButton3 = new JButton("새로고침");
		vmButton3.setBounds(230, 420, 90, 40);
		vmButton3.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		vmButton3.setFocusPainted(false);
		vmButton3.setForeground(Color.WHITE);
		vmButton3.setOpaque(true);
		vmButton3.setBackground(Color.BLACK);
		vmButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		vc.add(vmButton3);

		// 병원별 재고 현황 테이블
		// callTable() 메소드를 불러와서 vector에 넣는다.
		try {
			vector = new HospitalExcel().callTable();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		vmData = new HospitalExcel().getData(vector);// vmData = HospitalExcel getData 메소드에서 얻어온 data 값을 대입

		vmTable = new JTable(vmData, new Object[] { "시/구", "병원명", "재고 수량" });
		JTableHeader vmTableHd = vmTable.getTableHeader();
		vmScrollPane = new JScrollPane(vmTable);// 스크롤 있는 테이블로 변경

		vmTable.setFont(new Font("굴림", Font.PLAIN, 12));

		vmTableHd.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		vmTableHd.setOpaque(true);
		vmTableHd.setBackground(Color.DARK_GRAY);
		vmTableHd.setForeground(Color.YELLOW);

		vmScrollPane.setBounds(140, 120, 620, 300);
		vmScrollPane.setVisible(false);// 패널과 상관없이 보이는듯

		vc.add(vmScrollPane);
		vc.setVisible(false);

		frame.getContentPane().add(vc);

		// 새로고침 버튼 클릭 시 표에 있는 데이터가 수정되서 나타나게 만듬
		vmButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vc.setVisible(false);
				vc.remove(vmScrollPane);// 기존에 있던 표를 삭제
				try {
					// 새로운 표 생성을 위한 코드
					vector = new HospitalExcel().modifiedTable();
					vmData = new HospitalExcel().getData(vector);

					vmTable = new JTable(vmData, new Object[] { "시/구", "병원명", "재고 수량" });
					JTableHeader vmTableHd = vmTable.getTableHeader();
					vmScrollPane = new JScrollPane(vmTable);// 스크롤 있는 테이블로 변경

					vmTable.setFont(new Font("굴림", Font.PLAIN, 12));

					vmTableHd.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
					vmTableHd.setOpaque(true);
					vmTableHd.setBackground(Color.DARK_GRAY);
					vmTableHd.setForeground(Color.YELLOW);

					vmScrollPane.setBounds(140, 120, 620, 300);
					vmScrollPane.setVisible(true);// 패널과 상관없이 보이는듯

					vc.add(vmScrollPane);
					vc.setVisible(true);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vc.setVisible(true);

			}
		});

		// ==========================================================================

		// 2번 기능 - 병원 관리

		hs = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				Dimension d = hs.getSize();
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}

		};

		hs.setSize(900, 600);
		hs.setLayout(null);

		JLabel hmLabel = new JLabel("병원 현황");
		hmLabel.setBounds(140, 70, 100, 50);
		hmLabel.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		hmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hmLabel.setOpaque(true);
		hmLabel.setBackground(Color.BLACK);
		hmLabel.setForeground(Color.white);
		hs.add(hmLabel);

		JButton hmButton1 = new JButton("추가하기");
		hmButton1.setBounds(140, 420, 90, 40);
		hmButton1.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		hmButton1.setFocusPainted(false);
		hmButton1.setForeground(Color.WHITE);
		hmButton1.setOpaque(true);
		hmButton1.setBackground(Color.BLACK);
		hmButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hs.add(hmButton1);

		JButton hmButton2 = new JButton("새로고침");
		hmButton2.setBounds(230, 420, 90, 40);
		hmButton2.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		hmButton2.setFocusPainted(false);
		hmButton2.setForeground(Color.WHITE);
		hmButton2.setOpaque(true);
		hmButton2.setBackground(Color.BLACK);
		hmButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hs.add(hmButton2);

		JButton hmButton3 = new JButton("이전으로");
		hmButton3.setBounds(760, 490, 90, 40);
		hmButton3.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		hmButton3.setFocusPainted(false);
		hmButton3.setForeground(Color.WHITE);
		hmButton3.setOpaque(true);
		hmButton3.setBackground(Color.BLACK);
		hmButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hs.add(hmButton3);

		// 병원 정보 현황

		hdVector = new HospitalAddress().callAddress();

		hsData = new HospitalAddress().getAddress(hdVector);

		hmTable = new JTable(hsData, new Object[] { "시/구", "상세주소", "병원명" });
		JTableHeader hmTableHd = hmTable.getTableHeader();
		hmScrollPane = new JScrollPane(hmTable);

		hmTable.setFont(new Font("굴림", Font.PLAIN, 12));

		hmTableHd.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		hmTableHd.setOpaque(true);
		hmTableHd.setBackground(Color.DARK_GRAY);
		hmTableHd.setForeground(Color.YELLOW);

		hmScrollPane.setBounds(140, 120, 620, 300);
		hmScrollPane.setVisible(false);

		hs.add(hmScrollPane);
		hs.setVisible(false);

		frame.getContentPane().add(hs);

		// 새로고침 버튼 클릭 시 표에 추가한 주소가 포함되게 만듬
		hmButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				hs.setVisible(false);
				hs.remove(hmScrollPane);// 기존에 있던 표를 삭제
				try {
					// 새로운 표 생성을 위한 코드
					hdVector = new HospitalAddress().modifiedAddress();
					hsData = new HospitalAddress().getAddress(hdVector);

					hmTable = new JTable(hsData, new Object[] { "시/구", "상세주소", "병원명" });
					JTableHeader hmTableHd = hmTable.getTableHeader();
					hmScrollPane = new JScrollPane(hmTable);// 스크롤 있는 테이블로 변경

					hmTable.setFont(new Font("굴림", Font.PLAIN, 12));

					hmTableHd.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
					hmTableHd.setOpaque(true);
					hmTableHd.setBackground(Color.DARK_GRAY);
					hmTableHd.setForeground(Color.YELLOW);

					hmScrollPane.setBounds(140, 120, 620, 300);
					hmScrollPane.setVisible(true);// 패널과 상관없이 보이는듯

					hs.add(hmScrollPane);
					hs.setVisible(true);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hs.setVisible(true);
			}
		});
		// ==========================================================================

		// 3번 기능 - 회원 관리

		us = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				Dimension d = us.getSize();
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}

		};

		us.setSize(900, 600);
		us.setLayout(null);

		JLabel usLabel = new JLabel("회원 현황");
		usLabel.setBounds(100, 70, 100, 50);
		usLabel.setFont(new Font("Nanum Gothic", Font.PLAIN, 15));
		usLabel.setOpaque(true);
		usLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usLabel.setBackground(Color.BLACK);
		usLabel.setForeground(Color.white);
		us.add(usLabel);

		JButton usButton = new JButton("이전으로");
		usButton.setBounds(760, 490, 90, 40);
		usButton.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		usButton.setFocusPainted(false);
		usButton.setForeground(Color.WHITE);
		usButton.setOpaque(true);
		usButton.setBackground(Color.BLACK);
		usButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		us.add(usButton);

		ArrayList<User> ar = new AddSample().addsample();

		usData = new Object[ar.size()][5];

		for (int i = 0; i < ar.size(); i++) {
			for (int j = 0; j < 5; j++) {
				switch (j) {
				case 0:
					usData[i][j] = ar.get(i).getId();
					break;
				case 1:
					usData[i][j] = ar.get(i).getName();
					break;
				case 2:
					int pwLength = ar.get(i).getPassword().length();
					String secretCode = "";
					for (int k = 0; k < pwLength; k++) {
						secretCode += "*";
					}
					//System.out.println(secretCode);
					usData[i][j] = secretCode;
					//System.out.println("비번 길이 : " + ar.get(i).getPassword().length());
					break;
				case 3:
					int ssnLength = ar.get(i).getSsn().length();
					String secretSsn = ar.get(i).getSsn().substring(ssnLength - 6, ssnLength) + "******";
					usData[i][j] = secretSsn;
					break;
				case 4:
					usData[i][j] = ar.get(i).getEmail();
					break;
				default:
					break;
				}
			}
		}

		JTable usTable = new JTable(usData, new Object[] { "아이디", "이름", "비밀번호", "주민등록번호", "이메일" });
		JTableHeader usTableHd = usTable.getTableHeader();
		final JScrollPane usScrollPane = new JScrollPane(usTable);

		usTable.setFont(new Font("굴림", Font.PLAIN, 12));

		usTableHd.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		usTableHd.setOpaque(true);
		usTableHd.setBackground(Color.DARK_GRAY);
		usTableHd.setForeground(Color.YELLOW);

		usScrollPane.setBounds(100, 120, 700, 320);
		usScrollPane.setVisible(false);

		us.add(usScrollPane);
		us.setVisible(false);

		frame.getContentPane().add(us);

		// ==========================================================================

		// 버튼 기능 모음
		// 1. 관리자 메뉴에서 백신 재고 관리 버튼 클릭 시 백신 재고 현황이 나타나는 화면으로 이동
		vcManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerPanel.setVisible(false);
				vc.setVisible(true);
				vmScrollPane.setVisible(true);

			}
		});
		// 이전으로
		vmButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vc.setVisible(false);
				vmScrollPane.setVisible(false);
				ManagerPanel.setVisible(true);

			}
		});

		// 2. 관리자 메뉴에서 병원 관리 버튼 클릭 시 병원 현황이 나타나는 화면으로 이동
		hsManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerPanel.setVisible(false);
				hs.setVisible(true);
				hmScrollPane.setVisible(true);

			}

		});
		// 이전으로
		hmButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hs.setVisible(false);
				hmScrollPane.setVisible(false);
				ManagerPanel.setVisible(true);

			}
		});

		// 3. 관리자 메뉴에서 회원 관리 버튼 클릭 시 회원 현황이 나타나는 화면으로 이동
		userManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerPanel.setVisible(false);
				us.setVisible(true);
				usScrollPane.setVisible(true);

			}
		});
		// 이전으로
		usButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				us.setVisible(false);
				usScrollPane.setVisible(false);
				ManagerPanel.setVisible(true);

			}
		});

		// 로그아웃 버튼 클릭 시
		managerLgOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginPage();

			}
		});

		// ==========================================================================

		// 버튼 클릭을 통해 상세 기능을 가진 JFrame 클래스와 연결 - 백신 재고 관리의 수량변경 버튼
		vmButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new VaccineModifyFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// 버튼 클릭을 통해 상세 기능을 가진 JFrame 클래스와 연결 - 병원 관리의 추가 버튼
		hmButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new HospitalAddFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}
}