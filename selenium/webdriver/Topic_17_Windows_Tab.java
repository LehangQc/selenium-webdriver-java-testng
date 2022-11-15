package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Windows_Tab {
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
	public void TC_01_Basic_Form() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		// Vừa mở ra chỉ có duy nhất 1 tab => dùng hàm get ID chỉ có 1 ID, trả về ID của tab/windown current
		
		String formTabID = driver.getWindowHandle();
		System.out.println("Form tab ID = "+ formTabID); // đây gọi là GUID
		
		//Click vào Google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
	
	    switchToWindowByID(formTabID); // Hàm switchToWindowByID được tạo ra bên dưới public void: sẽ switch qua window nào khác parent I
	    // Đang ở trang B
	    
		//Nhập text vào ô search của ô Google
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("automation");
		sleepInSecond(4);
		
		String googleTabID = driver.getWindowHandle();
		// Quay về trang A
		switchToWindowByID(googleTabID);
		// Đang ở trang A => click vào Facebook
		
	}
    // Viết gọn lại : chỉ dùng cho 2 tabs hoặc 2 window
	public void switchToWindowByID(String parentID)
	
	//ParentID là ID của tab trang A.
	// Lấy ra tất cả ID của tab/window hiện có
	{Set<String> allWindowIDs = driver.getWindowHandles();
	
	// DÙng vòng lặp để duyệt qua
	for (String id: allWindowIDs) {
		//nếu như có ID nào mà khác ParentID thì switch vào
		
		if(!id.equals(parentID)) {
			driver.switchTo().window(id);
		}
	}
	}
	
	//Dùng được cho 2 hoặc nhiều hơn 2 tab/ windows => switch By title vì title của các page luôn khác nhau
	// Logic của nó là cho vòng lặp switch vào page trước rồi mới kiếm tra title = title mong muốn
	public void switchToWindowByTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Dùng vòng lặp để duyệt qua từng ID
		
	    for (String id: allWindowIDs) {
		// Switch vào từng tab/window
		driver.switchTo().window(id);
		//Lấy ra Title của page đã switch 
		String currentPageTitle = driver.getTitle();
		
		if(currentPageTitle.equals(expectedPageTitle)) {
			
		// Thoát khỏi vòng lặp - không duyệt nữa
		break;
		}
	    }
	}
			
	
	@Test
	public void TC_02_SwitchtoWindowBy_Title_above_Example() {
		
		driver.get("https://automationfc.github.io/basic-form/");
		
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Google");
		sleepInSecond(2);
		
		// Đang ở trang B
	    
	    //Nhập text vào ô search của ô Google
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("automation");
		sleepInSecond(4);
		// Quay về trang A
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	    sleepInSecond(2);
	    
		//Click FACEBOOK
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);
		//Switch to Facebook: console: document.title
		switchToWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(4);
		System.out.println(driver.getTitle());
		
		driver.findElement(By.cssSelector("input#email")).sendKeys("Hang");
		sleepInSecond(2);
		
		//Close all tab ngoại trừ parentID
		closeAllWindowWithoutParent(parentID);
		sleepInSecond(3);
	}

	//@Test
	public void TC_03_Window_techpanda() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(3);
		
		//Switch qua Window
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(3);
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		//Close window
		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		//Driver vấn đang ở trang COMPARE, nên phải switch về MOBILE
		
		switchToWindowByTitle("Mobilee");
		sleepInSecond(3);
		
		}
	
	public void closeAllWindowWithoutParent(String parentID) {
		//Lấy tất cả các ID của các tab/window đang có
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//DÙng vòng lặp để kiểm tra duyệt qua từng ID
		for (String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close(); // Đóng tab đang active
			
			}
		}
		// Vẫn còn lại thằng Parent => switch vào parent
		// Vì hiện tại driver đang ở 1 trong những thằng id trên
		
		driver.switchTo().window(parentID);
		
		
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