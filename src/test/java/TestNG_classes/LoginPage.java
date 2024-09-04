package TestNG_classes;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import CommonDataManage.Basic;
import UtilityFiles.utility;
import pageObjectModels.LoginPageElements;

public class LoginPage extends Basic {
	public LoginPage() {
		super();
	}
	WebDriver driver;
	LoginPageElements login;
     @BeforeMethod
     public void startTest() {
    	
    	 driver=browserChoose(prop.getProperty("browser"));
    	 login=new LoginPageElements(driver);
 		 driver.findElement(By.xpath("//div[contains(@class,'close')]")).click();

    	 login.loginRedirect();
     }
     @Test
     public void validLogintest() {
  	  
  	   login.loginTest(prop.getProperty("Email"), prop.getProperty("Password"));
   	   Assert.assertEquals(driver.getTitle(), "My Account","Login to different page");
     }
     
     @Test
     public void invalidLoginTest() {
    	 login.loginTest(utility.varyingMail(), dataprop.getProperty("InvalidPassword"));
    	 Assert.assertEquals(login.invalidCredentialsMessage(),"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.","Not showing message");
     }
     
     @Test
     public void loginUsingNullValue() {
    	 login.loginClick();
    	 Assert.assertEquals(login.nullEmailError(), "This is a required field.","No message displayed");
    	 Assert.assertEquals(login.nullPassError(), "This is a required field.","No message displayed");

     }
     
     @Test
     public void loginUsingInvalidEmail() {
    	 login.loginTest(dataprop.getProperty("InvalidUsername"), prop.getProperty("Password"));
    	 Assert.assertEquals(login.invalidCrederrors(), "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.","No message is showing");
     }
     
     @Test
     public void loginUsingInvalidPass() {
    	 login.loginTest(prop.getProperty("Email"), dataprop.getProperty("InvalidPassword"));
    	 Assert.assertEquals(login.invalidCrederrors(), "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.","No message is showing");
     }
     
     @Test
     public void showPasswordVerification() {
    	 login.enterDetails(prop.getProperty("Email"), prop.getProperty("Password"));
    	 String type=login.showpasswordCheck();
    	 Assert.assertEquals(type, "text","Not showing pqassword text");
     }
     
     @Test
     public void forgotPassVerification() {
    	 login.forgotPassCheck(prop.getProperty("Email"));
    	 Assert.assertEquals(login.forgotPassPageCheck(), "Forgot Your Password?","Page is not correct");
     }
     
     @Test
     public void numberOfTimesInvalidLoginCheck() {
    	 int count=1;
    	 while(count<=3) {
    		 login.loginTest(prop.getProperty("Email"), dataprop.getProperty("InvalidPassword"));
    		 login.clearData();
    		 count++;
    	 }
    	 String error="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
    	 if(login.invalidCrederrors().equals(error)){
    		 System.out.println("The Test is failed, Not showing security Check");
    	 }
    	 else {
    		 System.out.println("The test is passed");
    	 }
     }
     @Test
     public void showPaswordDoubleClick() {
    	 login.enterDetails(prop.getProperty("Email"), prop.getProperty("Password"));
    	 String type=login.showpasswordCheck(); 
    	 String type2=login.showpasswordCheck();
    	 Assert.assertEquals(type2, "password","paswword not hiding back");
     }
     
     @Test
     public void reopenBrowserLoginTest() {
    	 login.loginTest(prop.getProperty("Email"), prop.getProperty("Password"));
     	 Assert.assertEquals(driver.getTitle(), "My Account","Login to different page"); 
     	 driver.get(prop.getProperty("URL"));
     	 WebElement signout=driver.findElement(By.xpath("//div[@class='account-link__wrapper']/a"));   
     	Assert.assertTrue(signout.isDisplayed());
     	
     }
     
     @Test
     public void loginAfterChangePasswordLogin() {
    	 
    	 String message=login.changePassVerify(prop.getProperty("changeemail"), prop.getProperty("changepass"), prop.getProperty("Newpass"));
    	 
    	 Assert.assertEquals(message, "You saved the account information.","Password not changed");
    	 login.loginTest(prop.getProperty("changeemail"), prop.getProperty("Newpass"));
     	 Assert.assertEquals(driver.getTitle(), "My Account","Login to different page"); 
    	 
     }
     
     @Test
     public void verifyCustomerStillLoginafterClickBackButton() {
    	 login.loginTest(prop.getProperty("Email"), prop.getProperty("Password"));
     	 Assert.assertEquals(driver.getTitle(), "My Account","Login to different page"); 
     	 driver.navigate().back();
     	driver.navigate().back();
     	 WebElement signout=driver.findElement(By.xpath("//div[@class='account-link__wrapper']/a"));   
     	Assert.assertTrue(signout.isDisplayed(),"Not showing");
     }
     @AfterMethod
     public void tearDown() {

    	 
    	 driver.quit();
     }
}

