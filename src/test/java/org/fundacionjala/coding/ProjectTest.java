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

}
