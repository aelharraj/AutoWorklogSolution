package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

public class ReadExcelData_JiraIncurridos {

	public static void main(String[] args) {

		ReadExcelData_JiraIncurridos ex = new ReadExcelData_JiraIncurridos();
		// int sheetNumber=1;
		// ex.showExelData(ex.listData());
		// ex.ReadData(ex.listData());
		// ex.extractAllSteps(ex.listData());
		ex.ReadData2(ex.listData());
		// ex.readTestSetExcel(ex.testPrecondition());
	}

	public static ArrayList<ArrayList<String>> listData() {
		int compt = 0;
		// C:\\Tareas_Incurridos\\
		String fileName = "Tareas_worklog.xlsx";
		// String fileName =
		// "C:\\Users\\aelharra\\Documents\\Automated_solutions\\autoJiraWorklog\\Tareas_worklog.xlsx";

		ArrayList<ArrayList<String>> sheetData = new ArrayList<ArrayList<String>>();
		FileInputStream fis = null;
		try {

			fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<?> rows = sheet.rowIterator();
			ArrayList<String> data = new ArrayList<String>();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				if (!isRowEmpty(row)) {
					if (row.getRowNum() == 0 || row.getRowNum() == 1) {
						// System.out.println("Row "+compt+" has been skipped");
						compt++;
					} else {
						Iterator<?> cells = row.cellIterator();
						// while (cells.hasNext())
						for (int n = 0; n < 5; n++) {
							XSSFCell cell = (XSSFCell) cells.next();
							cell.setCellType(Cell.CELL_TYPE_STRING);
							data.add(cell.getStringCellValue());
						}
						sheetData.add(data);
						data = new ArrayList<String>();
						// k++;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return sheetData;
	}

	@SuppressWarnings("deprecation")
	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public static void ReadData(ArrayList<ArrayList<String>> sheetData) {
		System.out.println("SIZE: " + sheetData.size());

		for (int indexList = 2; indexList < sheetData.get(0).size(); indexList = indexList + 4) {
			// int indexList=0;
			System.out.println(
					"---------------------------------------- READ DATA METHOD ---------------------------------");

			/*
			 * String ppTest = sheetData.get(indexList).get(0); String
			 * description = sheetData.get(indexList).get(1);
			 */
			String Step = sheetData.get(0).get(indexList);
			// String Data = sheetData.get(indexList).get(3);
			// String ExpectedRslt = sheetData.get(0).get(5);

			// System.out.println(ppTest);
			// System.out.println(description);
			System.out.println(Step);
			// System.out.println(Data);
			// System.out.println(ExpectedRslt);

			// indexList+=4;

			System.out.println(
					"---------------------------------------- END READ DATA METHOD ---------------------------------");
		}
	}

	public void ReadData2(ArrayList<ArrayList<String>> arrayList) {
		for (int indexList = 0; indexList < arrayList.size(); indexList++) {
			ArrayList<String> contenu = arrayList.get(indexList);
			System.out.println(contenu);

		}
	}

	public static void extractAllSteps(ArrayList<ArrayList<String>> sheetData) {
		System.out.println(
				"----------------------------------------  READ DATA METHOD ---------------------------------");

		String ppTest = "";
		String description = "";
		String Environment = "";
		String hasTestSet = "";
		String testSetNumber = "";

		for (int indexGlobalList = 0; indexGlobalList < sheetData.size(); indexGlobalList++) {
			ppTest = sheetData.get(indexGlobalList).get(0);
			description = sheetData.get(indexGlobalList).get(1);
			Environment = sheetData.get(indexGlobalList).get(2);
			hasTestSet = sheetData.get(indexGlobalList).get(3);
			testSetNumber = sheetData.get(indexGlobalList).get(4);

			// IndexChildList is binded dynamicly in relation of Column numbers
			// of the first global sheet.
			for (int indexChildList = 5; indexChildList < sheetData.get(indexGlobalList).size(); indexChildList += 3) {
				sheetData.get(indexGlobalList).get(indexChildList);
				sheetData.get(indexGlobalList).get(indexChildList + 1);
				sheetData.get(indexGlobalList).get(indexChildList + 2);
			}
			System.out.println(ppTest);
			System.out.println(description);
			System.out.println(Environment);
			System.out.println(hasTestSet);
			System.out.println(testSetNumber);

		}
		System.out.println(
				"---------------------------------------- END READ DATA METHOD ---------------------------------");

	}

	public void showExelData(ArrayList<String> sheetData) {

		for (int i = 3; i < sheetData.size(); i++) {

			if (sheetData.get(i) != null && sheetData.size() > 0 && !sheetData.isEmpty()) {
				String Step = sheetData.get(i);
				String Data = sheetData.get(i + 1);
				String ExpectedRslt = sheetData.get(i + 2);

				System.out.print(Step + " ");
				System.out.println(Data);
				System.out.println(ExpectedRslt);
				i += 2;

			}
		}

		sheetData.clear();
	}

	// Read Test Set Excel
	public void readTestSetExcel(ArrayList<String> sheetData) {
		System.out.println("SIZE: " + sheetData.size());
		for (int i = 0; i < sheetData.size(); i += 4) {

			if (sheetData.get(i) != null && sheetData.size() > 0 && !sheetData.isEmpty()) {
				String testSetName = sheetData.get(i);
				String description = sheetData.get(i + 1);
				String environment = sheetData.get(i + 2);
				String testNumber = sheetData.get(i + 3);

				System.out.println(testSetName + " ");
				System.out.println(description);
				System.out.println(environment);
				System.out.println(testNumber);

			}
		}

		sheetData.clear();
	}

	// Test Set Data
	public static ArrayList<String> testSetData() {
		String fileName = "C:\\Users\\aelharra\\Desktop\\ReadExcelTest\\testSet.xls";
		ArrayList<String> sheetData = new ArrayList<String>();

		FileInputStream fis = null;

		try {

			fis = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<?> rows = sheet.rowIterator();
			ArrayList<String> data = new ArrayList<String>();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				if (!isRowEmpty(row)) {

					if (row.getRowNum() == 0) {
						System.out.println("First row has been skipped/sheet 0");

					} else {
						Iterator<?> cells = row.cellIterator();

						while (cells.hasNext()) {
							HSSFCell cell = (HSSFCell) cells.next();
							data.add(cell.getStringCellValue());

						}
						sheetData.addAll(data);
						data = new ArrayList<String>();
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return sheetData;
	}

	// Read test execution data
	public static ArrayList<String> testPrecondition() {
		// This could be works for execution as well
		String fileName = "C:\\Users\\aelharra\\Desktop\\ReadExcelTest\\testSet.xls";
		ArrayList<String> sheetData = new ArrayList<String>();

		FileInputStream fis = null;

		try {

			fis = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(1);
			Iterator<?> rows = sheet.rowIterator();
			ArrayList<String> data = new ArrayList<String>();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				if (!isRowEmpty(row)) {

					if (row.getRowNum() == 0) {
						System.out.println("First row has been skipped/sheet 0");

					} else {
						Iterator<?> cells = row.cellIterator();
						for (int i = 0; i < 4; i++) {
							HSSFCell cell = (HSSFCell) cells.next();
							data.add(cell.getStringCellValue());

						}
						sheetData.addAll(data);
						data = new ArrayList<String>();
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return sheetData;
	}

	public static ArrayList<String> StepsDataList() {
		// This could be works for execution as well
		String fileName = "C:\\Users\\aelharra\\Desktop\\ReadExcelTest\\testSet.xls";
		ArrayList<String> sheetData = new ArrayList<String>();

		FileInputStream fis = null;

		try {

			fis = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(1);
			Iterator<?> rows = sheet.rowIterator();
			ArrayList<String> data = new ArrayList<String>();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				if (!isRowEmpty(row)) {

					if (row.getRowNum() == 0) {
						System.out.println("First row has been skipped/sheet 0");

					} else {
						Iterator<?> cells = row.cellIterator();
						for (int i = 0; i < 3; i++) {
							HSSFCell cell = (HSSFCell) cells.next();
							data.add(cell.getStringCellValue());

						}
						sheetData.addAll(data);
						data = new ArrayList<String>();
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return sheetData;
	}
}

// else
// data.add(cellStep.getStringCellValue());

/*
 * cellStep = row.getCell(r, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); switch
 * (cellStep.getCellType()) { case Cell.CELL_TYPE_STRING:
 * data.add(cellStep.getStringCellValue()); break; case Cell.CELL_TYPE_NUMERIC:
 * data.add(Integer.toString((int) cellStep.getNumericCellValue())); break; case
 * Cell.CELL_TYPE_BOOLEAN: data.add(Boolean.toString((boolean)
 * cellStep.getBooleanCellValue())); break;
 * 
 * // When it's a blank cell, fill it with empty string
 * 
 * case Cell.CELL_TYPE_BLANK:
 * System.out.println("****The CELL****: "+cellStep+" **** IS EMPTY  ****");
 * data.add(""); break;
 * 
 * default: data.add(cellStep.getStringCellValue()); }
 */
