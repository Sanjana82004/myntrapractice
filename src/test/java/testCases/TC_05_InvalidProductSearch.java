package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_05_InvalidProductSearch extends BaseClass {

    @Test(description = "Verify search with non-existing product name")
    public void verifyInvalidProductSearch() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("Flying Shose 999");
        hp.clickSearch();

        // Assert
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("myntra"),
                "Search page did not load properly");
    }
}