package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_PII {
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
	public void TC_01_Page_Url() {
	//Step 1: Mở 1 url
		driver.get("http://live.techpanda.org/index.php/");
	//Step 2: Click vào My Account: Trước hết, sẽ inspect để bắt Element
    // Dùng cha-con: Nếu dùng Css thì lấy div.footer a[title='My Account']
	// Sau đó dùng hàm Click để thao tác click vào button
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
	//Step 2.5: Get ra url sau khi click vào button My account
		driver.getCurrentUrl();
	
	// Step 2.6 Gán biến (dùng khi dùng biến 2 lần, trong TH này thì ko nen dùng vì tốn bộ nhớ)
		//String logiPageUrl = driver.getCurrentUrl();
	//Step 3: Verify url
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	//Step 4: Click Create an account button
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	//Step 5: Verify Url trang Create an Account
	    driver.getCurrentUrl();
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	    
	}

	@Test
	public void TC_02_Page_Title() {
	//Step 1: Mở Url
		driver.get("http://live.techpanda.org/index.php/");
	//Step 2: Click MY ACCOUNT link tại footer
	    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//Step 3: Verify Title của Login Page = Customer Login
	    driver.getTitle();
            //Verify Title
	        // Tip lấy Title: cách 1, ở Element tab, gõ //title hoặc cách 2 ở Console tab gõ document.title
	    Assert.assertEquals(driver.getTitle(),"Customer Login");
    //Step 4: Click CREATE AN ACCOUNT button
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	//Step 5: Verify title của page Create an Account
	    Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}

	@Test
	public void TC_03_Navigation() {
	//Step 1: Mở Url
		driver.get("http://live.techpanda.org/index.php/");
	//Step 2: Click MY ACCOUNT link tại footer
	    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//Step 3: Click CREATE AN ACCOUNT button
	    driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	//Step 4: Verify Url cuar Register Page http://live.techpanda.org/customer/account/create/
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	//Step 5: Back lai trang Login Page
	    driver.navigate().back();
	//Step 6: Verify Url cua Login Page http://live.techpanda.org/index.php/customer/account/login/
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	//Step 7: Forward toi trang Register Page
	    driver.navigate().forward();
	//Step 8: Verify Title cua Register Page = 
	    Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	
	}
	
   @Test
   public void TC_04_Page_Source() {
	   
	 //Step 1: Truy cap vao trang http://live.techpanda.org/
	   driver.get("http://live.techpanda.org/");
	 //Step 2: Click MY ACCOUNT link tai footer
	   driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	 //Step 3: Verify Login Page chua text: Login or Create an Account
	   Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	 //Step 4: Click Create an account button:
	   driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
     //Step 5: Verify Register Page chua text: Create an Account
	   Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

   
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