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
    """
    {
    "name":"Rest Updated Benjah Cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Rest Updated Benjah Cucumber"
    And I send a DELETE request to "/projects/{p.id}"
    And I validate the response has status code 204