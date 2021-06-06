package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckBookPanel extends JPanel {

	static JPanel checkBookPanel = new CheckBookPanel();
	
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
		
		//테이블에 값 넣기
		Object[] header = {"0", "0"};
		Object[][] contents = { //나중에 값 가져올 것임
				{"이름", "김아무개"}
				, {"주민등록번호", "121212-1******"}
				, {"접종 예정 병원", "A병원"}
				, {"접종 일시", "2021-06-05"}
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
		
		add(bookInfo);
		add(scp);
		
		JButton back = new JButton("이전");
		back.setBounds(20, 480, 120, 60);
		back.setVisible(true);
		add(back);
		
		back.addMouseListener(new MouseAdapter() {	//"이전으로" 버튼 눌렀을 때 MainPanel화면으로 이동
			
			@Override
			public void mousePressed(MouseEvent e) {
				JButton back = (JButton)e.getSource();
				
				checkBookPanel.setVisible(false);
				MainMenu.mainPanel.setVisible(true);
//				back.setVisible(false);
				
			}
		});
		
		JButton cancel = new JButton("예약 취소");
		cancel.setBounds(745, 480, 120, 60);
		cancel.setVisible(true);
		add(cancel);
		cancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {

				JButton cancel = (JButton)e.getSource();
				
				int result = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?\n'확인' 시 되돌릴 수 없습니다.", "예약 취소",  JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {	//"예" 일 때
					//취소 처리 과정 필요
					JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.\n홈 화면으로 이동합니다.");
					checkBookPanel.setVisible(false);
					MainMenu.mainPanel.setVisible(true);
//					cancel.setVisible(false);
				}
			}
		});
	}



}