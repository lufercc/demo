Feature: Comments

  Scenario: POST Comments related to a project by ID
    Given I send a "POST" request to "/projects"
    """
    {
    "name": "Project Cucumber"
    }
    """
    And  I send a POST formed request to "/projects/{projectId}/stories"
    """
    {
    "name": "Story created in specific project"
    }
    """
    When I send a POST request to "/projects/{project_id}/stories/{story_id}/comments" formed by two ids
    """
    {
    "text": "Comment created in a specific story of specific project"
    }
    """
    Then I validate the response has status code "200"
