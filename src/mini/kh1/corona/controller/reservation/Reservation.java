package mini.kh1.corona.controller.reservation;

import java.util.Collections;
import java.util.List;

import mini.kh1.corona.model.comparator.AscUserAge;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;
//원래 booker로 해야하는데 임의로 user
public class Reservation {
	//BookerList bookerlist = new BookerList();
	List<User> age = InsertPage.temp.getJoinlist();//bookerlist.getBookerList()
	User[][] day = new User[age.size()/2][2];
	
	public void orderAge()
	{
		Collections.sort(age, new AscUserAge());	//정렬부분
		System.out.println("=================================================================");
		substringAge();	//년도 자르기
	}
	public void substringAge()
	{
		for(User u : age)
		{
			u.setSsn(u.getSsn().substring(0, 6));
			System.out.println(u+"주민번호 앞자리6");
		}
	}
	public void cutPeople()
	{
		int cut =0;	//하루에 접종 가능한 인원 수
		int datecount=0;	//날짜 카운트
		String date = "2021년 6월";
			for(int i=0; i<age.size()/2; i++)
			{
				for(int j=0; j<2; j++)
				{
					day[i][j] = age.get(cut++);
					System.out.println(day[i][j]);
					if(cut>age.size()-1)
						cut=0;
				}
				
				if(datecount<=age.size()-1)
				{
					if(i==datecount)
						System.out.println((String)(date+(i+1))+"일");	
				}
				
				datecount++;
			}

	}
}
