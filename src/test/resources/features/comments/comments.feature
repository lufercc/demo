Feature:  Endpoint: /projects/{project_id}/stories/{story_id}/comments
  Background: Story is created
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProjectPOST.json"
    And I save the response as "P"
    And I validate the response has status code 200
    When I send a "POST" request to "/projects/{P.id}/stories" with json file "json/request/bodyStoriesPOST.json"
    And I save the response as "S"
    Then I validate the response has status code 200

  Scenario: POST /projects/{project_id}/stories/{story_id}/comments
    When I send a "POST" request to "/projects/{P.id}/stories/{S.id}/comments" with json file "json/request/bodyCommentsPOST.json"
    And I save the response as "C"
    Then I validate the response has status code 200
    And I validate the response contains the values equals to json file "json/response/bodyCommentsPOST.json"
    And I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: PUT /projects/{project_id}/stories/{story_id}/comments/{comments_id}
    Given I send a "POST" request to "/projects/{P.id}/stories/{S.id}/comments" with json file "json/request/bodyCommentsPOST.json"
    And I save the response as "C"
    Then I validate the response has status code 200
    When I send a "PUT" request to "/projects/{P.id}/stories/{S.id}/comments/{C.id}" with json file "json/request/bodyCommentsPUT.json"
    Then I validate the response has status code 200
    And I validate the response contains the values equals to json file "json/response/bodyCommentsPUT.json"
    And I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: GET  /projects/{project_id}/stories/{story_id}/comments/{comments_id}
    Given I send a "POST" request to "/projects/{P.id}/stories/{S.id}/comments" with json file "json/request/bodyCommentsPOST.json"
    And I save the response as "C"
    Then I validate the response has status code 200
    When I send a "GET" request with no body to "/projects/{P.id}/stories/{S.id}/comments/{C.id}"
    Then I validate the response has status code 200
    And I validate the response contains the values equals to json file "json/response/bodyCommentsPOST.json"
    And I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: DELETE /projects/{project_id}/stories/{story_id}/comments/{comments_id}
    Given I send a "POST" request to "/projects/{P.id}/stories/{S.id}/comments" with json file "json/request/bodyCommentsPOST.json"
    And I save the response as "C"
    Then I validate the response has status code 200
    When I send a "DELETE" request with no body to "/projects/{P.id}/stories/{S.id}/comments/{C.id}"
    And I validate the response has status code 204
    And I send a "DELETE" request with no body to "/projects/{P.id}"
    And I validate the response has status code 204