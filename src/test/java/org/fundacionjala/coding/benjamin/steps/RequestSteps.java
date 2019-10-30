package org.fundacionjala.coding.benjamin.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.fundacionjala.coding.benjamin.EndpointHelper;
import org.fundacionjala.coding.benjamin.RequestManager;
import org.fundacionjala.coding.benjamin.RequestSpecFactory;
import org.fundacionjala.coding.benjamin.ScenarioContext;
import org.testng.Assert;

/**
 * @author Benjamin Huanca on 10/23/2019.
 * @version 1.0
 */
public class RequestSteps {

    private Response response;
    private ScenarioContext context;

    public RequestSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("I send a {string} request to {string}")
    public void iSendARequestTo(final String httpMethod, final String endpoint,
                                final String jsonBody) {

        if ("POST".equalsIgnoreCase(httpMethod)) {
            response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                    EndpointHelper.endpointBuilder(context, endpoint),
                    jsonBody);
        } else {
            response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                    EndpointHelper.endpointBuilder(context, endpoint),
                    jsonBody);
        }
    }

    @Then("I validate the response has status code {int}")
    public void iValidateTheResponseHasStatusCode(int expectedStatusCode) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, expectedStatusCode);

    }

    @And("I validate the response contains {string} equals {string}")
    public void iValidateTheResponseContainsEquals(String attribute, String expectedValue) {
        String actualProjectName = response.jsonPath().getString(attribute);
        Assert.assertEquals(actualProjectName, expectedValue);
    }

    @And("I save the response as {string}")
    public void iSaveTheResponseAs(String key) {
        context.set(key, response);
    }

    @And("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(final String endpoint) {
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                EndpointHelper.endpointBuilder(context, endpoint));
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(final String endpoint) {
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"),
                EndpointHelper.endpointBuilder(context, endpoint));
    }
}
