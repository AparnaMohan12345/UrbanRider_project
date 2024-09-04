package pageObjectModels;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class registerPageElements {
	WebDriver driver;
	JavascriptExecutor js;
	@FindBy(xpath="//div[@class='account-link__wrapper']/a")
	private WebElement signin;
	
	@FindBy(xpath="(//div[@class='primary']/a)[1]")
	private WebElement SigninButton;
	
	@FindBy(id="firstname")
	private WebElement FirstName;
	
	@FindBy(id="lastname")
	private WebElement LastName;
	
	@FindBy(id="is_subscribed")
	private WebElement Newsletter;
	
	@FindBy(id="dob")
	private WebElement DOB;
	
	@FindBy(xpath="//div[contains(@class,'customer-dob')]/button")
	private WebElement DOBIcon;
	
	@FindBy(id="dob")
	private WebElement dobfield;
	
	@FindBy(id="assistance_allowed_checkbox")
	private WebElement Remoteshopping;
	
	@FindBy(id="email_address")
	private WebElement Email;
	
	@FindBy(id="password")
	private WebElement Password;
	
	@FindBy(id="password-confirmation")
	private WebElement ConfirmPassword;
	
	@FindBy(id="show-password")
	private WebElement ShowPassword;
	
	@FindBy(id="send2")
	private WebElement submit;
	
	@FindBy(xpath="//div[@class='messages']/div/div[contains(text(),'Thank you for registering with Urban Rider.')]")
	private WebElement message;
	
	@FindBy(id="firstname-error")
	private WebElement FirstNameError;
	
	@FindBy(id="lastname-error")
	private WebElement LastError;
	
	@FindBy(id="email_address-error")
	private WebElement EmailError;
	
	@FindBy(id="password-error")
	private WebElement PasswordError;
	
	@FindBy(id="dob-error")
	private WebElement doberror;
	
	@FindBy(id="password-confirmation-error")
	private WebElement PasswordConfirmerror;
	
	@FindBy(xpath="//div[contains(text(),' There is already an account with this email address. If you are sure that it is your email address, ')]")
	private WebElement ExistingUserError;
	
	@FindBy(xpath="//div[@class='page-title-wrapper']/h1/span")
	private WebElement CreateAccountTitle;
	
	@FindBy(xpath="//div[contains(text(),'First Name is not valid!')]")
	private WebElement specialCharacterName;
	
	
	//Actions
	public registerPageElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void signInClick() {
		signin.click();
		SigninButton.click();
	}
	public void signInData(String fname, String lname, String mail, String pass, String confirmpass,String month, String year, String date) {
		FirstName.sendKeys(fname);
		LastName.sendKeys(lname);
		Newsletter.click();
		DOBIcon.click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement calendar=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
		while(true) {
			String Currentmonth=driver.findElement(By.xpath("//select[@aria-label='Select month']/option[@selected='selected']")).getText();
			String Currentyear=driver.findElement(By.xpath("//select[@aria-label='Select year']/option[@selected='selected']")).getText();

			if(Currentmonth.equals(month)&&Currentyear.equals(year)) {
				break;	
				}
			driver.findElement(By.xpath("//a[contains(@class,'ui-corner-all')][1]")).click();
			
			}
         
         
		List<WebElement> dateclick=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td/a"));
		
		for(WebElement dt:dateclick) {
			
			if(dt.getText().equals(date)) {
				dt.click();
				break;
			}
		}
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Remoteshopping);
		Email.sendKeys(mail);
		Password.sendKeys(pass);
		ConfirmPassword.sendKeys(confirmpass);
		js.executeScript("arguments[0].click()", ShowPassword);
		js.executeScript("arguments[0].click()", submit);
		
		
	}
	public String signInText() {
		return message.getText();
		}
	
	public void signinMandtory(String fname,String lname,String mail,String pass, String confirmpass)
	{
		FirstName.sendKeys(fname);
		LastName.sendKeys(lname);
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Remoteshopping);
		Email.sendKeys(mail);
		Password.sendKeys(pass);
		ConfirmPassword.sendKeys(confirmpass);
		js.executeScript("arguments[0].click()", submit);
	}
	public void signinWithoutField() {
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", submit);
		
	}
	public String firstError() {
		return FirstNameError.getText();	
	}
    public String LastError() {
	    return LastError.getText();	
    }
    public String emailError() {
	    return EmailError.getText();	
    }
    public String passError() {
	    return PasswordError.getText();	
    }
    public String confirmError() {
	    return PasswordConfirmerror.getText();
    }
    
    public String showPasswordCheck(String pass, String confirmpass) {
    	Password.sendKeys(pass);
		ConfirmPassword.sendKeys(confirmpass);
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", ShowPassword);
		return Password.getAttribute("type");
    }
    
    public void DOBHyphenCheck(String fname, String lname,String dob) {
    	FirstName.sendKeys(fname);
		LastName.sendKeys(lname);
		Newsletter.click();
		dobfield.sendKeys("dob");
    }
    public String existingUserMessage() {
    	return ExistingUserError.getText();  
    	
    }
    
    public String CreateAccountTitle() {
    	return CreateAccountTitle.getText();	
    }
    
    public String specialCharacternameCheck()
    {
    	return specialCharacterName.getText();
    	
    	}
    
    public void dobEnteringwithoutPicker(String fname,String lname,String dateof,String mail, String pass, String confirmpass) {
    	FirstName.sendKeys(fname);
		LastName.sendKeys(lname);
		Newsletter.click();
		DOBIcon.sendKeys(dateof);
		Email.sendKeys(mail);
		Password.sendKeys(pass);
		ConfirmPassword.sendKeys(confirmpass);
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", submit);
    }
    
    public String dobErrorText() {
    	return doberror.getText();
    }
    }

