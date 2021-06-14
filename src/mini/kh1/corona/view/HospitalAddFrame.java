package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mini.kh1.corona.controller.hospital.HospitalAddress;

public class HospitalAddFrame extends JFrame {

	private JPanel hafPanel;

	private JTextField hafTextField1;
	private JTextField hafTextField2;
	private JTextField hafTextField3;

	private Image image;
	
	public HospitalAddFrame() throws Exception {

		// 1. 프레임 설정
		setPreferredSize(new Dimension(600, 400));
		setSize(600, 400);
		//setTitle("병원 추가");
		setUndecorated(true);//타이틀바 안보이게
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 열려있는 창만 닫음
		
		//병원 관리 프레임 아이콘 이미지 추가(메소드에 throw하면 try-catch 없애도 될듯)
		
		setIconImage(ImageIO.read(new File("./image//managerIcon.PNG")));	
		
		image = ImageIO.read(new File("./image//manager1.PNG"));//메소드에 throws
		
		// 2. 병원 추가 패널 설정
		hafPanel = new JPanel(){

			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = hafPanel.getSize();
				//System.out.println("수량 입력 패널 사이즈 : " + hafPanel.getSize());
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}
			
		};
		hafPanel.setSize(600, 400);
		hafPanel.setLayout(null);
		hafPanel.setVisible(true);
		
		JLabel hafLabel0 = new JLabel("병원 정보 입력");
		hafLabel0.setBounds(80, 20, 120, 40);
		hafLabel0.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		hafLabel0.setHorizontalAlignment(SwingConstants.CENTER);		
		hafLabel0.setForeground(Color.BLACK);
		hafPanel.add(hafLabel0);

		JLabel hafLabel1 = new JLabel("시/구");
		hafLabel1.setBounds(80, 80, 120, 40);
		hafLabel1.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		hafLabel1.setHorizontalAlignment(SwingConstants.CENTER);		
		hafLabel1.setForeground(Color.DARK_GRAY);
		hafPanel.add(hafLabel1);
		
		JLabel hafLabel2 = new JLabel("상세주소");
		hafLabel2.setBounds(80, 140, 120, 40);
		hafLabel2.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		hafLabel2.setHorizontalAlignment(SwingConstants.CENTER);	
		hafLabel2.setForeground(Color.DARK_GRAY);
		hafPanel.add(hafLabel2);
		
		JLabel hafLabel3 = new JLabel("병원명");
		hafLabel3.setBounds(80, 200, 120, 40);
		hafLabel3.setFont(new Font("Nanum Gothic", Font.BOLD, 15));
		hafLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		hafLabel3.setForeground(Color.DARK_GRAY);
		hafPanel.add(hafLabel3);

		hafTextField1 = new JTextField(15);// 시/구 텍스트
		hafTextField1.setBounds(200, 80, 310, 40);
		hafTextField1.setOpaque(true);
		hafTextField1.setBackground(Color.DARK_GRAY);
		hafTextField1.setForeground(Color.WHITE);
		hafTextField1.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		hafPanel.add(hafTextField1);

		hafTextField2 = new JTextField(30);// 상세주소 텍스트
		hafTextField2.setBounds(200, 140, 310, 40);
		hafTextField2.setOpaque(true);
		hafTextField2.setBackground(Color.DARK_GRAY);
		hafTextField2.setForeground(Color.WHITE);
		hafTextField2.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		hafPanel.add(hafTextField2);

		hafTextField3 = new JTextField(15);// 병원명 텍스트
		hafTextField3.setBounds(200, 200, 310, 40);
		hafTextField3.setOpaque(true);
		hafTextField3.setBackground(Color.DARK_GRAY);
		hafTextField3.setForeground(Color.WHITE);
		hafTextField3.setFont(new Font("Nanum Gothic", Font.PLAIN, 13));
		hafPanel.add(hafTextField3);

		JButton hafButton1 = new JButton("취   소");
		hafButton1.setBounds(80, 280, 170, 40);
		hafButton1.setFont(new Font("Nanum Gothic", Font.PLAIN, 12));
		hafButton1.setFocusPainted(false);
		hafButton1.setForeground(Color.WHITE);
		hafButton1.setOpaque(true);
		hafButton1.setBackground(Color.BLACK);
		hafButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hafPanel.add(hafButton1);

		JButton hafButton2 = new JButton("추   가");
		hafButton2.setBounds(340, 280, 170, 40);
		hafButton2.setFont(new Font("Nanum Gothic", Font.BOLD, 12));
		hafButton2.setFocusPainted(false);
		hafButton2.setForeground(Color.WHITE);
		hafButton2.setOpaque(true);
		hafButton2.setBackground(Color.BLACK);
		hafButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hafPanel.add(hafButton2);

		add(hafPanel);

		// ==========================================================================

		hafButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		hafButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mainDistrict = hafTextField1.getText();
				String detailDistrict = hafTextField2.getText();
				String hName = hafTextField3.getText();
				int vaccine = 0;

				if(mainDistrict.isEmpty() || detailDistrict.isEmpty() || hName.isEmpty()) {//아무것도 입력 안했을 경우
					boolean emptyText = false;
					CompleteAdd(emptyText);
				}else {
					try {
						boolean result = new HospitalAddress().addAddress(mainDistrict, detailDistrict, hName, vaccine);//입력한 병원 정보가 추가 가능한지 여부 확인
						CompleteAdd(result);//여부에 따른 팝업창 띄우기
						if (result == true) { // 병원이 추가가 가능하면
							setVisible(false);
						} else { // 병원이 추가가 불가능하면
							// 다시 입력하기 편하게 기존에 입력된 값을 지움
							hafTextField1.setText("");
							hafTextField2.setText("");
							hafTextField3.setText("");
							setVisible(true);
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	// 알림 박스 출력용 메소드
	public void CompleteAdd(boolean result) {
		if (result == true) {
			JOptionPane.showMessageDialog(this, "병원 정보 저장이 완료되었습니다.");
		}

		if (result == false) {
			JOptionPane.showMessageDialog(this, "이미 존재하거나 잘못된 병원 정보입니다.");
		}

	}
}
