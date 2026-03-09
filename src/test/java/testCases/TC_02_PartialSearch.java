package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_02_PartialSearch extends BaseClass {

    @Test(description = "Verify that user can search product using partial keyword")
    public void verifySearchWithPartialKeyword() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("Sneak");
        hp.clickSearch();

        // Assert
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after search: " + pageTitle);

        Assert.assertTrue(pageTitle.toLowerCase().contains("sneak"),
                "Search result page not displayed");
    }
}