package mini.kh1.corona.view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.user.AddSample;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.HospitalVaccine;

//예약하기 -> 공지 -> 지역선택 패널 구현 코드  JPanel상속 

public class SelectHospital extends JPanel { // 병원 선택 화면 패널
	
	public static boolean isBookOver = false;

	HospitalExcel hExcel = new HospitalExcel();

	LoginPage loginpage;
	Vector<HospitalVaccine> hospitalList;

	String excelPath = "../data/HospitalData.xlsx";
	String sheetName = "병원정보";

	private Map map;
	private JTable table1 = null;
	private JTable table2 = null;

	private JButton mapButton = new JButton("병원지도");
	private JButton bookButton = new JButton("예  약");
	private JButton searchButton = new JButton("선택");
	private JButton refreshButton = new JButton("새로고침");

	String header[] = { "병원 이름", "잔여 백신 재고", "신청 가능" }; // 표의 윗 부분

	// 표 중간에 들어갈 내용

	private JComboBox combo;

	private JLabel label = new JLabel("* 해당 지역구마다 각 하나의 병원이 백신 접종 병원으로 배정되었으며, 추후 증가 될 예정입니다.");

	private JOptionPane option = new JOptionPane();

	public String mapLocation; // 지도에 입력 될 키워드
	public String hName;

	public static ArrayList bookerlist = new ArrayList();
	public AddSample sample = new AddSample();
	
	public BookerList blist = new BookerList();
	
	private Image image;
	
