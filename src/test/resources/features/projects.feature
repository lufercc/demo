Feature: Projects

  Scenario: PUT Project
    Given I send a "POST" request to "/projects" with Json body
    """
    {
    "name": "Project created by cucumber"
    }
    """
    And I save the response as "P"
    When I send a "PUT" request to "/projects/{P.id}" with Json body
    """
    {
    "name": "Project updated by cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project updated by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204
