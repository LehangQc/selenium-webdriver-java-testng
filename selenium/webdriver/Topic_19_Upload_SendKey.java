package webdriver;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_SendKey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	//Khai báo Image name
	
	String vietnam = "Giang tí tởn.PNG";
	String thailan = "Hà dồ dại.PNG";
	String indonesia = "Hang Vu.PNG";
	
	//Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "upload Files" + File.separator;
	
	
	//Khai báo Image path
	String vietnameFilePath = uploadFileFolderPath + vietnam;
	String thailanFilePath = uploadFileFolderPath + thailan;
	String indonesiaFilePath = uploadFileFolderPath + indonesia;
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//Load file len
		 //driver.findElement(By.xpath("//input[@type='file']")).sendKeys(vietnameFilePath);
		 //driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thailanFilePath);
		 //driver.findElement(By.xpath("//input[@type='file']")).sendKeys(indonesiaFilePath);
		
		// Load 1 lúc nhiều file: +\n giữa
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(vietnameFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);
		
		
		//Verify ten hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Giang tí tởn.PNG']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hà dồ dại.PNG']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hang Vu.PNG']")).isDisplayed());

		// Thực hiện Upload (Click vào từng file mỗi file cách nhau 5s)
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
	    for (WebElement startButton : startButtons) {
	    	startButton.click();
	    	sleepInSecond(2);
	    	
	    }
	   // Verify ten hinh dc upload len
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='Giang tí tởn.PNG']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='Hà dồ dại.PNG']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='Hang Vu.PNG']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_One_File_Per_Time_Java_Robot() {
		// Gỉa lập lại hành động copy paste vào Open File DiaLog -> Java support
		// Giả lập hành vi paste và ENTER vào Open FIle dialog
		
		// Copy file path
		StringSelection select = new StringSelection(vietnameFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Giang tí tởn.PNG']")).isDisplayed());
		
		
	}

	
		
	@Test
	public void TC_04_() {

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