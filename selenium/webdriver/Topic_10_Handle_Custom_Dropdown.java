package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	    explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery(){
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	
    // Đặt biến để dùng hàm lặp
	
	//* Chọn ITEM 10
	 selectItemInCustomDropdown("number-button", "//ul[@id='number-menu']/li/div", "10");
	// VERIFY DROPDOWN DC CHON VS GIA TRI = 10
	 Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"10");
	//* Chọn ITEM 5
	 selectItemInCustomDropdown("number-button", "//ul[@id='number-menu']/li/div", "5");
	// VERIFY DROPDOWN DC CHON VS GIA TRI = 5
	 Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"5");
 
	}

	// Viết thành hàm => không truyền gì cố định mà truyển qua "Tham số", tạo 3 biến trong hàm lặp như sau:
	public void selectItemInCustomDropdown(String parentLocator, String chilLocator, String expectedTextItem) {
		
	// Click vào dropdown để xổ hết các option
		driver.findElement(By.id(parentLocator)).click();
    // Chờ các item bên trong load ra => WebDriverWait:
    // By Locator phải đại diện cho tất cả các item element con bên trong
    // Lấy locator đến thẻ chứa text item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(chilLocator)));
	// Tìm item mong muốn, nêú không thấy thì phải scroll xuống để tìm => xử dụng vòng lặp, rồi getText từng cái
	// Trước hết phải lấy ra hết list item rồi lưu ra vào 1 List WebElement
		List<WebElement> allDropdownItems = driver.findElements(By.xpath(chilLocator));
	// Duyệt qua từng item trong list 19 items trên: sử dụng vòng lăp (index của item trong list tính từ 0)
		
		for (WebElement item : allDropdownItems) {   
		//Gán item là quả táo (biến tạm thời)
		// Dùng biến tạm thời để getText, rồi lưu vào 1 biến
		String actualTextItem = item.getText();
		System.out.println("Item text =" + actualTextItem);
		
		// Thấy item cần chọn (so sánh đk của biến tạm thời với item cần chọn) thì click vào
		
		if (actualTextItem.equals(expectedTextItem)) {
			item.click();
			sleepInSecond(1);
		
		
      // Chưa thoát ra khỏi vòng lặp, sẽ tiêps tục verify từ 6-> 19, cần 1 break để thoát
		break;
		
		}
		
	/// Chọn Item khác => phải viết lại nhiều lần đoạn code phía trên => Viết hàm để giải quyết vấn đề thu gọn code
		// => đặt đoạn code public void selectItemInCustomDropdown(){đoạn code chọn 1 item bất kỳ}
	}
	}

	@Test
	public void TC_02_() {
		
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
    
    public int generateRandomNumber() {
    	Random rand = new Random();
    	return rand.nextInt(99999);
    }
}