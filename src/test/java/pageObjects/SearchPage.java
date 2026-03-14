package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends basePage{
	
	WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//////////////////////////////////////
// Breadcrumb
//////////////////////////////////////

	@FindBy(xpath = "//div[contains(@class,'breadcrumbs')]")
	WebElement breadcrumb;

//////////////////////////////////////
// Page Title
//////////////////////////////////////

	@FindBy(xpath = "//span[@class='title-count']")
	WebElement totalProducts;

//////////////////////////////////////
// FILTER SECTION
//////////////////////////////////////

	@FindBy(xpath = "//div[text()='FILTERS']")
	WebElement filterHeader;

//////////////////////////////////////
// BRAND FILTER
//////////////////////////////////////

	@FindBy(xpath = "//div[text()='BRAND']")
	WebElement brandFilter;

	@FindBy(xpath = "//label[contains(text(),'Puma')]")
	WebElement pumaBrand;

	@FindBy(xpath = "//label[contains(text(),'Roadster')]")
	WebElement roadsterBrand;

	@FindBy(xpath = "//label[contains(text(),'Nike')]")
	WebElement nikeBrand;

//////////////////////////////////////
// PRICE FILTER
//////////////////////////////////////

	@FindBy(xpath = "//div[text()='PRICE']")
	WebElement priceFilter;

	@FindBy(xpath = "//div[contains(@class,'price-slider')]")
	WebElement priceSlider;

//////////////////////////////////////
// COLOR FILTER
//////////////////////////////////////

	@FindBy(xpath = "//div[text()='COLOR']")
	WebElement colorFilter;

	@FindBy(xpath = "//label[contains(@class,'common-customCheckbox')]//span[text()='White']")
	WebElement whiteColor;

	@FindBy(xpath = "//label[contains(@class,'common-customCheckbox')]//span[text()='Black']")
	WebElement blackColor;

//////////////////////////////////////
// DISCOUNT RANGE
//////////////////////////////////////

	@FindBy(xpath = "//div[text()='DISCOUNT RANGE']")
	WebElement discountRange;

	@FindBy(xpath = "//label[contains(text(),'30% and above')]")
	WebElement discount30;

	@FindBy(xpath = "//label[contains(text(),'40% and above')]")
	WebElement discount40;

//////////////////////////////////////
// TOP FILTER TABS
//////////////////////////////////////

	@FindBy(xpath = "//span[contains(text(),'Ankle Height')]")
	WebElement ankleHeightFilter;

	@FindBy(xpath = "//span[contains(text(),'Bundles')]")
	WebElement bundlesFilter;

	@FindBy(xpath = "//span[contains(text(),'Country of Origin')]")
	WebElement countryOriginFilter;

	@FindBy(xpath = "//span[contains(text(),'Fastening')]")
	WebElement fasteningFilter;

	@FindBy(xpath = "//span[contains(text(),'Insole')]")
	WebElement insoleFilter;

	@FindBy(xpath = "//span[contains(text(),'Materials')]")
	WebElement materialsFilter;

	@FindBy(xpath = "//span[contains(text(),'Micro Trends')]")
	WebElement microTrendsFilter;

//////////////////////////////////////
// SORT DROPDOWN
//////////////////////////////////////

	@FindBy(xpath = "//span[contains(text(),'Sort by')]")
	WebElement sortDropdown;

	@FindBy(xpath = "//label[contains(text(),'Price: Low to High')]")
	WebElement sortLowToHigh;

	@FindBy(xpath = "//label[contains(text(),'Price: High to Low')]")
	WebElement sortHighToLow;

	@FindBy(xpath = "//label[contains(text(),'Customer Rating')]")
	WebElement sortRating;

//////////////////////////////////////
// PRODUCT GRID
//////////////////////////////////////

	@FindBy(xpath = "//ul[@class='results-base']")
	WebElement productGrid;

	@FindBy(xpath = "//li[@class='product-base']")
	List<WebElement> allProducts;

//////////////////////////////////////
// FIRST PRODUCT
//////////////////////////////////////

	@FindBy(xpath = "(//li[@class='product-base'])[1]")
	WebElement firstProduct;

	@FindBy(xpath = "(//h3[@class='product-brand'])[1]")
	WebElement firstProductBrand;
	
	@FindBy(xpath = "(//h4[@class='product-product'])[1]")
	WebElement firstProductName;

	@FindBy(xpath = "(//span[@class='product-discountedPrice'])[1]")
	WebElement firstProductPrice;

	@FindBy(xpath = "(//span[@class='product-strike'])[1]")
	WebElement firstProductOriginalPrice;

	@FindBy(xpath = "(//span[@class='product-discountPercentage'])[1]")
	WebElement firstProductDiscount;

//////////////////////////////////////
// PRODUCT RATING
//////////////////////////////////////

	@FindBy(xpath = "(//div[@class='product-ratingsContainer'])[1]")
	WebElement firstProductRating;

//////////////////////////////////////
// WISHLIST ICON
//////////////////////////////////////

	@FindBy(xpath = "(//span[contains(@class,'product-wishlistIcon')])[1]")
	WebElement firstProductWishlist;

//////////////////////////////////////
// METHODS
//////////////////////////////////////

	public void verifySearchPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(productGrid));
	}

	public int getTotalProducts() {
		wait.until(ExpectedConditions.visibilityOf(productGrid));
		return allProducts.size();
	}

	public void applyNikeFilter() {
		wait.until(ExpectedConditions.elementToBeClickable(nikeBrand)).click();
	}

	public void applyPriceSortLowToHigh() {
		wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sortLowToHigh)).click();
	}

	

	public String getFirstProductPrice() {
		wait.until(ExpectedConditions.visibilityOf(firstProductPrice));
		return firstProductPrice.getText();
	}

	public String getFirstProductName() {
	    wait.until(ExpectedConditions.visibilityOf(firstProductName));
	    return firstProductName.getText();
	}

	public void addFirstProductToWishlist() {
		wait.until(ExpectedConditions.elementToBeClickable(firstProductWishlist)).click();
	}
	public void clickFirstProduct(WebDriver driver) {
		
	    wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
	    
	    // Naye tab par switch karne ka logic
	    for (String winHandle : driver.getWindowHandles()) {
	        driver.switchTo().window(winHandle);
	    }
	}
}