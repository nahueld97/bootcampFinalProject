Feature: Buy product
As a user I want to be able to add two or more products to the cart and proceed with the purchase
by placing the order.

  Scenario: Positive purchase of two phones
    Given The opened webpage = "www.demoblaze.com"
    When The product is selected
    And The "add to cart" button is pressed