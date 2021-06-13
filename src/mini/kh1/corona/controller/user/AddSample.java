package mini.kh1.corona.controller.user;

import java.util.ArrayList;

import mini.kh1.corona.model.vo.user.SampleList;
import mini.kh1.corona.model.vo.user.User;

public class AddSample {
	SampleList samplelist = new SampleList();
	
	public ArrayList<User> addsample()
	{
		samplelist.getSamplelist().add(new User("A","1234","김선효","9509208912345","sunhyo67@gmail.com"));
		samplelist.getSamplelist().add(new User("B","1234","김민경","9801237891245","kmk9259@naver.com"));
		samplelist.getSamplelist().add(new User("C","1234","김은아","3012165291234","my@gmail.com"));
		samplelist.getSamplelist().add(new User("D","1234","김미소","4004148212345","name@naver.com"));
		samplelist.getSamplelist().add(new User("E","1234","장준영","7206215912345","phantom1984meriel@gmail.com"));
		return samplelist.getSamplelist();
	}
	
	
	

}
