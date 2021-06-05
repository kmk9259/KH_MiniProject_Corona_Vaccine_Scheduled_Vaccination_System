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

public class VaccineModifyFrame extends JFrame {

	private JPanel vmfPanel1;
	private JPanel vmfPanel2;
	
	public VaccineModifyFrame() {

		// 백신 재고 현황 화면에서 수량변경 버튼 클릭 시
		// 첫 번째 패널은 병원명을 입력받는 용도
		// 두 번째 패널은 입력받은 병원의 정보를 출력하고 변경할 재고수량을 입력받는 용도

		// 1. 프레임 설정
		setPreferredSize(new Dimension(550, 350));
		setSize(550, 350);
		setTitle("백신 수량 변경");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 열려있는 창만 닫음

		// 2. 병원명 입력하는 패널 설정
		vmfPanel1 = new JPanel();
		vmfPanel1.setSize(550, 350);
		vmfPanel1.setLayout(null);
		vmfPanel1.setVisible(true);

		JLabel vmfLabel1 = new JLabel("병원명을 입력하세요.");
		vmfLabel1.setBounds(70, 50, 170, 40);
		vmfLabel1.setFont(new Font("굴림", Font.BOLD, 15));
		vmfLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		vmfLabel1.setOpaque(true);
		vmfLabel1.setBackground(Color.black);
		vmfLabel1.setForeground(Color.white);
		vmfPanel1.add(vmfLabel1);

		JTextField vmfTextField1 = new JTextField(10);
		vmfTextField1.setBounds(70, 130, 390, 50);
		vmfTextField1.setFont(new Font("굴림", Font.PLAIN, 15));
		vmfPanel1.add(vmfTextField1);

		JButton vmfButton1 = new JButton("취   소");
		vmfButton1.setBounds(70, 220, 150, 40);
		vmfButton1.setFont(new Font("굴림", Font.PLAIN, 12));
		vmfPanel1.add(vmfButton1);

		JButton vmfButton2 = new JButton("다   음");
		vmfButton2.setBounds(310, 220, 150, 40);
		vmfButton2.setFont(new Font("굴림", Font.PLAIN, 12));
		vmfPanel1.add(vmfButton2);

		add(vmfPanel1);

		// 3. 백신 수량 입력하는 패널 설정
		vmfPanel2 = new JPanel();
		vmfPanel2.setSize(600, 400);
		vmfPanel2.setLayout(null);
		vmfPanel2.setVisible(false);// 첫 화면 아니기때문에 안보이게 설정

		JLabel vmfLabel2 = new JLabel("병원 정보");
		vmfLabel2.setBounds(70, 50, 120, 40);
		vmfLabel2.setFont(new Font("굴림", Font.BOLD, 15));
		vmfLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		vmfLabel2.setOpaque(true);
		vmfLabel2.setBackground(Color.black);
		vmfLabel2.setForeground(Color.white);
		vmfPanel2.add(vmfLabel2);

		JLabel vmfLabel3 = new JLabel("서울특별시 동작구 상도동 11-1 동작병원");
		vmfLabel3.setBounds(70, 115, 440, 40);
		vmfLabel3.setFont(new Font("굴림", Font.PLAIN, 13));
		vmfLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		vmfLabel3.setOpaque(true);
		vmfLabel3.setBackground(Color.CYAN);
		vmfLabel3.setForeground(Color.blue);
		vmfPanel2.add(vmfLabel3);

		JLabel vmfLabel4 = new JLabel("수량 입력");
		vmfLabel4.setBounds(70, 180, 120, 40);
		vmfLabel4.setFont(new Font("굴림", Font.BOLD, 15));
		vmfLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		vmfLabel4.setOpaque(true);
		vmfLabel4.setBackground(Color.black);
		vmfLabel4.setForeground(Color.white);
		vmfPanel2.add(vmfLabel4);

		JTextField vmfTextField2 = new JTextField(10);
		vmfTextField2.setBounds(200, 180, 310, 40);
		vmfPanel2.add(vmfTextField2);

		JButton vmfButton3 = new JButton("이   전");
		vmfButton3.setBounds(225, 250, 130, 40);
		vmfButton3.setFont(new Font("굴림", Font.PLAIN, 12));
		vmfPanel2.add(vmfButton3);

		//버튼 1,4,5는 수량에 따라 결과가 달라지므로 추후 기능 또는 코드 추가를 통해 실현할 것
		JButton vmfButton4 = new JButton("확   인");
		vmfButton4.setBounds(380, 250, 130, 40);
		vmfButton4.setFont(new Font("굴림", Font.BOLD, 12));
		vmfPanel2.add(vmfButton4);

		JButton vmfButton5 = new JButton("취   소");
		vmfButton5.setBounds(70, 250, 130, 40);
		vmfButton5.setFont(new Font("굴림", Font.PLAIN, 12));
		vmfPanel2.add(vmfButton5);

		add(vmfPanel2);

		// ==========================================================================

		// 버튼 기능 실현
		// 1. 첫 번째 패널에서 다음 버튼 클릭 시
		vmfButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmfPanel1.setVisible(false);
				setSize(600,400);
				vmfPanel2.setVisible(true);// 두 번째 패널로 넘어감
			}
		});
		// 취소 버튼 클릭 시
		vmfButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);// 수량이 변경되지 않은 화면으로 이동
			}
		});

		// 2. 두 번째 패널에서 확인 버튼 클릭 시
		vmfButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);// 수량이 변경된 화면으로 이동

			}
		});
		// 취소 버튼 클릭 시
		vmfButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);// 수량이 변경되지 않은 화면으로 이동

			}
		});

		// 이전 버튼 클릭 시
		vmfButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vmfPanel2.setVisible(false);
				setSize(550,350);
				vmfPanel1.setVisible(true);

			}
		});
	}
}