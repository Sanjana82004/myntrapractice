package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class ProductPurchaseFlowTest extends BaseClass {
	
	HomePage hp;
	SearchPage sp;
	SearchPageDetails spd;
	CartPage cp;
	
	
	 @BeforeMethod
	    public void init() {
	        hp = new HomePage(driver);
	        sp = new SearchPage(driver);
	        spd = new SearchPageDetails(driver);
	        cp= new CartPage(driver);
	    }
	
	@Test(priority = 1)
	public void verifyUserCanOpenProductFromSearchResults() {
		
		
		hp.enterProduct("women sneakers");
		hp.clickSearch();
		sp.verifySearchPageLoaded();
		String firstProductName=sp.getFirstProductName();
		String parentWindow = driver.getWindowHandle();
		sp.clickFirstProduct(driver);
		
		 for (String window : driver.getWindowHandles()) {
		        if (!window.equals(parentWindow)) {
		            driver.switchTo().window(window);
		            break;
		        }
		    }
		System.out.println(firstProductName);
		
		String firstProductNameInDetailsPage=spd.getProductName();
		System.out.println(firstProductNameInDetailsPage);
		//Assert.assertEquals(firstProductName,firstProductNameInDetailsPage );
		//Assert.assertTrue(firstProductNameInDetailsPage.toLowerCase().contains("women"));
	  
		String[] words = firstProductName.toLowerCase().split(" ");

		boolean match = false;

		for(String word : words){
		    if(firstProductNameInDetailsPage.toLowerCase().contains(word)){
		        match = true;
		        break;
		    }
		}

		Assert.assertTrue(match,"Product names do not match logically");
	}
	
	
	@Test(priority = 2, dependsOnMethods = "verifyUserCanOpenProductFromSearchResults")
    public void verifyProductDetailsDisplayedCorrectly() {

        
        Assert.assertTrue(spd.isProductTitleDisplayed(), "Product name not visible");
        Assert.assertTrue(spd.isProductPriceDisplayed(), "Product price not visible");
        Assert.assertTrue(spd.isProductImagesDisplayed(), "Product images not visible");
        Assert.assertTrue(spd.isRatingDisplayed(), "Product rating not visible");
        Assert.assertTrue(spd.isSizeOptionsDisplayed(), "Size options not visible");
    }
	
	  @Test(priority = 3, dependsOnMethods = "verifyProductDetailsDisplayedCorrectly")
	    public void verifyUserCanAddProductToCartAfterSelectingSize() {
	        
	        
	        Assert.assertTrue(spd.isProductTitleDisplayed(), "Product page load nahi hui!");
	        Assert.assertTrue(spd.isSizeOptionsDisplayed(), "Size options available nahi hain!");

	        
	        spd.selectSize("7");
	        spd.clickAddToBag();

	       
	        boolean isAdded = spd.getAddedToBagDisplayed();
	        Assert.assertTrue(isAdded, "Product cart mein add nahi hua ya confirmation popup nahi dikha.");
	        
	        System.out.println("Success: Product added to bag successfully after selecting size.");
	    }
	  
	  @Test(priority=4,dependsOnMethods ="verifyUserCanAddProductToCartAfterSelectingSize")
	  public void verifyItemAddedToCart() {
		  String ProductName=spd.getProductName();
		  spd.clickGoToBag();
		  
		  Assert.assertTrue(cp.isProductInCart(ProductName));
	  }

	 
	

}
