package stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.testng.asserts.SoftAssert;
import Academy.HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import pageObjects.homePage;
import resources.base;


@RunWith(Cucumber.class)
public class HomePagestepDefination extends base{
	
	Logger log;
	homePage h;
	
	SoftAssert asset = new SoftAssert();
	
	@Given("^Initialise the driver$")
	public void initialise_the_driver() throws Throwable {
		
		initialzedriver();
		
		log = LogManager.getLogger(HomePage.class);
		
		h= new homePage();
		
		log.info("Driver Initialised in HomePage Class");
		
		log.debug("CHECKPOINT : State of driver in HomePage Class : "+ tdriver.get());
    }

    @When("^Popup is present$")
    public void popup_is_present() throws Throwable {
        
    	
    	
    	asset.assertTrue(elementIsPresent(h.getCloseButton()));
    }

    @Then("^Close the the pop up window$")
    public void close_the_the_pop_up_window() throws Throwable {
        
    	h.closeHomeWindow();
    	
    	log.debug("X Button Clicked");
    }

    @And("^Navigate to the (.+)$")
    public void navigate_to_the(String url) throws Throwable {
    	
    	log.debug("Navigatin to the URL");
		
		tdriver.get().get(url);
		
		log.info("ULR Launched");
    }

    @And("^Close the browser$")
    public void close_the_browser() throws Throwable {
    	
    	asset.assertAll();
    	
//    	tdriver.get().close();
//    	
//    	log.info("Diver instance closed : " + tdriver.get());
       
    }

}
