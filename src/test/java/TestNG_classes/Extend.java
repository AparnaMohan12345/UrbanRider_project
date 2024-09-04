package TestNG_classes;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extend {
	public static final ExtentReports extentreports = new ExtentReports();
	
	@BeforeClass
	public synchronized static ExtentReports createReports() {
		ExtentSparkReporter reporter= new ExtentSparkReporter("D:\\Urban_extent\\reports1.html");
		reporter.config().setReportName("UrbanRider");
		extentreports.attachReporter(reporter);
		extentreports.setSystemInfo("Test Name", "Test1");
		extentreports.setSystemInfo("Test Created By", "Aparna Mohan");
		
		return extentreports;
	}
	
	@AfterClass
	public static void endTest() {
		extentreports.flush();
	}

}
