Feature:  Endpoint: /projects/{project_id}
  Scenario: GET /projects/{project_id}
    Given  I send a "POST" request to "/projects" with json body
    """
    {
    "name": "Project created by cucumber"
    }
    """
    And I save the response as "P"
    When I send a "GET" request to "/projects/{P.id}" with json body
    """
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project created by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: POST /projects
    When I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "Project created by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204