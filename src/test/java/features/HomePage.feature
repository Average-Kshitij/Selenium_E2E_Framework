Feature: Home page of application

@login
Scenario Outline: To check whether home page is getting scheduled and window is handeled
Given Initialise the driver
And Navigate to the <URL>
#When Popup is present
#Then Close the the pop up window
#And Close the browser

Examples:
|URL		 									|
|https://www.flipkart.com |