package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//Wait cho trạng thái, điều kiện cụ thể của element
	WebDriverWait explicitWait;
	
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
	    
		// Khởi tạo dữ liệu
	//	explicitWait = new WebDriverWait(driver, 15); // Apply 15s cho điều kiện/ trạng thái cụ thể
	
		
	//	driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().window().maximize();
		
	}
    @Test
	public void TC_01_Not_Enough_time() {
		
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
		 explicitWait = new WebDriverWait(driver, 3);
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //Thiếu time để cho element tiếp theo hoạt động được
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
		}

	@Test
	public void TC_02_Enough_Time() {
		
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
         explicitWait = new WebDriverWait(driver, 5);
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //đủ time để cho element tiếp theo hoạt động được
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
		 }

	@Test
	public void TC_03_More_Time() {
		
		 driver.get("https://automationfc.github.io/dynamic-loading/");
		 
          explicitWait = new WebDriverWait(driver, 50);
		 
		 // Click vào Start button 
		 driver.findElement(By.xpath("//div/button")).click();
		 
		 //Thừa time để cho element tiếp theo hoạt động được
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
		 }
	@Test
	public void TC_04_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		 
        explicitWait = new WebDriverWait(driver, 10);
		 
		 // Click vào Start button 
		driver.findElement(By.xpath("//div/button")).click();
		 
		 //Wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		 
		 //Get text và verify
		 Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
	}
	
	@Test
	public void TC_05_Ajax_loading_Invisible() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        		
		explicitWait = new WebDriverWait(driver, 15);
		
		// chờ cho date picker hiển thị 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		
		//Verify cho ngày selected date là ko có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
		
		//Chờ cho ngày 19 có thể click được
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		//Click vào 19
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		// Chờ cho Apax loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']")));
		
		//Chờ cho thằng 19 vừa được chọn có thể clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		
		//Verify copy text hiển thị 
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Saturday, November 19, 2022");
		}
	
	@Test
	public void TC_06_Upload_File() {
		
	driver.get("https://gofile.io/uploadFiles");
	
	// Wait cho ADD FILE button visible
	explicitWait = new WebDriverWait(driver,15);
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary btn-lg mb-1 uploadButton']")));
	
	//trong Dev tool gõ tìm input[type='file] rồi sendkey vào => cách upload file chứ ko dùng button 
    //Upload file
	driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnameFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);
	
	//Wait cho TẤT CẢ các loading icon biến mất
	
	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='progress position-relative mt-1']"))));
	
	// Wait cho message hiển thị 
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
	
	//Verify message hiển thị
    Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
    
    //Wait +Click cho SHow file button
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='rowUploadSuccess-showFiles']"))).click();
	
	
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
}