	public SelectHospital() { 

		repaint();
		setVisible(false);
		setLayout(null);

		label.setBounds(250, 295, 800, 20);
		label.setVisible(false);//일단 안 보이게 해놓고 지역 선택하면 문구 보이게
		add(label); // 안내 문구 한 줄 표 아래 출력! 

		try {
			hospitalList = hExcel.callTable();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		String location[] = new String[hospitalList.size()];
		location[0] = "지역 선택";
		for (int i = 0; i < hospitalList.size() - 1; i++) {
			location[i + 1] = hospitalList.get(i).getMainDistrict();
		}


		combo = new JComboBox(location);

		mapButton.setBounds(80, 450, 180, 35);
		mapButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		mapButton.setForeground(Color.WHITE);
		mapButton.setOpaque(true);
		mapButton.setBackground(Color.BLACK);
		mapButton.setFocusPainted(false);
		mapButton.setVisible(true);
		mapButton.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		mapButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				for (int i = 0; i < hospitalList.size(); i++) {
					if (combo.getSelectedItem().equals(hospitalList.get(i).getMainDistrict()))
						mapLocation = hospitalList.get(i).gethName();

				}

				map = new Map(mapLocation); // 선택된 병원의 이름을 맵의 초기값으로 매개변수로 넣어준다.
				map.setLocationRelativeTo(null); // 맵을 현재 창의 가운데로 생성되게 한다.

			}
		});
		add(mapButton); // 메인 패널에 병원위치(지도) 버튼 추가

		// 예약하기 버튼 설정
		bookButton.setBounds(630, 450, 180, 35);
		bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		bookButton.setForeground(Color.WHITE);
		bookButton.setOpaque(true);
		bookButton.setBackground(Color.BLACK);
		bookButton.setFocusPainted(false);
		bookButton.setVisible(true);
		bookButton.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		bookButton.addMouseListener(new MouseAdapter() {

			String addHName;

			@Override
			public void mousePressed(MouseEvent e) { // 예약버튼 클릭 시 이벤트 (팝업실행)
				try {
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				for (int i = 0; i < hospitalList.size(); i++) {
					if (hospitalList.get(i).getMainDistrict().equals(combo.getSelectedItem())) {
						addHName = hospitalList.get(i).gethName();

						if (hospitalList.get(i).getVaccine() == 0) { // 재고 백신 체크
							JOptionPane.showMessageDialog(null, "신청 불가", "WARNING_MESSAGE",
									JOptionPane.WARNING_MESSAGE);
							break;
						} else { // 재고가 남아있으면

							int result = option.showConfirmDialog(null, "예약 신청하시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {

								setVisible(false);
								MainMenu.mainPanel.setVisible(true);
								break;
							} else { // 예약 들어갔을 경우
								
								
								BookerList list111 = new BookerList();
								
								String loca = combo.getSelectedItem().toString();
								Booker booker1 = new Booker(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getName(),
										InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getSsn(),
										InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getEmail(), loca, addHName,"");

								list111.setBookerAdd(booker1);
								
									
									//둘다 리스트에 추가 반영은 일단 되는 상태에요 둘다 가져와서 한번 테스트 해보셔야 할듯 해요
									//전 일단 1번방법으로 사용했어영
								int vaccine = 0;
								for (int j = 0; j < hospitalList.size(); j++) {
									if (hospitalList.get(i).gethName().equals(addHName)) {
										vaccine = hospitalList.get(i).getVaccine();
										
										if(vaccine == 1) { //사용자가 백신이 1개남았을 때 예약한다면 예약 마감처리해줌. 
			                                 isBookOver = true;
			                              }
			                              
										
										hospitalList.get(i).setVaccine(hospitalList.get(i).getVaccine() - 1);
										
										
										
										try {

											hExcel.modifyVaccine(addHName, vaccine);
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										break;
									}
								}

								// 예약된 병원의재고를 감소시켜주는 메소드


								JOptionPane.showMessageDialog(null,
										"예약 신청이 완료되었습니다.\n이메일을 통해 접종 날짜를 알려드리오니, 꼭 이메일을 확인하시기 바랍니다.", "공지",
										JOptionPane.WARNING_MESSAGE);
								
								setVisible(false);
								MainMenu.mainPanel.setVisible(true);
							}
						}

					}
				}
			}
		});

		add(bookButton); // 패널에 병원위치(지도) 버튼 추가

		combo.setBounds(60, 80, 120, 30);
		combo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		combo.setForeground(Color.WHITE);
		combo.setOpaque(true);
		combo.setBackground(Color.BLACK);
		add(combo);
		String[][] table = null;

		searchButton.setBounds(180, 80, 60, 30);
		searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		searchButton.setForeground(Color.WHITE);
		searchButton.setOpaque(true);
		searchButton.setBackground(Color.BLACK);
		searchButton.setFocusPainted(false);
		searchButton.setVisible(true);

		searchButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (combo.getSelectedIndex() == 0) {

					JOptionPane.showMessageDialog(null, "지역을 선택해주세요");
				}else{
					for (int i = 0; i < hospitalList.size(); i++) {
						if (combo.getSelectedItem().equals(hospitalList.get(i).getMainDistrict())) {
							String[][] table = {
									{ hospitalList.get(i).gethName(), hospitalList.get(i).getVaccine() + " 개",
											checkBook(hospitalList.get(i).getVaccine()) },
									{ "  ", "  ", "  " }, { "  ", "  ", "  " }, { "  ", "  ", "  " } };

							table1 = new JTable(table, header); // 병원의 이름,재고,신청가능여부를 보여줄 표
							
							
							table1.setRowHeight(50);
							table1.setVisible(true);

							final JScrollPane jscp1 = new JScrollPane(table1); // table은 이런식으로 넘겨줘야 정상출력된다.

							jscp1.setLocation(250, 80);
							jscp1.setSize(500, 210);

							jscp1.setVisible(true);
							
							label.setVisible(true);//지역 선택 시 안내문구 보이게 설정

							add(jscp1);
							break;
						}

					}
				}

				

				
			}
		});

		add(searchButton); // 패널에 병원위치(지도) 버튼 추가

	}

	public String checkBook(int vaccine) { // 백신 개수를 참고해 신청 가능 여부를 보여줌!
		if (vaccine == 0) {
			return "X";
		} else {
			return "O";
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
