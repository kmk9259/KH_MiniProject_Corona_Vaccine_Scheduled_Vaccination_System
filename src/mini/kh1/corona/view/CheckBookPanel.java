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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.user.User;

public class CheckBookPanel extends JPanel {
	static BookerList list = new BookerList();
	static ArrayList<Booker> bookerlist = list.getBookerList();
	static List<Booker> age = list.getBookerList();
	static String name =null;
	static String date =null;
	static String hospital=null;
	static String ssn=null;
	private Booker b = null;
	private int sNum = LoginPage.sessionNum;
	private List<User> ulist = InsertPage.temp.getJoinlist();
	
	Reservation r = new Reservation();
	ArrayList<Booker> rlist = r.getnBookerList();
	
	
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
		
		Reservation r = new Reservation();
		String[][] dd = new String [age.size()/2][3];
		dd = r.cut();
		for(int i=0; i<age.size()/2; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.println(dd[i][j]+"!");
				for(int k=0; k<bookerlist.size(); k++)
				{
					if(dd[i][j].equals(bookerlist.get(k).getName()))	//예약자 리스트와 날짜를 담은 dd이중배열을 string으로 받아옴
					{													
						hospital = bookerlist.get(k).gethName();
						name = bookerlist.get(k).getName();
						date = dd[i][2];
						ssn = bookerlist.get(k).getSsn();
						
						
					}
						
				}
			}
		}
			
		// 5. 가져온 정보를 테이블의 각각의 자리에 입력한다.
		//테이블에 값 넣기
		Object[] header = {"0", "0"};
		Object[][] contents = { //나중에 값 가져올 것임
				{"이름", name}
				, { "주민등록번호", ssn}
				, {"접종 예정 병원", hospital}
				, {"접종 일시", date}	//값을 가져와야함
		};
		
		JTable bookInfo = new JTable(contents, header);
		bookInfo.setLocation(50, 105);
		bookInfo.setSize(800, 360);
		
		bookInfo.setRowHeight(90);
		bookInfo.getColumnModel().getColumn(0).setMaxWidth(200);
		bookInfo.getColumnModel().getColumn(1).setMaxWidth(600);
		//JScrollPane scp = new JScrollPane(bookInfo);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
	     
	    TableColumnModel tcm = bookInfo.getColumnModel() ;
	     
	    for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	    tcm.getColumn(i).setCellRenderer(dtcr);  
	    }
		
	    bookInfo.setEnabled(false);
		add(bookInfo);
		
		// 6. "뒤로가기"버튼 눌렀을 때 "메인메뉴"로 이동
		JButton back = new JButton("이전");
		back.setBounds(20, 480, 120, 60);
		back.setVisible(true);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		back.setForeground(Color.WHITE);
		back.setOpaque(true);
		back.setBackground(Color.BLACK);
		back.setFocusPainted(false);
		add(back);
		
		back.addMouseListener(new MouseAdapter() {	//"이전으로" 버튼 눌렀을 때 MainPanel화면으로 이동
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				setVisible(false);
				MainMenu.mainPanel.setVisible(true);
				
			}
		});
		
		// 7. "취소"버튼을 눌렀을 때 취소 메소드 호출
		JButton cancel = new JButton("예약 취소");
		cancel.setBounds(745, 480, 120, 60);
		cancel.setVisible(true);
		cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		cancel.setForeground(Color.WHITE);
		cancel.setOpaque(true);
		cancel.setBackground(Color.BLACK);
		cancel.setFocusPainted(false);
		add(cancel);
		cancel.addMouseListener(new MouseAdapter() {
			
			@Override
	         public void mousePressed(MouseEvent e) {

	            JButton cancel = (JButton)e.getSource();
	            
	            int result = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?\n'확인' 시 되돌릴 수 없습니다.", "예약 취소",  JOptionPane.YES_NO_OPTION);
	            if(result == JOptionPane.YES_OPTION) {   //"예" 일 때
	               //취소 처리
	               for(int i = 0; i < bookerlist.size(); i++) {
	                  if(ulist.get(sNum).getSsn().equals(bookerlist.get(i).getSsn())) {
	                     b = bookerlist.get(i);
	                  }
	               }
	               
	               list.setBookerRemove(b);
	               
	               JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.\n홈 화면으로 이동합니다.");
	               
	               setVisible(false);
	               MainMenu.mainPanel.setVisible(true);
	            }
	         }
		});
		
		
	}
	public void bookerInfo()
	{
		Reservation r = new Reservation();
		String[][] dd = new String [age.size()/2][3];
		dd = r.cut();
		for(int i=0; i<age.size()/2; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.println(dd[i][j]+"!");
				for(int k=0; k<bookerlist.size(); k++)
				{
					if(dd[i][j].equals(bookerlist.get(k).getName()))	//예약자 리스트와 날짜를 담은 dd이중배열을 string으로 받아옴
					{													
						hospital = bookerlist.get(k).gethName();
						name = bookerlist.get(k).getName();
						date = dd[i][2];
						ssn = bookerlist.get(k).getSsn();
						
						
					}
						
				}
			}
		}
		
	}

	

}