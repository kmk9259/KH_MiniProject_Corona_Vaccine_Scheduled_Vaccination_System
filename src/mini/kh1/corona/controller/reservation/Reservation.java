package mini.kh1.corona.controller.reservation;

import java.util.Collections;
import java.util.List;

import mini.kh1.corona.model.comparator.AscUserAge;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;

public class Reservation {
	List<User> age = InsertPage.temp.getJoinlist();
	
	public void orderAge()
	{
		Collections.sort(age, new AscUserAge());	//정렬부분
		
		for(User u :age)
		{
			System.out.println(u+"순서 정렬");
		}
		System.out.println("=================================================================");
		substringAge();	//년도 자르기
	}
	public void substringAge()
	{
		for(User u : age)
		{
			u.setSsn(u.getSsn().substring(0, 6));
			System.out.println(u+"substring 후");
		}
	}
}
