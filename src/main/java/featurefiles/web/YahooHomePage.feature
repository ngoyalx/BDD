Feature: Yahoo Home Page

  @web
  Scenario: Verify google search with valid keywords
    Given user is on the yahoo home page
    When user search some keyword
    Then user should see the search results