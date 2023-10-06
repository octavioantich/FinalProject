@tag
Feature: Login
	As a user
	I want to be able to add two or more products to the cart 
	and proceed with the purchase by placing the order

@tag
Scenario: Positive LogIn test
	Given Open the shop page
	When I add the first 2 products to the cart and place the order
	Then A message with a Thank you for your purchase! text and button OK is displayed
