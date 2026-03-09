package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_06_EmptySearch extends BaseClass {

    @Test(description = "Verify behavior when search is performed with empty input")
    public void verifyEmptySearch() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("");
        hp.clickSearch();

        // Assert
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("myntra"),
                "Application did not handle empty search properly");
    }
}