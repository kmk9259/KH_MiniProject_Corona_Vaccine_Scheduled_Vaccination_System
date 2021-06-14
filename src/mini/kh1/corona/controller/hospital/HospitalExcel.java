package mini.kh1.corona.controller.hospital;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mini.kh1.corona.model.vo.HospitalDetail;
import mini.kh1.corona.model.vo.HospitalVaccine;
import mini.kh1.corona.view.VaccineModifyFrame;

public class HospitalExcel {

	private Vector<HospitalVaccine> list = new Vector<HospitalVaccine>(10);

	// JTable 있는 클래스에서 callTable을 소환해 vector 구한 후 이 메소드를 소환해서 JTable에 넣을 수 있게 2차원배열로
	// 만듬
	public Object[][] getData(Vector<HospitalVaccine> vector) {

		// 여기서는 메인주소, 병원명, 백신 수량을 가져옴
		// 얻으려는 데이터가 3개 이므로 열을 3으로 작성
		Object[][] data = new Object[vector.size()][3];
		//System.out.println("vector 사이즈 : " + vector.size());

		for (int i = 0; i < vector.size(); i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {// 메인주소가 있는 열
					data[i][j] = vector.get(i).getMainDistrict();
				}
				if (j == 1) {
					data[i][j] = vector.get(i).gethName();
				}
				if (j == 2) {
					data[i][j] = vector.get(i).getVaccine();
				}
			}
			// 확인용
			//System.out.println("시/구 : " + vector.get(i).getMainDistrict());
			//System.out.println("병원명 : " + vector.get(i).gethName());
			//System.out.println("재고량 : " + vector.get(i).getVaccine());
		}

