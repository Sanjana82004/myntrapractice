package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sortingPage extends basePage{
    public WebDriverWait wait;
	public sortingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//div[@class='sort-sortBy']") WebElement sortBtn;
	@FindBy(xpath = "//ul[@class='sort-list']//label[contains(text(),'Low to High')]") WebElement lowToHigh;
	
	@FindBy(xpath = "//ul[@class='sort-list']//label[contains(text(),'High to Low')]") WebElement highToLow;
	@FindBy(xpath = "//span[@class='product-discountedPrice']") List<WebElement> productPrices;
	
	@FindBy(className = "desktop-searchBar")private WebElement searchBox;

    //for search button
    @FindBy(className = "desktop-submit")private WebElement searchButton;
	
	
	public void clickSort() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(sortBtn));
        sortBtn.click();
    }
	
	public void sortLowToHigh() {
        sortBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lowToHigh));
        lowToHigh.click();
        waitUntilPricesVisible();
    }
	
	public void sortHighToLow() {
        sortBtn.click();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(highToLow));
        
        highToLow.click();
        waitUntilPricesVisible();

    }
	
	
	public void enterProduct(String product) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(product);
    }

    //to click search button
    public void clickSearch() {
    	
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        waitUntilPricesVisible();
    }
    
    private void waitUntilPricesVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
    }

	 
	
	 public List<Integer> getFirstFivePrices() {
        
		 waitUntilPricesVisible();
		 
	        List<Integer> prices = new ArrayList<>();

	        for(int i=0;i<5;i++) {

	            String priceText = productPrices.get(i).getText();

	            priceText = priceText.replace("Rs.","")
	                                 .replace("₹","")
	                                 .replace(",","")
	                                 .trim();

	            int price = Integer.parseInt(priceText);

	            prices.add(price);
	        }

	        return prices;
	    }
	
	

}
