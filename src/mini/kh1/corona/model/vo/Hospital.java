package mini.kh1.corona.model.vo;

public class Hospital {

	private String mainDistrict;	//시,구
	private String hName;			//병원명
	
	public Hospital() {
		// TODO Auto-generated constructor stub
	}

	public Hospital(String mainDistrict, String hName) {
		super();
		this.mainDistrict = mainDistrict;
		this.hName = hName;
	}

	public String getMainDistrict() {
		return mainDistrict;
	}

	public void setMainDistrict(String mainDistrict) {
		this.mainDistrict = mainDistrict;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ 병원 기본 정보 -> 시/구 : " + mainDistrict + ", 병원명 : " + hName + " ]";
	}	
	
}
