Feature:  Endpoint: /projects/{project_id}
  Scenario: GET /projects/{project_id}
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    And I validate the response has status code 200
    When I send a "GET" request with no body to "/projects/{P.id}"
    Then I validate the response has status code 200
    And I validate the response contains the values equals to json file "json/responseBodyProjectGET.json"
    When I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: POST /projects
    When I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    Then I validate the response has status code 200
    And I validate the response contains the values equals to json file "json/responseBodyProjectPOST.json"
    When I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: PUT /projects/project_id
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    And I validate the response has status code 200
    When I send a "PUT" request to "/projects/{P.id}" with json file "json/requestBodyProjectPUT.json"
    And I validate the response contains the values equals to json file "json/responseBodyProjectPUT.json"
    When I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: DELETE /projects/project_id
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    And I validate the response has status code 200
    When I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204
    When I send a "GET" request with no body to "/projects/{P.id}"
    Then I validate the response has status code 403