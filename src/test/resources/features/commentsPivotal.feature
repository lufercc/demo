Feature: Comments
  Scenario: PUT Comment
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
    And I send a "POST" request to "/projects/{p.id}/stories/{s.id}/comments"
    """
    {
    "text":"Rest Comment Benjah Cucumber"
    }
    """
    And I save the response as "c"
    When I send a "PUT" request to "/projects/{p.id}/stories/{s.id}/comments/{c.id}"
    """
    {
    "text":"Rest Comment Updated Benjah Cucumber"
    }
    """
    Then I validate the response has status code "200"
    And I validate the response contains "text" equals "Rest Comment Updated Benjah Cucumber"
    And I send a DELETE request to "/projects/{projectId}"
    And I validate the response has status code "204"