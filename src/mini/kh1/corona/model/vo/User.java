package mini.kh1.corona.model.vo;

public class User {

	private String id;
	private String name;
	private String password;
	private String ssn;
	private String email;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String id, String name, String password, String ssn, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.ssn = ssn;
		this.email = email;
	}

	public static String getId() {
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

}
