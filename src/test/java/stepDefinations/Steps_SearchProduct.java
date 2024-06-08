package stepDefinations;


import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.SearchObjects;
import resources.base;

public class Steps_SearchProduct extends base {

	SearchObjects so;
	SoftAssert asset = new SoftAssert();
	
	@When("^Search box is persent on the page\\.$")
	public void search_box_is_persent_on_the_page() throws Throwable {
	    
		elementIsPresent(so.getWe_searchBox());
	}

	@Then("^Check the info message in the search box\\.$")
	public void check_the_info_message_in_the_search_box() throws Throwable {
	    
		String searchText = so.getWe_searchBox().getAttribute("placeholder");
	    
		asset.assertEquals(searchText, "Search for products, brands and more");
	}

	@Then("^Enter the (.+) in the search box\\.$")
	public void enter_the_Mobile_in_the_search_box(String prodName) throws Throwable {
	    
		searchProduct(prodName, so.getWe_searchBox());
	    
	}

	@Then("^Click on seach button\\.$")
	public void click_on_seach_button() throws Throwable {
	    
		so.getWe_searchButton().click();
	}

	@Then("^Verify the product displayed on the search\\.$")
	public void verify_the_product_displayed_on_the_search() throws Throwable {
		
		List searchedPoducts =  new ArrayList<String>();
		
//		searchedPoducts =  so.getWe_productNames().findElements();
	    
	}

}
