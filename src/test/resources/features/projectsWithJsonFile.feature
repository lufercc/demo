Feature: Projects

  Scenario: PUT Project
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProject.json"
    And I save the response as "P"
    When I send a "PUT" request to "/projects/{P.id}" with json body
    """
    {
    "name": "Project updated by cucumber"
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project updated by cucumber"
    And I validate the response contains "week_start_day" equals "Friday"
    And I validate the response contains "iteration_length" equals "2"
    And I validate the response contains "public" equals "true"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204
