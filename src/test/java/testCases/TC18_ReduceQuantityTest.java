package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class TC18_ReduceQuantityTest extends BaseClass {

    @Test
    public void verifyReduceQuantity() throws InterruptedException {

        // Create object of HomePage to access search methods
        HomePage home = new HomePage(driver);

        // Enter product name in search box
        home.enterProduct("shirt");

        // Click search button
        home.clickSearch();

        // Wait for search results to load
        Thread.sleep(4000);

        // Click first product from search results
        driver.findElement(By.cssSelector(".product-base")).click();

        // Wait for product page to open
        Thread.sleep(3000);

        // Myntra opens product page in new tab, so switch to it
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Create object for product details page
        SearchPageDetails product = new SearchPageDetails(driver);

        // Select size before adding product to cart
        product.selectSize("38");

        // Click Add to Bag button
        product.clickAddToBag();

        // Wait for product to be added to cart
        Thread.sleep(3000);

        // Open cart page directly
        driver.get("https://www.myntra.com/checkout/cart");

        // Wait for cart page to load
        Thread.sleep(4000);

        // Create object of CartPage
        CartPage cart = new CartPage(driver);

        // Open quantity dropdown for first product in cart
        cart.allQtyDropdowns.get(0).click();

        Thread.sleep(2000);

        // Increase quantity to 5
        driver.findElement(By.xpath("//div[normalize-space()='5']")).click();

        // Click DONE button to confirm quantity
        cart.doneButton.click();

        Thread.sleep(2000);

        // Open quantity dropdown again to reduce quantity
        cart.allQtyDropdowns.get(0).click();

        Thread.sleep(2000);

        // Reduce quantity to 2
        driver.findElement(By.xpath("//div[normalize-space()='2']")).click();

        // Click DONE button again
        cart.doneButton.click();

        Thread.sleep(2000);

        // Get updated quantity from cart
        String qty = cart.allQtyDropdowns.get(0).getText();

        // Print quantity in console
        System.out.println("Final Quantity: " + qty);

        // Verify quantity is reduced to 2
        Assert.assertTrue(qty.contains("2"));

        // Print success message
        System.out.println("Quantity reduced successfully");
    }
}


//steps

//1️⃣ The test opens the Myntra website.
//2️⃣ It searches for "shirt".
//3️⃣ It clicks the first product from the search results.
//4️⃣ It switches to the product details tab.
//5️⃣ It selects size 38.
//6️⃣ It clicks Add to Bag to add the product to the cart.
//7️⃣ It opens the Cart page.
//8️⃣ It increases the quantity to 5.
//9️⃣ Then it reduces the quantity to 2.
//🔟 Finally, it verifies that the cart quantity is updated to 2 using Assert.