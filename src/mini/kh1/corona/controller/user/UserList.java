package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.User;

public class UserList{
	static ArrayList<User> list = new ArrayList<User>();
	
	public ArrayList<User> UserList() {
		return list;
	}
	public void addList(String id, String name, String password, String ssn, String email)
	{
		list.add(new User(id, name, password, ssn, email));
		System.out.println(list);
	}
	
	
	
	
	

}
