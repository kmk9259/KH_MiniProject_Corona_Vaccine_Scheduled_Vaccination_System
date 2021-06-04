package mini.kh1.corona.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckBookPanel extends JPanel {


	public CheckBookPanel() {
		setVisible(false);
		setLayout(null);
		setSize(900, 600);

		JButton back = new JButton("이전");
		back.setBounds(20, 480, 120, 60);
		add(back);
		
		JButton cancel = new JButton("예약 취소");
		cancel.setBounds(745, 480, 120, 60);
		add(cancel);
		
		JLabel info = new JLabel("예약 정보");
		info.setSize(800, 70);
		info.setLocation(50, 20);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBackground(Color.WHITE);
		info.setOpaque(true);
		add(info);
		
		Object[] header = {"0", "0"};
		Object[][] contents = { //나중에 값 가져올 것임
				{"이름", "0"}
				, {"주민등록번호", "0"}
				, {"접종 예정 병원", "0"}
				, {"접종 일시", "0"}
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
	     
	    //전체 열에 지정
	    for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	    tcm.getColumn(i).setCellRenderer(dtcr);  
	    }
		
		add(bookInfo);
		add(scp);
	}

}
