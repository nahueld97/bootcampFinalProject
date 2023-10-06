Feature: Buy product
As a user I want to be able to add two or more products to the cart and proceed with the purchase
by placing the order.

  Scenario: Positive purchase of two items
    Given The opened webpage = "https://www.demoblaze.com/"
    When Add first product to the cart
    And Back to homepage
    And Add second product to the cart
    And Go to the cart
    And Purchase the selected items
    Then A successfull message should be showed
    And The correct information must be showed