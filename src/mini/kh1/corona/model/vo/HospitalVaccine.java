package mini.kh1.corona.model.vo;

public class HospitalVaccine extends Hospital{

	private int vaccine;
	
	public HospitalVaccine() {
		// TODO Auto-generated constructor stub
	}

	public HospitalVaccine(String mainDistrict, String hName, int vaccine) {
		super(mainDistrict, hName);
		this.vaccine = vaccine;
	}

	public int getVaccine() {
		return vaccine;
	}

	public void setVaccine(int vaccine) {
		this.vaccine = vaccine;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "[ 추가 백신 정보 : " + vaccine + " ]";
	}
	
	
}
