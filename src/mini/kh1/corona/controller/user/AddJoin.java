package mini.kh1.corona.controller.user;

import java.util.List;

import mini.kh1.corona.model.vo.user.JoinList;

public class AddJoin {
	JoinList joinlist = new JoinList();
	AddSample sample = new AddSample();
	AddSignup signup = new AddSignup();
	
	public JoinList setjoin(List newuser) {	//newuser가 회원가입한 사람들
		joinlist.getJoinlist().addAll(sample.addsample());
		joinlist.getJoinlist().addAll(newuser);
		
		return joinlist;
	}

}
