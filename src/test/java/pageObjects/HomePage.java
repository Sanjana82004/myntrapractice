package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basePage {

    //constructor
    public HomePage(WebDriver driver) {
    	
    	//we can use it when basePage is extended
        super(driver);
    }

    //for myntra logo
    @FindBy(className = "desktop-logo")private WebElement myntraLogo;

    //for men menu
    @FindBy(linkText = "MEN")private WebElement menMenu;

    //for women menu
    @FindBy(linkText = "WOMEN")private WebElement womenMenu;

    //for search menu
    @FindBy(className = "desktop-searchBar")private WebElement searchBox;

    //for search button
    @FindBy(className = "desktop-submit")private WebElement searchButton;
    
    //to search for kids
    @FindBy(linkText="KIDS")private WebElement kidsMenu;
     
    //to search for beauty
    @FindBy(linkText="BEAUTY")private WebElement beautyMenu;
    
    

    //to check if logo is visible 
    public boolean isLogoDisplayed() {
        return myntraLogo.isDisplayed();
    }

    //to click men menu
    public void clickMenMenu() {
        menMenu.click();
    }

    //to click women menu
    public void clickWomenMenu() {
        womenMenu.click();
    }
    
    public void clickKids() {
        kidsMenu.click();
    }
    
    public void clickBeauty() {
        beautyMenu.click();
    }

    //to enter product in search bar
    public void enterProduct(String product) {
        searchBox.sendKeys(product);
    }

    //to click search button
    public void clickSearch() {
        searchButton.click();
    }
    
    
}

//     for myntra logo
//    @FindBy(css = ".desktop-logo")private WebElement myntraLogo;
//
//    for men menu
//    @FindBy(css = "a[href*='men']")private WebElement menMenu;
//
//    for women menu
//    @FindBy(css = "a[href*='women']")private WebElement womenMenu;
//
//    for search box
//    @FindBy(css = ".desktop-searchBar")private WebElement searchBox;
//
//    for search button
//    @FindBy(css = ".desktop-submit")private WebElement searchButton;
//
//    for kids menu    
//    @FindBy(css = "a[href*='kids']")private WebElement kidsMenu;
//
//    for beauty menu
//    @FindBy(css = "a[href*='beauty']")private WebElement beautyMenu;

