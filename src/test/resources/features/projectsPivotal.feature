Feature: Projects
  Scenario: PUT Project
    Given I send a "POST" request to "/projects"
    """
    {
    "name":"Rest Benjah Cucumber"
    }
    """