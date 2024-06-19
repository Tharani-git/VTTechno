package loginPackage;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainPackage.MainClass;
import objectRepository.Page1CLass;

public class SampleClass extends MainClass{

	@Test(dataProvider = "TotalData")
	void login(String User,String pass,String Run)
	{
		logger = extent.startTest("login");
		if(Run.equals("No"))
		{
			System.out.println("Skipped one step");
//			throw new SkipException("Skipped");
			
		}
		else {
		System.out.println("not Skipped");
//		Page1CLass obj=new Page1CLass(driver,logger);
//		obj.veryHeader(User);
		}
	}
	
//	@Test
//	void cust(String User,String pass,String Run)
//	{
//		logger = extent.startTest("cust");
//		if(Run.equals("No"))
//		{
//			throw new SkipException("Skipped");
//		}
//		Page1CLass obj=new Page1CLass(driver,logger);
//	obj.veryHeader(User);
//	}
	@DataProvider(name="TotalData")
	public Object[][] TotalData(){Object[][] data = getExcelData("TestData.xlsx", "Cred");
		return data;
	}

}
