package mini.kh1.corona.run;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public TestExcel(String excelPath, String sheetName) {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("C:\\Users\\hya20\\git\\MiniProject_Test\\data\\TestExcel.xlsx");
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
	}

	public void getCellData(int rowNum, int colNum) throws IOException {

		DataFormatter formatter = new DataFormatter();
		Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));// 이렇게 하면 밑에 자료형 안맞추고 객체 타입으로 다
																						// 가져올 수있음

		// double value = sheet.getRow(1).getCell(2).getNumericCellValue();
		System.out.println(value);

	}

	public void getRowCount() {

		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("No of Rows : " + rowCount);

	}

}
