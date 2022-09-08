package com.flipkart.stepdefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

public class MobilePurchasing{
	public static WebDriver driver;
	@BeforeClass
	public static void beforeclass() throws Throwable {
		System.out.println("1");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\prem kumar\\Desktop\\Automation class\\JSel\\Driver\\chromedriver.exe" );
		 driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	}
	
	@Before
	public void beforemethod() {
		System.out.println("2");
		System.out.println(java.time.LocalTime.now());
		
	}
	
	@Test
	public void method1() {
		System.out.println("3");
		try {
		WebElement Login = driver.findElement(By.xpath("//button[text()='✕']"));
		Login.click();
		}
		catch (Exception e) {
			System.out.println("Login page is not present");
		}
		
	}
	
	@Test
	public void method2() throws Throwable {
		System.out.println("4");
		Thread.sleep(3000);
	    driver.findElement(By.name("q")).sendKeys("Nokia Mobiles",Keys.ENTER);
	    Thread.sleep(8000);
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		
		File f = new File("C:\\Users\\prem kumar\\Desktop\\Automation class\\JunitSep\\src\\test\\resources\\Mobiles.xlsx"); 
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet("Mobiles"); 
		for(int i =0;i<products.size(); i++)
		   {
		    Row r = s.createRow(i);
			Cell c = r.createCell(0);
			WebElement product = products.get(i);
			String text = product.getText();
			c.setCellValue(text);
		   }
			FileOutputStream f1 = new FileOutputStream(f);
			w.write(f1);
			f1.close();
		 }
	
	@Test
	public void method3() throws Throwable {
		System.out.println("5");
		 Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='_4rR01T'])[2]")).click();
		String par = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		for (String x : child) {
			if (!par.equals(x)) {
				driver.switchTo().window(x);
			}
		}
		
	}
	
	@Test
	public void method4() throws Throwable {
		System.out.println("6");
		Thread.sleep(7000);
		WebElement second = driver.findElement(By.xpath("//span[@class='B_NuCI']"));
		String actualText = second.getText();
		File f = new File("C:\\Users\\prem kumar\\Desktop\\Automation class\\JunitSep\\src\\test\\resources\\Mobiles.xlsx"); 
		FileInputStream f3 = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(f3);
		Sheet s = w.getSheet("Mobiles"); 
		Row row = s.getRow(1);
		Cell cell = row.getCell(0);
        String expectedText = cell.getStringCellValue();	
        Assert.assertEquals(actualText, expectedText);	
	}
	
	@Test
	public void method5() throws Throwable {
		System.out.println("7");
		WebElement down = driver.findElement(By.xpath("//div[text()='Description']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
		
		TakesScreenshot tk =(TakesScreenshot)driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File desc = new File("C:\\Users\\prem kumar\\Desktop\\Automation class\\JunitSep\\src\\test\\resources\\Specification");
		FileUtils.copyFile(src, desc);
	}
	
	@After
	public void aftermethod() {
		System.out.println("8");
		System.out.println(java.time.LocalTime.now());
	}
	
	@AfterClass
	public static void afterclass() {
		System.out.println("9");
		driver.quit();
	}
	
}