		return data;

	}

	// 이 메소드를 Vector 타입으로 리턴해서 JTable에서 호출해서 사용
	public Vector<HospitalVaccine> callTable() throws Exception {

		// 엑셀 파일을 불러오는 과정

    FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
		
    // 해당 엑셀파일의 워크북을 불러옴
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		HospitalVaccine hv = new HospitalVaccine();

		// 액셀의 행렬에 대한 인덱스
		int rowIndex = 0;
		int columnIndex = 0;

		// 해당 엑셀파일의 워크북의 시트(제목이 병원정보인)를 불러옴
		XSSFSheet sheet = workbook.getSheet("병원정보");
		// XSSFSheet sheet = workbook.getSheetAt(0); sheet 인덱스로 사용해도 가능

		// 백신 재고 관리에서 병원정보 추출할 변수 생성
		String mainDistrict = "";
		String hName = "";
		int vaccine = 0;

		int maxRowNum = sheet.getPhysicalNumberOfRows();// 해당 시트의 최대 row 번호를 얻어옴

		System.out.println(maxRowNum);

		for (rowIndex = 1; rowIndex < maxRowNum; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);// 시트 해당 인덱스의 row(행)을 받아옴

			if (row != null) {// row(행)이 비어있지 않으면

				int maxCellNum = row.getPhysicalNumberOfCells();// 시트 해당 인덱스의 row의 cell의 최대 번호를 받아옴

				for (columnIndex = 0; columnIndex < maxCellNum; columnIndex++) {

					XSSFCell cell = row.getCell(columnIndex);// 시트 해당 인덱스의 row의 cell을 얻어옴(cell값이 아닌 cell자체)

					if (cell.getCellType() == CellType.NUMERIC) { // 해당 cell 타입이 Numeric인 경우
						vaccine = (int) cell.getNumericCellValue(); // double형이라서 int형으로 강제 형변환 실시
					}

					if (cell.getCellType() == CellType.STRING) {

						if (columnIndex == 0) { // 엑셀에 메인주소가 있는 열의 인덱스가 0이므로
							mainDistrict = "" + cell.getStringCellValue(); // Cell에 있는 String타입의 값을 얻어옴
						} else if (columnIndex == 2) {
							hName = "" + cell.getStringCellValue();
						}
					}

				}

			}
			// HospitalVaccine 클래스에 객체로 생성하는 과정
			hv.setMainDistrict(mainDistrict);
			hv.sethName(hName);
			hv.setVaccine(vaccine);
			list.add(rowIndex - 1, new HospitalVaccine(mainDistrict, hName, vaccine));
			// 위에 엑셀에서는 rowIndex가 1일 때부터 읽어야하지만(rowIndex = 0 은 헤더 내용)
			// Vector list에 추가할 때는 인덱스 0부터 채워야하기 때문에 -1을 해줌(여기는 for문 안임)

		}

		System.out.println(list);// 확인용

		return list;
	}

	// 백신 재고 수량을 수정하는 메소드
	public void modifyVaccine(String hName, int vNum) throws Exception {

    FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
    
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int rowIndex = 0;
		int columnIndex = 0;

		XSSFSheet sheet = workbook.getSheet("병원정보");
		int rowNum = 0;

		String mainDistrict = "";
		String detailDistrict = "";

		String info = "";

		int rows = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < rows; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < cells - 1; columnIndex++) {

					XSSFCell cell = row.getCell(2);
					XSSFCell cell2 = row.getCell(columnIndex);

					if (hName.equals(cell.getStringCellValue())) {
						rowNum = rowIndex;

						switch (columnIndex) {
						case 0:
							mainDistrict = cell2.getStringCellValue();
							break;
						case 1:
							detailDistrict = cell2.getStringCellValue();
							break;
						default:
							
							break;
						}
						info = mainDistrict + " " + detailDistrict + " " + hName;

					}
				}
			}
		}

		sheet.getRow(rowNum).getCell(3).setCellValue(vNum);// 재고 수량 수정

		// 수정한 내용을 다시 엑셀 파일에 입력

    FileOutputStream fos = new FileOutputStream("./data//HospitalInfo.xlsx");
    
		workbook.write(fos);
		fos.close();

	}

	public Vector<HospitalVaccine> modifiedTable() throws Exception {

		// 엑셀 파일을 불러오는 과정
		FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
		// 해당 엑셀파일의 워크북을 불러옴
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		HospitalVaccine hv = new HospitalVaccine();
		Vector<HospitalVaccine> modifiedList = new Vector<HospitalVaccine>();

		// 액셀의 행렬에 대한 인덱스
		int rowIndex = 0;
		int columnIndex = 0;

		// 해당 엑셀파일의 워크북의 시트(제목이 병원정보인)를 불러옴
		XSSFSheet sheet = workbook.getSheet("병원정보");
		// XSSFSheet sheet = workbook.getSheetAt(0); sheet 인덱스로 사용해도 가능

		// 백신 재고 관리에서 병원정보 추출할 변수 생성
		String mainDistrict = "";
		String hName = "";
		int vaccine = 0;

		int maxRowNum = sheet.getPhysicalNumberOfRows();// 해당 시트의 최대 row 번호를 얻어옴

		System.out.println(maxRowNum);

		for (rowIndex = 1; rowIndex < maxRowNum; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);// 시트 해당 인덱스의 row(행)을 받아옴

			if (row != null) {// row(행)이 비어있지 않으면

				int maxCellNum = row.getPhysicalNumberOfCells();// 시트 해당 인덱스의 row의 cell의 최대 번호를 받아옴

				for (columnIndex = 0; columnIndex < maxCellNum; columnIndex++) {

					XSSFCell cell = row.getCell(columnIndex);// 시트 해당 인덱스의 row의 cell을 얻어옴(cell값이 아닌 cell자체)

					if (cell.getCellType() == CellType.NUMERIC) { // 해당 cell 타입이 Numeric인 경우
						vaccine = (int) cell.getNumericCellValue(); // double형이라서 int형으로 강제 형변환 실시
					}

					if (cell.getCellType() == CellType.STRING) {

						if (columnIndex == 0) { // 엑셀에 메인주소가 있는 열의 인덱스가 0이므로
							mainDistrict = "" + cell.getStringCellValue(); // Cell에 있는 String타입의 값을 얻어옴
						} else if (columnIndex == 2) {
							hName = "" + cell.getStringCellValue();
						}
					}

				}

			}
			// HospitalVaccine 클래스에 객체로 생성하는 과정
			hv.setMainDistrict(mainDistrict);
			hv.sethName(hName);
			hv.setVaccine(vaccine);
			modifiedList.add(rowIndex - 1, new HospitalVaccine(mainDistrict, hName, vaccine));

		}
		System.out.println(modifiedList);// 확인용

		return modifiedList;
	}

	// 병원 정보(메인주소 + 상세주소 + 병원명) 출력 시 가져올 메소드
	public String findHospitalInfo(String hName) throws Exception {

		FileInputStream fis = new FileInputStream("./data//HospitalInfo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int rowIndex = 0;
		int columnIndex = 0;

		XSSFSheet sheet = workbook.getSheet("병원정보");
		int rowNum = 0;

		String mainDistrict = "";
		String detailDistrict = "";

		//System.out.println("hName 1 : " + hName);

		String info = null;

		int rows = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < rows; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < cells - 1; columnIndex++) {

					XSSFCell cell = row.getCell(2);
					XSSFCell cell2 = row.getCell(columnIndex);

					if (hName.equals(cell.getStringCellValue())) {
						// System.out.println(rowIndex);
						rowNum = rowIndex;

						switch (columnIndex) {
						case 0:
							mainDistrict = cell2.getStringCellValue();
							break;
						case 1:
							detailDistrict = cell2.getStringCellValue();
							break;
						default:
							break;
						}
						info = mainDistrict + " " + detailDistrict + " " + hName;
					}
				}
			}
		}
		//System.out.println(mainDistrict);
		//System.out.println(detailDistrict);
		//System.out.println("hName 2 : " + hName);
		//System.out.println("메소드 결과 : " + info);

		return info;
	}

}