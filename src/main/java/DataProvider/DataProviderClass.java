package DataProvider;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProviderClass {
	private Logger log = Logger.getLogger(DataProviderClass.class);
	public String[][] getExcelData(String fileName, String sheetName){
	String[][] data = null;
	try
	{
	FileInputStream fis = new FileInputStream(fileName);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheet(sheetName);
	XSSFRow row = sh.getRow(0);
	int noOfRows = sh.getPhysicalNumberOfRows();
	int noOfCols = row.getLastCellNum();
	Cell cell;
	data = new String[noOfRows-1][noOfCols];
	for(int i =1; i<noOfRows;i++){
	for(int j=0;j<noOfCols;j++){
	row = sh.getRow(i);
	cell= row.getCell(j);
	// data[i-1][j] = cell.getStringCellValue();
	if(cell.getCellType()==Cell.CELL_TYPE_STRING)
	data[i-1][j] = cell.getStringCellValue();
	else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
	data[i-1][j] = " ";
	else if(cell.getCellType()== Cell.CELL_TYPE_NUMERIC || cell.getCellType()== Cell.CELL_TYPE_FORMULA)
	{
	String cellValue = String.valueOf(cell.getNumericCellValue());
	/* if(XSSF.isCellDateFormatted(cell))
	{
	DateFormat df = new SimpleDateFormat("dd/MM/yy");
	Date date = cell.getDateCellValue();
	cellValue = df.format(date);
	}*/
	data[i-1][j] = cellValue;
	}
	}
	}
	}
	catch (Exception e) {
	System.out.println("The exception is: " +e.getMessage());
	}
	return data;
	}

}
