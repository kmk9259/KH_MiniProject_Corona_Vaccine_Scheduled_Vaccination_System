package mini.kh1.corona.controller.view_booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import mini.kh1.corona.controller.hospital.HospitalExcel;
import mini.kh1.corona.controller.reservation.Reservation;
import mini.kh1.corona.controller.user.BookerList;
import mini.kh1.corona.model.vo.Booker;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.model.vo.user.User;
import mini.kh1.corona.view.InsertPage;
import mini.kh1.corona.view.LoginPage;
import mini.kh1.corona.view.SelectHospital;

public class GoToView {
	
	private HospitalExcel hExcel = new HospitalExcel();
	
	private Vector<HospitalVaccine> hospitalList = new Vector<HospitalVaccine>();
	
//	BookerList list;
//	ArrayList<Booker> bookerlist = list.getBookerList();
	private List<User> ulist = InsertPage.temp.getJoinlist();
	private int sn = LoginPage.getsn();
	private boolean reyn = false;	//예약이 되어있으면 true, 안되어있으면 false
	private boolean bo = SelectHospital.isBookOver;
	int vaccine;
	
	BookerList list = new BookerList();
	ArrayList<Booker> bookerlist = list.getBookerList();
	
	ArrayList<Booker> d = null;
	
	Reservation r = new Reservation();
	ArrayList<Booker> bb = null;
	
	public int go() { 
		
		try {
			hospitalList = hExcel.callTable();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String hName = "";
		
		//사용자가 예약자 리스트에 있는지
		for(int i = 0; i < bookerlist.size(); i++) {
			if(ulist.get(sn).getSsn().equals(bookerlist.get(i).getSsn())) {
				reyn = true;
				hName = bookerlist.get(i).gethName();
			}
		}
		
		if(reyn == true) {
			if(bo == true) {
				return 0; //신청인원 마감, 신청함
			}
			else {
				return 1;	//신청인원 마감 안됨
			}
		}
		else {
			return 2;	//신청 안함
		}
	}
}
	
