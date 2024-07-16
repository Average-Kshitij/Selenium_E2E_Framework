package pageObjects;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import resources.base;

public class SearchObjects extends base{

	
	Logger log = LogManager.getLogger(SearchObjects.class);
	
	public SearchObjects() {
		
		log.info("*************In SignUpElements Class*****************");
		
		
		PageFactory.initElements(tdriver.get(), this);
	}
	
	
	

	@FindBy(name ="q") private WebElement we_searchBox;
	@FindBy(xpath ="//*[@name='q']/parent::div/preceding-sibling::button") private WebElement we_searchButton;
	//@FindBy(xpath ="//button[@class='L0Z3Pu' and @type='submit']") private WebElement we_searchButton;
	@FindBy(xpath ="//div[@class='_4rR01T']") private WebElement we_productNames;
	@FindBy(xpath ="//img[@title=\"Flipkart\"]") private WebElement we_flipkartImage;
	
	
	
	public WebElement getWe_flipkartImage() {
		return we_flipkartImage;
	}

	public WebElement getWe_searchBox() {
		return we_searchBox;
	}

	public WebElement getWe_searchButton() {
		
		return we_searchButton;
	}
	public WebElement getWe_productNames() {
		return we_productNames;
	}
}
