package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// Khai báo 1 biến driver đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Set geckodriver: giao tiếp giữa browser và code
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//Bật trình duyệt Firefox lên
		driver = new FirefoxDriver();
		
		//Set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Bật browser to lên
		driver.manage().window().maximize();
		// Mở app ra
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		// Id: câu lệnh tìm Id là email và nhập giá trị chuỗi vào
		driver.findElement(By.id("email")).sendKeys("dam@gmail.com");
	 // Tagname: Tìm xem có bao nhiêu element cùng loại trên trang 
		 // Ví dụ tìm có bao nhiêu thẻ Img trong element
		driver.findElement(By.tagName("a"));
	   // Class: 
		driver.findElement(By.className("fb_logo"));
		// Name:
		driver.findElement(By.className("fb_logo"));
		//Link Text: Truyền cả text vào
		//Partial Link text: 1 phần text => độ chính xác không cao
		driver.findElement(By.linkText("Tiếng Việt"));
		driver.findElement(By.partialLinkText("Tiếng"));
		
		//CSS: ko làm việc vs text dùng thuộc tính khác của thẻ a để thao tác
		//Cách 1
		
		driver.findElement(By.cssSelector("a[title='Vietnamese']"));
		driver.findElement(By.cssSelector("a[onclick*='vi_VN']"));
		driver.findElement(By.cssSelector("a[title*='Vietnam']"));
		
		
		
		//Xpath: có thể cover hết toàn bộ 
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//img[@class='fb_logo_8ilh img']"));
		driver.findElement(By.xpath("//img[contains(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//img[starts-with@class,'fb_logo')]"));
        driver.findElement(By.xpath("//input[@name='email']"));
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
        driver.findElement(By.xpath("//a[contains(text(),'Tiếng Việt')]"));
        driver.findElement(By.xpath("//a[contains(text().'Việt')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Tiếng')]"));
        
	}

	@Test
	public void TC_02_() {
	
	}

	@Test
	public void TC_03_() {
	
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}