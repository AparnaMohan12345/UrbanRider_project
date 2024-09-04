package CommonDataManage;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Basic {
	public Properties prop;
	public Properties dataprop;
	WebDriver driver;
public Basic() {
	prop= new Properties();
	File propfile= new File(System.getProperty("user.dir")+"\\src\\test\\java\\Test_Data\\Config.properties");

	try {
	FileInputStream dataread= new FileInputStream(propfile);
	prop.load(dataread);
	}catch(Throwable e) {
		e.printStackTrace();
		
		}
	dataprop= new Properties();
	File datapropfile= new File(System.getProperty("user.dir")+"\\src\\test\\java\\Test_Data\\AllOtherData.properties");
	try {
	FileInputStream AllDataRead= new FileInputStream(datapropfile);
	dataprop.load(AllDataRead);
	}catch(Throwable e) {
		e.printStackTrace();
	}
	}
public WebDriver browserChoose(String Browsername) {
	
	if (Browsername.equalsIgnoreCase("chrome")){
		driver= new ChromeDriver();
	}
	else if (Browsername.equalsIgnoreCase("firefox")){
		driver= new FirefoxDriver();
	}
	else if (Browsername.equalsIgnoreCase("edge")){
		driver= new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(prop.getProperty("URL"));

	return driver;
}


}
