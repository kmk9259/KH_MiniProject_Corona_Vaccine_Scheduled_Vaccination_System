package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.User;

public class UserList{
	static ArrayList<User> list = new ArrayList<User>();
	
	static public ArrayList<User> UserList() {
		return list;
	}
	public void addList(String id, String password, String name,  String ssn, String email)
	{
		list.add(new User(id, password,name, ssn, email));
		System.out.println(list);
	}
	//아이딛 비번 이름 주민 이메일
	
	
	
	
	

}
