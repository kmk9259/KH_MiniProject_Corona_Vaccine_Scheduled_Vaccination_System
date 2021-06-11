package mini.kh1.corona.model.vo.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JoinList implements Iterator<String> {
	private List<User> joinlist = new ArrayList<User>();
	

	public List<User> getJoinlist() {
		return joinlist;
	}

	public void setJoinlist(List list) {
		this.joinlist = list;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
