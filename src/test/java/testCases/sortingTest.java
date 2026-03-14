package testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.sortingPage;

public class sortingTest extends BaseClass {
	HomePage hp ;
	SearchPage sp;
	sortingPage st;
	public WebDriverWait wait;
	@BeforeMethod()
	public void pageSetup() {
		hp = new HomePage(driver);
		sp = new SearchPage(driver);
		st = new sortingPage(driver);
		hp.clickMenMenu();
		st.enterProduct("shirt");
		st.clickSearch();
	}
	
	@Test(priority = 1)
	public void testSortLowToHigh() {
		
		st.sortLowToHigh();
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<Integer>actualPrices = st.getFirstFivePrices();
		System.out.println(  "actual price is :"+ actualPrices);
		List<Integer> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices);
		System.out.println("sorted prices is"+sortedPrices);
		Assert.assertEquals(sortedPrices, actualPrices);
		
	}
	
	
	@Test(priority = 2)
	public void testSortHighToLow() {
		st.sortHighToLow();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		List<Integer> actualPrices = st.getFirstFivePrices();
		System.out.println(  "actual price is :"+ actualPrices);
		List<Integer> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices,Collections.reverseOrder());
		System.out.println("sorted prices is"+sortedPrices);
		Assert.assertEquals(actualPrices, sortedPrices);
		
	}
	
	
	@Test(priority = 3)
	public void testCaptureFirstfive() {
		List<Integer>prices = st.getFirstFivePrices();
		System.out.println("price is :" + prices);
		Assert.assertEquals(prices.size(), 5);
	} 
    
	
	@Test(priority = 4)
	
	public void testPriceFormatAfterSort() {
		List<Integer> prices = st.getFirstFivePrices();
		for(Integer price : prices) {
			Assert.assertTrue(price>0);
		}
	} 
	
	@Test(priority = 5)
	
	public void testSortDropdownNotClickable() {
		try {
			st.clickSort();
			Assert.assertTrue(true);
		} catch(Exception e) {
		Assert.fail("drop down is not interactable")	;
		}
	}
	
	@Test(priority = 6)
	public void testSortingMismatch() {
	List<Integer>prices= st.getFirstFivePrices() ; 
	Assert.assertFalse(prices.get(0)<prices.get(4));
		
	} 
	
	@Test(priority = 7)
	
	public void testDynamicLoadingInterruption() {
		st.sortLowToHigh();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		List<Integer>prices = st.getFirstFivePrices();
		Assert.assertNotNull(prices);
	} 
	
	
	@Test(priority = 8)
	
	public void testPriceParsingError() {
		List<Integer> prices = st.getFirstFivePrices();
		for(Integer p : prices) {
			Assert.assertNotSame(p,0);
		}
	} 
	
}
