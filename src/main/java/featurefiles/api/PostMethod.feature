Feature: Verify Post feature of an api
  Scenario Outline: user creates a new resource using POST method
    When user send a post request at "<url>" with json data "<filename>"
    Then response code should be "<statuscode>"
  Examples:
    |url|filename|statuscode|
    |/content_items.json|Story1  |201       |