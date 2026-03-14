package testCases;

// TestNG assertion use karne ke liye (test pass ya fail check karne ke liye)
import org.testng.Assert;

// TestNG me test method declare karne ke liye
import org.testng.annotations.Test;

// Selenium locator use karne ke liye
import org.openqa.selenium.By;

// Page Object classes import ki
import pageObjects.HomePage;
import pageObjects.SearchPageDetails;
import pageObjects.CartPage;

// Test class jo BaseClass ko extend kar rahi hai
// BaseClass me usually driver setup, browser launch aur teardown hota hai
public class TC13_IncreaseQuantityTest extends BaseClass {

    // TestNG test method
    @Test
    public void verifyIncreaseQuantity() throws InterruptedException {

        // HomePage object banaya taaki homepage ke methods use kar sake
        HomePage hp = new HomePage(driver);

        // Product details page ke actions use karne ke liye object
        SearchPageDetails product = new SearchPageDetails(driver);

        // Cart page ke elements aur methods use karne ke liye object
        CartPage cart = new CartPage(driver);

        // Search box me "shirt" enter karta hai
        // Ye method HomePage class me defined hoga
        hp.enterProduct("shirt");

        // Search button click karta hai
        hp.clickSearch();

        // Page load hone ke liye wait
        // Alternative: WebDriverWait use karna better hai
        Thread.sleep(4000);

        // Search results me first product open karta hai
        // cssSelector ".product-base" first product container ko represent karta hai
        driver.findElement(By.cssSelector(".product-base")).click();

        // Product page load hone ka wait
        Thread.sleep(3000);

        // Myntra me product new tab me open hota hai
        // Isliye driver ko new tab par switch kar rahe hain
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Product ka size select karta hai
        // Example: 38 size
        product.selectSize("38");

        // Add to bag button click karta hai
        product.clickAddToBag();

        // Cart update hone ka wait
        Thread.sleep(3000);

        // Direct cart page open kar rahe hain
        // Alternative: cart icon click kar sakte hain
        driver.get("https://www.myntra.com/checkout/cart");

        // Cart page load hone ka wait
        Thread.sleep(4000);

        // Quantity dropdown open karta hai
        // get(0) matlab cart me first product
        cart.allQtyDropdowns.get(0).click();

        // Quantity popup open hone ka wait
        Thread.sleep(2000);

        // Quantity 2 select karta hai
        // XPath popup me "2" button ko locate karta hai
        driver.findElement(By.xpath("//div[text()='2']")).click();

        // Quantity select hone ka short wait
        Thread.sleep(1000);

        // DONE button click karta hai taaki quantity confirm ho jaye
        cart.doneButton.click();

        // Cart update hone ka wait
        Thread.sleep(3000);

        // Dropdown se current quantity ka text read kar rahe hain
        String qty = cart.allQtyDropdowns.get(0).getText();

        // Console me print kar rahe hain debugging ke liye
        System.out.println("Selected Quantity: " + qty);

        // Assertion check karta hai ki quantity 2 hai ya nahi
        // Agar qty me "2" nahi hai to test fail ho jayega
        Assert.assertTrue(qty.contains("2"), "Quantity not increased to 2");
    }
}