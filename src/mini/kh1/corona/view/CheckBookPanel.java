package mini.kh1.corona.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Renderer;

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
		
		
		JTable bookInfo = new JTable(4, 2);
		bookInfo.setTableHeader(null);
		bookInfo.setLocation(50, 105);
		bookInfo.setSize(800, 360);
		
		bookInfo.setRowHeight(90);
		bookInfo.getColumnModel().getColumn(0).setMaxWidth(200);
		bookInfo.getColumnModel().getColumn(1).setMaxWidth(600);
		JScrollPane scp = new JScrollPane(bookInfo);
		
		
//		JPanel bookInfo = new JPanel();
//		bookInfo.setLayout(new GridLayout(4, 2));
//		bookInfo.setLocation(50, 10);
//		bookInfo.setSize(800, 400);
//		bookInfo.setBackground(Color.WHITE);
//
//		bookInfo.add(new JLabel("이름"));
		
		
		add(scp);
	}

}
