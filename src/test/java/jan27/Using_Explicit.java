package jan27;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Using_Explicit {
	WebDriver driver;
	@Test
	public void verifyLogin()
	{
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://gmail.com");
		driver.manage().window().maximize();
		//creating object for webdriver
		WebDriverWait wait=new WebDriverWait(driver, 400);
		//wait for username textbox
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
		driver.findElement(By.name("identifier")).sendKeys("jampula.swathi@gmail.com");
		//wait until element is clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='RveJvd snByac']")));
		driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
		//wait until password text box is available
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Swathi@543");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Next')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
	}

}
