Feature: Search Product feature for flipkart

@search @smoke
Scenario Outline: Enter a product name and click on search button.
    Given Initialise the driver
    And Navigate to the <URL>
    #When Popup is present
    #Then Close the the pop up window
    When Search box is persent on the page.
    Then Check the info message in the search box.
    And Enter the <Product Name> in the search box.
    And Click on search button.
    Then Verify the product displayed on the search.

    Examples: 
      | Product Name | URL                       |
      | Mobile       | https://www.flipkart.com/ |
      | Trimmer      | https://www.flipkart.com/ |
