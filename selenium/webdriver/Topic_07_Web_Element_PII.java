package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test
	public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email ")).sendKeys("automation@gmail.com");
        
        
        driver.findElement(By.id("new_username")).sendKeys("automati");
        driver.findElement(By.id("new_username")).clear();
        driver.findElement(By.id("new_username")).sendKeys("automationfc");
        WebElement passwordTextbox = driver.findElement(By.id("new_password"));
        WebElement signUpbutton = driver.findElement(By.id("create-account"));
        //Lowercase
        passwordTextbox.sendKeys("auto");
        sleepInSecond(1);
       
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
       
    // Uppercase
        passwordTextbox.clear();
        passwordTextbox.sendKeys("AUTO");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
        Assert.assertFalse(signUpbutton.isEnabled());
	
    // Case number
	   passwordTextbox.clear();
       passwordTextbox.sendKeys("1234");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
	// Ky tu dac biet
       passwordTextbox.clear();
       passwordTextbox.sendKeys("!@#");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
    // 8 character minimum
       
       passwordTextbox.clear();
       passwordTextbox.sendKeys("12345678");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
    
       // Combine
       passwordTextbox.clear();
       passwordTextbox.sendKeys("Hanghunghang1994@");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
       Assert.assertTrue(signUpbutton.isEnabled());
       
       
       
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