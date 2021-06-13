package mini.kh1.corona.model.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;

public class MatchingDate {
	List<User> age = InsertPage.temp.getJoinlist();
	Calendar calendar = Calendar.getInstance();
	String[][] d = new String [age.size()/2][2];
	public MatchingDate() {
		// TODO Auto-generated constructor stub
	}
	public void matchingdate(Calendar c, String[][] d, User[][] day)
	{
		System.out.println("===============================");
		
		//System.out.println(c+"캘린더");
		for(int i=0; i<3; i++)	
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일");
			System.out.println("!!!!!"+sdf1.format(c.getTime()));
			
			for(int j=0; j<2; j++)
			{
				if(d[i][j]!=null)
				{
					System.out.println(d[i][j]+"님"+i+"아이"+j+"제이");
					
				}
				
					
				
			}
		}
		
	}
	

}
