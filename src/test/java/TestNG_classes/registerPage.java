package TestNG_classes;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import CommonDataManage.Basic;
import UtilityFiles.Extentmanager;
import UtilityFiles.utility;
import pageObjectModels.registerPageElements;

public class registerPage extends Basic{
	public registerPage() {
	
      super();
	}
	WebDriver driver;
	registerPageElements register;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeMethod
	public void startPage() {
		extent= Extentmanager.getExtentData();
		driver=browserChoose(prop.getProperty("browser"));
		register=new registerPageElements(driver);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.findElement(By.xpath("//div[contains(@class,'close')]")).click();
		
		register.signInClick();
		
	}
	@Test
	public void customerRegistrationWithAllDetails() {
		logger= extent.createTest("Registration By filling all fields");
		try {
		register.signInData(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), utility.varyingMail(), dataprop.getProperty("password"),dataprop.getProperty("ConfirmPassword"),"Jun","2022","25");
	    Assert.assertEquals(register.signInText(), "Thank you for registering with Urban Rider.");
	    
		logger.info("All data given");
		logger.pass("Registration is Succesfull");
		}catch(Exception e) {
			logger.fail("Registration failed"+e.getMessage());

		}
	
		
	}
	@Test
     public void customerRegistrationWithMandatoryFieldsOnly() {
		logger= extent.createTest("Registration By filling mandatory fields");
		try {
		register.signinMandtory(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), utility.varyingMail(), dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"));
	    Assert.assertEquals(register.signInText(), "Thank you for registering with Urban Rider.");
	    
		logger.info("Mandatory data given");
		logger.pass("Registration is Succesfull");
		}catch(Exception e) {
			logger.fail("Registration failed"+e.getMessage());
		}
	}
	@Test
	public void customerRegistrationWithoutFillfields() {
		logger= extent.createTest("Registration Without fill fields");
		try {
		register.signinWithoutField();
		Assert.assertEquals(register.firstError(),"This is a required field.","No message is showing");
		Assert.assertEquals(register.LastError(), "This is a required field.","No message is showing");
		Assert.assertEquals(register.emailError(), "This is a required field.","No message is showing");
		Assert.assertEquals(register.passError(), "This is a required field.","No message is showing");
		Assert.assertEquals(register.confirmError(), "This is a required field.","No message is showing");
		
		logger.info("No data given");
		logger.pass("Registration not Succesfull, shows mandatory fields error");
		}catch(Exception e) {
			logger.fail("Registration failed"+e.getMessage());
		}
	}
	@Test
	public void customerRegistrationWithInvalidMail() {
		logger= extent.createTest("Registration By invalid mail id");
		try {
		register.signInData(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), dataprop.getProperty("informatEmail"), dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"),"Jun","2022","25");
		Assert.assertEquals(register.emailError(), "Please enter a valid email address (Ex: johndoe@domain.com).","No message is showing");
		
		logger.info("Invalid mail given");
		logger.pass("Registration is not Succesfull");
		}catch(Exception e) {
			logger.fail("Not showing invalid message");
		}
	}
	
	@Test
	public void customerRegistrationWithInvalidPass() {
		logger= extent.createTest("Registration By invalid password");
		try {
		register.signInData(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), utility.varyingMail(), dataprop.getProperty("informatpass"), dataprop.getProperty("informatpass"),"Jun","2022","25");
		Assert.assertEquals(register.passError(), "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.","Not showing error message");
		
		logger.info("Invalid password given");
		logger.pass("Registration is not Succesfull");
		}catch(Exception e) {
			logger.fail("Invalid password error message is not showing");
		}
	}
	@Test
	public void showPasswordCheck() {
		logger= extent.createTest("Show password checkbox verification");
		try {
		String passwordType=register.showPasswordCheck(dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"));
		Assert.assertEquals(passwordType, "text","Showpassword not working");
		
		logger.info("Click on Show password check box");
		logger.pass("Password is showing");
		}catch(Exception e) {
			logger.fail("password is not showing");
		}
	}
	
//	@Test
//	public void hyphenCheckOnDOB() {
//		register.DOBHyphenCheck(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), dataprop.getProperty("informatDOb"));
//	}
//	
	@Test
	public void confirmPasswordErrorCheck() {
		logger= extent.createTest("Confirm password input box verification");
		try {
		register.signInData(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), utility.varyingMail(), dataprop.getProperty("password"), dataprop.getProperty("Incorrectconfirmpassword"),"Jun","2022","25");
        Assert.assertEquals(register.confirmError(), "Please enter the same value again.","Not showing error message");
        
		logger.info("Given invalid confirm password data");
		logger.pass("Must show error message for invalid confirm password");
		}catch(Exception e) {
			logger.fail("Error not showing for invalid confirm password");
		}
	}
	
//	@Test
//	public void customerRegisterWithExistingData() {
//		register.signInData(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), dataprop.getProperty("Email"), dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"),"Jun","2022","25");
////		System.out.println(register.existingUserMessage());
//        Assert.assertEquals(register.existingUserMessage(), "");
//	}
	
	@Test
	public void createAccountTitleVerify() {
		logger= extent.createTest("Create account page title verification");
		try {
		
		Assert.assertEquals(register.CreateAccountTitle(), "Create New Customer Account","This is not create account page");
		
		logger.info("Account title fetch");
		logger.pass("Account title verified");
		}catch(Exception e) {
			logger.fail("Account title is wrong");
		}
		}
	
	@Test
	public void customerSignInUsingSpecialcharactername() {
		logger= extent.createTest("Registration By filling special character on name field");
		try {
		register.signInData(dataprop.getProperty("SpecialcharacterFirstName"), dataprop.getProperty("Lastname"), utility.varyingMail(), dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"),"Jun","2022","25");
        Assert.assertEquals(register.specialCharacternameCheck(), "First Name is not valid!","Not dispalying error message");
        
		logger.info("Invalid name given");
		logger.pass("Showing error for invalid name");
		}catch(Exception e) {
			logger.fail("Not showing error for invalid name");
		}
	}
	
	@Test
	public void customerSendingDOBWithoutDatepicker() {
		logger= extent.createTest("Registration By filling invalid DOB");
		try {
		register.dobEnteringwithoutPicker(dataprop.getProperty("Firstname"), dataprop.getProperty("Lastname"), dataprop.getProperty("DOB"),utility.varyingMail(), dataprop.getProperty("password"), dataprop.getProperty("ConfirmPassword"));
		Assert.assertEquals(register.dobErrorText(), "Invalid date");
		
		logger.info("Invalid date given");
		logger.pass("Showing error for invalid DOB");
		}catch(Exception e) {
			logger.fail("Not showing error for invalid DOB");
		}

	}
	
	@AfterMethod
	public void tearDown() {
		Extentmanager.flusReport();
		driver.quit();
	}
	}
