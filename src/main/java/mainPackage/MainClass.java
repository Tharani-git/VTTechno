package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class MainClass {
	public static WebDriver driver=null;
	public static ExtentTest logger;
	public static ExtentReports extent;
	@BeforeTest
	public void chromelaunch()
	{
		String path=System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"\\src\\main\\Driver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus() == ITestResult.FAILURE){
			
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = MainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report 
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
			//logger.log(LogStatus.PASS, "Test Case Passed is "+result.getThrowable());
			String screenshotPath = MainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report 
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}
	
	
	@AfterTest
	public void driverclose()
	{
	driver.close();
	 extent.flush();
     extent.close();
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = System.getProperty("user.dir")+"/src/main/resources/"+excelName;
		
		Object[][] data = getExcelDataW(excelLocation, sheetName);
		return data;
	}
	
	
public String[][] getExcelDataW(String fileName, String sheetName){
    	
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
		    	 //  data[i-1][j] = cell.getStringCellValue();
		    	   
		    	   if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		    		   data[i-1][j] = cell.getStringCellValue();
		    	   else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		    		   data[i-1][j] = " ";
		            
		    	   else if(cell.getCellType()== Cell.CELL_TYPE_NUMERIC || cell.getCellType()== Cell.CELL_TYPE_FORMULA)
		    	   {
		    	       String cellValue = String.valueOf(cell.getNumericCellValue());
		    	     /*  if(XSSF.isCellDateFormatted(cell))
		    	       {
		    	           DateFormat df = new SimpleDateFormat("dd/MM/yy");
		    	           Date date = cell.getDateCellValue();
		    	           cellValue = df.format(date);
		    	       }*/
		    	       
		    	       data[i-1][j] =  cellValue;
		    	   }
		    	  
	   	 	   }
	   	}
	  	}
	  	catch (Exception e) {
	     	   System.out.println("The exception is: " +e.getMessage());
       	}
    	return data;
	}


	
public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
            //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}
	
}
