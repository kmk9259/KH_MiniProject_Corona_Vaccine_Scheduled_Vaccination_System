package mini.kh1.corona.model.comparator;

import java.util.Comparator;

import mini.kh1.corona.model.vo.Booker;

public class AscUserAge implements Comparator<Booker>{

	public int compare(Booker o1, Booker o2) {
		// TODO Auto-generated method stub
		return o1.getSsn().compareTo(o2.getSsn());
	}

}
