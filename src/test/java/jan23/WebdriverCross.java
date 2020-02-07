package jan23;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebdriverCross {
	WebDriver driver;

	String url="http://orangehrm.qedgetech.com/";
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String brw)throws Throwable
	{
	if(brw.equalsIgnoreCase("chrome"))
	{
	System.out.println("Executing on Chrome browser");
	System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(url);
	driver.manage().window().maximize();
	}
	else if(brw.equalsIgnoreCase("firefox"))
	{
		System.out.println("Executing on firefox browser");
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
		driver=new FirefoxDriver();
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
