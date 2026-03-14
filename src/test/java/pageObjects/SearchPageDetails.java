package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
  


public class SearchPageDetails extends basePage {
	
	 // Constructor
    public SearchPageDetails(WebDriver driver) {
        super(driver);
    }
  
    
    
    // 1. Elements Initialization
    @FindBy(css = ".pdp-title")
    private WebElement brandName;

    @FindBy(xpath = "//div[@class='pdp-price-info']//h1[@class='pdp-name']")
    private WebElement productName;

    @FindBy(css = "span[class='pdp-price'] strong")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='pdp-description-container']//s[1]")
    private WebElement originalMRP;

    @FindBy(xpath = "//span[@class='pdp-discount']")
    private WebElement discountPercent;

    @FindBy(css = "div[class='index-overallRating'] div:nth-child(1)")
    private WebElement productRating;

    @FindBy(xpath = "//div[@class='size-buttons-size-buttons']//div[@class='size-buttons-tipAndBtnContainer']")
    private List<WebElement> sizeOptions;

    @FindBy(xpath = "//div[@class='image-grid-container common-clearfix']//div[@class='image-grid-col50']")
    private List<WebElement> productImages;

    @FindBy(css = ".pdp-add-to-bag.pdp-button.pdp-flex.pdp-center")
    private WebElement addToBagBtn;

    @FindBy(css = ".pdp-add-to-wishlist.pdp-button.pdp-add-to-wishlist.pdp-button.pdp-flex.pdp-center")
    private WebElement addToWishlistBtn;
    
    @FindBy(xpath = "//span[@class='size-buttons-size-error-message']")
    private WebElement sizeErrorMessage;
    
    @FindBy(xpath = "//body/div[@id='desktop-headerMount']/div/div[1]")
    private WebElement addedToBagPopup;

   
  @FindBy(xpath="//a[@class='pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center ']")
  private WebElement goToBagBtn;
  
  
  
  
    // --- 2. Action Methods ---
    
    
    
    //  return brand name
    public String getBrandName() {
        return brandName.getText();
    }
    
    // return product name
    public String getProductName() {
        return productName.getText();
    }

    
    // return price
    public int getPrice() {
        return Integer.parseInt(productPrice.getText());
    }

   
    public int getRating() {
        return Integer.parseInt(productRating.getText());
    }

   
    public void selectSize(String size) {
        for (WebElement s : sizeOptions) {
            if (s.getText().equalsIgnoreCase(size)) {
                s.click();
                break;
            }
        }
    }
    
    public int getOriginalMRPValue() {
        String fullText = originalMRP.getText();
        return Integer.parseInt(fullText.replaceAll("[^0-9]", ""));
    }
    
    public int getDiscountValue() {
        String fullText = discountPercent.getText();
        return Integer.parseInt(fullText.replaceAll("[^0-9]", ""));
    }

   
    public int getImagesCount() {
        return productImages.size();
    }

    public void clickAddToBag() {
        addToBagBtn.click();
    }

    public void clickAddToWishlist() {
        addToWishlistBtn.click();
    }
    
    public String getSizeErrorMessageText() {
        return sizeErrorMessage.getText();
    }
    
    
    
    public String getAddedToBagText() {
        return addedToBagPopup.getText();
    }
    public void clickGoToBag() {
        goToBagBtn.click();
    }
    
    
 // Verify product title is visible
    public boolean isProductTitleDisplayed() {
        return productName.isDisplayed();
    }

    // Verify product price is visible
    public boolean isProductPriceDisplayed() {
        return productPrice.isDisplayed();
    }

    // Verify product images are visible
    public boolean isProductImagesDisplayed() {
        return productImages.size() > 0;
    }

    // Verify rating is visible
    public boolean isRatingDisplayed() {
        return productRating.isDisplayed();
    }

    // Verify size options are visible
    public boolean isSizeOptionsDisplayed() {
        return sizeOptions.size() > 0;
    }
    
    public boolean getAddedToBagDisplayed() {
    	try {
           
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(addedToBagPopup));
            return addedToBagPopup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}