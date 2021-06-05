package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HospitalAddFrame extends JFrame {

	private JPanel hafPanel;
	
	public HospitalAddFrame() {

		// 1. 프레임 설정
		setPreferredSize(new Dimension(600, 400));
		setSize(600, 400);
		setTitle("병원 추가");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 열려있는 창만 닫음

		// 2. 병원 추가 패널 설정
		hafPanel = new JPanel();
		hafPanel.setSize(600, 400);
		hafPanel.setLayout(null);
		hafPanel.setVisible(true);

		JLabel hafLabel1 = new JLabel("시/구");
		hafLabel1.setBounds(70, 50, 120, 40);
		hafLabel1.setFont(new Font("굴림", Font.BOLD, 15));
		hafLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		hafLabel1.setOpaque(true);
		hafLabel1.setBackground(Color.black);
		hafLabel1.setForeground(Color.white);
		hafPanel.add(hafLabel1);

		JLabel hafLabel2 = new JLabel("상세주소");
		hafLabel2.setBounds(70, 110, 120, 40);
		hafLabel2.setFont(new Font("굴림", Font.BOLD, 15));
		hafLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		hafLabel2.setOpaque(true);
		hafLabel2.setBackground(Color.black);
		hafLabel2.setForeground(Color.white);
		hafPanel.add(hafLabel2);

		JLabel hafLabel3 = new JLabel("병원명");
		hafLabel3.setBounds(70, 170, 120, 40);
		hafLabel3.setFont(new Font("굴림", Font.BOLD, 15));
		hafLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		hafLabel3.setOpaque(true);
		hafLabel3.setBackground(Color.black);
		hafLabel3.setForeground(Color.white);
		hafPanel.add(hafLabel3);

		JTextField hafTextField1 = new JTextField(15);// 시/구 텍스트
		hafTextField1.setBounds(200, 50, 310, 40);
		hafTextField1.setFont(new Font("굴림", Font.PLAIN, 13));
		hafPanel.add(hafTextField1);

		JTextField hafTextField2 = new JTextField(30);// 상세주소 텍스트
		hafTextField2.setBounds(200, 110, 310, 40);
		hafTextField2.setFont(new Font("굴림", Font.PLAIN, 13));
		hafPanel.add(hafTextField2);

		JTextField hafTextField3 = new JTextField(15);// 병원명 텍스트
		hafTextField3.setBounds(200, 170, 310, 40);
		hafTextField3.setFont(new Font("굴림", Font.PLAIN, 13));
		hafPanel.add(hafTextField3);

		JButton hafButton1 = new JButton("취   소");
		hafButton1.setBounds(70, 250, 170, 40);
		hafButton1.setFont(new Font("굴림", Font.PLAIN, 12));
		hafPanel.add(hafButton1);

		JButton hafButton2 = new JButton("추   가");
		hafButton2.setBounds(340, 250, 170, 40);
		hafButton2.setFont(new Font("굴림", Font.BOLD, 12));
		hafPanel.add(hafButton2);

		add(hafPanel);

		// ==========================================================================
		// 버튼 1,2 결과가 다르게 출력되도록 추후 설정할 것

		hafButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		hafButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
	}
}
