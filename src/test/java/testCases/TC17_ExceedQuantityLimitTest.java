package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class TC17_ExceedQuantityLimitTest extends BaseClass {

    @Test // TestNG annotation that tells TestNG to run this method as a test
    public void verifyQuantityLimit() throws InterruptedException {

        // Creating object of HomePage class
        // This allows us to access methods like enterProduct() and clickSearch()
        HomePage home = new HomePage(driver);

        // Enter "shirt" in the search bar
        // If you want to test another product, you can change "shirt"
        home.enterProduct("shirt");

        // Click the search icon/button
        home.clickSearch();

        // Wait for search results to load
        // Not recommended in real frameworks; WebDriverWait is better
        Thread.sleep(4000);

        // Click the first product from the search results
        // .product-base is the CSS selector for product cards on Myntra
        // If Myntra changes the class name, this locator must change
        driver.findElement(By.cssSelector(".product-base")).click();

        // Wait for product page to open
        Thread.sleep(3000);

        // Myntra opens product details in a new tab
        // So we must switch the driver to the new window
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Create object for Product Details Page
        SearchPageDetails product = new SearchPageDetails(driver);

        // Select product size
        // "38" is the size value
        // If testing another size, change "38" to "M", "40", etc.
        product.selectSize("38");

        // Click "Add to Bag" button
        // This adds the product to the cart
        product.clickAddToBag();

        // Wait for item to be added to cart
        Thread.sleep(3000);

        // Directly open the cart page
        // Alternative: Click the bag icon instead of using URL
        driver.get("https://www.myntra.com/checkout/cart");

        // Wait for cart page to load
        Thread.sleep(4000);

        // Create CartPage object
        CartPage cart = new CartPage(driver);

        // Click the quantity dropdown for the first product in the cart
        // allQtyDropdowns is a list because multiple products could exist
        // get(0) means first product
        cart.allQtyDropdowns.get(0).click();

        // Wait for quantity popup (1,2,3,4,5...) to appear
        Thread.sleep(2000);

        // Select quantity = 5 from the popup
        // normalize-space() removes extra spaces
        // If the UI shows 10 instead of 5, change this to '10'
        driver.findElement(By.xpath("//div[normalize-space()='5']")).click();

        // Click DONE button to confirm the quantity selection
        // Without clicking DONE the quantity will not update
        cart.doneButton.click();

        // Wait for cart to update quantity
        Thread.sleep(2000);

        // Get the quantity text shown in the cart
        String qty = cart.allQtyDropdowns.get(0).getText();

        // Print selected quantity in console for debugging
        System.out.println("Selected Quantity: " + qty);

        // Verify quantity updated to 5
        // If quantity does not contain "5" the test will fail
        Assert.assertTrue(qty.contains("5"));

        // Print success message in console
        System.out.println("Max quantity limit applied successfully");
    }
}


//1️⃣ Open Myntra website.
//2️⃣ Search for "shirt" in the search bar.
//3️⃣ Click the first product from the search results.
//4️⃣ Switch to the product details tab.
//5️⃣ Select size 38.
//6️⃣ Click Add to Bag.
//7️⃣ Open the Cart page.
//8️⃣ Click the quantity dropdown.
//9️⃣ Select maximum quantity (5).
//🔟 Click Done.
//1️⃣1️⃣ Get the updated quantity from cart.
//1️⃣2️⃣ Verify quantity is 5 using assertion.