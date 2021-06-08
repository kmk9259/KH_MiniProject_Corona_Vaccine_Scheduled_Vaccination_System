package mini.kh1.corona.view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mini.kh1.corona.controller.reservation.GoogleAPI;


//이 코드는 수정하지 말아주세요
public class Map extends JFrame{
	
	private JTextField textField = new JTextField(30);
	private JButton button = new JButton("검색");
	private JPanel panel = new JPanel();

	private GoogleAPI googleAPI = new GoogleAPI();
	private JLabel googleMap = new JLabel();
	
	private SelectHospital sh = new SelectHospital();
	private String location = "서울";

	
	public void setMap(String location) {
		
		
		googleAPI.downloadMap(location);
		googleMap.setIcon(googleAPI.getMap(location));
		googleAPI.fileDelete(location);
		add(BorderLayout.SOUTH,googleMap);
		pack();

	}
	
	
	
	public Map() {

		
		setLayout(new BorderLayout());
		setResizable(false);
		
		googleAPI.downloadMap(location);
		googleMap = new JLabel(googleAPI.getMap(location));
		googleAPI.fileDelete(location);
		add(googleMap);
		
		panel.add(textField);
		panel.add(button);
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				setMap(textField.getText());
			}
		});
		
		
		add(BorderLayout.NORTH,panel);
		add(BorderLayout.SOUTH,googleMap);
		
		
		setTitle("병원 위치");
		setVisible(true);
		pack();
	}
	


}
