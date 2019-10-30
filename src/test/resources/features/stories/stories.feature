Feature: Endpoint /projects/{project_id}/stories
  Background: Project is created
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    Then I validate the response has status code 200

    Scenario: POST /projects/{project_id}/stories
      When I send a "POST" request to "/projects/{P.id}/stories" with json file "json/request/bodyStoriesPOST.json"
      And I save the response as "S"
      Then I validate the response has status code 200
      And I validate the response contains the values equals to json file "json/response/bodyStoriesPOST.json"
      And I send a "DELETE" request with no body to "/projects/{P.id}/stories/{S.id}"
      And I validate the response has status code 204
      And I send a DELETE request to "/projects/{P.id}"
      And I validate the response has status code 204

    Scenario: GET /projects/{project_id}/stories/{story_id}
      Given I send a "POST" request to "/projects/{P.id}/stories" with json file "json/request/bodyStoriesPOST.json"
      And I save the response as "S"
      And I validate the response has status code 200
      When I send a "GET" request with no body to "/projects/{P.id}/stories/{S.id}"
      Then I validate the response has status code 200
      And I validate the response contains the values equals to json file "json/response/bodyStoriesPOST.json"
      And I send a "DELETE" request with no body to "/projects/{P.id}/stories/{S.id}"
      And I validate the response has status code 204
      When I send a "DELETE" request with no body to "/projects/{P.id}"
      And I validate the response has status code 204

    Scenario: PUT /projects/{project_id}/stories/{story_id}
      Given I send a "POST" request to "/projects/{P.id}/stories" with json file "json/request/bodyStoriesPOST.json"
      And I save the response as "S"
      And I validate the response has status code 200
      When I send a "PUT" request to "/projects/{P.id}/stories/{S.id}" with json file "json/request/bodyStoriesPUT.json"
      And I validate the response contains the values equals to json file "json/response/bodyStoriesPUT.json"
      And I send a DELETE request to "/projects/{P.id}"
      And I validate the response has status code 204

    Scenario: DELETE /projects/{project_id}/stories/{story_id}
      Given I send a "POST" request to "/projects/{P.id}/stories" with json file "json/request/bodyStoriesPOST.json"
      And I save the response as "S"
      And I validate the response has status code 200
      When I send a "DELETE" request with no body to "/projects/{P.id}/stories/{S.id}"
      Then I validate the response has status code 204
      And I send a "GET" request with no body to "/projects/{P.id}/stories/{S.id}"
      And I validate the response has status code 404
      When I send a "DELETE" request with no body to "/projects/{P.id}"
      And I validate the response has status code 204