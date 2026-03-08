package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basePage {
   
	
public WebDriver driver;
	
    public basePage(WebDriver driver) {
    	this.driver= driver;
    	//this line ensures that local driver will get the same value
    	//as sign by tets class so that browser window will remain same for whole project
    	PageFactory.initElements(driver, this);
   
    	}
}
