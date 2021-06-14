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
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;
import mini.kh1.corona.view.LoginPage;
//원래 booker로 해야하는데 임의로 user
public class Reservation {
	static BookerList bookerlist2 = new BookerList();
	public static List<Booker> age = bookerlist2.getBookerList();
	//

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
						
						d[i][2] = sdf1.format(calendar.getTime());
					}
						
				}
				
				datecount++;
			}
		return d;
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
		
		substringAge();	//년도 자르기
	}
	public void substringAge()
	{
		for(Booker b : age)
		{
			b.setSsn(b.getSsn().substring(0, 6));
			System.out.println("나이순 User 정렬 :"+b);
		}
	}
	
}
