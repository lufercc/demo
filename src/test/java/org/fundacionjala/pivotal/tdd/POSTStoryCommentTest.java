package org.fundacionjala.pivotal.tdd;

import io.restassured.response.Response;
import org.fundacionjala.pivotal.RequestManager;
import org.fundacionjala.pivotal.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class POSTStoryCommentTest {

    private String projectId;
    private String storyId;

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
    }

    @Test
    public void testPOSTStoryComment() {
        //When
        String expectedStoryCommentString = "story comment";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments", projectId, storyId),
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
