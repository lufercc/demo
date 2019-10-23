Feature: Projects

  Scenario: PUT Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project created by cucumber"
    }
    """
    When I send a "PUT" request to "/projects/{projectId}"
    """
    {
    "name": "Project updated by cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project updated by cucumber"
    And I send a DELETE request to "/projects/{projectId}"
    And I validate the response has status code 204
