package mini.kh1.corona.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//예약하기 -> 공지 -> 지역선택 패널 구현 코드  JPanel상속 

public class SelectHospital extends JPanel { // 병원 선택 화면 패널

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
	String location[] = { "지역 선택", "서울 서대문구", "경기도 고양시", "부산 해운대구" }; // 콤보 박스 안에 들어갈 내용

	private JComboBox combo = new JComboBox(location);

	private JTable table1 = new JTable(hospital1, header); // 병원의 이름,재고,신청가능여부를 보여줄 표
	private JTable table2 = new JTable(hospital2, header); // 병원의 이름,재고,신청가능여부를 보여줄 표
	private JTable table3 = new JTable(hospital3, header); // 병원의 이름,재고,신청가능여부를 보여줄 표

	private JLabel label = new JLabel("* 해당 지역구마다 각 하나의 병원이 백신 접종 병원으로 배정되었으며, 추후 증가 될 예정입니다.");

	private JOptionPane option = new JOptionPane();

	public String getSelectHospital() { // 각 지역 선택시 해당하는 병원을 주소에 전달하여 위치버튼 실행 시 보여줌
		switch (combo.getSelectedIndex()) {
		case 1:
			return "연세대세브란스병원";

		case 2:
			return "일산백병원";

		case 3:
			return "인제대해운대백병원";
		default:
			return "서울시";

		}

	}

	public SelectHospital() {

		setVisible(false);
		setLayout(null);

		label.setBounds(120, 350, 800, 20);
		add(label); // 안내 문구 한 줄 표 아래 출력!

		// 병원위치(지도) 버튼 설정
		mapButton.setBounds(80, 450, 180, 35);
		mapButton.setVisible(true);
		mapButton.setFont(new Font("고딕", Font.BOLD, 15));
		mapButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				map = new Map();
				map.setLocationRelativeTo(null);

			}
		});
		add(mapButton); // 메인 패널에 병원위치(지도) 버튼 추가

		// 예약하기 버튼 설정
		bookButton.setBounds(630, 450, 180, 35);
		bookButton.setVisible(true);
		bookButton.setFont(new Font("고딕", Font.BOLD, 15));
		bookButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

//				combo.getSelectedIndex(); 선택 된 병원 이름의 정보를 담고 있음.
				// 매개 변수로 전달해야함
				// 재고 감소 처리

				// 예약버튼 클릭 시 이벤트 (팝업실행)
				option.showMessageDialog(null, "예약이 완료되셨습니다.");
				setVisible(false);
				MainMenu.mainPanel.setVisible(true);

			}
		});
		add(bookButton); // 패널에 병원위치(지도) 버튼 추가

		table1.setRowHeight(50);
		table1.setVisible(false);
		final JScrollPane jscp1 = new JScrollPane(table1); // table은 이런식으로 넘겨줘야 정상출력된다.
		jscp1.setLocation(250, 80);
		jscp1.setSize(500, 210);

		table2.setRowHeight(50);
		table2.setVisible(false);
		final JScrollPane jscp2 = new JScrollPane(table2); // table은 이런식으로 넘겨줘야 정상출력된다.
		jscp2.setLocation(250, 80);
		jscp2.setSize(500, 210);

		table3.setRowHeight(50);
		table3.setVisible(false);
		final JScrollPane jscp3 = new JScrollPane(table3); // table은 이런식으로 넘겨줘야 정상출력된다.
		jscp3.setLocation(250, 80);
		jscp3.setSize(500, 210);

		searchButton.setBounds(180, 80, 60, 30);
		searchButton.setVisible(true);
		searchButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				switch (combo.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					add(jscp1);
					table1.setVisible(true);
					break;
				case 2:
					add(jscp2);
					table2.setVisible(true);

					break;
				case 3:
					add(jscp3);
					table3.setVisible(true);
					break;
				}

			}
		});
		add(searchButton); // 패널에 병원위치(지도) 버튼 추가

		// 검색하기 버튼

		combo.setBounds(60, 80, 120, 30);
		add(combo);

	}

}
