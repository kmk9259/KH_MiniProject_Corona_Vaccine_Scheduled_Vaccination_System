package mini.kh1.corona.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.controller.user.UserList;
import mini.kh1.corona.model.vo.HospitalVaccine;

//예약하기 -> 공지 -> 지역선택 패널 구현 코드  JPanel상속 

public class SelectHospital extends JPanel { // 병원 선택 화면 패널

	HospitalExcel hExcel = new HospitalExcel();

	Vector<HospitalVaccine> hospitalList = new Vector<HospitalVaccine>();
	LoginPage loginpage;

	String excelPath = "../data/HospitalData.xlsx";
	String sheetName = "병원정보";

	private Map map;

	private JButton mapButton = new JButton("병원지도");
	private JButton bookButton = new JButton("예  약");
	private JButton searchButton = new JButton("선택");

	String header[] = { "병원 이름", "잔여 백신 재고", "신청 가능" }; // 표의 윗 부분
	String[][] hospital1 = { { "연세대세브란스병원", "150개", "O" }, { "  ", "  ", "  " }, { "  ", "  ", "  " },
			{ "  ", "  ", "  " } };
	String[][] hospital2 = { { "일산백병원", "150개", "O" }, { "  ", "  ", "  " }, { "  ", "  ", "  " },
			{ "  ", "  ", "  " } };
	String[][] hospital3 = { { "인제대해운대백병원", "150개", "O" }, { "  ", "  ", "  " }, { "  ", "  ", "  " },
			{ "  ", "  ", "  " } };
	// 표 중간에 들어갈 내용

	private JComboBox combo;

	private JTable table2 = new JTable(hospital2, header); // 병원의 이름,재고,신청가능여부를 보여줄 표
	private JTable table3 = new JTable(hospital3, header); // 병원의 이름,재고,신청가능여부를 보여줄 표

	private JLabel label = new JLabel("* 해당 지역구마다 각 하나의 병원이 백신 접종 병원으로 배정되었으며, 추후 증가 될 예정입니다.");

	private JOptionPane option = new JOptionPane();

	public String mapLocation; // 지도에 입력 될 키워드
	public String hName;

	public SelectHospital() {

		setVisible(false);
		setLayout(null);

		label.setBounds(120, 350, 800, 20);
		add(label); // 안내 문구 한 줄 표 아래 출력!

		try {
			hospitalList = hExcel.callTable();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String location[] = new String[hospitalList.size()+1];

		for (int i = 0; i < hospitalList.size(); i++) {
			location[i] = hospitalList.get(i).getMainDistrict();
		}

//		String location[] = { "지역 선택", hospitalList.get(0).getMainDistrict(), hospitalList.get(1).getMainDistrict(),
//				hospitalList.get(2).getMainDistrict() }; // 콤보 박스 안에 들어갈 내용

		combo = new JComboBox(location);

		
		mapButton.setBounds(80, 450, 180, 35);
		mapButton.setVisible(true);
		mapButton.setFont(new Font("고딕", Font.BOLD, 15));
		mapButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
			
				for(int i = 0; i < hospitalList.size(); i++) {
					if(combo.getSelectedItem().equals(hospitalList.get(i).getMainDistrict()))
						mapLocation = hospitalList.get(i).gethName();

				}

				
				map = new Map(mapLocation); // 선택된 병원의 이름을 맵의 초기값으로 매개변수로 넣어준다.
				map.setLocationRelativeTo(null); // 맵을 현재 창의 가운데로 생성되게 한다.

			}
		});
		add(mapButton); // 메인 패널에 병원위치(지도) 버튼 추가

		// 예약하기 버튼 설정
		bookButton.setBounds(630, 450, 180, 35);
		bookButton.setVisible(true);
		bookButton.setFont(new Font("고딕", Font.BOLD, 15));
		bookButton.addMouseListener(new MouseAdapter() {

			String addHName;

			@Override
			public void mousePressed(MouseEvent e) { // 예약버튼 클릭 시 이벤트 (팝업실행)

				for (int i = 0; i < hospitalList.size(); i++) {
					if (hospitalList.get(i).getMainDistrict().equals(combo.getSelectedItem())) {
						addHName = hospitalList.get(i).gethName();
						
						
						if (hospitalList.get(i).getVaccine() == 0) { //재고 백신 체크
							JOptionPane.showMessageDialog(null, "신청 불가",  "WARNING_MESSAGE",
									JOptionPane.WARNING_MESSAGE);
						} else { //재고가 남아있으면

							int result = option.showConfirmDialog(null, "예약 신청하시겠습니까?", "확인",
									JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.NO_OPTION) {

								setVisible(false);
								MainMenu.mainPanel.setVisible(true);
							} else { //예약 들어갔을 경우

								BookerList.addList(UserList.UserList().get(loginpage.sessionNum).getName(),
										UserList.UserList().get(loginpage.sessionNum).getSsn(),
										UserList.UserList().get(loginpage.sessionNum).getEmail(),
										(String) combo.getSelectedItem(), addHName);

								int vaccine = 0;
								for(int j = 0; j<hospitalList.size(); j++) {
									if(hospitalList.get(i).gethName().equals(addHName)) {
										vaccine = hospitalList.get(i).getVaccine()-1;
									}
								}
								
								
								// 예약된 병원의재고를 감소시켜주는 메소드
								
								try {
									hExcel.modifyVaccine(addHName,vaccine);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								
								System.out.println(BookerList.BookerList());

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
		add(combo);

		searchButton.setBounds(180, 80, 60, 30);
		searchButton.setVisible(true);
		searchButton.addMouseListener(new MouseAdapter() {
			String[][] table = null;
			JTable table1 = null;

			@Override
			public void mousePressed(MouseEvent e) {
				
				for (int i = 0; i < hospitalList.size(); i++) {
					if (combo.getSelectedItem().equals(hospitalList.get(i).getMainDistrict())) {
						String[][] table = {
								{ hospitalList.get(i).gethName(), hospitalList.get(i).getVaccine() + " 개",
										checkBook(hospitalList.get(i).getVaccine()) },
								{ "  ", "  ", "  " }, { "  ", "  ", "  " }, { "  ", "  ", "  " } };

						table1 = new JTable(table, header); // 병원의 이름,재고,신청가능여부를 보여줄 표

					}
				}
				table1.setRowHeight(50);
				table1.setVisible(true);

				final JScrollPane jscp1 = new JScrollPane(table1); // table은 이런식으로 넘겨줘야 정상출력된다.

				jscp1.setLocation(250, 80);
				jscp1.setSize(500, 210);

				jscp1.setVisible(true);
				add(jscp1);
			}
		});
		add(searchButton); // 패널에 병원위치(지도) 버튼 추가

		// 검색하기 버튼

	}

	public String checkBook(int vaccine) { // 백신 개수를 참고해 신청 가능 여부를 보여줌!
		if (vaccine == 0) {
			return "X";
		} else {
			return "O";
		}
	}


}
