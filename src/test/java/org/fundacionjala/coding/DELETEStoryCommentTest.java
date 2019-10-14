package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DELETEStoryCommentTest {
    private String projectId;
    private String storyId;
    private String commentId;

    @BeforeTest
    public void setUp() {
        String projectName = "Project DELETE Comment";
        String storyName = "Story1";
        String StoryComment = "FirstStoryComment";

        //Given
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects", "{\"name\":\"" + projectName + "\"}");
        projectId = response.jsonPath().getString("id");

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        storyId = response.jsonPath().getString("id");

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments", projectId, storyId),
                "{\"text\":\"" + StoryComment + "\" }");
        commentId = response.jsonPath().getString("id");
    }

    @Test
    public void testDELETEStoreComment() {
        //When
        Response response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId));

        //Then
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);

    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
