Feature: Epics

  Scenario: DELETE Epic
    Given I send a "POST" request to "/projects"
    """
    {
    "name":"Evaluation Project - Epic"
    }
    """
    And I save the response as "p"
    And I send a "POST" request to "/projects/{p.id}/epics"
    """
    {
    "name":"Epic - cucumber"
    }
    """
    And I save the response as "e"
    When I send a DELETE request to "/projects/{p.id}/epics/{e.id}"
    Then I validate the response has status code 204
    And I send a DELETE request to "/projects/{p.id}"
    And I validate the response has status code 204