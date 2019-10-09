package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest {

    @Test
    public void testPUTProject() {
        //Given
        String expectedProjectName = "Rest Assured new";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");
        String expectedNewProjectName = "Test PUT";
        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId),
                "{\"name\":\"" + expectedNewProjectName + "\"}");

        //Then
        actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedNewProjectName);
    }
    @Test
    public void testGETProject() {
        //Given
        String expectedProjectName = "Rest Assured newGet";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");
        String expectedWeekStartDay = "Monday";
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId));

        //Then
        String actualWeekStartDay = response.jsonPath().getString("week_start_day");
        Assert.assertEquals(actualWeekStartDay, expectedWeekStartDay);
    }

    @Test
    public void testDELETEProject() {
        //Given
        String expectedProjectName = "Rest Assured newDelete";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");
        Integer expectedDeleteResponse = 204;
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId));

        //Then
        Integer actualDeleteResponse = response.statusCode();
        Assert.assertEquals(actualDeleteResponse, expectedDeleteResponse);
    }



}
