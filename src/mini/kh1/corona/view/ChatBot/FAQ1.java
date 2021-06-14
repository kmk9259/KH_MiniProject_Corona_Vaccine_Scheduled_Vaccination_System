package mini.kh1.corona.view.ChatBot;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FAQ1 extends JPanel{
	Image faq1  =  new ImageIcon("Image/chat.png").getImage();
	public FAQ1() {
		
	
		setSize(900, 600);
		setLayout(null);
		setEnabled(false);
		
		
	}
	public void paintComponent (Graphics g)
	{
		g.drawImage(faq1,0,0,null);
		
	}

}
