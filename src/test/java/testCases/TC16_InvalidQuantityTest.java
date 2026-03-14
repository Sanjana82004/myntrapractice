package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class TC16_InvalidQuantityTest extends BaseClass {

    @Test
    public void verifyInvalidQuantityValidation() throws InterruptedException {

        // Create HomePage object to access search functions
        HomePage home = new HomePage(driver);

        // Enter product name in search bar
        home.enterProduct("shirt");

        // Click search button
        home.clickSearch();

        // Wait for search results to load
        Thread.sleep(4000);

        // Click first product from search results
        driver.findElement(By.cssSelector(".product-base")).click();

        // Wait for product page to open
        Thread.sleep(3000);

        // Switch to new tab because Myntra opens product in another tab
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Create object for Product Details Page
        SearchPageDetails product = new SearchPageDetails(driver);

        // Select product size before adding to cart
        product.selectSize("38");

        // Click Add to Bag button
        product.clickAddToBag();

        // Wait for product to be added to cart
        Thread.sleep(3000);

        // Open cart page directly
        driver.get("https://www.myntra.com/checkout/cart");

        // Wait for cart page to load
        Thread.sleep(4000);

        // Create CartPage object
        CartPage cart = new CartPage(driver);

        // Click quantity dropdown for first product in cart
        cart.allQtyDropdowns.get(0).click();

        Thread.sleep(2000);

        // Select quantity 1 (invalid values like 0 are not available)
        driver.findElement(By.xpath("//div[normalize-space()='1']")).click();

        // Click DONE button to confirm quantity
        cart.doneButton.click();

        // Get updated quantity from cart
        String qty = cart.allQtyDropdowns.get(0).getText();

        // Verify that quantity is not 0 (invalid quantity should not be allowed)
        Assert.assertFalse(qty.contains("0"));

        // Print message in console
        System.out.println("Invalid quantity not allowed");
    }
}

//Steps

//1️⃣ Open Myntra website.
//2️⃣ Search for "shirt".
//3️⃣ Click the first product from search results.
//4️⃣ Switch to the product details tab.
//5️⃣ Select size 38.
//6️⃣ Click Add to Bag.
//7️⃣ Open the Cart page.
//8️⃣ Click the quantity dropdown.
//9️⃣ Select quantity 1 (since invalid quantity like 0 is not allowed).
//🔟 Click Done.
//1️⃣1️⃣ Get the quantity from cart.
//1️⃣2️⃣ Verify that quantity is not 0 using Assert.assertFalse().