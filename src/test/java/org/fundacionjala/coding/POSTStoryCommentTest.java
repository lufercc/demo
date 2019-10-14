package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class POSTStoryCommentTest {

    private String projectId;
    private String storyId;

    @BeforeTest
    public void setUp() {
        String projectName = "Project POST Comment";
        String storyName = "StoryTest";

        //Given
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects", "{\"name\":\"" + projectName + "\"}");
        projectId = response.jsonPath().getString("id");

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        storyId = response.jsonPath().getString("id");
    }

    @Test
    public void testPOSTStoryComment() {
        String StoryComment = "Story comment";
        String commitIdentifier = "comment001";
        String commitType = "github";

        //When
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments", projectId, storyId),
                "{\"commit_identifier\":\"" + commitIdentifier + "\",\"commit_type\": \"" + commitType + "\" , " +
                        "\"text\":\"" + StoryComment + "\" }");

        //Then
        Assert.assertEquals(response.jsonPath().getString("text"), StoryComment);
        Assert.assertEquals(response.jsonPath().getString("commit_identifier"), commitIdentifier);
        Assert.assertEquals(response.jsonPath().getString("commit_type"), commitType);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
