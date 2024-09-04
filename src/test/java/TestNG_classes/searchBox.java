package TestNG_classes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonDataManage.Basic;
import UtilityFiles.utility;
import pageObjectModels.LoginPageElements;
import pageObjectModels.searchElements;

public class searchBox extends Basic {
	
     public searchBox() {
    	 super();
     }
     WebDriver driver;
     searchElements search;
     LoginPageElements login;
     @BeforeMethod
     public void startSite() {;
    	 driver=browserChoose(prop.getProperty("browser"));
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    	 driver.findElement(By.xpath("//div[contains(@class,'close')]")).click();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    	 search= new searchElements(driver);
    	 login=new LoginPageElements(driver);
     }
     @Test
     public void validateWithExistingProduct() {
    	 search.searchOnBox(dataprop.getProperty("Searchword"));
    	 String text=search.succesfulMessage();
    	 Assert.assertEquals(text, "PRODUCT TYPE","Not landed on search page");
     }
   
     @Test
     public void warningMessageForInvalidProduct() {
    	 search.searchOnBox(dataprop.getProperty("InvalidProduct")); 
    	 boolean message=search.searchWarning();
    	 Assert.assertEquals(message, true,"No message showing for invalid product");
     }
     
     @Test
     public void verifyUsingNoProduct() {
    	 String title= driver.getTitle();
    	 search.searchOnBox(dataprop.getProperty("NoProduct")); 
    	 String SearchAfterTitle =driver.getTitle();
    	 Assert.assertEquals(title,SearchAfterTitle,"Redirect to search page" );
    	
     }
     
     @Test
     public void verifySearchIconWorks() {
    	 search.searchIconVerify(dataprop.getProperty("Searchword"));
    	 String text=search.succesfulMessage();
    	 Assert.assertEquals(text, "PRODUCT TYPE","Not landed on search page");
     }
     
     @Test
     public void verifyUsingGenderProducts() {
    	 search.searchOnBox(dataprop.getProperty("GenderProduct"));
    	 String result=search.searchResulttext();
    	 if(result.contains("dataprop.getProperty(\"GenderProduct\")")) {
    		 System.out.println("Search is success");
    	 }
    	 else {
    		 System.out.println("Search is not success");
    	 }
    	 }
     
     @Test
     public void verifyWishList() {
    	 login.loginRedirect();
    	 login.loginTest(prop.getProperty("Email"), prop.getProperty("Password"));
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    	 search.searchOnBox(dataprop.getProperty("Searchword"));
//    	 driver.findElement(By.xpath("//div[contains(@class,'close')]")).click();
    	 search.clickingOnProduct();
    	 search.wishListClick();
     }
     
     @AfterMethod
     public void tearDown() {

    	 
    	 driver.quit();
     }
     
     
     
}
