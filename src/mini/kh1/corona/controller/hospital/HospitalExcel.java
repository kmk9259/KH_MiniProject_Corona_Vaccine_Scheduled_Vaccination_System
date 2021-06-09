package mini.kh1.corona.controller.hospital;

import java.io.FileInputStream;
import java.util.Vector;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mini.kh1.corona.model.vo.HospitalVaccine;

public class HospitalExcel {

	private Vector<HospitalVaccine> list = new Vector<HospitalVaccine>(10);

	// 이 메소드를 Vector 타입으로 리턴해서 JTable에서 호출해서 사용
	public Vector<HospitalVaccine> callTable() throws Exception {

		// 엑셀 파일을 불러오는 과정
		FileInputStream fis = new FileInputStream("C:\\Users\\hya20\\git\\MiniProject_Test\\data\\HospitalData.xlsx");
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

		}

		System.out.println(list);// 확인용

		return list;
	}
}