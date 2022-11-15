package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Fixed_popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Fixed_In_DOM() {
	driver.get("https://ngoaingu24h.vn/");
	// Kiểm tra pop up đăng nhập không hiển thị = cách verfiy header hoặc button đại diện không hiển thị 
	WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
	
	// Verify login popup is undisplayed
	
	Assert.assertFalse(loginPopup.isDisplayed());
	
	driver.findElement(By.xpath("//button[@class ='login_ icon-before']")).click();
	
	Assert.assertTrue(loginPopup.isDisplayed());
	
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationfc");
	
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("automationfc");
    
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='row error-login-panel' and text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
	
	//Click x button to dissmiss the pop-up
	
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
	
	Assert.assertFalse(loginPopup.isDisplayed());
	
	}

	//@Test
	public void TC_02_Fixed_in_DOM() {
		driver.get("https://kyna.vn/");
	    
				WebElement loginPopup2 = driver.findElement(By.xpath("//div[@id='k-popup-account-login']"));
				
				// Verify login popup is undisplayed
				
				Assert.assertFalse(loginPopup2.isDisplayed());
				
				driver.findElement(By.xpath("//a[@class='login-btn']")).click();
				
				Assert.assertTrue(loginPopup2.isDisplayed());
				
				driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc");
				
				driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc");
			    
				driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
				sleepInSecond(3);
				
				Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
				sleepInSecond(3);
				//Click x button to dissmiss the pop-up
				
				driver.findElement(By.xpath("//button[@class='k-popup-account-close close' and @aria-label='Close']")).click();
				
				Assert.assertFalse(loginPopup2.isDisplayed());	
	}

	//@Test
	public void TC_03_Fixed_Pop_Up_Not_In_DOM() {
		driver.get("https://tiki.vn/");
	    
		//HIển thị thì còn trong DOM, mất đi thì ko còn trong DOM nữa
		//Khi mới mở trang ra thì popup ko hề có trong DOM nên ko findElement được
		//Trong trường hợp pop-up ko có trong DOM thì findElements này sẽ tìm thấy 0 Element và cũng chờ hết timeout của implicitWait nhưng ko đánh failed và ko show exception 
		// Empty list = 0 element
		List<WebElement> loginPopup = driver.findElements(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']"));
		
		//(0 trả items hoặc nhiều hơn items trong khoảng thời gian ImplicilyWait time)
		
		// Verify login popup is undisplayed
		
		Assert.assertEquals(loginPopup.size(),0);
		//Click button đăng nhập đăng ký
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
		sleepInSecond(3);
		
		//Displayed (Single element: findElement)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']")).isDisplayed());
		sleepInSecond(3);
		
		//Displayed (Multiple element: findElements)
		loginPopup = driver.findElements(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']"));
		Assert.assertEquals(loginPopup.size(),1);
		Assert.assertTrue(loginPopup.get(0).isDisplayed());
		
		// Close popup
		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		sleepInSecond(3);
	    //Verify lại pop-up khi close X: lại findelement lại, ko find lại sẽ bị lỗi để cập nhật giá trị cho login pop-up, bên trên là giá trị 1
		
		loginPopup = driver.findElements(By.xpath("//div[@class='ReactModal__Overlay ReactModal__Overlay--after-open']"));
		Assert.assertEquals(loginPopup.size(), 0);
		}
	
	
	//@Test
	public void TC_04_Random_Popup_in_DOM(){
        // Random Popup: Nó có thể hiển thị theo đợt/ngày/tháng/ chương trình quảng cáo sale
		// Phải luôn chạy được testcase dù pop up có đang hiển thị hay không
		
		driver.get("https://www.kmplayer.com/home");
		
		WebElement popupLayer = driver.findElement(By.xpath("//img[@id='support-home']"));
		
		// Hàm thỏa mãn Đk nếu đang trong đợt sale nó hiên thị thì Script phải đóng nó đi để chạy tiếp, còn hết đợt sale ko hiển thị thì chạy luôn 
		// Giả lập case ko hiên thị = cách tắt pop up đi trước khi gọi tới hàm check displayed
		
		if (popupLayer.isDisplayed()) {
				// Close nó đi để chạy step tiếp theo
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//area[@id='btn-r']")));
		
        sleepInSecond(3);       		
		} 
		
	   // Step tiếp theo 
		
		driver.findElement(By.xpath("//p[@class='donate-coffee']")).click();
	    sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
		

	}
	
	
	@Test
	public void TC_05_Random_Popup_NOT_in_DOM(){
		   // Random Popup: Nó có thể hiển thị theo đợt/ngày/tháng/ chương trình quảng cáo sale
		   // Phải luôn chạy được testcase dù pop up có đang hiển thị hay không
		driver.get("https://dehieu.vn/");
		List<WebElement> contentPopup = driver.findElements(By.xpath("//div[@class='popup-content']"));
		
		//Nếu Element size > 0 (từ 1 trở lên => Pop up xuất hiện) -. thì close it
		if(contentPopup.size() >0 && contentPopup.get(0).isDisplayed()) {
		// Sendkey Popup
			driver.findElement(By.xpath("//input[@placeholder='Họ tên*']")).sendKeys("Nguyen Thi Le hang");
			driver.findElement(By.xpath("//input[@placeholder='Email*']")).sendKeys("lehang@gmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Điện thoại*']")).sendKeys("0984763663");
	   //Close popup
		driver.findElement(By.xpath("//button[@class='close']")).click();
		sleepInSecond(3);
		
		}
	   // Step tiếp theo
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
		
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