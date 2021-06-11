package mini.kh1.corona.controller.user;

import java.util.HashMap;
import java.util.List;

import mini.kh1.corona.model.vo.user.JoinList;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.LoginPage;

public class AddJoin {
	
	private HashMap<String, User> bookMap;
	
	JoinList joinlist = new JoinList();
	AddSample sample = new AddSample();
	AddSignup signup = new AddSignup();
	
	LoginPage loginpage;
	
	public JoinList setjoin(List newuser) {	//newuser가 회원가입한 사람들
		joinlist.getJoinlist().addAll(sample.addsample());
		joinlist.getJoinlist().addAll(newuser);
		
		return joinlist;
	}

	//=========================================================
/*	
public int login(String id, String pw) {
		
		int result = 0; // 로그인 값은 정수로 받아 참,거짓 을 만들려고 변수 설정한다...
		
		for(int i = 0; i < InsertPage.temp.getJoinlist().size(); i++) {
		
	
		if(InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getId().equals(id)
				&& InsertPage.temp.getJoinlist().get(loginpage.sessionNum).getPassword().equals(pw)) {
			
			result = 1; // get 메소드 이용해서 로그인 값이 멤버 변수 안에 있는 값과 같다면...  1이란 숫자 표기로 
		}else {
		 
			result = 0;
		}
		

		}
		return result;
		
	}
*/
//============================================================================



}

	


