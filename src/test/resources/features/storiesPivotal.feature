Feature: Stories
  Scenario: PUT Story
    Given I send a "POST" request to "/projects"
    """
    {
    "name":"Rest Project Benjah Cucumber"
    }
    """
    And I save the response as "p"
    And I send a "POST" request to "/projects/{p.id}/stories"
    """
    {
    "name":"Rest Story Benjah Cucumber"
    }
    """
    And I save the response as "s"
    When I send a "PUT" request to "/projects/{p.id}/stories/{s.id}"
    """
    {
    "name":"Rest Story Updated Benjah Cucumber"
    }
    """
    Then I validate the response has status code "200"
    And I validate the response contains "name" equals "Rest Benjah Cucumber"