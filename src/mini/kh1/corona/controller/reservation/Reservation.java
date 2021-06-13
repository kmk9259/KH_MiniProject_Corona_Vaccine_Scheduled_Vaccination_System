package mini.kh1.corona.controller.reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.comparator.AscUserAge;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;
import mini.kh1.corona.view.LoginPage;
//원래 booker로 해야하는데 임의로 user
public class Reservation {
	//BookerList bookerlist = new BookerList();
//	List<User> age = InsertPage.temp.getJoinlist();//bookerlist.getBookerList()
//	User[][] day = new User[age.size()/2][2];
	
	
	private HospitalExcel hExcel = new HospitalExcel();
	
	private Vector<HospitalVaccine> hospitalList = new Vector<HospitalVaccine>();
	
	BookerList list = new BookerList();
	ArrayList<Booker> bookerlist = list.getBookerList();
	private List<User> ulist = InsertPage.temp.getJoinlist();
	private int sn = LoginPage.getsn();
	
	private ArrayList<Booker> newbooker = new ArrayList<Booker>();; //해당 병원을 예약한 사람들
	
	
	
	
	public void cutPeople(String hname)
	{
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
		Collections.sort(newbooker, new AscUserAge());	//정렬부분
		System.out.println("=================================================================");
		substringAge();	//년도 자르기
	}
	public void substringAge()
	{
		for(Booker b : newbooker)
		{
			b.setSsn(b.getSsn().substring(0, 6));
			System.out.println(b+"주민번호 앞자리6");
		}
	}
	
}
