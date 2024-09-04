package pageObjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class searchElements {
	WebDriver driver;
	JavascriptExecutor js;
	@FindBy(xpath="//input[@name='q']")
	private WebElement Searchbox;
	
	@FindBy(xpath="(//div[@class='filter-options-title'])[1]")
	private WebElement SearchPagetext;
	
	@FindBy(xpath="//div[@class='message notice']")
	private WebElement WarningMessage;
	
	@FindBy(xpath="//button[contains(@class,'amsearch-button')][2]")
	private WebElement Searchicon;
	
	@FindBy(linkText="Customer Rating ")
	private WebElement Filter;
	
	@FindBy(xpath="//div[@class='page-title-wrapper']/h1/span")
	private WebElement SearchResult;
	
	@FindBy(xpath="(//a[@class='product-item-link'])[1]")
	private WebElement productclick;
	
	@FindBy(xpath="//div[@class='product-addto-links']")
	private WebElement wishlist;
	
	public searchElements(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchOnBox(String searchitem) {
		Searchbox.sendKeys(searchitem+Keys.ENTER);
		
	}
	public String succesfulMessage() {
		return SearchPagetext.getText();
	}
	public void searchIconVerify(String data) {
		Searchbox.sendKeys(data);
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", Searchicon);
		
		
	}
	public boolean searchWarning() {
		return WarningMessage.isDisplayed();
		
	}
	
	public String searchResulttext() {
		return SearchResult.getText();
	}
    public void clickingOnProduct() {
    	js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", productclick);
    	
    }
    public void wishListClick() {
    	js=	(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", wishlist);
    	
    }
}
