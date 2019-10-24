Feature: Stories

  Scenario: PUT Story
    Given I send a "POST" request to "/projects" with Json body
    """
    {
    "name": "Project created by cucumber"
    }
    """
    And I save the response as "P"
    And I send a "POST" request to "/projects/{P.id}/stories" with Json body
    """
    {
    "name": "story created by cucumber"
    }
    """
    And I save the response as "S"
    When I send a "PUT" request to "/projects/{P.id}/stories/{S.id}" with Json body
    """
    {
    "name": "story updated by cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "story updated by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204
