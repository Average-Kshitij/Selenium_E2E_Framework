package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.SignUpElements;
import resources.base;

public class SignUpPage extends base {
	
	
	Logger log = LogManager.getLogger(SignUpPage.class);
	
	//WebDriver driver = null;1
	

	
	@Test(dataProvider = "getData")
	public void signUp(String name) throws IOException, InterruptedException
	{
		
		SignUpElements sp =new SignUpElements();
		
		log.info("In SignUpPage class");
		
		log.debug("Driver initialization started");
		
		
		log.debug("Driver initalization finished");
		
		//log.info("CHECKPOINT : State of driver instance in SignUpPage class"+DriverFactory.getDriverFactoryInstance().getDriverInstance());
		log.info("CHECKPOINT : State of driver instance in SignUpPage class"+tdriver.get());
		
		Thread.sleep(5000);
		
		
		sp.getLoginButton().click();
		
		log.info("SignUpPage : Login Buttin Clicked");
	
		
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data=new Object[1][1];
		
		data[0][0]= "Kshitij";
		
		return data;
		
	}

}
