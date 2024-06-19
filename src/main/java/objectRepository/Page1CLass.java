package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page1CLass {

	WebDriver driver;
	ExtentTest logger;
	@FindBy(xpath="//input[@title='Search']")
	WebElement inp_Search;
	
	public Page1CLass(WebDriver driver,ExtentTest logger) {
	this.driver = driver;
	this.logger = logger;
	PageFactory.initElements(driver, this);
	}

	public void veryHeader(String Username) {
		inp_Search.click();
		logger.log(LogStatus.INFO, "CLick");
		inp_Search.sendKeys(Username);
		logger.log(LogStatus.INFO, "Sending Keys");
	}
	
	
}
