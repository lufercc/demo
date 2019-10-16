Feature: Stories

  Scenario: POST Stories related to a project by ID
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project Cucumber Stories"
    }
    """
    When I send a POST formed request to "/projects/{projectId}/stories"
    """
    {
    "name": "Story created by cucumber"
    }
    """
    Then I validate the response has status code "200"
    And I validate the response contains "name" equals "Story created by cucumber"
    And I send a DELETE request to "/projects/{projectId}" and "project_id"
    And I validate the response has status code "204"

    
    