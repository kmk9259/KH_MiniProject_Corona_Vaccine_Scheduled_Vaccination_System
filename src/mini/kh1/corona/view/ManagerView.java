package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
import mini.kh1.corona.model.vo.HospitalDetail;
import mini.kh1.corona.model.vo.HospitalVaccine;

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

	public ManagerView() throws Exception {

		// 관리자 모드 프레임
		frame.setPreferredSize(new Dimension(900, 600));
		frame.setSize(900, 600);
		frame.setTitle("관리자모드");
		frame.setResizable(false);// 창 사이즈 변경 불가
		frame.setLocationRelativeTo(null);// 모니터 중간에 창 띄우기
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 관리자모드 메인 메뉴 패널
		ManagerPanel = new JPanel();

		ManagerPanel.setSize(900, 600);
		ManagerPanel.setLayout(null);
		ManagerPanel.setVisible(true);// 첫 패널만 일단 보이게 해놓음

		JLabel managerMenu = new JLabel("관리자 모드");
		managerMenu.setBounds(370, 70, 100, 50);
		managerMenu.setFont(new Font("굴림", Font.PLAIN, 15));
		managerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		ManagerPanel.add(managerMenu);
		// 1번 기능
		JButton vcManager = new JButton("백신 재고 관리");
		vcManager.setBounds(335, 180, 180, 60);
		vcManager.setFont(new Font("굴림", Font.PLAIN, 15));
		ManagerPanel.add(vcManager);
		// 2번 기능
		JButton hsManager = new JButton("병원 관리");
		hsManager.setBounds(335, 280, 180, 60);
		hsManager.setFont(new Font("굴림", Font.PLAIN, 15));
		ManagerPanel.add(hsManager);
		// 3번 기능
		JButton userManager = new JButton("회원 관리");
		userManager.setBounds(335, 380, 180, 60);
		userManager.setFont(new Font("굴림", Font.PLAIN, 15));
		ManagerPanel.add(userManager);
		// 4번 로그아웃 기능
		JButton managerLgOut = new JButton("로그아웃");
		managerLgOut.setBounds(760, 490, 90, 40);
		managerLgOut.setFont(new Font("굴림", Font.PLAIN, 12));
		ManagerPanel.add(managerLgOut);

		frame.add(ManagerPanel);

		// ==========================================================================

		// 1번기능 - 백신 재고 관리
		vc = new JPanel();
		vc.setSize(900, 600);
		vc.setLayout(null);
		// 라벨
		JLabel vmLabel = new JLabel("백신 재고 현황");
		vmLabel.setBounds(140, 70, 110, 50);
		vmLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		vmLabel.setHorizontalAlignment(SwingConstants.CENTER);// 라벨 박스에서 가운데 정렬
		vmLabel.setOpaque(true);// 라벨 배경색 바꿀 수 있게 만들어주는 기능
		vmLabel.setBackground(Color.BLACK);// 추후 변경
		vmLabel.setForeground(Color.WHITE);
		vc.add(vmLabel);
		// 수량 변경 버튼
		JButton vmButton1 = new JButton("수량 변경");
		vmButton1.setBounds(140, 418, 90, 40);
		vmButton1.setFont(new Font("굴림", Font.PLAIN, 12));
		vc.add(vmButton1);
		// 이전으로 버튼
		JButton vmButton2 = new JButton("이전으로");
		vmButton2.setBounds(760, 490, 90, 40);
		vmButton2.setFont(new Font("굴림", Font.PLAIN, 12));
		vc.add(vmButton2);

		JButton vmButton3 = new JButton("새로고침");
		vmButton3.setBounds(230, 418, 90, 40);
		vmButton3.setFont(new Font("굴림", Font.PLAIN, 12));
		vc.add(vmButton3);

		// 병원별 재고 현황 테이블
		// callTable() 메소드를 불러와서 vector에 넣는다.
		vector = new HospitalExcel().callTable();

		// vector 사이즈 : 들어가있는 HospitalVaccine의 객체 수 / 3 : 나타낼 정보가 3개(메인주소, 병원명, 재고수량)니까
		/*
		 * 메소드로 만들어봄 Object[][] vmData = new Object[vector.size()][3];
		 * System.out.println("vector 사이즈 : " + vector.size());
		 * 
		 * for (int i = 0; i < vector.size(); i++) { for (int j = 0; j < 3; j++) { if (j
		 * == 0) {// 메인주소가 있는 열 vmData[i][j] = vector.get(i).getMainDistrict(); } if (j
		 * == 1) { vmData[i][j] = vector.get(i).gethName(); } if (j == 2) { vmData[i][j]
		 * = vector.get(i).getVaccine(); } } // 확인용 System.out.println("시/구 : " +
		 * vector.get(i).getMainDistrict()); System.out.println("병원명 : " +
		 * vector.get(i).gethName()); System.out.println("재고량 : " +
		 * vector.get(i).getVaccine());
		 * 
		 * }
		 */
		vmData = new HospitalExcel().getData(vector);// vmData = HospitalExcel getData 메소드에서 얻어온 data 값을 대입

		vmTable = new JTable(vmData, new Object[] { "시/구", "병원명", "재고 수량" });
		JTableHeader vmTableHd = vmTable.getTableHeader();
		vmScrollPane = new JScrollPane(vmTable);// 스크롤 있는 테이블로 변경

		vmTable.setFont(new Font("굴림", Font.PLAIN, 12));

		vmTableHd.setFont(new Font("굴림", Font.BOLD, 15));
		vmTableHd.setBackground(Color.cyan);// 나중에 꾸밀 때 변경

		vmScrollPane.setBounds(140, 120, 620, 300);
		vmScrollPane.setVisible(false);// 패널과 상관없이 보이는듯

		vc.add(vmScrollPane);
		vc.setVisible(false);

		frame.add(vc);

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

					vmTableHd.setFont(new Font("굴림", Font.BOLD, 15));
					vmTableHd.setBackground(Color.cyan);// 나중에 꾸밀 때 변경

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
		hs = new JPanel();
		hs.setSize(900, 600);
		hs.setLayout(null);

		JLabel hmLabel = new JLabel("병원 현황");
		hmLabel.setBounds(140, 70, 100, 50);
		hmLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		hmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hmLabel.setOpaque(true);
		hmLabel.setBackground(Color.BLACK);
		hmLabel.setForeground(Color.white);
		hs.add(hmLabel);
//버튼 기능 수정=======================================>>>>
		JButton hmButton1 = new JButton("추가하기");
		hmButton1.setBounds(140, 418, 90, 40);
		hmButton1.setFont(new Font("굴림", Font.PLAIN, 12));
		hs.add(hmButton1);

		JButton hmButton2 = new JButton("새로고침");
		hmButton2.setBounds(230, 418, 90, 40);
		hmButton2.setFont(new Font("굴림", Font.PLAIN, 12));
		hs.add(hmButton2);

		JButton hmButton3 = new JButton("이전으로");
		hmButton3.setBounds(760, 490, 90, 40);
		hmButton3.setFont(new Font("굴림", Font.PLAIN, 12));
		hs.add(hmButton3);
//<<<<=================================================
		// 병원 정보 현황
		hdVector = new HospitalAddress().callAddress();
		hsData = new HospitalAddress().getAddress(hdVector);

		hmTable = new JTable(hsData, new Object[] { "시/구", "상세주소", "병원명" });
		JTableHeader hmTableHd = hmTable.getTableHeader();
		hmScrollPane = new JScrollPane(hmTable);

		hmTable.setFont(new Font("굴림", Font.PLAIN, 12));

		hmTableHd.setFont(new Font("굴림", Font.PLAIN, 15));
		hmTableHd.setBackground(Color.CYAN);

		hmScrollPane.setBounds(140, 120, 620, 300);
		hmScrollPane.setVisible(false);

		hs.add(hmScrollPane);
		hs.setVisible(false);

		frame.add(hs);

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

					hmTableHd.setFont(new Font("굴림", Font.BOLD, 15));
					hmTableHd.setBackground(Color.cyan);// 나중에 꾸밀 때 변경

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
		us = new JPanel();
		us.setSize(900, 600);
		us.setLayout(null);

		JLabel usLabel = new JLabel("회원 현황");
		usLabel.setBounds(100, 70, 100, 50);
		usLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		usLabel.setOpaque(true);
		usLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usLabel.setBackground(Color.BLACK);
		usLabel.setForeground(Color.white);
		us.add(usLabel);

		JButton usButton = new JButton("이전으로");
		usButton.setBounds(760, 490, 90, 40);
		usButton.setFont(new Font("굴림", Font.PLAIN, 12));
		us.add(usButton);

		Object[][] usData = { { "abc123", "홍길동", "230101-1******", "99", "hong123@gmail.com" } };// 회원정보

		JTable usTable = new JTable(usData, new Object[] { "아이디", "이름", "주민번호", "나이", "이메일" });
		JTableHeader usTableHd = usTable.getTableHeader();
		final JScrollPane usScrollPane = new JScrollPane(usTable);

		usTable.setFont(new Font("굴림", Font.PLAIN, 12));

		usTableHd.setFont(new Font("굴림", Font.BOLD, 15));
		usTableHd.setBackground(Color.cyan);

		usScrollPane.setBounds(100, 120, 700, 320);
		usScrollPane.setVisible(false);

		us.add(usScrollPane);
		us.setVisible(false);

		frame.add(us);

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
				new HospitalAddFrame();

			}
		});
	}
}