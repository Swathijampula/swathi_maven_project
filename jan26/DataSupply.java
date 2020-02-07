package jan26;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class DataSupply {
	WebDriver driver;
 @BeforeTest
	  public void setUp() {
	 System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromrdriver.exe");
	 driver=new ChromeDriver();
	  }
	
  @Test(dataProvider = "orange")
  public void verifyLogin(String username , String password)throws throwable{
	  driver.get("http://orangehrm.qedgetech.com/");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
	  driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	  Thread.sleep(5000);
	  if(driver.getCurrentUrl().contains("dash"))
	  {
		  Reporter.log("login success"),true;
	  }
	  else
	  {
		  String message=driver.findElement(By.id("spanMessage")).getText();
		  Reporter.log(message,true);
	  }
	  
  
	  
  }
  

  @DataProvider
  public Object[][] orange() {
	  object login[][]=new object[5][2];
	  login [0][0]="Admin";
	  login [0][1]="Qedge123!@#";
	  login [1][0]="Admin";
	  login [1]1]="Qedge123!@#";
	  login [2][0]="test";
	  login [2][1]="Qedge123!@#";
	  login [3][0]="Admin";
	  login [3][1]="Qedge123!@#";
	  return login;
	  }
 

  @AfterTest
  public void afterTest()throws Throwable  {
	  Thread.sleep(5000);
	  driver.close();
  }

}
