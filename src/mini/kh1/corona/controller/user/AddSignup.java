package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.user.SignupList;
import mini.kh1.corona.model.vo.user.User;

public class AddSignup {
	SignupList signuplist = new SignupList();
	
	public ArrayList<User> addsignup()
	{
		System.out.println(signuplist.getSignuplist()+"$$$");
		return signuplist.getSignuplist();
	}
	public void adduser(String id, String password, String name,  String ssn, String email)
	{
		signuplist.getSignuplist().add(new User(id, password, name, ssn, email));
		System.out.println(signuplist.getSignuplist()+"회원가입");
	}
	

}
