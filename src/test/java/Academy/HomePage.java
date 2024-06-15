package Academy;

import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageObjects.homePage;
import resources.base;

public class HomePage extends base{
	
	
	//public WebDriver driver = null;
	
	Logger log = LogManager.getLogger(HomePage.class);
	
	//Properties prop = new Properties();

	
	
	@BeforeMethod
	public void driverInitialize()
	{
		try
		{
		initialzedriver();
		
		log.info("Driver Initialised in HomePage Class");
		
		log.debug("CHECKPOINT : Satte of driver in HomePage Class : "+ tdriver.get());
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	@Test
	public void basePageNaviagtion()
	{
		
		
		try
		{
		prop.load(new FileInputStream("C:\\Users\\MY PC\\eclipse-workspace\\E2EProject\\src\\test\\java\\resources\\data.properties"));
		
		homePage h= new homePage();
		
		log.debug("Navigatin to the URL");
		
		//DriverFactory.getDriverFactoryInstance().getDriverInstance().get(prop.getProperty("URL"));
		tdriver.get().get(prop.getProperty("URL"));
		
		log.info("ULR Launched");
		
		String PageTitle= h.getxPageTitle().getAttribute("title");
		
		Assert.assertEquals(true,PageTitle.contains("Flipkart"));
	
		
		h.closeHomeWindow();
		
		log.debug("X Button Clicked");
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	


}
