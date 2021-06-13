package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.user.User;

public class CheckBookPanel extends JPanel {

	public static JPanel checkBookPanel = new CheckBookPanel();
	
	private int sNum = LoginPage.sessionNum;
	private List<User> ulist = InsertPage.temp.getJoinlist();
	BookerList list = new BookerList();
	ArrayList<Booker> bookerlist = list.getBookerList();
	Reservation r = new Reservation();
	ArrayList<Booker> rlist = r.getnBookerList();
	
	private Booker b = null;
	public CheckBookPanel() {
	
		
		setVisible(false);
		setLayout(null);
		setSize(900, 600);
		setEnabled(true);
		
		JLabel info = new JLabel("예약 정보");
		info.setSize(800, 70);
		info.setLocation(50, 20);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBackground(Color.WHITE);
		info.setOpaque(true);
		add(info);
		
		// 사용자의 예약정보(이름, 주민등록번호, 접종 병원, 접종일시)를 보여준다.
		
		// 1. ulist에서 정보를 가져와 주민등록 번호를 뽑아 온다. 
		String s = ulist.get(sNum).getSsn();	//사용자 주민등록 번호
		
		// 2. bookerList의 주민등록번호들과   ulist에서 뽑아온 주민등록번호(s)를 비교 --> 일치하는 인덱스 번호No를 가져온다.
		int No = 0;
		
		for(int i = 0; i < rlist.size(); i++) {
			if(s.equals(rlist.get(i).getSsn())) {
				No = i;
			}
		}
		
		// 3. bookerlist의 No번째 사람의 정보들을 가져온다.
		b = rlist.get(No);
		
		// 4. 가져온 사람의 정보에서 (이름, 주민, 병원, 일시)를 가져온다.
		
		String name = b.getName();
		String str = b.getSsn();	//예약자 주민번호
		String hName = b.getLocation() + " " + b.gethName();
		String Rday = b.getRday();
	
		// 5. 가져온 정보를 테이블의 각각의 자리에 입력한다.
		//테이블에 값 넣기
		Object[] header = {"0", "0"};
		Object[][] contents = { //나중에 값 가져올 것임
				{"이름", name}
				, { "주민등록번호", str}
				, {"접종 예정 병원", hName}
				, {"접종 일시", Rday}	//값을 가져와야함
		};
		
		JTable bookInfo = new JTable(contents, header);
//		bookInfo.setTableHeader(null);
		bookInfo.setLocation(50, 105);
		bookInfo.setSize(800, 360);
		
		bookInfo.setRowHeight(90);
		bookInfo.getColumnModel().getColumn(0).setMaxWidth(200);
		bookInfo.getColumnModel().getColumn(1).setMaxWidth(600);
		JScrollPane scp = new JScrollPane(bookInfo);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	     
	    TableColumnModel tcm = bookInfo.getColumnModel() ;
	     
	    for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	    tcm.getColumn(i).setCellRenderer(dtcr);  
	    }
		
	    bookInfo.setEnabled(false);
	    scp.setEnabled(false);
		add(bookInfo);
		add(scp);
		
		// 6. "뒤로가기"버튼 눌렀을 때 "메인메뉴"로 이동
		JButton back = new JButton("이전");
		back.setBounds(20, 480, 120, 60);
		back.setVisible(true);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		add(back);
		
		back.addMouseListener(new MouseAdapter() {	//"이전으로" 버튼 눌렀을 때 MainPanel화면으로 이동
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				MainMenu.MFrame.remove(checkBookPanel);
				MainMenu.mainPanel.setVisible(true);
				
			}
		});
		
		// 7. "취소"버튼을 눌렀을 때 취소 메소드 호출
		JButton cancel = new JButton("예약 취소");
		cancel.setBounds(745, 480, 120, 60);
		cancel.setVisible(true);
		cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		add(cancel);
		cancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {

				JButton cancel = (JButton)e.getSource();
				
				int result = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?\n'확인' 시 되돌릴 수 없습니다.", "예약 취소",  JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {	//"예" 일 때
					//취소 처리
					list.setBookerRemove(b);
					
					JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.\n홈 화면으로 이동합니다.");
					
					MainMenu.MFrame.remove(checkBookPanel);
					MainMenu.mainPanel.setVisible(true);
				}
			}
		});
		
		
	}

	

}