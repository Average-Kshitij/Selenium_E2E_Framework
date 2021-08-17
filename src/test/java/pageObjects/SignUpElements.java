package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.DriverFactory;
import resources.base;

public class SignUpElements extends base {
	
//public WebDriver driver;
	
	Logger log = LogManager.getLogger(SignUpElements.class);
	
	public SignUpElements()
	{	
		
	log.info("*************In SignUpElements Class*****************");
	
	log.debug("Initializing Pagefactory in SignupElement class");
	
	//PageFactory.initElements(DriverFactory.getDriverFactoryInstance().getDriverInstance() ,this);
	
	PageFactory.initElements(tdriver.get(),this);
	
	log.debug("Initializing Pagefactory in SignupElement class done");
	}
	
	

	@FindBy(xpath="//a[contains(text(),'Login')]")	WebElement loginButton;
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")	WebElement SignUpButton;
	
	public WebElement getLoginButton() {
		return loginButton;
	}
	public WebElement getSignUpButton() {
		return SignUpButton;
	}

	
	

}
