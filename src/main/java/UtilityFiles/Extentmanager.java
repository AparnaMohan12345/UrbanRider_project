package UtilityFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentmanager {
	private static ExtentReports extent;
	public static ExtentReports getExtentData() {
		if(extent==null) {
			extent=new ExtentReports();
			ExtentSparkReporter reporter= new ExtentSparkReporter("D:\\Urban_extent\\reports1.html");
			extent.attachReporter(reporter);
		}
		return extent;
		}
	public static void flusReport() {
		if(extent!=null) {
			extent.flush();
		}
	}
}
