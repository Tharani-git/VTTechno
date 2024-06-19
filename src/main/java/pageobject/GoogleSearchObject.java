package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleSearchObject{
	WebDriver driver;
	ExtentTest logger;
	@FindBy(xpath="//input[contains(@class,'gLFyf')]")
	WebElement inp_Search;
	public GoogleSearchObject(WebDriver driver,ExtentTest logger) {
	this.driver = driver;
	this.logger = logger;
	PageFactory.initElements(driver, this);
	}
	public void veryHeader(String Username) {
	inp_Search.click();
	inp_Search.sendKeys(Username);
	logger.log(LogStatus.INFO, "Sending Keys");
	}
	public void keysenter()
	{
	inp_Search.sendKeys(Keys.ENTER);
	}
	

}
