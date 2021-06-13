package mini.kh1.corona.controller.hospital;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mini.kh1.corona.model.vo.HospitalDetail;

public class HospitalAddress {

	private Vector<HospitalDetail> list = new Vector<HospitalDetail>(10);

	public Object[][] getAddress(Vector<HospitalDetail> hdVector) {

		// 여기서는 메인주소, 상세주소, 병원명을 가져옴
		// 얻으려는 데이터가 3개 이므로 열을 3으로 작성
		Object[][] address = new Object[hdVector.size()][3];
		//System.out.println("vector 사이즈 : " + hdVector.size());

		for (int i = 0; i < hdVector.size(); i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					address[i][j] = hdVector.get(i).getMainDistrict();
				}
				if (j == 1) {
					address[i][j] = hdVector.get(i).getDetailDistrict();
				}
				if (j == 2) {
					address[i][j] = hdVector.get(i).gethName();
				}
			}
			// 확인
			//System.out.println("시/구 : " + hdVector.get(i).getMainDistrict());
			//System.out.println("상세주소 : " + hdVector.get(i).getDetailDistrict());
			//System.out.println("병원명 : " + hdVector.get(i).gethName());
		}

		return address;
	}

	// 병원 관리 들어가면 출력되게 할 테이블
	public Vector<HospitalDetail> callAddress() throws Exception {

		FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		HospitalDetail hd = new HospitalDetail();

		int rowIndex = 0;
		int columnIndex = 0;

		XSSFSheet sheet = workbook.getSheet("병원정보");

		String mainDistrict = "";
		String detailDistrict = "";
		String hName = "";

		int maxRowNum = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < maxRowNum; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);// 행 인덱스를 얻어옴

			if (row != null) {

				int maxCellNum = row.getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < maxCellNum; columnIndex++) {
					XSSFCell cell = row.getCell(columnIndex);// 열(셀) 인덱스를 얻어옴

					if (cell.getCellType() == CellType.STRING) {

						switch (columnIndex) {
						case 0:
							mainDistrict = "" + cell.getStringCellValue();
							break;
						case 1:
							detailDistrict = "" + cell.getStringCellValue();
							break;
						case 2:
							hName = "" + cell.getStringCellValue();
							break;
						default:
							break;
						}

					}
				}
			}
			// HospitalDetail 클래스에 객체로 생성
			hd.setMainDistrict(mainDistrict);
			hd.setDetailDistrict(detailDistrict);
			hd.sethName(hName);
			// 인덱스 0부터 채우기 위해 rowIndex에서 1을 빼줌
			list.add(rowIndex - 1, new HospitalDetail(mainDistrict, hName, detailDistrict));
		}

		return list;
	}

	public boolean addAddress(String mainDistrict, String detailDistrict, String hName, int vaccine) throws Exception {

		FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("병원정보");
		HospitalDetail hd = new HospitalDetail();

		// 엑셀의 행렬 변수값 초기화
		int rowIndex = 0;
		int columnIndex = 0;

		String addMD = "";
		String addDD = "";
		String addHN = "";

		boolean addPossible = true;

		int maxRowNum = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < maxRowNum; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int maxCellNum = row.getPhysicalNumberOfCells();
				for (columnIndex = 0; columnIndex < maxCellNum; columnIndex++) {

					XSSFCell cell = row.getCell(columnIndex);

					// 입력 시 값이 일치하는 경우와 텍스트필드가 빈 칸인 경우만 고려
					switch (columnIndex) {
					case 0:
						if (mainDistrict.equals(cell.getStringCellValue())) {
							//System.out.println("이미 메인주소 있음");
							addPossible = false;
							break;
						} else {
							addMD = mainDistrict;

						}
						break;
					case 1:
						if (detailDistrict.equals(cell.getStringCellValue())) {
							//System.out.println("이미 상세주소 있음");
							addPossible = false;
							break;
						} else {
							addDD = detailDistrict;

						}
						break;
					case 2:
						if (hName.equals(cell.getStringCellValue())) {
							//System.out.println("이미 병원명 있음");
							addPossible = false;
							break;
						} else {
							addHN = hName;

						}
						break;
					default:
						break;
					}
				}
			}
		}

		XSSFRow newRow = sheet.createRow(maxRowNum);
		// maxRowNum은 행 번호를 의미하고 기존 파일에서 읽어오는 인덱스는 maxRowNum 이전 번호까지를 의미하므로 maxRowNum을
		// 그대로 넣음
		newRow.createCell(0).setCellValue(addMD);
		newRow.createCell(1).setCellValue(addDD);
		newRow.createCell(2).setCellValue(addHN);
		newRow.createCell(3).setCellValue(vaccine);// 백신을 공급받을 병원을 먼저 선정 후 백신 공급 절차가 이뤄지기 때문에 일단 0으로 해놓음

		if (addPossible == true) {// 값을 넣을 수 있을 때만 엑셀로 데이터 내보내기

			FileOutputStream fos = new FileOutputStream("./data//HospitalInfo.xlsx");
			workbook.write(fos);
			fos.close();

		}

		return addPossible;
	}

	public Vector<HospitalDetail> modifiedAddress() throws Exception {

		FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
		HospitalDetail hd = new HospitalDetail();
		Vector<HospitalDetail> modifiedList = new Vector<HospitalDetail>();
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("병원정보");

		int rowIndex = 0;
		int columnIndex = 0;

		String mainDistrict = "";
		String detailDistrict = "";
		String hName = "";

		int maxRowNum = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < maxRowNum; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);// 행 인덱스를 얻어옴

			if (row != null) {

				int maxCellNum = row.getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < maxCellNum; columnIndex++) {
					XSSFCell cell = row.getCell(columnIndex);// 열(셀) 인덱스를 얻어옴

					if (cell.getCellType() == CellType.STRING) {

						switch (columnIndex) {
						case 0:
							mainDistrict = "" + cell.getStringCellValue();
							break;
						case 1:
							detailDistrict = "" + cell.getStringCellValue();
							break;
						case 2:
							hName = "" + cell.getStringCellValue();
							break;
						default:
							break;
						}

					}
				}
			}
			hd.setMainDistrict(mainDistrict);
			hd.setDetailDistrict(detailDistrict);
			hd.sethName(hName);
			modifiedList.add(rowIndex - 1, new HospitalDetail(mainDistrict, hName, detailDistrict));
		}

		return modifiedList;
	}

}
