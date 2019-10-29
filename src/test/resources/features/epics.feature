Feature: Epics

  Background: I create an Epic
    Given I send a "POST" request to "/projects" with json body
    """
    {
    "name": "Project created by cucumber"
    }
    """
    And I save the response as "P"
    And I send a "POST" request to "/projects/{P.id}/epics" with json body
    """
    {
    "name": "epic created by cucumber",
    "description": "description created by cucumber",
    "label":{"name":"label created by cucumber"}
    }
    """
    And I save the response as "E"

  Scenario: PUT Epic
    When I send a "PUT" request to "/projects/{P.id}/epics/{E.id}" with json body
    """
    {
    "name": "epic updated by cucumber",
    "description": "description updated by cucumber",
    "label":{"name":"label updated by cucumber"}
    }
    """
    Then I validate the response has status code 200
    And I validate the response contains "name" equals "epic updated by cucumber"
    And I validate the response contains "description" equals "description updated by cucumber"
    And I validate the component "name" from "label" equals "label updated by cucumber"
    And I validate the component "kind" from "label" equals "label"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204

  Scenario: POST Epic Comment
    When I send a "POST" request to "/projects/{P.id}/epics/{E.id}/comments" with json body
    """
    {
    "text": "comment created by cucumber"
    }
    """
    And I save the response as "C"
    Then I validate the response has status code 200
    And I validate the response contains "text" equals "comment created by cucumber"
    And I send a DELETE request to "/projects/{P.id}"
    And I validate the response has status code 204
