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

public class HospitalDelFrame extends JFrame {
	
	private JPanel hdfPanel1;
	private JPanel hdfPanel2;

	public HospitalDelFrame() {

		// 1. 프레임 설정
		setPreferredSize(new Dimension(540, 360));
		setSize(540, 360);
		setTitle("병원 삭제");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 열려있는 창만 닫음

		// 2. 병원명 입력하는 패널 설정
		hdfPanel1 = new JPanel();
		hdfPanel1.setSize(540, 360);
		hdfPanel1.setLayout(null);
		hdfPanel1.setVisible(true);

		JLabel hdfLabel1 = new JLabel("병원명을 입력하세요.");
		hdfLabel1.setBounds(70, 50, 170, 40);
		hdfLabel1.setFont(new Font("굴림", Font.BOLD, 15));
		hdfLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		hdfLabel1.setOpaque(true);
		hdfLabel1.setBackground(Color.black);
		hdfLabel1.setForeground(Color.white);
		hdfPanel1.add(hdfLabel1);

		JTextField hdfTextField1 = new JTextField(10);
		hdfTextField1.setBounds(70, 130, 385, 50);
		hdfTextField1.setFont(new Font("굴림", Font.PLAIN, 15));
		hdfPanel1.add(hdfTextField1);

		JButton hdfButton1 = new JButton("취   소");
		hdfButton1.setBounds(70, 220, 150, 40);
		hdfButton1.setFont(new Font("굴림", Font.PLAIN, 12));
		hdfPanel1.add(hdfButton1);

		JButton hdfButton2 = new JButton("다   음");
		hdfButton2.setBounds(305, 220, 150, 40);
		hdfButton2.setFont(new Font("굴림", Font.PLAIN, 12));
		hdfPanel1.add(hdfButton2);

		add(hdfPanel1);

		// 3. 병원 정보 확인 후 삭제하는 패널 설정
		hdfPanel2 = new JPanel();
		hdfPanel2.setSize(600, 400);
		hdfPanel2.setLayout(null);
		hdfPanel2.setVisible(false);// 첫 화면 아니기때문에 안보이게 설정

		JLabel hdfLabel2 = new JLabel("병원 정보");
		hdfLabel2.setBounds(70, 50, 120, 40);
		hdfLabel2.setFont(new Font("굴림", Font.BOLD, 15));
		hdfLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		hdfLabel2.setOpaque(true);
		hdfLabel2.setBackground(Color.black);
		hdfLabel2.setForeground(Color.white);
		hdfPanel2.add(hdfLabel2);

		JLabel hdfLabel3 = new JLabel("서울특별시 동작구 상도동 11-1 동작병원ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		hdfLabel3.setBounds(70, 115, 440, 40);
		hdfLabel3.setFont(new Font("굴림", Font.PLAIN, 13));
		hdfLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		hdfLabel3.setOpaque(true);
		hdfLabel3.setBackground(Color.CYAN);
		hdfLabel3.setForeground(Color.blue);
		hdfPanel2.add(hdfLabel3);

		JLabel hdfLabel4 = new JLabel("삭제하시겠습니까?");
		hdfLabel4.setBounds(70, 180, 200, 40);
		hdfLabel4.setFont(new Font("굴림", Font.BOLD, 15));
		hdfLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		hdfLabel4.setOpaque(true);
		hdfLabel4.setBackground(Color.black);
		hdfLabel4.setForeground(Color.white);
		hdfPanel2.add(hdfLabel4);

		JButton hdfButton3 = new JButton("취   소");
		hdfButton3.setBounds(70, 250, 130, 40);
		hdfButton3.setFont(new Font("굴림", Font.PLAIN, 12));
		hdfPanel2.add(hdfButton3);

		JButton hdfButton4 = new JButton("확   인");
		hdfButton4.setBounds(380, 250, 130, 40);
		hdfButton4.setFont(new Font("굴림", Font.BOLD, 12));
		hdfPanel2.add(hdfButton4);

		JButton hdfButton5 = new JButton("이   전");
		hdfButton5.setBounds(225, 250, 130, 40);
		hdfButton5.setFont(new Font("굴림", Font.PLAIN, 12));
		hdfPanel2.add(hdfButton5);

		add(hdfPanel2);

		// ==========================================================================
		// 삭제된 결과가 나타나게 추후 코드랑 기능 수정할 것 (버튼 1,3,4 고려사항)

		// 첫 번째 패널의 취소 버튼
		hdfButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		// 첫 번째 패널의 확인 버튼
		hdfButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hdfPanel1.setVisible(false);
				setSize(600, 400);
				hdfPanel2.setVisible(true);

			}
		});

		// 두 번째 패널의 취소 버튼
		hdfButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		// 두 번째 패널의 확인 버튼
		hdfButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		// 두 번째 패널의 이전 버튼 클릭 시
		hdfButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hdfPanel2.setVisible(false);
				setSize(540, 360);
				hdfPanel1.setVisible(true);

			}
		});
	}
}