Feature: Google Home Page

  Scenario: Verify google search with valid keywords
    Given user is on the google home page
    When user search some keyword
    Then user should see the search results