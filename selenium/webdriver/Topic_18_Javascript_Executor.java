package webdriver;

import java.util.Random;
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

public class Topic_18_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Techpanda() {
		// Truy cập vào trang sử dụng Javascript Executor
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		// Get domain của page, rồi verify nó = live.techpanda.org
		String techPandaDomain = (String) executeForBrowser("return document.domain");
		
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");
		
		// Get URL của page, rồi verify nó 
		String homePageUrl = (String) executeForBrowser("return document.URL");
		
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
		
        // Open trang Mobile
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		// Add sản phẩm vào card - click vào add to cart
		
		hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		sleepInSecond(3);
		//Verify text hiển thị
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//open trang customer service
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		// Scroll tới NEWLETTERS textbox ở cuối trang
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInSecond(3);
		//Input 1 email hợp lệ vào text box
		String emailAddress = "afc" + generateRandomNumber() + "@hotmail.vn";
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		sleepInSecond(3);
		//Click Subcribe
		
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		// Navigate tới trang khác rồi kiểm tra DOMAIN
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
        String guRuDomain = (String) executeForBrowser("return document.domain");
		
		Assert.assertEquals(guRuDomain, "demo.guru99.com");
		
	}

	@Test
	public void TC_02_Rode() {
        driver.get("https://warranty.rode.com/");
        
        By registerButton = By.xpath("//button[contains(text(),'Register')]");
        
        //Click button Register
        driver.findElement(registerButton).click();
        sleepInSecond(3);
        
        
        //verify text-firstname hien thi sau khi tap register button
        Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"),"Please fill out this field.");
        
        
        //Sendkey
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("automation");
        
        //Click button register
        driver.findElement(registerButton).click();
        sleepInSecond(3);
        
        //last name textbox hieen thi message
        Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"),"Please fill out this field.");
        
        
        //Sendkey
        driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("fc");
        
        //Click button register
        driver.findElement(registerButton).click();
        sleepInSecond(3);
        
        //Email textbox hieen thi message
        Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='email']"),"Please fill out this field.");
       
       
        //Sendkey
        driver.findElement(By.xpath("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='email']")).sendKeys("abnd#gc");
        
        //Verify message hien thi
        Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='email']"),"Please enter an email address.");
        
        //Sendkey email hop le
        driver.findElement(By.xpath("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='email']")).sendKeys("lehang@gmail.com");
       
        //Click button register
        driver.findElement(registerButton).click();
        sleepInSecond(3);
        
        //Verify message hien thi cho password
        Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='password']"),"Please fill out this field.");
        
        //Sendkey passord hop le
        driver.findElement(By.xpath("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='password']")).sendKeys("lehang");
        
      //Click button register
        driver.findElement(registerButton).click();
        sleepInSecond(3);
        
        //Verify message hien thi cho password-confirm
        Assert.assertEquals(getElementValidationMessage("//div[contains(text(), 'Register')]/following-sibling::div//input[@id='password-confirm']"),"Please fill out this field.");
        sleepInSecond(2);
        
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

    public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	  public int generateRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(99999);
	  }
			
	   public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}



}