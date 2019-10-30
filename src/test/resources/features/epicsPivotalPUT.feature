Feature: Epics

  Scenario: PUT Epic
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
    When I send a "PUT" request to "/projects/{p.id}/epics/{e.id}"
    """
    {
    "description":"new description"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "description" equals "new description"
    And I send a DELETE request to "/projects/{p.id}"
    And I validate the response has status code 204