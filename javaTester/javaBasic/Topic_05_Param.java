package javaBasic;

import org.testng.annotations.Test;
public class Topic_05_Param {
	//hàm không có tham số
	public void clickToElement() {

	}
		

    public void clickToElement(String elementName) {
    	
    }
    
    public void clickToElement(String elementName, String locatorName) {
    	
    }

@Test
public void TC_01_Login() {
	clickToElement("Textbox");
}

}