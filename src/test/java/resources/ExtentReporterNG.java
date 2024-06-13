package resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	
	public static  ExtentReports getReportObject()
	{
	String path = System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Web AUtomation Result");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().enableOfflineMode(true);
	
	
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Kshitij Sawant");
	
	
	return extent;
	
	
	}
}
