package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.user.SampleList;
import mini.kh1.corona.model.vo.user.User;

public class AddSample {
	SampleList samplelist = new SampleList();
	
	public ArrayList<User> addsample()
	{
		samplelist.getSamplelist().add(new User("A","1234","김선효","9509208912345","hi@gmail.com"));
		samplelist.getSamplelist().add(new User("B","1234","김민경","9801237891245","hello@naver.com"));
		samplelist.getSamplelist().add(new User("C","1234","김은아","3012165291234","my@gmail.com"));
		samplelist.getSamplelist().add(new User("D","1234","김미소","4004148212345","name@naver.com"));
		samplelist.getSamplelist().add(new User("E","1234","장준영","7206215912345","is@gmail.com"));
		System.out.println(samplelist.getSamplelist()+"sample");
		return samplelist.getSamplelist();
	}

}
