package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PUTStoryCommentTest {

    private String projectId;
    private String storyId;
    private String storyCommentId;

    @BeforeTest
    public void setUp() {
        //Given
        String expectedProjectName = "Test Story project";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");
        projectId = response.jsonPath().getString("id");

        //And
        String expectedStoryString = "story string";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + expectedStoryString + "\"}");
        storyId = response.jsonPath().getString("id");

        //And
        String expectedStoryCommentString = "story comment";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments", projectId, storyId),
                "{\"text\":\"" + expectedStoryCommentString + "\"}");
        storyCommentId = response.jsonPath().getString("id");
    }

    @Test
    public void testPUTStoryComment() {
        //When
        String expectedStoryCommentString = "updated comment";
        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, storyCommentId),
                "{\"text\":\"" + expectedStoryCommentString + "\"}");

        //Then
        String actualStoryCommentString = response.jsonPath().getString("text");
        Assert.assertEquals(actualStoryCommentString, expectedStoryCommentString);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
