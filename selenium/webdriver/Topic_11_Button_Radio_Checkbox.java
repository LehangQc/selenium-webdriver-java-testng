package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("0936615511");
		
		driver.findElement(By.cssSelector("#login_password")).sendKeys("hannah");
		
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//Verify background cua button Dang nhap
		// Đặt biến cho màu của button - in ra là Rgba
		String logginButtonBackgroundRgba = driver.findElement(loginButtonBy).getCssValue("background-color");
		//đổi mã color Rgba sang Hexa
		String logginButtonBackgroundHexa = Color.fromString(logginButtonBackgroundRgba).asHex().toUpperCase();
		//Verify 
		Assert.assertEquals(logginButtonBackgroundHexa,"#C92127");
	    
	}

	@Test
	public void TC_02_Default_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
	// 1 chọn option checkbox
		
		driver.findElement(By.xpath("//input[@value='Emotional Disorder']")).click();
		
		
		driver.findElement(By.xpath("//input[@value='Gout']")).click();
		
		sleepInSecond(3);
    // Chọn radio button: 5+days/ I do have a diet plan/3-4 glasses/day
		
		
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		
		
   // 2- verify-chọn rồi hay chưa chọn thành công (isSelected)
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Emotional Disorder']")).isSelected());
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		
 		
      // 3- Bo chon
		
         driver.findElement(By.xpath("//input[@value='Emotional Disorder']")).click();
		
		
		driver.findElement(By.xpath("//input[@value='Gout']")).click();
		
		
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		
	
	    // 4- verify sau khi bo chon
		
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Emotional Disorder']")).isSelected());
		
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
	}

	@Test
	public void TC_03_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				 checkbox.click();
				 sleepInSecond(1);
			}
		}
		// Verify all checkbox
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		
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