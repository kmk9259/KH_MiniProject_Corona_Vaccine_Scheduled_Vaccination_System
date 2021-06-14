package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.Booker;

public class BookerList{

	public static ArrayList<Booker> list = new ArrayList<Booker>();
	
	public BookerList() {}
	
	public ArrayList<Booker> getBookerList() {
		return list;
	}
	
	public void setBookerAdd(Booker booker1) {
		list.add(booker1);
	}

	public void setBookerRemove(Booker booker) {
		list.remove(booker);
	}
	
}