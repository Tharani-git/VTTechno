package testpackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainpackage.Mainclass;
import pageobject.GoogleSearchObject;

public class Testclass extends Mainclass{
	//private Logger log = Logger.getLogger(GoogleSearchClass.class);
	@Test(dataProvider="sampledata", description = "TestCheck", priority = 1, enabled = true)
	public void homepage_keysenter(String UserName,String Password) {
	logger = extent.startTest("homepage_keysenter");
	GoogleSearchObject obj=new GoogleSearchObject(driver,logger);
	obj.veryHeader(UserName);
	}
	@DataProvider(name="sampledata")
	public Object[][] sampledata(){Object[][] data = getExcelData("WorkBook.xlsx", "Moorthy");
	return data;
	}
	}
	


