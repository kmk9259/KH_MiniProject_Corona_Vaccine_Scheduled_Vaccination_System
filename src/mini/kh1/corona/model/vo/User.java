package mini.kh1.corona.model.vo;

import java.util.ArrayList;

public class User {

	private String id;
	private String name;
	private String password;
	private String ssn;
	private String email;
	private int age;		//나이
	

	public User() {
		// TODO Auto-generated constructor stub
	}

	
	//매개변수 생성자
	public User(String id, String name, String password, String ssn, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.email = email;
		this.age = age;
	}


	//오버로딩 생성자
	public User(String id, String password, String name,String ssn, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.email = email;
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", ssn=" + ssn + ", email=" + email
				+ "]";
	}

	public String information() {
		return id + "\n" + password + "\n" + name + "\n" + ssn + "\n" + email;
	}
	
	public String information2() {
		
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", ssn=" + ssn + ", email=" + email
				+ ", age = " + age + " ]";
	}
}
