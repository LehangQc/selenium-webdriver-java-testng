package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_PI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test
	public void TC_01_Displayed() {
	//Step 1: Access vao trang https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
	//Step 2: kiem tra cac hien thi tren trang Email, Age, Education
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		if(emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
			
		}
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if(ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 radio is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if(educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
		
	// Step 3: Kiểm tra phần tử Name: User5 không hiển thị 
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if(name5Text.isDisplayed()) {
			System.out.println("Name 5 Text is displayed");
		} else {
			System.out.println("Name 5 Text is not displayed");
		}
		
	}

	@Test
	public void TC_02_Enabled() {
		//Step 1: Access vao trang https://automationfc.github.io/basic-form/index.html
				driver.get("https://automationfc.github.io/basic-form/index.html");
			//Step 2: kiem tra cac hien thi tren trang Email, Age, Education
				WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
				if(emailTextbox.isEnabled()) {
					emailTextbox.sendKeys("Automation Testing");
					System.out.println("Email textbox is enabled");
				} else {
					System.out.println("Email textbox is disabled");
					
				}
				WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
				if(ageUnder18Radio.isEnabled()) {
					ageUnder18Radio.click();
					System.out.println("Age under 18 radio is enabled");
				} else {
					System.out.println("Age under 18 is disabled");
				}
				
				WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
				if(educationTextarea.isEnabled()) {
					educationTextarea.sendKeys("Automation Testing");
					System.out.println("Education textarea is enabled");
				} else {
					System.out.println("Education textarea is disabled");
				}
		// Dung Assert
				WebElement jobRole01 = driver.findElement(By.xpath("//label[@for='job1']"));
				Assert.assertTrue(jobRole01.isEnabled());
				
				WebElement interestsDevelopment = driver.findElement(By.xpath("//label[text()='Interests:']"));
				Assert.assertTrue(interestsDevelopment.isEnabled());
				
	   // Dung Assert cho case expect = Disabled
				WebElement job3Dropdown = driver.findElement(By.cssSelector("#job3"));
				Assert.assertFalse(job3Dropdown.isEnabled());
	}
	

	@Test
	public void TC_03_Selected() {
		//Kiem tra phan tu selected
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		
		
		ageUnder18Radio.click();
		javaCheckbox.click();
		
		// Verify 2 field la selected
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());
		
		//Click tiep
		ageUnder18Radio.click();
		javaCheckbox.click();
		// verify 1 thang true 1 thang false
		// Do thằng checkbox nếu click lần nữa sẽ thành unselected
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
	
		Assert.assertFalse(javaCheckbox.isSelected());
		
		if(ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 Radio is selected");}
			else { System.out.println("Age under 18 Radio is Desselected");}
		
		if(javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");}
			else { System.out.println("Java Checkbox is Desselected");}
		
		
	}
	@Test
	public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        
        driver.findElement(By.id("new_username")).sendKeys("automationfc");
        
        WebElement passwordTextbox = driver.findElement(By.id("new_password"));
        WebElement signUpbutton = driver.findElement(By.cssSelector("#create-account"));
        //Lowercase
        passwordTextbox.sendKeys("auto");
        sleepInSecond(1);
       
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
       
    // Uppercase
        passwordTextbox.clear();
        passwordTextbox.sendKeys("AUTO");
        sleepInSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
        Assert.assertFalse(signUpbutton.isEnabled());
	
    // Case number
	   passwordTextbox.clear();
       passwordTextbox.sendKeys("1234");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
	// Ky tu dac biet
       passwordTextbox.clear();
       passwordTextbox.sendKeys("!@#");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
    // 8 character minimum
       
       passwordTextbox.clear();
       passwordTextbox.sendKeys("12345678");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
       Assert.assertFalse(signUpbutton.isEnabled());
    
       // Combine
       passwordTextbox.clear();
       passwordTextbox.sendKeys("Hanghunghang1994@");
       sleepInSecond(1);
       Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
       Assert.assertTrue(signUpbutton.isEnabled());
       
       
       
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