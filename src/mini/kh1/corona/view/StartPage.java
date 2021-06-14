package mini.kh1.corona.view;

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
import javax.swing.JPanel;

public class StartPage extends JFrame {

	private JPanel startPanel;
	private Image image;
	private JButton startButton;

	public StartPage() {

		setPreferredSize(new Dimension(900, 600));
		setSize(900, 600);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		/*
		startPanel = new JPanel();

		startPanel.setSize(900, 600);
		startPanel.setLayout(null);
		startPanel.setVisible(false);
		startPanel.setOpaque(false);
		*/

		try {
			image = ImageIO.read(new File("./image//startImage.PNG"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		startButton = new JButton() {

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Dimension d = startButton.getSize();
				//System.out.println("메인패널 길이 : " + startButton.getSize());
				g.drawImage(image, 0, 0, d.width, d.height, null);
			}
			
		};
		startButton.setBounds(0, 0, 900, 600);
		startButton.setFocusPainted(false);// 버튼 테두리 없애기
		startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		startButton.setVisible(true);
		add(startButton);
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("스타트 버튼 클릭");
				setVisible(false);
				new LoginPage();

			}
		});

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		try {
			image = ImageIO.read(new File("./image//startImage.PNG"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Dimension d = getSize();
		//System.out.println("메인패널 길이 : " + getSize());
		g.drawImage(image, 0, 0, d.width, d.height, null);
		
	}
	
	
}
