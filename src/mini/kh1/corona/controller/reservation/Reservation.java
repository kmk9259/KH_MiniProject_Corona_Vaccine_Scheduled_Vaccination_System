package mini.kh1.corona.controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.comparator.AscUserAge;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.model.vo.MatchingDate;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;
import mini.kh1.corona.view.LoginPage;
//원래 booker로 해야하는데 임의로 user
public class Reservation {
	static BookerList bookerlist2 = new BookerList();
	public static List<Booker> age = bookerlist2.getBookerList();
	//
	MatchingDate m = new MatchingDate();
	String[][] d = new String [age.size()/2][3];
	
	
	private HospitalExcel hExcel = new HospitalExcel();
	
	private Vector<HospitalVaccine> hospitalList = new Vector<HospitalVaccine>();
	
	BookerList list = new BookerList();
	ArrayList<Booker> bookerlist = list.getBookerList();
	private List<User> ulist = InsertPage.temp.getJoinlist();
	private int sn = LoginPage.getsn();
	
	private ArrayList<Booker> newbooker = new ArrayList<Booker>(); //해당 병원을 예약한 사람들
	
	public String[][] cut()
	{
		
		int cut =0;	//하루에 접종 가능한 인원 수
		int datecount=0;
		
		Calendar calendar = Calendar.getInstance();

			for(int i=0; i<age.size()/2; i++)
			{
				for(int j=0; j<2; j++)
				{
					d[i][j] = age.get(cut++).getName();
					if(cut>age.size()-1)
						cut=0;
					if(i==datecount)
					{
						calendar.set(2021, 5, i+1);
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일");
						//System.out.println("@@@@"+sdf1.format(calendar.getTime()));
						//m.matchingdate(calendar, d,day);
						d[i][2] = sdf1.format(calendar.getTime());
						System.out.println(d[i][j]+"######");
						System.out.println(d[i][2]+"sssssssssssssssssssss");
					}
						
				}
				
				datecount++;
			}
		return d;
	}


	
	
	public void cutPeople(String hname)
	{
		/*
		 * day[0][0] = age.get(0);	1일
		 * day[0][1] = age.get(1);	1일
		 * 
		 * day[1][0] = age.get(2);	2일	
		 * day[1][1] = age.get(3);	2일
		 * 
		 * day[2][0] = age.get(4);
		 * day[2][1] = age.get(5);
		 * 
		 * 
		 */
//		int cut =0;	//하루에 접종 가능한 인원 수
//		int datecount=0;	//날짜 카운트
//		String date = "2021년 6월";
//			for(int i=0; i<age.size()/2; i++)
//			{
//				for(int j=0; j<2; j++)
//				{
//					day[i][j] = age.get(cut++);
//					System.out.println(day[i][j]);
//					if(cut>age.size()-1)
//						cut=0;
//				}
//				
//				if(datecount<=age.size()-1)
//				{
//					if(i==datecount)
//						System.out.println((String)(date+(i+1))+"일");	
//				}
//				
//				datecount++;
//			}

		try {
			hospitalList = hExcel.callTable();
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
//		String hName = "";
//		
//		//사용자가 예약자 리스트에 있는지
//		for(int i = 0; i < bookerlist.size(); i++) {
//			if(ulist.get(sn).getSsn().equals(bookerlist.get(i).getSsn())) {
//				hName = bookerlist.get(i).gethName();	//내가 예약한 병원 이름
//			}
//		}
		
		
		for(int i = 0; i < bookerlist.size(); i++ ) {
			if(hname.equals(bookerlist.get(i).gethName())) {	//예약한 병원과 병원리스트의 이름과 비교
				setnBookerAdd(bookerlist.get(i));//해당 병원을 예약한 사람 추가
			}
		}
		
		//병원 별 예약자 정렬
		orderAge();
		
		int cut = 2;
		int day = 1;
		String date = "2021년 6월";
		
		for(int i = 0; i < newbooker.size(); i++) {
			if(cut > 0) {
				newbooker.get(i).setRday((String)(date + day + "일"));
				cut--;
			}
			else if (cut == 0) {
				cut = 2;
				day++;
				i--;
				continue;
			}
		}
		
		System.out.println("******************리저베이션******************");
		for(int i = 0; i< newbooker.size(); i++) {
			System.out.println(newbooker.get(i).getName() + "----" + newbooker.get(i).getRday());
		}
	}
	
	public void setnBookerAdd(Booker b) {
		newbooker.add(b);
	}
	
	public ArrayList<Booker> getnBookerList() {
		return newbooker;
	}
	
	public void orderAge()
	{
		Collections.sort(age, new AscUserAge());	//정렬부분
		System.out.println(age+"나이 재배치22");
		substringAge();	//년도 자르기
	}
	public void substringAge()
	{
		for(Booker b : age)
		{
			b.setSsn(b.getSsn().substring(0, 6));
			System.out.println(b+"주민번호 앞자리6");
		}
	}
	
}
