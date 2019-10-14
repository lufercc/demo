package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GETPUTStoryCommentTest {

    private String projectId;
    private String storyId;
    private String commentId;

    @BeforeTest
    public void setUp() {
        String projectName = "Project API Comments";
        String storyName = "Story1";
        String StoryComment = "First comment";
        String commitIdentifier = "comment001";
        String commitType = "github";

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
                "{\"commit_identifier\":\"" + commitIdentifier + "\",\"commit_type\": \"" + commitType + "\" , " +
                        "\"text\":\"" + StoryComment + "\" }");
        commentId = response.jsonPath().getString("id");
    }

    @Test
    public void testGETStoryComment() {
        String expectedStoryComment = "First comment";
        String expectedCommitIdentifier = "comment001";
        String expectedCommitType = "github";

        //When
        Response response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId));

        //Then
        Assert.assertEquals(response.jsonPath().getString("text"), expectedStoryComment);
        Assert.assertEquals(response.jsonPath().getString("commit_identifier"), expectedCommitIdentifier);
        Assert.assertEquals(response.jsonPath().getString("commit_type"), expectedCommitType);
    }

    @Test
    public void testPUTStoryComment() {
        String expectedUpdatedStoryComment = "Updated comment";
        String expectedCommitIdentifier = "comment001";
        String expectedCommitType = "github";

        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId),
                "{\"text\":\"" + expectedUpdatedStoryComment + "\" }");

        Assert.assertEquals(response.jsonPath().getString("text"), expectedUpdatedStoryComment);
        Assert.assertEquals(response.jsonPath().getString("commit_identifier"), expectedCommitIdentifier);
        Assert.assertEquals(response.jsonPath().getString("commit_type"), expectedCommitType);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
