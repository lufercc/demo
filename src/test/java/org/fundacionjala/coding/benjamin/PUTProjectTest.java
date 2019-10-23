package org.fundacionjala.coding.benjamin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Benjamin Huanca on 10/16/2019.
 * @version 1.0
 */
public class PUTProjectTest {
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
    public void testPUTProject() {
        //When
        String expectedNewProjectname = "Rest benjah updated";
        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects/" + projectId,
                "{\"name\":\"" + expectedNewProjectname + "\"}");
        //Then
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedNewProjectname);
    }
    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects/" + projectId);
    }
}
