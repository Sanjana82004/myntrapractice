package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;


public class TC_01_SearchProductTest extends BaseClass {

    @Test(description = "Verify that user is able to search Women Sneakers on Myntra")
    public void verifyValidProductSearch() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("Women Sneakers");
        hp.clickSearch();

        // Assert
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after search: " + pageTitle);

        Assert.assertTrue(pageTitle.contains("Sneakers"), "Search result page not displayed");
    }
}