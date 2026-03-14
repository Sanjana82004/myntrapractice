package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_04_InvalidSearch extends BaseClass {

    @Test(description = "Verify search with invalid product name containing special characters")
    public void verifyInvalidProductSearch() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("@#$Sneakers");
        hp.clickSearch();

        // Assert
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after search: " + pageTitle);

        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("myntra"));
    }
}