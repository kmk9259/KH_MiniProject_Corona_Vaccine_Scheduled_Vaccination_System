package mini.kh1.corona.model.vo;

public class Hospital {

	private String mainDistrict;	//시,구
	private String detailDistrict;	//상세주소
	private String hName;			//병원명
	private int vaccine;			//백신 재고 수량
	
	public Hospital() {
		// TODO Auto-generated constructor stub
	}

	public Hospital(String mainDistrict, String detailDistrict, String hName, int vaccine) {	//전체 정보 출력하는 매개변수 생성자
		super();
		this.mainDistrict = mainDistrict;
		this.detailDistrict = detailDistrict;
		this.hName = hName;
		this.vaccine = vaccine;
	}

	public Hospital(String mainDistrict, String detailDistrict, String hName) {					//병원 현황 출력 시 사용하는 오버로딩 생성자
		super();
		this.mainDistrict = mainDistrict;
		this.detailDistrict = detailDistrict;
		this.hName = hName;
	}

	public Hospital(String hName, int vaccine) {												//백신 재고 현황 출력 시 사용하는 오버로딩 생성자
		super();
		this.hName = hName;
		this.vaccine = vaccine;
	}

	public String getMainDistrict() {
		return mainDistrict;
	}

	public void setMainDistrict(String mainDistrict) {
		this.mainDistrict = mainDistrict;
	}

	public String getDetailDistrict() {
		return detailDistrict;
	}

	public void setDetailDistrict(String detailDistrict) {
		this.detailDistrict = detailDistrict;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	public int getVaccine() {
		return vaccine;
	}

	public void setVaccine(int vaccine) {
		this.vaccine = vaccine;
	}

	@Override
	public String toString() {	//병원 전체 정보
		// TODO Auto-generated method stub
		return "[ 병원 정보 -> 주소 : " + mainDistrict + " " + detailDistrict + ", 병원명 : " + hName + ", 백신 재고 수량 : " + vaccine + " ]";
	}
	
	public String info1() {		//병원 현황
		return "[ 병원 현홯 -> 주소 : " + mainDistrict + " " + detailDistrict + ", 병원명 : " + hName + " ]";
	}
	
	public String info2() {
		return "[ 백신 재고 현황 -> 병원명 : " + hName + ", 백신 재고 수량 : " + vaccine + " ]";
	}
	
	
}
