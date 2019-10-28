Feature: Epics

  Scenario: PUT Epic

    Given I send a "POST" request to "/projects" with json body
    """
    {
    "name": "Project created by cucumber"
    }
    """
    And I save the response as "P"
    And I send a "POST" request to "/projects/{P.id}/stories" with json body
    """
    {
    "name": "Story created by cucumber"
    }
    """
    And I save the response as "S"
    And I send a "POST" request to "/projects/{P.id}/epics" with json body
    """
    {
    "name": "epic created by cucumber",
    "description": "description created by cucumber"
    }
    """
    And I save the response as "E"
    And I send a "PUT" request to "/projects/{P.id}/epics/{E.id}" with json body
    """
    {
    "name": "epic updated by cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "epic updated by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204

