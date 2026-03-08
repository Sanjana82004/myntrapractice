package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
  


public class SearchPageDetails extends basePage {
	
	 // Constructor
    public SearchPageDetails(WebDriver driver) {
        super(driver);
    }
  
    
    
    // 1. Elements Initialization
    @FindBy(css = ".pdp-title")
    private WebElement brandName;

    @FindBy(css = ".pdp-name")
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
}