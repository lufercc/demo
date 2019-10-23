package org.fundacionjala.coding.tdd;

import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.fundacionjala.coding.benjamin.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Benjamin Huanca on 10/22/2019.
 * @version 1.0
 */
public class DELETEProjectTest {
    private String projectId;
    @BeforeTest
    public void setUp(){
        //Given
        //Step 1: A Project already created is needed.
        String expectedProjectname = "Rest project API";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectname + "\"}");
        projectId = response.jsonPath().getString("id");
    }

    @Test
    public void testDELETEProject() {
        //When

        Response response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects/" + projectId);
        //Then
        Assert.assertEquals(response.getStatusCode(), 204);
        //Using Rest API
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects/" + projectId);
        Assert.assertEquals(response.jsonPath().getString("code"),"unauthorized_operation");

    }
}
