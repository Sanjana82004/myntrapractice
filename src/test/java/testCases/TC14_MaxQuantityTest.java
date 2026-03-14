package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

public class TC14_MaxQuantityTest extends BaseClass {

    @Test
    public void verifyIncreaseQuantityToTen() throws InterruptedException {

        HomePage home = new HomePage(driver);

        home.enterProduct("shirt");
        home.clickSearch();

        Thread.sleep(4000);

        driver.findElement(By.cssSelector(".product-base")).click();

        Thread.sleep(3000);

        // switch to product tab
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        SearchPageDetails product = new SearchPageDetails(driver);

        product.selectSize("38");
        product.clickAddToBag();

        Thread.sleep(3000);

        driver.get("https://www.myntra.com/checkout/cart");

        Thread.sleep(4000);

        CartPage cart = new CartPage(driver);

        // open quantity popup
        cart.allQtyDropdowns.get(0).click();

        Thread.sleep(2000);

        // select 10
        driver.findElement(By.xpath("//div[text()='10']")).click();

        Thread.sleep(1000);

        // click done
        cart.doneButton.click();

        // wait until quantity updates
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.textToBePresentInElement(cart.allQtyDropdowns.get(0), "10"));

        String qty = cart.allQtyDropdowns.get(0).getText();

        System.out.println("Updated Quantity: " + qty);

        Assert.assertTrue(qty.contains("10"));

        System.out.println("Quantity updated to 10 successfully");
    }
}