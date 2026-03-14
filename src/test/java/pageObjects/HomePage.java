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
    @FindBy(className = "desktop-logo")
    private WebElement myntraLogo;

    //for men menu
    @FindBy(linkText = "MEN")
    private WebElement menMenu;

    //for women menu
    @FindBy(linkText = "WOMEN")
    private WebElement womenMenu;

    //for kids menu
    @FindBy(linkText = "KIDS")
    private WebElement kidsMenu;

    //for beauty menu
    @FindBy(linkText = "BEAUTY")
    private WebElement beautyMenu;

    //for search box
    @FindBy(className = "desktop-searchBar")
    private WebElement searchBox;

    //for search button

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

    //to click kids menu
    public void clickKids() {
        kidsMenu.click();
    }

    //to click beauty menu
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


    //to click cart icon
    public void clickCartIcon() {
        cartBtn.click();
    }
}