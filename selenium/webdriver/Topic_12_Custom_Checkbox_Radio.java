package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Checkbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;	
	 
 	}

	@Test
	public void TC_01_Custom_Checkbox() {
		
		driver.get("https://material.angular.io/components/radio/examples");
        
	// Case 1: Thẻ input không click đc -> Fail do thẻ input bị che ko click đc
	// Thẻ input dùng verify được -> pass
		
		
	// Case 2: Không dùng thẻ input để click,-> Thẻ span chứa text -> pass
	// không dùng thẻ input để verify -> Fail vì thẻ span ko dùng cho isSelected được chỉ có h iển thị hay không hiển thị thôi 
		
		//By checkedCheckbox = By.xpath("//span[text()='Checked']");
		//driver.findElement(checkedCheckbox).click();
		//Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
	// Case 3: - DÙng thẻ span để click
	//     - Dùng input để verify
		//By checkedCheckboxText = By.xpath("//span[text()='Checked']");
		//driver.findElement(checkedCheckboxText).click();
		
		
		//By checkedCheckboxbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		//driver.findElement(checkedCheckboxbox).click();
		// Kết luận: Chỉ case 3 pass: nhưng vấn đề là phải dùng 2 find elements => nên sinh ra nhiều phụ thuộc khi maintain code
		
	//case 4: DÙng thẻ input để click (dùng Javascript Executor: không quan tâm element bị che hay không vẫn click được)
		
		        By checkedCheckboxbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
				driver.findElement(checkedCheckboxbox).click();
	     	    
				jsExecutor.executeScript("argument[0].click();", driver.findElement(checkedCheckboxbox));
				
	
	
	
	}

	@Test
	public void TC_02_Google_docs() {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		driver.findElement(By.xpath("//div[@aria-label='Hà Nội']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Hà Nội']")).getAttribute("aria-checked"), "true");
	}

	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {

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
}