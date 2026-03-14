package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class TC15_UpdateQuantityTest extends BaseClass {

    @Test
    public void verifyQuantityUpdate() throws InterruptedException {

        HomePage home = new HomePage(driver);

        // Search product
        home.enterProduct("shirt");
        home.clickSearch();

        Thread.sleep(4000);

        // Open first product
        driver.findElement(By.cssSelector(".product-base")).click();

        Thread.sleep(3000);

        // Switch to new tab
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Product page
        SearchPageDetails product = new SearchPageDetails(driver);

        product.selectSize("38");
        product.clickAddToBag();

        Thread.sleep(3000);

        // Open cart
        driver.get("https://www.myntra.com/checkout/cart");

        Thread.sleep(4000);

        CartPage cart = new CartPage(driver);

        // Open quantity dropdown
        cart.allQtyDropdowns.get(0).click();

        Thread.sleep(2000);

        // Select 10(quantity)
        driver.findElement(By.xpath("//div[normalize-space()='5']")).click();

        // Click DONE
        cart.doneButton.click();

        Thread.sleep(3000);

        // Verify quantity updated
        String qty = cart.allQtyDropdowns.get(0).getText();

        System.out.println("Updated Quantity: " + qty);

        Assert.assertTrue(qty.contains("5"), "Quantity update failed");

        System.out.println("Quantity updated successfully");
    }
}