package testCases;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.SearchPageDetails;
import utilities.ExcelReporter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ExternalData extends BaseClass{

	
	HomePage home ;
	SearchPage search ;
	SearchPageDetails productDetails;
	CartPage cart ;
	
	
	
	@BeforeMethod
    public void initPages() {
        
        home = new HomePage(driver);
        search = new SearchPage(driver);
        productDetails = new SearchPageDetails(driver);
        cart = new CartPage(driver);
    }
@Test
public void verifyEmptyCartWithReporting() {
	
    int row = 1; 
    String parentWindow = driver.getWindowHandle();
    try {
      
        

        
        List<String> productList = Files.readAllLines(Paths.get("src/test/resources/products.txt"));
        
        
        for (String productName : productList) {
            home.enterProduct(productName);
            home.clickSearch();
            search.clickFirstProduct(driver);
            
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            productDetails.selectSize("7");
            productDetails.clickAddToBag();
            driver.close();
            driver.switchTo().window(parentWindow);
           // driver.get("https://www.myntra.com/")  ;     
            }
         home.clickCartIcon();

       
        int initialCount = cart.individualItemCloseIcons.size();
        
        for (int i = 0; i < initialCount; i++) {
            cart.removeAllItemsOneByOne(); 
            
            
            String status = "PASS"; 
            ExcelReporter.recordResult( "TC_01_" + (i+1), "Remove item ", "Removed" ,"Success",
                                      status); 
        } 

        
        Assert.assertTrue(cart.emptyCartMainMessage.isDisplayed(), "Cart empty nahi hui!");
        ExcelReporter.recordResult("TC_FINAL", "Verify Empty Cart UI", 
                                 "Visible", "Validated", "PASS"); 

    } catch (Exception e) {
       
        ExcelReporter.recordResult("TC_ERROR", "Error Execution", 
                                 "Should work", e.getMessage(), "FAIL"); 
        Assert.fail("Test failed due to: " + e.getMessage());
    }
}
} 