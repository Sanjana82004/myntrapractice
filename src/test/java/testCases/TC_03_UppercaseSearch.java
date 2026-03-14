package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class TC_03_UppercaseSearch extends BaseClass {

    @Test(description = "Verify that search works with uppercase product name")
    public void verifySearchWithUppercase() {

        // Arrange
        HomePage hp = new HomePage(driver);

        // Act
        hp.enterProduct("WOMEN SNEAKERS");
        hp.clickSearch();

        // Assert
        String pageTitle = driver.getTitle();
        System.out.println("Page Title after search: " + pageTitle);

        Assert.assertTrue(pageTitle.toLowerCase().contains("sneakers"),
                "Search result page not displayed for uppercase input");
    }
}