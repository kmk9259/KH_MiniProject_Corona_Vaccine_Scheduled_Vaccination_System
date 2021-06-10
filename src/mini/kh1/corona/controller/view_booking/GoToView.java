package mini.kh1.corona.controller.view_booking;

import java.util.ArrayList;

import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.controller.user.UserList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.view.LoginPage;

public class GoToView {
	
	private ArrayList<Booker>  bookerlist = BookerList.BookerList();
	private int reyn = 1;	//예약이 되어있으면 0, 안되어있으면 1
	
	public int go(int vaccine) { //vaccine : 남은 백신 수 , reyn : 예약 여부
	//신청인원 현황 가져오기
	//신청인원이 마감되면 진입가능
		
		//사용자가 예약자 리스트에 있는지
		for(int i = 0; i < bookerlist.size()/2; i++) {
			if(UserList.UserList().get(LoginPage.sessionNum).getSsn().equals(bookerlist.get(i).getSsn())) {
				reyn = 0;
			}
			
		}
		
		if(vaccine == 0) {
			if(reyn == 0) {
				return 0; //신청인원 마감, 신청함
			}
			else {
				return 2;	//신청 안함
			}
		}
		else {
			return 1;	//신청인원 마감 안됨
		}
	
	//마감되지 않았으면 
	//"신청이 마감되지 않았습니다. 신청이 마감되면 알람을 보내드리오니 이메일을 확인해주세요."
	//메세지 창 띄우고 홈화면에 머무르기
	
	
	//마감되면 
	//예약 조회 들어가기 전 예약을 했는지 확인
	//예약자 리스트에 있는지
	
	//예약하지 않았으면 
	//"예약하기"를 먼저 진행해 주세요"
	//메세지 창 띄우고 홈 화면에 머무르기
	
	
	//예약 했으면 조회 패널로 이동
//		return 1;
	}
}
