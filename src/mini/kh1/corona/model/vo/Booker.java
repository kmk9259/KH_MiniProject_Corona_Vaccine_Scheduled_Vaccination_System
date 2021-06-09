package mini.kh1.corona.model.vo;

public class Booker {

	String name; // 이름
	String ssn; // 주민
	String email; // 이멜
	String location; // 지역구
	String hName; // 병원

	public Booker() {
		// TODO Auto-generated constructor stub
	}
	

	public Booker(String name, String ssn, String email, String location, String hName) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.email = email;
		this.location = location;
		this.hName = hName;
	}






	@Override
	public String toString() {
		return "Booker [name=" + name + ", ssn=" + ssn + ", email=" + email + ", location=" + location + ", hName="
				+ hName + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

}
