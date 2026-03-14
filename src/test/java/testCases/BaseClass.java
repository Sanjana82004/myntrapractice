package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public WebDriver driver;
	  
	  
	  @BeforeClass
	  public void setup() {
		  driver = new ChromeDriver();
		  driver.manage().deleteAllCookies();	
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    driver.get("https://www.myntra.com/"); // reading url from properties file
		    driver.manage().window().maximize();
	  }
	   @AfterClass
	  public void tearDown() {
		  driver.quit();
	  }
	   
	   
	   public String captureScreen(String tname) throws IOException{
	    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())	;
	    	
	    	TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	    	File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	    	
	    	String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\"+tname+"_"+timeStamp+".png";
	    	File targetFile = new File(targetFilePath);
	    	FileUtils.copyFile(sourceFile, targetFile);
	    	 //sourceFile.renameTo(targetFile);
	    	return targetFilePath;
	    }
}
