package mini.kh1.corona.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Notice extends JPanel {

	JPanel selectHospital = new SelectHospital();
	static JPanel NOTICE = new Notice();
	
	private Image image;

	public Notice() {

		setSize(900, 600);
		setLayout(null);
		//setEnabled(false);//이거 지워야 버튼 커서 모양이 바꿔서 주석처리 해놓았습니다.

		/*
		 * JLabel label = new JLabel();
		 * 
		 * label.setHorizontalAlignment(JLabel.CENTER); label.setText(
		 * "<HTML><body><center>공지사항 <br>" + "선착순으로 신청을 받습니다 <br> " +
		 * "단, 신청인 중에서 나이에 따른 순차적 접종이 시작되오니 유의해 주시길 바랍니다" +
		 * "<br> 백신의 순서가 한정적이므로 가급적 예약취소는 삼가해주시길 바랍니다" +
		 * "<br>이런식으로 출력하면 줄바꿈이되나 <br> 계속 이렇게 하면 줄바굼 할수있삼" +
		 * "<br> 줄바꿈으로 글을 다 넣어야 하나...이미지로 넣고 싶은디..." + "  </center></body></HTML>");
		 * 
		 * label.setFont(new Font("굴림", Font.BOLD, 20));
		 * 
		 * label.setSize(900, 600); add(label);
		 */

		JButton btn = new JButton(" 확  인  ");

		btn.setBounds(385, 475, 123, 51);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경	
		btn.setVisible(true);
		add(btn);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				JButton btn = (JButton)e.getSource();
					
				int num = JOptionPane.showConfirmDialog(null, " 공지사항을 충분히 숙지 하셨습니까? \n '예' 를 누르시면 지역샌택으로 넘어갑니다 ",
						"공지사항 ", JOptionPane.YES_NO_OPTION);

				if (num == JOptionPane.YES_OPTION) {
					
					NOTICE.setVisible(false);	//공지 감추기
					
					MainMenu.MFrame.add(selectHospital);	//프레임에 병원선택화면 추가
					selectHospital.setVisible(true);	//병원선택화면 노출
					// 병원 선택 판넬 소환하기

				} else { // 아니오 선택시 메인으로 돌아가기

					JOptionPane.showConfirmDialog(null, "메인으로 돌아갑니다","돌아가기",JOptionPane.YES_NO_OPTION);
					
					NOTICE.setVisible(false);	//공지 감추기
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
