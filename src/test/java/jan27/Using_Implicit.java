package jan27;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Using_Implicit {
WebDriver driver;
@Test
public void verifyLogin()
{
	System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://gmail.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.name("identifier")).sendKeys("jampula.swathi@gmail.com");
	driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Swathi@543");
	driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
}

}
