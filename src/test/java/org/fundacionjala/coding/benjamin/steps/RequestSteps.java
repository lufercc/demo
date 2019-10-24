package org.fundacionjala.coding.benjamin.steps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.fundacionjala.coding.benjamin.RequestManager;
import org.fundacionjala.coding.benjamin.RequestSpecFactory;

/**
 * @author Benjamin Huanca on 10/23/2019.
 * @version 1.0
 */
public class RequestSteps {
private String projectId;
    @Given("I send a {string} request to {string}")
    public void iSendARequestTo(String httpMethod, String endpoint, String jsonBody) {
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                endpoint,
                jsonBody);
        projectId = response.jsonPath().getString("id");
    }

}
