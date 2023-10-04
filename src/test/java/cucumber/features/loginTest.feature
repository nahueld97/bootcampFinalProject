Feature: Login
  as a user I want to log in.

  Scenario: Positive LogIn test
    Given I am on the web page
    When I type username="student" into Username field
    And I type password="Password123" into Password field
    And I pressed Submit button
    Then I would have login
    
  Scenario: Negative username test
  	Given I am on the web page
    When I type an incorrect username="incorrectUser" into Username field
    And I type password="Password123" into Password field
    And I pressed Submit button
    Then I would see an error message
  
  Scenario: Negative password test
  	Given I am on the web page
    When I type username="student" into Username field
    And I type an incorrect password="incorrectPassword" into Password field
    And I pressed Submit button
    Then I would see an error message