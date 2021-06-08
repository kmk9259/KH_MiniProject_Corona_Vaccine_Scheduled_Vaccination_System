package mini.kh1.corona.controller;

import java.util.Scanner;

import mini.kh1.corona.model.vo.User;

public class InsertConstroller {

	private User u = null; // 비어있는 회원 명단. 초기화 된 값...
	private Scanner sc = new Scanner(System.in);


	public void insertInfo(String id, String password, String name, String ssn, String email) {

		u = new User(id, password, name, ssn, email); // 입력받은 값으로 객체 설정

	}

	public User selectMem() { //user 값 던져줄 수 있게.. 
		
		return u;
	}

	public void updateInfo(String id, String password, String name, String ssn, String email) {
		u.setId(id);
		u.setPassword(password);
		u.setName(name);
		u.setSsn(ssn);
		u.setEmail(email);
		
	}
	
	public void deleteInfo() {
		// TODO Auto-generated method stub

	}

	

	

}
