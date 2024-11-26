Feature: Create a new product
  As a user
  I want to be able to create a new product
  So that I can add it to the product list

  Scenario: Successful creation of a new product
    Given I have a product payload
    When I send a POST request to "/produtos" with the product payload
    Then I receive a 201 status code
    And the response body should match the product payload