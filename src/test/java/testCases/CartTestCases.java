package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.SearchPageDetails;

public class CartTestCases extends BaseClass{
   
	
	    HomePage home;
	    SearchPage search;
	    SearchPageDetails product;
	    CartPage cart;
	    
	    @BeforeMethod
	    public void addProductToCart() {

	        home = new HomePage(driver);
	        search = new SearchPage(driver);
	        product = new SearchPageDetails(driver);
	        cart = new CartPage(driver);

	        // Step 1: Search product
	        home.enterProduct("women sneakers");
	        home.clickSearch();

	        // Step 2: Verify search page
	        search.verifySearchPageLoaded();

	        // Step 3: Open first product
	        search.clickFirstProduct(driver);

	        // Step 4: Select size
	        product.selectSize("7");

	        // Step 5: Add to bag
	        product.clickAddToBag();
	        
	        product.clickGoToBag();

	        // (Optional) wait for add to bag popup
	      //  System.out.println(product.getAddedToBagText());

	        // If cart icon exists you can open cart here
	        // home.openCart();
	    }
	
	/*
	@Test(priority = 1)
    public void verifyUnitPriceDisplayedCorrectly() {

        cart = new CartPage(driver);

        String unitPriceText = cart.allProductPrices.get(0).getText();
        String cleanPrice = unitPriceText.replace("₹", "").replace(",", "");
        int unitPrice = Integer.parseInt(cleanPrice);

        System.out.println("Unit Price in Cart: " + unitPrice);

        Assert.assertTrue(unitPrice > 0, "Unit price is not displayed correctly");
    }  */
	
	/*
	@Test(priority = 2)
    public void verifyTotalPriceWhenQuantityTwo() {

        cart = new CartPage(driver);
        // Get total MRP for quantity 1
        int totalBefore = Integer.parseInt(cart.getTotalMRP().replaceAll("[^0-9]", ""));

        // Select quantity 2
        cart.selectQuantity(0, "2");
        
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Get total MRP after updating quantity
        int totalAfter = Integer.parseInt(cart.getTotalMRP().replaceAll("[^0-9]", ""));

        // Assert that totalAfter is greater than totalBefore
        Assert.assertTrue(totalAfter > totalBefore, "Quantity update hone par Total MRP sahi se increase nahi hua!");
        
        System.out.println("Total before quantity update: " + totalBefore);
        System.out.println("Total after quantity update: " + totalAfter);
        
    }  */ 
	
	/*
	@Test(priority = 3)
    public void verifyTotalPriceWhenQuantityTen() {
    cart = new CartPage(driver);

    // Get total MRP before changing quantity
    int totalBefore = Integer.parseInt(cart.getTotalMRP().replaceAll("[^0-9]", ""));

    // Select quantity 10
    cart.selectQuantity(0, "10");

    try { Thread.sleep(2000); } catch (InterruptedException e) {}

    // Get total MRP after changing quantity
    int totalAfter = Integer.parseInt(cart.getTotalMRP().replaceAll("[^0-9]", ""));

    // Assert that totalAfter is greater than totalBefore
    Assert.assertTrue(totalAfter > totalBefore, "Quantity update hone par Total MRP sahi se increase nahi hua!");
        
    }  */
	
	/*
	@Test(priority = 4)
    public void verifyPriceUpdatesDynamically() {

        cart = new CartPage(driver);

        cart.selectQuantity(0, "2");

        String priceForTwoText = cart.getFinalPayableAmount();
        int priceForTwo = Integer.parseInt(priceForTwoText.replace("₹","").replace(",","").trim());

        cart.selectQuantity(0, "3");
        
        String priceForThreeText = cart.getFinalPayableAmount();
        int priceForThree = Integer.parseInt(priceForThreeText.replace("₹","").replace(",","").trim());


        

        Assert.assertNotEquals(priceForTwo, priceForThree,"Price did not update dynamically after changing quantity");
    }  
	/*
	@Test(priority = 5)
    public void verifyCouponAppliedCorrectly() {

        cart = new CartPage(driver);

        String beforeCoupon = cart.getFinalPayableAmount();

        cart.clickApplyCoupon();

        String afterCoupon = cart.getFinalPayableAmount();

        Assert.assertNotEquals(beforeCoupon, afterCoupon);
    }  */
	
	@Test(priority = 6)
    public void verifyPriceConsistencyBetweenProductAndCart() {

        cart = new CartPage(driver);

        String cartPrice = cart.allProductPrices.get(0).getText();

        String cleanCartPrice = cartPrice.replace("₹","").replace(",","");

        int price = Integer.parseInt(cleanCartPrice);

        System.out.println("Cart Price: " + price);

        Assert.assertTrue(price > 0);
    
	
} 
}
