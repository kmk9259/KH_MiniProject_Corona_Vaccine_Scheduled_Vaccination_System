package mini.kh1.corona.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Notice extends JPanel {

	JPanel selectHospital = new SelectHospital();
	
	private Image image;

	public Notice() {

		setSize(900, 600);
		setLayout(null);

		JButton btn = new JButton(" 확  인  ");

		btn.setBounds(385, 475, 123, 51);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경	
		btn.setForeground(Color.WHITE);
		btn.setOpaque(true);
		btn.setBackground(Color.BLACK);
		btn.setFocusPainted(false);
		
		add(btn);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				JButton btn = (JButton)e.getSource();
					
				int num = JOptionPane.showConfirmDialog(null, " 공지사항을 충분히 숙지 하셨습니까? \n '예' 를 누르시면 지역선택으로 넘어갑니다 ",
						"공지사항 ", JOptionPane.YES_NO_OPTION);

				if (num == JOptionPane.YES_OPTION) {
					
					setVisible(false);	//공지 감추기
					
					MainMenu.MFrame.add(selectHospital);	//프레임에 병원선택화면 추가
					selectHospital.setVisible(true);	//병원선택화면 노출
					// 병원 선택 판넬 소환하기

				} else { // 아니오 선택시 메인으로 돌아가기

					JOptionPane.showConfirmDialog(null, "메인으로 돌아갑니다","돌아가기",JOptionPane.YES_NO_OPTION);
					
					setVisible(false);	//공지 감추기
					MainMenu.mainPanel.setVisible(true);	//메인화면 노출
					
				}

			}

		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		try {
			image = ImageIO.read(new File("./image//notice.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}

	
}
