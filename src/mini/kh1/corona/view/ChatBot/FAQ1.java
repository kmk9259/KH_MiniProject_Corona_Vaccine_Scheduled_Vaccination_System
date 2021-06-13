package mini.kh1.corona.view.ChatBot;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FAQ1 extends JPanel{
	Image faq1  =  new ImageIcon("Image/chat.png").getImage();
	public FAQ1() {
		
		System.out.println("실행");
		setSize(900, 600);
		setLayout(null);
		setEnabled(false);
		
		JButton btn = new JButton("뒤로가기");
		add(btn);
		btn.setBounds(0, 0, 120, 50);
		btn.setVisible(true);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//######버튼 커서 변경
		
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new ChatBotView();
			}
			
		});
		
	}
	public void paintComponent (Graphics g)
	{
		g.drawImage(faq1,0,0,null);
		
	}

}
