package mini.kh1.corona.model.vo;

public class HospitalDetail extends Hospital{

	private String detailDistrict;
	
	public HospitalDetail() {
		// TODO Auto-generated constructor stub
	}

	public HospitalDetail(String mainDistrict, String hName, String detailDistrict) {
		super(mainDistrict, hName);
		this.detailDistrict = detailDistrict;
	}

	public String getDetailDistrict() {
		return detailDistrict;
	}

	public void setDetailDistrict(String detailDistrict) {
		this.detailDistrict = detailDistrict;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "[ 추가 정보 -> 상세 주소 : " + detailDistrict + " ]";
	}

}
