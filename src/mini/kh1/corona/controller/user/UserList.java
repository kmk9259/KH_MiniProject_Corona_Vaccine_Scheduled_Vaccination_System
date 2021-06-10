package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.User;

public class UserList{
	static ArrayList<User> list = new ArrayList<User>();
	
	static public ArrayList<User> UserList() {
		list.add(new User("A","1234","김선효","9509208912345","hi@gmail.com"));
		list.add(new User("B","1234","김민경","9801237891245","hello@naver.com"));
		list.add(new User("C","1234","김은아","3012165291234","my@gmail.com"));
		list.add(new User("D","1234","김미소","4004148212345","name@naver.com"));
		list.add(new User("E","1234","장준영","7206215912345","is@gmail.com"));
		list.add(new User("F","1234","장진석","7204207912345","hi@gmail.com"));
//		list.add(new User("G","1234","장동민","6208018912345","hi@gmail.com"));
//		list.add(new User("H","1234","유민상","5812046741234","hi@gmail.com"));
//		list.add(new User("I","1234","유상무","6606068491234","hi@gmail.com"));
//		list.add(new User("J","1234","유재석","5505055789234","hi@gmail.com"));
//		list.add(new User("K","1234","홍길동","4402024481234","hi@gmail.com"));
//		list.add(new User("L","1234","홍길순","3801017855647","hi@gmail.com"));
		return list;
	}
	
	public void addList(String id, String password, String name,  String ssn, String email)
	{
		
		list.add(new User(id, password,name, ssn, email));
		System.out.println(list);
	}
	
	
	
	
	

}
