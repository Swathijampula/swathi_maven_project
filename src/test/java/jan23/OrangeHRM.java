package jan23;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrangeHRM {
	WebDriver driver;
	DesiredCapabilities cap;
	@BeforeTest
	public void setup()throws Throwable
	{
	cap=new DesiredCapabilities();
	cap.setBrowserName("chrome");
	cap.setPlatform(Platform.WINDOWS);
	//connect test to hub
	driver=new RemoteWebDriver(new URL("http://localhost:5550/wd/hub"),cap);
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	}
	@Test
	public void verifyLogin()throws Throwable
	{
	driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	driver.findElement(By.name("txtPassword")).sendKeys("Admin");
	driver.findElement(By.name("Submit")).click();
	Thread.sleep(5000);
	if(driver.getCurrentUrl().contains("dash"))
	{
	Reporter.log("Login success",true);
	}
	else
	{
	Reporter.log("Login Fail",true);
	}
	}
	@AfterTest
	public void logout()
	{
		driver.close();
	}
}
