package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		// "Switch to"
		// Switch Alert -> hoc roi
		
		// Switch vao Frame/Iframe - cách 1: dùng index, ko nên dùng vì sau này sẽ cập nhật thêm ifram thì sẽ thay đổi
		//driver.switchTo().frame(0);
		
		//Cách 2: dùng ID/ Name( phải có name hoặc id mới chạy đc)
		//driver.switchTo().frame("cs_chat_iframe");
		
		//Cách 3: WebElement: handle được tất cả các cases (khuyến khích nên dùng)
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		
		String facebookLikeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		sleepInSecond(3);
		Assert.assertEquals(facebookLikeNumber, "166K likes");
		
		// Nhảy về lại trang Kyna để click vào Chatbox (iframe B): từ B-> dùng defaultContent, nếu có C nhúng B, thì nhảy từ C ->B dùng parentIframe
		
		driver.switchTo().defaultContent();
		
		//Switch to Chatbox
		driver.switchTo().frame("cs_chat_iframe");
		
		//Thao tác với element thuộc C
		
        // Click vào element chat 
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Auto");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987635552");
		
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("I nover");
		sleepInSecond(3);
		
		//Nhảy lại về trang A: Kyna => switch về default content
		
		driver.switchTo().defaultContent();
		
		//Thao tác tiếp trên A
		
		// Search Element thuộc A, search "Excel"
		
		
		String keyword = "Excel";
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button[class='search-button']")).click();
		
		//Verify ket qua search
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		//Number
		Assert.assertEquals(courseName.size(), 9);
		for (WebElement course : courseName ) {
			//Course name contains keyword
		Assert.assertTrue(course.getText().contains(keyword));
		}
	
	}

	@Test
	public void TC_02_frame() {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");  //Phai lấy name hoặc id trong thẻ frame, k phải trong thẻ element
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("hangle");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@name='fldPassword']")).sendKeys("automation");
		
		
		
		
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