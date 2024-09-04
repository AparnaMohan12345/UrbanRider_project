package pageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.util.Assert;
;

public class LoginPageElements {
	 WebDriver driver;
		JavascriptExecutor js;

	
	@FindBy(xpath="//div[@class='account-link__wrapper']/a")
	private WebElement signin;
	
	@FindBy(id="email")
    private  WebElement Email;
	
	@FindBy(id="pass")
	private  WebElement Password;
	
	@FindBy(id="show-password")
	private WebElement ShowPassword;
	
	@FindBy(id="send2")
	private WebElement Login;
	
	@FindBy(xpath="(//div[@class='secondary']/a)[1]")
	private WebElement ForgotPass;
	
	
	
	@FindBy(xpath="//div[contains(text(),'try again later')]")
	private WebElement invalidcredentialserror;
	
	@FindBy(xpath="//div[contains(text(),'A login and a password are required.')]")
	private WebElement Nullerror;
	
	@FindBy(id="email-error")
	private WebElement Emailerror;
	
	@FindBy(id="pass-error")
	private WebElement passerror;
	
	@FindBy(xpath="//div[contains(text(),'disabled temporarily')]")
	private WebElement invalidemailorpass;
	
	@FindBy(xpath="//div[@class='page-title-wrapper']/h1/span")
	private WebElement ForgotPassPage;
	
	@FindBy(xpath="//div[contains(text(),'account information.')]")
	private WebElement ConfirmChangePass;
	
	@FindBy(xpath="//a[contains(@class,'change-password')]")
	private WebElement ChangePass;
	
	@FindBy(id="current-password")
	private WebElement confirmpass;
	
	@FindBy(id="password")
	private WebElement Newpass;
	
	@FindBy(id="password-confirmation")
	private WebElement Newpassconfirm;
	
	@FindBy(xpath="(//div[@class='primary']/button)[1]")
	private WebElement Submitpass;
	
	public LoginPageElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	
	public void loginRedirect() {
		signin.click();
	}
	public void loginTest(String email, String password ) {
		Email.sendKeys(email);
		Password.sendKeys(password);
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Login);
		
	}
	
	public String invalidCredentialsMessage() {
		return invalidcredentialserror.getText();	
		}
	
	public String nullEmailError() {
		
		return Emailerror.getText();		
	}
	
	public String nullPassError() {
		return passerror.getText();	}
	
	public void loginClick() {
		Login.click();
	}
	
	public String invalidCrederrors() {
		return invalidemailorpass.getText();
		}
	
	public String showpasswordCheck() {
		
		ShowPassword.click();
		return Password.getAttribute("type");
	}
	
	public void enterDetails(String email, String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
	}
	
	public void forgotPassCheck(String email) {
		Email.sendKeys(email);
		ForgotPass.click();
		
	}
	
	public String forgotPassPageCheck() {
		return ForgotPassPage.getText();	
		}
	public void clearData() {
		Email.clear();
		Password.clear();
	}
	
	public String changePassVerify(String email, String pass, String newpass) {
		Email.sendKeys(email);
		Password.sendKeys(pass);
		js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Login);
		ChangePass.click();
		confirmpass.sendKeys(pass);
		Newpass.sendKeys(newpass);
		Newpassconfirm.sendKeys(newpass);
		js.executeScript("arguments[0].click()", Submitpass);
		 return ConfirmChangePass.getText();
		
	}
	
	public String signinPresentCheck() {
		return signin.getText();
	}
}




