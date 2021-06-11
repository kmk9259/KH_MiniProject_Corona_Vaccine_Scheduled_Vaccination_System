package mini.kh1.corona.controller.view_booking;

import java.util.ArrayList;

import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.view.InsertPage;
import mini.kh1.corona.view.LoginPage;

public class Cancel {

	private ArrayList<Booker>  bookerlist = BookerList.BookerList();
	private int sNum = LoginPage.sessionNum;
	InsertPage insertPage;
	//사용자의 개인정보, 예약정보 가져오기
	
	//checkbookpanel 에서 취소버튼 누르면 실행
	//사용자의 접종 병원, 접종 날짜 삭제
	
	public ArrayList<Booker> RCancel() {
		
		for(int i = 0; i < bookerlist.size()/2; i++) {//현재 접속하고있는 유저의 이름을 가져와서 예약자 명단의 이름과 일치하는 객체를 삭제
			if(InsertPage.temp.getJoinlist().get(LoginPage.sessionNum).getName().equals(bookerlist.get(i).getName())) {
				
				bookerlist.remove(LoginPage.sessionNum);//접종예약 리스트에서 삭제
			}
		}
		
		return bookerlist;
	}
	
}
