package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PUTProjectTest {

    private String projectId;

    @BeforeTest
    public void setUp() {
        //Given
        String expectedProjectName = "Rest Assured new 1";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");
        projectId = response.jsonPath().getString("id");
    }

    @Test
    public void testPUTProject() {
        //When
        String expectedNewProjectName = "Test PUT 1";
        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId),
                "{\"name\":\"" + expectedNewProjectName + "\"}");

        //Then
        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedNewProjectName);
    }


    @Test
    public void testPUTMoreParamsProject() {
        //When

        String expectedNewProjectName = "Test PUT mfa";
        String expectedProjectType = "public";
        String expectedProjectDescription = "This is a test for API testing";
        String expectedProjectWeekStartDay = "Wednesday";
        String expectedProjectPointScale = "0,1,2,3,5,8";
        int expectedProjectIterationLength = 2;
        int expectedProjectAccountId = 1103421;

        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s", projectId),
                "{\"name\":\"" + expectedNewProjectName + "\"," +
                        "\"project_type\":\"" + expectedProjectType + "\"," +
                        "\"description\":\"" + expectedProjectDescription + "\"," +
                        "\"iteration_length\": " + expectedProjectIterationLength + "," +
                        "\"week_start_day\":\"" + expectedProjectWeekStartDay + "\"," +
                        "\"point_scale\":\"" + expectedProjectPointScale + "\"," +
                        "\"enable_tasks\": " + false + "," +
                        " \"account_id\": " + expectedProjectAccountId + " }");
        //Then

        String actualProjectName = response.jsonPath().getString("name");
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

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }

}
