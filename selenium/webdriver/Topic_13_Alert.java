package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String autoITFirefox = projectPath + "\\autoITAuthentication\\authen_firefox.exe";
    Alert alert;
   
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//// NOTE OVERALL
	    // Có 5 hàm dùng cho Alert: Accept - Dismiss - Cancel - getText - SendKeys
	    // Khi Alert xuất hiện => cần phải switch vào nó: dùng hàm alert =  driver.switchTo().alert();
	    // Authentication Alert: ko thể dùng thư viện Selenium được: Dùngg 2 cách: Trick và tool AutoIT (Window only)

	@Test
	public void TC_01_Accept_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(3);
        // nó chỉ nên khởi tạo khi element/ alert đó xuất hiện
		
		// Muốn thao tác được với Alert đang bật lên đó, cần phải switch vào nó, switch vào Alert sau lúc alert này bật lên:
        alert =  driver.switchTo().alert();
        
        // Verify title của alert
        
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        
        // Accept alert này
        alert.accept();
        
        // Verify sau khi accrpt alert
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
        
        
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(3);
        // nó chỉ nên khởi tạo khi element/ alert đó xuất hiện
		
		// Muốn thao tác được với Alert đang bật lên đó, cần phải switch vào nó, switch vào Alert sau lúc alert này bật lên:
        alert =  driver.switchTo().alert();
        
        // Verify title của alert
        
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        
        // Cancel alert này
        alert.dismiss();
        
        // Verify sau khi cancel alert
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
        
        
		
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(3);
        // nó chỉ nên khởi tạo khi element/ alert đó xuất hiện
		
		// Muốn thao tác được với Alert đang bật lên đó, cần phải switch vào nó, switch vào Alert sau lúc alert này bật lên:
        alert =  driver.switchTo().alert();
        
        // Verify title của alert
        
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        
        // nhập text alert này
        String text = "AutomationFC";
        alert.sendKeys(text);
        sleepInSecond(3);
        alert.accept();
        // Verify sau khi accrpt alert
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);
		
        
	}
	@Test
	public void TC_04_Authentication_Alert_Trick() {
		//Selenium nó cho mình cái pass cái User/Pass trực tiếp vào Url trước khi open nó ra
		//Format: http(s): //Username:Password@domain
		// Cách này ko bật Alert lên mà tự điền vào url luôn, sẽ hiển thị trang login thành công trang ngay
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password +"@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		
		// So sánh tuyệt đối: Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/div/p")).getText(), "Congratulations! You must have the proper credentials.");
		// Nếu text ko gọn thì dùng so sánh tương đối vơi Assert.assertTrue  - contains
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/div/p")).getText().contains("Congratulations! You must have the proper credentials."));
		
		}
	
	@Test
	public void TC_05_Authentication_Alert_Navigation_Page() {
		String username = "admin";
		String password = "admin";
		
		//Không nên Click vào link để cho nó show Dialog ra, mà nên get URL của link đó
		
		
		driver.get("http://the-internet.herokuapp.com/");
		sleepInSecond(5);
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text() = 'Basic Auth']")).getAttribute("href");
		
		// 1- Tách link (http://the-internet.herokuapp.com/basic_auth) ra nhiều chuỗi
		String[] authenLinkArray = basicAuthenLink.split("//");
		//authenLinkArray[0]=http:
		//authenLinkArray[1] = the-internet.herokuapp.com/basic_auth
		// Mảng và List là theo kiểu index
		// 2- Lấy chuỗi cộng vào
		String newAuthenLinkUrl = authenLinkArray[0] + "//" + username + ":" + password + "@" +authenLinkArray[1];
		
		driver.get(newAuthenLinkUrl);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/div/p")).getText().contains("Congratulations! You must have the proper credentials."));
	}
	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		
		//Trước khi bật url lên
		//Cần phải bật tool đê chờ cho Alert bật lên
		//Để trigger cho cái tooadminl thì cần 1 câu lệnh
		
		Runtime.getRuntime().exec(new String[] {autoITFirefox, username, password});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/div/p")).getText().contains("Congratulations! You must have the proper credentials."));
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
