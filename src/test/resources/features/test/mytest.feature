Feature: my test
#
#  Scenario: this is a test
#    Given I use the "test" service
#    And I send a GET request to "/posts/1"
#    And I validate the response has status code 200

  @tt
  Scenario: this is a test
    Given I use the "test2" service
    And I send a POST request to "/netcore_get.cgi"
    And I validate the response has status code 200