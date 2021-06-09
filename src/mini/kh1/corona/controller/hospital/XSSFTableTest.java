package mini.kh1.corona.controller.hospital;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.Vector;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mini.kh1.corona.model.vo.HospitalVaccine;

public class XSSFTableTest {

	
	
	private Vector<HospitalVaccine> list = new Vector<HospitalVaccine>(10);



	public Vector<HospitalVaccine> callTable() throws Exception {

		FileInputStream fis = new FileInputStream("C:\\Workspace\\JAVA\\MiniProject-Test2\\data\\HospitalData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		HospitalVaccine h = new HospitalVaccine();

		int rowIndex = 0;
		int columnIndex = 0;// 엑셀 시트 행렬 인덱스 초기화

		// 시트 여러 개면 for문 사용
		XSSFSheet sheet = workbook.getSheetAt(0);
		// XSSFSheet sheet = workbook.getSheet("Sheet1");으로 써도됨

		// 백신 재고 관리 시 필요한 변수 생성
		String value1 = ""; // 메인 주소
		String value2 = ""; // 병원명
		int vNum = 0; // 재고 수량

		int rows = sheet.getPhysicalNumberOfRows();// 엑셀의 행 개수를 받아옴

		// 행,렬 두 가지가 존재하기 때문에 중첩for문 사용
		for (rowIndex = 1; rowIndex < rows; rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);

			if (row != null) {
				int cells = row.getPhysicalNumberOfCells(); //각 로우마다 몇 개의 행이존재하는지
				for (columnIndex = 0; columnIndex < cells; columnIndex++) {
					XSSFCell cell = row.getCell(columnIndex);

					if (cell.getCellType() == CellType.NUMERIC) {
						vNum = (int) cell.getNumericCellValue();
					}

					if (cell.getCellType() == CellType.STRING) {
						if (columnIndex == 0) {
							value1 = "" + cell.getStringCellValue();

						} else if (columnIndex == 2) {
							value2 = "" + cell.getStringCellValue();
						}

					}

				}
			}
			h.setMainDistrict(value1);
			h.sethName(value2);
			h.setVaccine(vNum);
			list.add(rowIndex - 1, new HospitalVaccine(value1, value2, vNum));
		}

		return list;

	}

	public void deleteTable() throws Exception {//병원명 검색해서 삭제

		FileInputStream fis = new FileInputStream("C:\\Workspace\\JAVA\\Test2\\data\\TestData4.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Scanner sc = new Scanner(System.in);

		int rowIndex = 0;
		int columnIndex = 0;

		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum = 0;
		
		
		System.out.println("병원명을 입력하세요 : ");
		String name = sc.nextLine();

		String value1 = "";
		String value2 = "";
		String value3 = "";
		
		String info = "";
		
		//병원명 입력해서 삭제하기
		int rows = sheet.getPhysicalNumberOfRows();
		System.out.println("rows :"+ rows);

		for (rowIndex = 1; rowIndex < rows; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();
				
				for (columnIndex = 0; columnIndex < cells-1; columnIndex++) {

					XSSFCell cell = row.getCell(2);
					XSSFCell cell2 = row.getCell(columnIndex);
					
						if (name.equals(cell.getStringCellValue())) {//우선 병원명 입력해서 정보 출력 후 확인
							System.out.println(rowIndex);
							rowNum = rowIndex;
							
							switch(columnIndex) {
							case 0 :
								value1 = cell2.getStringCellValue();
								break;
							case 1 :
								value2 = cell2.getStringCellValue();
								break;
							case 2 :
								value3 = cell2.getStringCellValue();
								break;
							default :
								System.out.println("잘못 입력");
								break;
							}
							
							info = value1 + " " + value2 + " " + value3;
						}
						
						
				}
			}
		
		}
		
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(info);
		
		 // 입력한 name이 포함되어있는 행 삭제
		XSSFRow delRow = sheet.getRow(rowNum);
		sheet.removeRow(delRow);
		
		
		//삭제하고 빈 셀로 한 칸씩 땡겨오기
		int count = 0;
		XSSFRow row2 = null;
		XSSFRow row3 = null;
		
		System.out.println("rows :"+ rows);
		for(rowIndex = 1; rowIndex < rows; rowIndex++) {
			
			XSSFRow row0 = sheet.getRow(0);
			row2 = sheet.getRow(rowIndex);
			row3 = sheet.getRow(rowIndex + 1);
			
			if(row2 == null) {
				
				row2 = sheet.createRow(rowIndex);
				int cells2 = row0.getPhysicalNumberOfCells();
				System.out.println("cells2 : " + cells2);
				
				for(columnIndex = 0; columnIndex<cells2; columnIndex++) {
					
					XSSFCell cell2 = row3.getCell(columnIndex);
					
					switch(cell2.getCellType()) {
					case STRING :
						row2.createCell(columnIndex).setCellValue(cell2.getStringCellValue());
						break;
					case NUMERIC :
						row2.createCell(columnIndex).setCellValue(cell2.getNumericCellValue());
						break;
					default : 
						System.out.println("에러 발생");
						break;
					}
					
					
				}
				sheet.removeRow(row3);
			}
			
		}
		
		//sheet.removeRow(sheet.getRow(rows-1));//마지막 비어있는거 삭제
		//sheet.removeRowBreak(rows-1);
		

		FileOutputStream fos = new FileOutputStream("C:\\Workspace\\JAVA\\Test2\\data\\TestData3.xlsx");
		workbook.write(fos);
		fos.close();

	}
	
	public int findHospital() throws Exception {//병원명 검색해서 병원 정보 출력
		
		FileInputStream fis = new FileInputStream("C:\\Workspace\\JAVA\\Test2\\data\\TestData4.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Scanner sc = new Scanner(System.in);
		
		int rowIndex = 0;
		int columnIndex = 0;

		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum = 0;
		
		System.out.println("병원명을 입력하세요 : ");
		String name = sc.nextLine();

		String value1 = "";
		String value2 = "";
		String value3 = "";
		
		String info = "";
		
		int rows = sheet.getPhysicalNumberOfRows();

		for (rowIndex = 1; rowIndex < rows; rowIndex++) {

			XSSFRow row = sheet.getRow(rowIndex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < cells-1; columnIndex++) {

					XSSFCell cell = row.getCell(2);
					XSSFCell cell2 = row.getCell(columnIndex);
					
						if (name.equals(cell.getStringCellValue())) {
							System.out.println(rowIndex);
							rowNum = rowIndex;
							
							switch(columnIndex) {
							case 0 :
								value1 = cell2.getStringCellValue();
								break;
							case 1 :
								value2 = cell2.getStringCellValue();
								break;
							case 2 :
								value3 = cell2.getStringCellValue();
								break;
							default :
								System.out.println("잘못 입력");
								break;
							}
							
							info = value1 + " " + value2 + " " + value3;
						}
				}
			}
		}
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
		System.out.println(info);
		
		return rowNum;	
	}
	
	public void modifyVaccine() throws Exception {//백신 수량 입력
		
		FileInputStream fis = new FileInputStream("C:\\Workspace\\JAVA\\Test2\\data\\TestData4.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Scanner sc = new Scanner(System.in);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rowNum = findHospital();
		
		System.out.println("변경할 수량 입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		sheet.getRow(rowNum).getCell(3).setCellValue(num);

		FileOutputStream fos = new FileOutputStream("C:\\Workspace\\JAVA\\Test2\\data\\TestData4.xlsx");
		workbook.write(fos);
		fos.close();
	}

}
