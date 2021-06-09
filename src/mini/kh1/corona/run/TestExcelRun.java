package mini.kh1.corona.run;

import java.io.IOException;

public class TestExcelRun {
	public static void main(String[] args) throws IOException {

		String excelPath = "./data/TestExcel.xlsx";
		String sheetName = "Sheet1";

		TestExcel excel = new TestExcel(excelPath, sheetName);

		excel.getRowCount();
		excel.getCellData(1, 0);
		excel.getCellData(1, 1);
		excel.getCellData(1, 2);

	}

}
