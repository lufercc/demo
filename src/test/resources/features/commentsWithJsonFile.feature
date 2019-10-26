Feature: Comments

  Background: Project and Story are created
    Given I send a "POST" request to "/projects" with json file "json/requestBodyProject.json"
    And I save the response as "P"
    And I send a "POST" request to "/projects/{P.id}/stories" with json file "json/requestBodyStory.json"
    And I save the response as "S"

  Scenario: POST Comment
    When I send a "POST" request to "/projects/{P.id}/stories/{S.id}/comments" with json file "json/requestBodyStoryComments.json"
    Then I validate the response has status code 200
