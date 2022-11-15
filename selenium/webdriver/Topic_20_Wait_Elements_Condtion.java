package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Elements_Condtion {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	    explicitWait = new WebDriverWait(driver, 10);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_visible_displayed_visibility() {
		driver.get("https://www.facebook.com/");
		
		 // 1. Có trên UI (bắt buộc) + Có trong HTML (bắt buộc)
	    // Wait cho email address textbox hiển thị trong vong 10 giay
	    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		 // 1. KO Có trên UI (bắt buộc) + Có trong HTML
		  driver.get("https://www.facebook.com/");
		  sleepInSecond(3);
		  driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		  sleepInSecond(2);
		
		// Wait cho confirm email address textbox KHONG hiển thị trong vong 10 giay
		  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		  
		
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		
		 // 1. KO Có trên UI (bắt buộc) + KO Có trong HTML
		  driver.get("https://www.facebook.com/");
		  sleepInSecond(3);
		
		// Wait cho confirm email address textbox KHONG hiển thị trong vong 10 giay
		  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		  
			}
	//@Test
	public void TC_03_Presence_I() {
         // Có trên UI + Có trong HTML (bắt buộc)
		
		 driver.get("https://www.facebook.com/");
		// Wait cho email address textbox presence (ko quan tâm hiển thị hay không) trong HTML trong vong 10 giay
		 explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	@Test
	public void TC_03_Presence_II() {
		 // 1. KO Có trên UI  + Có trong HTML(bắt buộc)
		  driver.get("https://www.facebook.com/");
		  sleepInSecond(3);
		  driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		  sleepInSecond(2);
		
		// Wait cho confirm email address textbox KHONG hiển thị trong vong 10 giay
		  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_04_Staleness() {
		 
		// 1. KO Có trên UI (bắt buộc) + KO Có trong HTML
		  driver.get("https://www.facebook.com/");
		  sleepInSecond(3);
		  driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		 // Phase 1: Element có trong cây HTML
		 WebElement reEnterEmailAddressTextbox= driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		 
		 // Thao tác vs element khác làm cho element re-enter email ko còn trong DOM nữa 
		 
		 
		  //Close Pop-up
		 driver.findElement(By.cssSelector("img._8idr")).click();
		 // Chờ cho Re-enter email textbox  không còn trong DOM trong vòng 10s
		 explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		 
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

    public void sleepInSecond(long timeInSecond) {
    	try {
    		Thread.sleep(timeInSecond*1000);
    	}catch (InterruptedException e) {
    		e.printStackTrace();
 
    	}
    }
}