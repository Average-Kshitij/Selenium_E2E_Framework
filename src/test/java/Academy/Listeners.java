package Academy;


import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import resources.ExtentReporterNG;
import resources.base;

public class Listeners extends base implements ITestListener {
	
	
	Logger log = LogManager.getLogger(Listeners.class);
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestReport = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("Inside Test on start listener");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTestReport.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testName= result.getMethod().getMethodName();
		
		extentTestReport.get().log(Status.PASS,"Test Case passed");
		try {
			extentTestReport.get().addScreenCaptureFromPath(getScreenShot("\\PASS\\"+testName,tdriver.get()), testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//getScreenShot("\\PASS\\"+testName,DriverFactory.getDriverFactoryInstance().getDriverInstance());
		;
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName= result.getMethod().getMethodName();
		
		test.info(result.getThrowable().getMessage());
		extentTestReport.get().fail(result.getThrowable());
		
		try {
			extentTestReport.get().addScreenCaptureFromPath(getScreenShot("\\Fail\\"+testName, tdriver.get()), testName)
			//getScreenShot("\\Fail\\"+testName, DriverFactory.getDriverFactoryInstance().getDriverInstance());
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		try {
			log.info("In Listerner finish method");

			log.debug("Closing the driver instance");

			// DriverFactory.getDriverFactoryInstance().getDriverInstance().close();
			tdriver.get().close();

			log.debug("Driver instance closed : " + tdriver.get());

			extent.flush();
		} catch (NoSuchSessionException nss) {
			log.error(Listeners.class.getName() + ":Session is already closed");

			nss.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
