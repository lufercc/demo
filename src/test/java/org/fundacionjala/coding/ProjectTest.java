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
    public void testPUTMoreParamsProject() {
        //Given
        String expectedProjectName = "Rest Assured test";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");

        String expectedNewProjectName = "Test PUT mfa";
        String expectedProjectType = "public";
        String expectedProjectDescription = "This is a test for API testing";
        String expectedProjectWeekStartDay = "Wednesday";
        String expectedProjectPointScale = "0,1,2,3,5,8";
        int expectedProjectIterationLength = 2;
        int expectedProjectAccountId = 1103421;

        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId),
                "{\"name\":\"" + expectedNewProjectName + "\"," +
                        "\"project_type\":\"" + expectedProjectType + "\"," +
                        "\"description\":\"" + expectedProjectDescription + "\"," +
                        "\"iteration_length\": " + expectedProjectIterationLength + "," +
                        "\"week_start_day\":\"" + expectedProjectWeekStartDay + "\"," +
                        "\"point_scale\":\"" + expectedProjectPointScale + "\"," +
                        "\"enable_tasks\": " + false + "," +
                        " \"account_id\": " + expectedProjectAccountId + " }");
        //Then

        actualProjectName = response.jsonPath().getString("name");
        String actualProjectType = response.jsonPath().getString("project_type");
        String actualProjectDescription = response.jsonPath().getString("description");
        String actualProjectWeekStartDay = response.jsonPath().getString("week_start_day");
        String actualProjectPointScale = response.jsonPath().getString("point_scale");
        int actualProjectIterationLength = response.jsonPath().getInt("iteration_length");
        int actualProjectAccountId = response.jsonPath().getInt("account_id");

        Assert.assertEquals(actualProjectName, expectedNewProjectName);
        Assert.assertEquals(actualProjectType, expectedProjectType);
        Assert.assertEquals(actualProjectDescription, expectedProjectDescription);
        Assert.assertEquals(actualProjectWeekStartDay, expectedProjectWeekStartDay);
        Assert.assertEquals(actualProjectPointScale, expectedProjectPointScale);
        Assert.assertEquals(actualProjectIterationLength, expectedProjectIterationLength);
        Assert.assertFalse(response.jsonPath().getBoolean("enable_tasks"));
        Assert.assertEquals(actualProjectAccountId, expectedProjectAccountId);

    }


    @Test
    public void testGetProject() {
        //Given
        String expectedProjectName = "Rest Assured to Get method";
        int expectedProjectAccountId = 1103421;

        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"," +
                        " \"account_id\": " + expectedProjectAccountId + " }");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");

        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId));

        //Then
        actualProjectName = response.jsonPath().

                getString("name");

        int actualProjectAccountId = response.jsonPath().getInt("account_id");
        Assert.assertEquals(actualProjectName, expectedProjectName);
        Assert.assertEquals(actualProjectAccountId, expectedProjectAccountId);
    }


    @Test
    public void testDeleteProject() {
        //Given
        String expectedProjectName = "Rest Assured to delete";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");

        int expectedStatus = 204;
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId));

        //Then
        int actualStatus = response.statusCode();
        Assert.assertEquals(actualStatus, expectedStatus);
    }

}
