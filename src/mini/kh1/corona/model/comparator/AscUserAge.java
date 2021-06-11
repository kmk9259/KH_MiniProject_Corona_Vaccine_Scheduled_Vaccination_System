package mini.kh1.corona.model.comparator;

import java.util.Comparator;

import mini.kh1.corona.model.vo.user.User;

public class AscUserAge implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		return o1.getSsn().compareTo(o2.getSsn());
	}

}
