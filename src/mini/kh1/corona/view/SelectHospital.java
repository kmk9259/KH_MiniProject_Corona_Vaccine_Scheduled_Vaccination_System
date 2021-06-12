package mini.kh1.corona.view;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.AddSample;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.controller.view_booking.GoToView;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.model.vo.user.User;

//예약하기 -> 공지 -> 지역선택 패널 구현 코드  JPanel상속 

public class SelectHospital extends JPanel { // 병원 선택 화면 패널

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
	
	
	public SelectHospital() { 

		repaint();
		setVisible(false);
		setLayout(null);

		label.setBounds(120, 350, 800, 20);
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

//		String location[] = { "지역 선택", hospitalList.get(0).getMainDistrict(), hospitalList.get(1).getMainDistrict(),
//				hospitalList.get(2).getMainDistrict() }; // 콤보 박스 안에 들어갈 내용

		combo = new JComboBox(location);

		mapButton.setBounds(80, 450, 180, 35);
		mapButton.setVisible(true);
		mapButton.setFont(new Font("고딕", Font.BOLD, 15));
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
		bookButton.setVisible(true);
		bookButton.setFont(new Font("고딕", Font.BOLD, 15));
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
								System.out.println("list111.getBookerList().size() ["+list111.getBookerList().size()+"]");
								
								String loca = combo.getSelectedItem().toString();
								Booker booker1 = new Booker(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getName(),
										InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getSsn(),
										InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getEmail(), loca, addHName);

								list111.setBookerAdd(booker1);
								
								System.out.println("list111.getBookerList().size() ["+list111.getBookerList().size()+"]");
//								bookerlist = sample.addsample(); //유저샘플리스트를 가져와서 그대로 예약자 샘플리스트로 쓴다.
//							 User booker = null; //유저리스트 가져왔기 때문에 일단 예약자도 유저객체로 통일시킴.(제네릭을 삭제하긴 했는데 
//							 									                          	//그래도 캐스트 오류 발생했었음 )
//							 booker = InsertPage.temp.getJoinlist().get(loginpage.sessionNum);//로그인한 사람 담음
//								
//								String loca = combo.getSelectedItem().toString();
//								
//								bookerlist.add(booker); //1번 방법 샘플리스트 그대로 복사해온 예약자리스트에 새로운 예약자 추가 
								
//								blist.setBookerList(bookerlist); //2번방법 따로 생성해놓은 예약자리스트 클래스도 새로운 리스트로 셋팅 
								
							
//									System.out.println(bookerlist.toString()); // 방법 1 : 이 클래스에 만들어놓은 스태틱 예약자리스트 출력
//									System.out.println(blist.getBookerList().toString()); //방법 2 : 따로 만들어놓은 예약자리스트 클래스호출 출력
									
									
									//둘다 리스트에 추가 반영은 일단 되는 상태에요 둘다 가져와서 한번 테스트 해보셔야 할듯 해요
									//전 일단 1번방법으로 사용했어영
								int vaccine = 0;
								for (int j = 0; j < hospitalList.size(); j++) {
									if (hospitalList.get(i).gethName().equals(addHName)) {
										vaccine = hospitalList.get(i).getVaccine();
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
		add(combo);
		String[][] table = null;

		searchButton.setBounds(180, 80, 60, 30);
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

}
