Feature: Projects
  Scenario: PUT Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name":"Rest Benjah Cucumber"
    }
    """
    And I save the response as "p"
    When I send a "PUT" request to "/projects/{p.id}"
    Then I validate the response has status code "200"
    And I validate the response contains "name" equals "Rest Benjah Cucumber"