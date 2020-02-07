package jan23;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HRMOrange {
	WebDriver driver;
	DesiredCapabilities cap;
	String url="http://orangehrm.qedgetech.com/";
	String node="http://localhost:5550/wd/hub";
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String brw)throws Throwable
	{
	if(brw.equalsIgnoreCase("chrome"))
	{
	System.out.println("Executing on Chrome browser");
	cap=new DesiredCapabilities();
	cap.setBrowserName("chrome");
	driver=new RemoteWebDriver(new URL(node),cap);
	driver.get(url);
	driver.manage().window().maximize();
	}
	else if(brw.equalsIgnoreCase("firefox"))
	{
		System.out.println("Executing on firefox browser");
		cap=new DesiredCapabilities();
		cap.setBrowserName("firefox");
		driver=new RemoteWebDriver(new URL(node),cap);
		driver.get(url);
		driver.manage().window().maximize();
	}
	else
	{
	System.out.println("Browse is not matching");
	}
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
