package mini.kh1.corona.controller.user;

import java.util.List;

import mini.kh1.corona.model.vo.user.JoinList;
import mini.kh1.corona.view.InsertPage;

public class AddJoin {
	JoinList joinlist = new JoinList();
	AddSample sample = new AddSample();
	AddSignup signup = new AddSignup();
	InsertPage insert;
	
	public void join()
	{
		joinlist.getJoinlist().addAll(sample.addsample());	//sample
		joinlist.getJoinlist().addAll(signup.addsignup());	//달러
		System.out.println(signup.addsignup()+"sign");	//달러,sign
		System.out.println(joinlist.getJoinlist()+"join");	//join
	}

	public void abc(List newuser) {
		
		joinlist.getJoinlist().addAll(sample.addsample());	//sample
		joinlist.getJoinlist().addAll(newuser);
		System.out.println(joinlist.getJoinlist()+"join2");
		
	}
	

}
