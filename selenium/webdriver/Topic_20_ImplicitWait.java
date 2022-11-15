package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_ImplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
		// Ảnh hưởng trực tiếp tới 2 hàm FindElement và FindElements
		// Ngoại lệ: ImplicitWait được apply tại đâu thì nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng giá trị mới - ko dùng giá trị cũ
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Version Selenium 4:  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Not_Enough_time() {
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
		}

	@Test
	public void TC_02_Enough_Time() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
		 }

	@Test
	public void TC_03_More_Time() {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
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