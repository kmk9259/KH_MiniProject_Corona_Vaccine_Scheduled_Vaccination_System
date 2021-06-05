package mini.kh1.corona.view;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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

	String header[] = {"병원 이름","잔여 백신 재고","신청 가능"}; //표의 윗 부분
	Object[][] hospital = {{ "연세대세브란스병원", "150개", "O" },{"  ","  ","  " },{"  ","  ","  "},{"  ","  ","  "}};
	 //표 중간에 들어갈 내용

	private JTable table = new JTable(hospital,header); // 병원의 이름,재고,신청가능여부를 보여줄 표
	private JLabel label = new JLabel("* 해당 지역구마다 각 하나의 병원이 백신 접종 병원으로 배정되었으며, 추후 증가 될 예정입니다.");

	private Choice choice; //드롭다운 기능
	private JOptionPane option = new JOptionPane();


	public String getSelectHospital() { //각 지역 선택시 해당하는 병원을 주소에 전달하여 위치버튼 실행 시 보여줌
		switch(choice.getSelectedIndex()) {
		case 0 : 
			return "연세대세브란스병원";
		case 1 :
			return "일산백병원";
		case 2 : 
			return "인제대해운대백병원";
		}
		return "서울시";
		
		
	}

	





	public SelectHospital() {
		
		setVisible(false);
		setLayout(null);
		
	    choice = new Choice();// 드롭다운 (지역 선택)
		choice.add("서울 서대문구");
		choice.add("경기도 고양시");
		choice.add("부산 해운대구");
		choice.setBounds(50, 80, 120, 100);

		add(choice);

		table.setRowHeight(50);
		table.setVisible(false);
		JScrollPane jscp = new JScrollPane(table); //table은 이런식으로 넘겨줘야 정상출력된다.
		jscp.setLocation(250,80);
		jscp.setSize(500,210);
		add(jscp);

		label.setBounds(120, 350, 800, 20); 
		add(label); // 안내 문구 한 줄 표 아래 출력!
		
		// 병원위치(지도) 버튼 설정
		mapButton.setBounds(80, 450, 180, 35);
		mapButton.setVisible(true);
		mapButton.setFont(new Font("고딕",Font.BOLD,15));
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
		bookButton.setFont(new Font("고딕",Font.BOLD,15));
		bookButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				//예약버튼 클릭 시 이벤트 (팝업실행)
				option.showMessageDialog(null, "예약이 완료되셨습니다.");
				setVisible(false);
				MainMenu.mainPanel.setVisible(true);
				
			}
		});
		add(bookButton); // 메인 패널에 병원위치(지도) 버튼 추가
	
	
	
		
		
		
		choice.addMouseListener(new MouseAdapter() { // 지역선택시 이벤트
			
			
			@Override
			public void mousePressed(MouseEvent e) {

				switch (choice.getSelectedItem()) { // 초이스 된 아이템의 정보를 가져온다.
				case "서울 서대문구":
					// 연세대세브란스병원의 재고 현황 출력
					
					table.setVisible(true);
					 
					
					break;
				case "경기도 고양시":
					// 일산백병원의 재고 현황 출력
					break;
				case "부산 해운대구":
					// 인제대해운대백병원의 재고 현황 출력
					break;

				}
			}

		});
		
		
		

	}

}
