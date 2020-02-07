package Qedge;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
	WebDriver driver;
	Workbook wb;
	Sheet ws;
	Row r;
	FileInputStream fi;
	FileOutputStream fo;
	ExtentReports report;
	ExtentTest test;
	String inputpath="E:\\Orangelogin.xlsx";
	String outputpath="E:\\orangehrmResults.xlsx";
	@BeforeTest
	public void SetUp()
	{
		report=new ExtentReports("./Reports/report.html");
		System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
		driver=new ChromeDriver();
		
	}
	@Test
	public void verifyLogin()throws Throwable
	{
		fi=new FileInputStream(inputpath);
	    wb=WorkbookFactory.create(fi);
		ws=wb.getSheetAt(0);
	    int rc=ws.getLastRowNum();
	    Reporter.log("no.of rows are:"+rc,true);
	    for(int i=1;i<=rc;i++)
	    {
	    	test=report.startTest("Verify Login");
	    	driver.get("http://orangehrm.qedgetech.com/");
	    	driver.manage().window().maximize();
	    	String username=ws.getRow(i).getCell(0).getStringCellValue();
	    	String password=ws.getRow(i).getCell(1).getStringCellValue();
	    	driver.findElement(By.name("txtUsername")).sendKeys(username);
	    	driver.findElement(By.name("txtPassword")).sendKeys(password);
	    	driver.findElement(By.name("Submit")).click();
	    	if(driver.getCurrentUrl().contains("dash"))
	    	{
	    		test.log(LogStatus.PASS, "Login Success");
	    		Reporter.log("Login success",true);
	    		ws.getRow(i).createCell(2).setCellValue("Login success");
	    		ws.getRow(i).createCell(3).setCellValue("Pass");
	    	}
	    	else
	    	{
	    		String message=driver.findElement(By.id("spanMessage")).getText();
	    		test.log(LogStatus.FAIL, message);
	    		Reporter.log(message,true);
	    		ws.getRow(i).createCell(2).setCellValue(message);
	    		ws.getRow(i).createCell(3).setCellValue("Fail");
	    	}
	    	report.endTest(test);
	    	report.flush();
	    	
	    	
	    }
	    fi.close();
	    fo=new FileOutputStream(outputpath);
	    wb.write(fo);
	    fo.close();
	    wb.close();
		
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}

}
