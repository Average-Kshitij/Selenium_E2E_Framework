package resources;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	
	private DriverFactory ()
	{
		
	}
	
	
	private static DriverFactory df = new DriverFactory();
	
	public static DriverFactory getDriverFactoryInstance()
	{
		return df;
	}
	
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	
	public void setDriverInstance(WebDriver driverParam)
	{
		driver.set(driverParam);
	}
	
	public WebDriver getDriverInstance()
	{
		
		return driver.get();
	}
	
	public void closeBrowser()
	{
		driver.get().close();
		driver.remove();
	}
}
