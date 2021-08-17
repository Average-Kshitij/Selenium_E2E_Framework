package pageObjects;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.SignUpPage;
import resources.DriverFactory;
import resources.base;

public class homePage extends base{
	

	Logger log = LogManager.getLogger(homePage.class);
	
	public homePage()
	{

		
		log.info("In homePageObject class");
		
		log.info("CHECKPOINT : State of driver instance in homePageOject class : "+tdriver.get());
		
		log.debug("PageFactory initailiaztion started");
		
		//PageFactory.initElements(DriverFactory.getDriverFactoryInstance().getDriverInstance() ,this);
		
		PageFactory.initElements(tdriver.get(),this);
		
		log.debug("PageFactory initailiaztion done");
		
	}
	

	@FindBy(xpath="//button[@class='_2KpZ6l _2doB4z']")
	WebElement xButton;
	
	@FindBy(xpath="//img[@title='Flipkart']")
	WebElement xPageTitle;
	
	public void closeHomeWindow()
	{
		
		
		log.debug("Clicking the 'X' button after landing on homepage"+" Webelement xButton : "+xButton.toString());
		
		xButton.click();
		
	}
	
	public WebElement getxPageTitle()
	{
		return xPageTitle;
	}

}
