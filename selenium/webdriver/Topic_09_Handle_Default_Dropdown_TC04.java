package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Default_Dropdown_TC04 {
	WebDriver driver;
	Select select;
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName = "Barack";
		lastName = "Obama";
		day = "1";
		month = "May";
		year = "1980";
		emailAddress = "barackobama" + generateRandomNumber() + "@gmail.com";
		companyName = "White House";
		password = "123456"; 
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_NopCommerce(){
		
		driver.get("https://demo.nopcommerce.com/register");
		
// I- action data
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		// Dropdown: Khi nào dùng để thao tác với Element thì mới khởi tạo
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		//Thao tác với Dropdown
		
		// Kieu Index: Thứ tự của thẻ option
		   //select.selectByIndex(1);
		   //select.selectByIndex(5);
		
	   // Kieu Value: 
	 	   //select.selectByValue("5");
	   
       //Kieu  Text: item text
	// Chọn item Date
		 select.selectByVisibleText(day);
		    //select.selectByVisibleText("5");
		
		// Nên dùng cách nào trong các kiểu trên?: => Nên dùng Text
   //Verify item đã được select hay chưa
		 Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
		 
		 // Kiểm tra dropdown có phải là multiple options ko 
		 Assert.assertFalse(select.isMultiple());
		 
		
	// Chọn item Month	

		select= new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
    // Chọn item Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
   // input Email
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		
    // input Company
		driver.findElement(By.cssSelector("#Company")).sendKeys(companyName);
   //input Password
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);
		
   // input Confirm Password
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
   //Click button register
		driver.findElement(By.cssSelector("#register-button")).click();
		
//II- verify output
		  
	Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed");
	
	driver.findElement(By.className("ico-account")).click();
	
	Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
	Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
	Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
	Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
	select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
	select= new Select(driver.findElement(By.name("DateOfBirthMonth")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		
		
		
		
		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {
		
		driver.get("https://demo.nopcommerce.com");
	// Click Register link tren header
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
	// Chon day = 1
		
	}
	

	@AfterClass
	public void afterClass() {
	//	driver.quit();
	}

    public void sleepInSecond(long timeInSecond) {
    	try {
    		Thread.sleep(timeInSecond*1000);
    	}catch (InterruptedException e) {
    		e.printStackTrace();
 
    	}
    }
    
    public int generateRandomNumber() {
    	Random rand = new Random();
    	return rand.nextInt(99999);
    }
}