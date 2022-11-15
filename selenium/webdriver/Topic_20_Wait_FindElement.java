package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // đang apply 15s cho việc tìm element
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
		//1- Tìm thấy 1 element/node
		   // => thao tác trực tiếp lên node đó. vÌ NÓ tìm thấy nên ko cần chờ hết 15s
		
		//2- Tìm thấy nhiều element/node
		   // => Chỉ thao tác với element đầu tiên, không quan tâm tới node khác
		   // Trong TH bắt locator sao thì nó tìm sai => failed
		
		
		//3- Ko tìm thấy element/node nào
		   // cơ chế tìm lại cứ 0.5 giaay sẽ tìm lại  1 lần
		   // Nếu trong thời gian tìm 15s mà tìm thấy thì pas còn trong 15s mà ko thấy thì HOĂC Failed tại step này + ko chạy step tiếp hoặc Throw ra 1 ngoại lệ là NoSuchElementException
		
		
	}

	@Test
	public void TC_02_FindElements() {
		
		//1-  Tìm thấy 1 element/node
		    // => tìm thấy và lưu vào 1 list = 1 element
		
		//2- Tìm thấy nhiều element/node
		    // => Lưu vào list các element tương ứng
	
		
		//2- Ko tìm thấy element/node nào
		   //cơ chế tìm lại cứ 0.5 giaay sẽ tìm lại  1 lần
		   // Nếu trong thời gian tìm 15s mà tìm thấy thì pass  còn trong 15s mà ko thấy thì
		     // +không đánh Failed + vẫn chạy tiêp TCS
		     // + Trả về list rỗng 
		
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