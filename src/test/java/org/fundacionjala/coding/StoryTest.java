package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoryTest {

    @Test
    public void testPUTStory() {

        //Given
        String expectedProjectName = "Rest Assured new1";

        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");

        String projectId = response.jsonPath().getString("id");

        String storyName = "My Story";

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");

        String storyId = response.jsonPath().getString("id");

        // When
        String expectedNewStoryName = "New Story Name";

        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories/%s", projectId, storyId),
                "{\"name\":\"" + expectedNewStoryName + "\"}");


        Assert.assertEquals(response.jsonPath().getString("name"), expectedNewStoryName);
    }

    @Test
    public void testDeleteStory() {

        //Given
        String expectedProjectName = "Rest Assured delete";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");
        String projectId = response.jsonPath().getString("id");

        String storyName = "My Story";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        String storyId = response.jsonPath().getString("id");

        // When
        int expectedStatus = 204;
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories/%s", projectId, storyId));
        //Then
        int actualStatus = response.statusCode();
        Assert.assertEquals(actualStatus, expectedStatus);
    }

    @Test
    public void testGetStory() {

        //Given
        String expectedProjectName = "Rest Assured get";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");
        String projectId = response.jsonPath().getString("id");

        String storyName = "My Story";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        String storyId = response.jsonPath().getString("id");

        // When
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"), String.format("/projects/%s/stories/%s", projectId, storyId));
        //Then

        String actualStoryName = response.jsonPath().getString("name");
        String actualProjectId = response.jsonPath().getString("project_id");
        Assert.assertEquals(actualStoryName, storyName);
        Assert.assertEquals(actualProjectId, projectId);
    }

}
