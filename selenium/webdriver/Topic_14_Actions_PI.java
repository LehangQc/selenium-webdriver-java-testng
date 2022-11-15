package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Actions_PI {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	WebElement webElement;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	@Test
	public void TC_01_Hover() {
		
       driver.get("https://automationfc.github.io/jquery-tooltip/");
       
       WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
       
	   action.moveToElement(ageTextbox).perform();
	   sleepInSecond(5);
	   
	   Assert.assertEquals(driver.findElement(By.xpath("//div[@class= 'ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	   
	}

	@Test
	public void TC_02_Hover() {
        
        driver.get("https://www.myntra.com/");
        
        WebElement kidLink = driver.findElement(By.xpath("//a[@data-group='kids']"));
        
        action.moveToElement(kidLink).perform();
        sleepInSecond(5);
        
        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
        
        
        
		
	}

	@Test
	public void TC_03_Hover() {
	
        driver.get("https://www.fahasa.com/");
        
        WebElement sachTrongNuoc = driver.findElement(By.xpath("//span[@class='icon_menu']"));
        
        action.moveToElement(sachTrongNuoc).perform();
        
        action.click(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='home']/following-sibling::li/strong")).isDisplayed());
        
        		
	}
	@Test
	public void TC_04_Click_And_Hold_Block() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Store list 12 các item cần click and hold lại
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		// Click and hold tu 1-11 

		 action.clickAndHold(allNumbers.get(0)) // click and hold vào số thứ nhất: index 0
		.moveToElement(allNumbers.get(10)) //di chuyển tới index 10 - tương ứng với số 11
		.release() //nhả chuột trái
		.perform();
		 
	    // Verify => các thuộc tính sẽ được update thẻ : thêm thuộc tính là ui-selected
		 allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		 //Expected: List allNumbers chỉ có 9 selected items: sử dụng hàm size
		 Assert.assertEquals(allNumbers.size(), 9);
		 
		 }
	@Test
	public void TC_05_Click_And_Hold_Random()  {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		//Store list 12 các item cần click and hold lại
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		 Assert.assertEquals(allNumbers.size(), 12);
		
		 // Click random các số sử dụng phím CONTROL - hàm Keydown(Key.CONTROL)

	     action.keyDown(Keys.CONTROL).perform();
	     action.click(allNumbers.get(0))
	     .click(allNumbers.get(1))
	     .click(allNumbers.get(3))
	     .click(allNumbers.get(8))
	     .click(allNumbers.get(9))
	     .perform();
	     
	     // Nhả phím CONTROL
	     action.keyUp(Keys.CONTROL).perform();
	     
		 
		 //Expected: List allNumbers chỉ có 9 selected items: sử dụng hàm size
	     allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		 Assert.assertEquals(allNumbers.size(), 5);
	}
	
	@Test
	public void TC_06_Double_Click()  {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform(); => ko dùng đc vì ko scroll đc tới viewport
	
		// mà cần sử dụng javascript 
		
		WebElement doubleClickMeText = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		//Scroll tới element, nếu truyển là true là scroll tới mép trên, còn false là mép dưới
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickMeText);
		
		action.doubleClick(doubleClickMeText).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
				
		}
	@Test
	public void TC_07_Right_Click()  {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		sleepInSecond(3);
		
		WebElement deleteBefore = driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-delete']"));
		
		action.moveToElement(deleteBefore).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-delete context-menu-hover context-menu-visible']")).isDisplayed());
		
		action.click(deleteBefore).perform();
		
		Alert alert = driver.switchTo().alert();
		sleepInSecond(5);
		
	    Assert.assertEquals(alert.getText(), "clicked: delete");
	        
	    // Accept alert này
	   alert.accept();
	   sleepInSecond(3);
	   
	    Assert.assertFalse(deleteBefore.isDisplayed());
       
	}
	@Test
	public void TC_08_Drag_Drop_HTML4()  {
	    driver.get("https://automationfc.github.io/kendo-drag-drop/");
	    
	    WebElement source = driver.findElement(By.cssSelector("div#draggable"));
	    
	    WebElement target = driver.findElement(By.cssSelector("div#droptarget"));
	    
	    action.dragAndDrop(source, target).perform();
	    
	    Assert.assertEquals(target.getText(),"You did great!");
	    
	    // Verify background color
	    String logginButtonBackgroundRgba = target.getCssValue("background-color");
		//đổi mã color Rgba sang Hexa
		String logginButtonBackgroundHexa = Color.fromString(logginButtonBackgroundRgba).asHex().toUpperCase();
		//Verify 
		Assert.assertEquals(logginButtonBackgroundHexa,"#03A9F4");
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
