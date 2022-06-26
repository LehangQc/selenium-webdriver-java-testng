package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_Textarea {
	//Khai bao
	WebDriver driver;
	
	JavascriptExecutor jsExecutor;
	String emailAddress, loginUrl, userID, passWord, customerName, genderValue;
	String dateOfBirthInput, dateOfBirthOutput, addressInput, addressOutput, city, state, pinNumber, phoneNumber;
	
	
	//Khai bao + Khoi tao
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver; 
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4");
		loginUrl = driver.getCurrentUrl();
	// khoi tao du lieu
		emailAddress= "Hannah" + generateRandomNumber() + "@gmail.com";
		customerName = "Hannah";
		genderValue = "male";
		dateOfBirthInput = "08/09/1990";
		dateOfBirthOutput = "1990-08-09";
		addressInput = "123 PO Box\nLos Angeles\nUnited State";
		addressOutput = "123 PO Box Los Angeles United State";
		city = "New York";
		state = "Los Angeles";
		pinNumber = "123456";
		phoneNumber = "05284874774";
	

	
	}

	@Test
	public void TC_01_Register() {
  // Step1: Click vao visit here link
		driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/']")).click();
		  //Cach khac lay locator: //a[text()='here']
  //Step 2: input EmailID
		//driver.findElement(By.xpath("//input[@name='emailid']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
		
	}

	@Test
	public void TC_02_Login() {
		
//  Back lai trang login: 
	driver.get(loginUrl);
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).sendKeys(passWord);
	
	driver.findElement(By.name("btnLogin")).click();
	driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
	
	Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : " + userID);
	
	}

	@Test
	public void TC_03_Create_New_Customer() {
    driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).click();
    
    driver.findElement(By.name("name")).sendKeys(customerName);
    driver.findElement(By.xpath("//input[@value='m']")).click();
    
    
    WebElement dateOfBirthTextbox = driver.findElement(By.name("dob"));
    jsExecutor.executeScript("arguments[0].removeAttribute('type')",dateOfBirthTextbox);
    
    dateOfBirthTextbox.sendKeys(dateOfBirthInput);
    
    driver.findElement(By.name("addr")).sendKeys(addressInput);
    driver.findElement(By.name("city")).sendKeys(city);
    driver.findElement(By.name("state")).sendKeys(state);
    driver.findElement(By.name("pinno")).sendKeys(pinNumber);
    driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
    driver.findElement(By.name("emailid")).sendKeys(emailAddress);
    driver.findElement(By.name("password")).sendKeys(passWord);
    driver.findElement(By.name("sub")).click();
   // Verify output
   // driver.findElement(By.xpath("//p[@class='heading3']")).getText();
    Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
    
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),genderValue);	
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressOutput);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pinNumber);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phoneNumber);
    Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress);
  
    
	
	
	
	}

	
	
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
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