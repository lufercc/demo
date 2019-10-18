package org.fundacionjala.coding.tdd;

import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.fundacionjala.coding.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DELETEStoryCommentTest {

    private String projectId;
    private String storyId;
    private String storyCommentId;
    private int statusCode;

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
    public void testDELETEStoryComment() {
        //When
        String expectedStoryCommentString = "story comment";
        Response response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, storyCommentId));

        //Then
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
