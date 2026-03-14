package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends basePage
{
	public WebDriverWait wait;
	public CartPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
	}
	
		@FindBy(xpath = "//div[@class='addressStripV2-base-highlight']")
	    public WebElement checkDeliveryTitle;

	    @FindBy(xpath = "//div[text()='ENTER PIN CODE']")
	    public WebElement enterPinCodeBtn;

	    // Out of Stock Global Alert
	    @FindBy(xpath = "//span[@class='messageContainer-base-highlightText']")
	    public WebElement globalOutOfStockMsg;


	    // ==========================================
	    // 2. CART ITEM LIST (Dynamic & Static)
	    // ==========================================

	    // Total Items Selected Label (e.g., "2/2 ITEMS SELECTED")
	    @FindBy(xpath = "//span[@class='bulkActionStrip-itemSelected']")
	    public WebElement itemsSelectedLabel;

	    @FindBy(xpath = "//button[normalize-space()='REMOVE']")
	    public WebElement removeAllBtn;

	    @FindBy(xpath = "//button[normalize-space()='MOVE TO WISHLIST']")
	    public WebElement moveToWishlistBtn;

	 
	    @FindBy(xpath = "//div[@class='itemContainer-base-details']//a")
	    public List<WebElement> allProductNames;
	    
	    
	    @FindBy(xpath = "//div[contains(@class,'itemComponents-base-price')]//div")
	    public List<WebElement> allProductPrices;
	    
	    @FindBy(xpath = "//div[contains(@class, 'item-base-item')]")
	    public List<WebElement> allCartItemContainers;
	    

	    // Common elements for items (using List if you want to loop)
	    @FindBy(xpath = "//div[contains(@class, 'item-base-item')]")
	    public List<WebElement> allCartItems;

	    @FindBy(xpath = "//div[contains(@class, 'item-base-selectedIndicator')]")
	    public List<WebElement> itemCheckboxes;
	    
	    @FindBy(xpath = "//div[contains(@class, 'itemComponents-base-size')]")
	    public List<WebElement> allSizeDropdowns;

	    @FindBy(xpath = " //div[normalize-space()='DONE']")
	    public WebElement doneButton;
	    
	    @FindBy(xpath = "//div[contains(@class, 'itemComponents-base-quantity')]")
	    public List<WebElement> allQtyDropdowns;
	    
	    
	    
	    
	    // ==========================================
	    // 3. COUPONS & GIFTING (Right Sidebar)
	    // ==========================================

	    @FindBy(xpath = "//span[text()='Apply Coupons']")
	    public WebElement applyCouponsHeader;

	    @FindBy(xpath = "//div[contains(@class, 'coupons-base-applyButton')]")
	    public WebElement applyBtn;

	    @FindBy(xpath = "//span[contains(text(), 'Login to get upto')]")
	    public WebElement loginOfferText;

	    @FindBy(xpath = "//div[contains(text(), 'Buying for a loved one?')]")
	    public WebElement giftingSectionText;

	    @FindBy(xpath = "//div[text()='ADD GIFT PACKAGE']")
	    public WebElement addGiftPackageBtn;


	    // ==========================================
	    // 4. DONATION SECTION
	    // ==========================================

	    @FindBy(xpath = "//div[text()='Donate and make a difference']")
	    public WebElement donateCheckboxLabel;

	   


	    // ==========================================
	    // 5. PRICE DETAILS (Billing Section)
	    // ==========================================

	    @FindBy(xpath = "//div[@class='priceBlock-base-priceHeader']")
	    public WebElement priceDetailsHeader;

	    @FindBy(xpath = "//span[text()='Total MRP']/following-sibling::span")
	    public WebElement totalMRPValue;

	    @FindBy(xpath = "//span[text()='Discount on MRP']/following-sibling::span")
	    public WebElement discountValue;

	    @FindBy(xpath = "//span[text()='Coupon Discount']/following-sibling::span")
	    public WebElement couponDiscountValue;

	    @FindBy(xpath = "//span[text()='Platform Fee']/following-sibling::span")
	    public WebElement platformFeeValue;

	    @FindBy(xpath = "//div[contains(@class,'priceDetail-base-total')]//span[contains(@class,'priceDetail-base-value')]//span[2]")
	    public WebElement totalAmountFinal;

	
      @FindBy(xpath = "//div[contains(text(),'PLACE ORDER')]") WebElement placeOrder;
      
      
      
      // apply for coupon 
       
      
      public String getPlatformFee() {

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    wait.until(ExpectedConditions.visibilityOf(platformFeeValue));

    	    String priceText = platformFeeValue.getText();
    	    String cleanPrice = priceText.replace("₹", "")
    	                                 .replace(",", "")
    	                                 .trim();

    	    return cleanPrice;
    	}
     
      
      public void clickEnterPinCode() {
          enterPinCodeBtn.click();
      }

      public String getDeliveryStatusText() {
          return checkDeliveryTitle.getText();
      }

      public boolean isOutOfStockAlertVisible() {
          try {
              return globalOutOfStockMsg.isDisplayed();
          } catch (Exception e) {
              return false;
          }
      }
      
      
      public String getItemsSelectedCountText() {
          return itemsSelectedLabel.getText();
      }

      public void clickRemoveAll() {
          removeAllBtn.click();
      }

      public void clickMoveToWishlist() {
          moveToWishlistBtn.click();
      }
      
      public void clickApplyCoupon() {
          applyBtn.click();
      }

      public void clickAddGiftPackage() {
          addGiftPackageBtn.click();
      }
	    
	    public String getFinalPayableAmount() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(totalAmountFinal));

	        String priceText = totalAmountFinal.getText();

	        return priceText.replace("₹", "").replace(",", "").trim();
	       
	    }
	    
	    
	    public void printAllProductDetails() {
	        for (int i = 0; i < allProductNames.size(); i++) {
	            System.out.println("Product: " + allProductNames.get(i).getText());
	            System.out.println("Price: " + allProductPrices.get(i).getText());
	            System.out.println("-------------------------");
	        }
	    }
	    
	    public boolean isProductInCart(String expectedName) {
	        return allProductNames.stream()
	                .anyMatch(element -> element.getText().contains(expectedName));
	    }
	    
	    
	    public void selectSizeDynamically(int productIndex, String sizeValue) {
	        // 1. Dropdown kholiye
	        allSizeDropdowns.get(productIndex).click();

	        // 2. Common XPath String banayein (Normalize space ke saath)
	        String commonXpath = "//div[contains(@class, 'sizeSelector')]//*[normalize-space()='" + sizeValue + "']";
	        
	        // 3. Size click karein
	        driver.findElement(By.xpath(commonXpath)).click();

	        // 4. DONE click karein
	        doneButton.click();
	    }
	    
	    
	    public void selectQuantity(int productIndex, String qtyValue) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebElement dropdown = allQtyDropdowns.get(productIndex);
	        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
	        try {
	            dropdown.click();
	        } catch (Exception e) {
	            js.executeScript("arguments[0].click();", dropdown);
	        }
	        String dynamicQtyXpath = "//div[@class='dialogs-base-display' and normalize-space()='" + qtyValue + "']";
	        WebElement qty = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicQtyXpath)));
	        js.executeScript("arguments[0].click();", qty);
	        wait.until(ExpectedConditions.visibilityOf(doneButton));
	        
	        try {
	            doneButton.click();
	        } catch (Exception e) {
	            
	            js.executeScript("arguments[0].click();", doneButton);
	        }
	    }
	   
	    
	    
	    
	    
	    public void selectDonationAmountDynamically(String amount) {
	        // Ye XPath kisi bhi amount ko handle kar lega (e.g., "10", "20", "50", "100")
	        String dynamicXpath = "//div[@data-key='" + amount + "']";
	        
	        WebElement amountElement = driver.findElement(By.xpath(dynamicXpath));
	        amountElement.click();
	    }
	    
	    public String getTotalMRP() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(totalMRPValue));
	        
	       
	        String priceText = totalMRPValue.getText();
	    	String cleanPrice = priceText.replace("₹", "").replace(",", "").trim();
	    	return cleanPrice;
	    	}
	    
	    public String getDiscountAmount() { 
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(discountValue));
	        
	       
	        String priceText = discountValue.getText();
	    	String cleanPrice = priceText.replace("₹", "").replace(",", "").replace("-", "").trim();
	    	return cleanPrice;
	    	}
	    
	    
	    
	    public void clickPlaceOrder() {
	    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();
	    }
	    
	    
	    public void printCartSummary() {
	        System.out.println("Total MRP: " + getTotalMRP());
	        System.out.println("Final Amount: " + getFinalPayableAmount());
	    }
	    
	    
	    
	    
	    
	   // handle remove pop up
	    
	    @FindBy(xpath = "//*[name()='svg' and contains(@class, 'itemContainer-base-closeIcon')]")
	    public List<WebElement> individualItemCloseIcons;
	    
	    @FindBy(xpath = "//div[contains(@class,'confirmOrCancelModal-buttonClass')]//button[contains(@class,'inlinebuttonV2-base-actionButton')][normalize-space()='REMOVE']")
	    public WebElement popupConfirmRemoveBtn;
	    
	    @FindBy(xpath = "//div[contains(@class,'emptyCart-base-emptyDesc')]")
	    public WebElement emptyCartMainMessage;
	    
	    
	    
	    public void removeAllItemsOneByOne() {
	        try {
	            // Step 1: Check karein ki items hain ya nahi
	            // @FindBy se aane wali list stale ho sakti hai, isliye ise refresh karna zaroori hai
	            int totalItems = individualItemCloseIcons.size();
	            System.out.println("Total items found to remove: " + totalItems);

	            for (int i = 0; i < totalItems; i++) {
	                // Step 2: Har baar element ko fresh dhoondein taaki "Stale" error na aaye
	                // Myntra delete ke baad DOM reload karta hai
	                WebElement closeIcon = wait.until(ExpectedConditions.elementToBeClickable(individualItemCloseIcons.get(0)));
	                closeIcon.click();

	                // Step 3: Remove Button Popup ka wait karein
	                wait.until(ExpectedConditions.elementToBeClickable(popupConfirmRemoveBtn)).click();

	                // Step 4: Sabse Important - Item gayab hone ka wait karein
	                // Thread.sleep yahan zaruri hai kyunki Myntra ka UI update hone mein time leta hai
	                Thread.sleep(3000); 
	                
	                System.out.println("Item " + (i + 1) + " successfully removed.");
	            }
	        } catch (Exception e) {
	            System.out.println("Removal process mein error: " + e.getMessage());
	        }
	    }
	    
	    
	  
	    
	}
	
	
	
	


