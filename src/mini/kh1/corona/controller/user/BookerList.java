package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.Booker;

public class BookerList{
	
	static ArrayList<Booker> bookerlist = new ArrayList<Booker>();
	
	static public ArrayList<Booker> BookerList() {
		
		 //이름,주민,이멜,지역구,병원
		bookerlist.add(new Booker("김선효","9509208912345","hi@gmail.com","서울특별시 서대문구","연세대세브란스병원"));
		bookerlist.add(new Booker("김민경","9801237891245","hello@naver.com","경기도 고양시","일산백병원"));
		bookerlist.add(new Booker("김은아","3012165291234","my@gmail.com","서울특별시 서대문구","연세대세브란스병원"));
		bookerlist.add(new Booker("김미소","4004148212345","name@naver.com","부산시 해운대구","인제대해운대백병원"));
		bookerlist.add(new Booker("장준영","7206215912345","is@gmail.com","부산시 해운대구","인제대해운대백병원"));
//		bookerlist.add(new Booker("장진석","7204207912345","zzabbong@naver.com","서울특별시 서대문구","일산백병원"));
//		bookerlist.add(new Booker("장동민","6208018912345","nice@gmail.com","서울특별시 서대문구","연세대세브란스병원"));
//		bookerlist.add(new Booker("유민상","5812046741234","to@naver.com","부산시 해운대구","인제대해운대백병원"));
//		bookerlist.add(new Booker("유상무","6606068491234","meet@gmail.com","경기도 고양시","일산백병원"));
//		bookerlist.add(new Booker("유재석","5505055789234","you@naver.com","경기도 고양시","일산백병원"));
//		bookerlist.add(new Booker("홍길동","4402024481234","and@gmail.com","서울특별시 서대문구","연세대세브란스병원"));
//		bookerlist.add(new Booker("홍길순","3801017855647","youuu@naver.com","경기도 고양시","일산백병원"));
//		
		
		
		
		return bookerlist;
	}
	
	
	
	
	public static void addList(String name, String ssn, String email,  String location, String hName)
	{
		bookerlist.add(new Booker(name, ssn,email, location, hName));
	}
	
	
	
	
	

}