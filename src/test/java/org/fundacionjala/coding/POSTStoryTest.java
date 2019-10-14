package org.fundacionjala.coding;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class POSTStoryTest {

    private String projectId;

    @BeforeTest
    public void setUp() {
        String projectName = "Project POST Story";

        //Given
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects", "{\"name\":\"" + projectName + "\"}");
        projectId = response.jsonPath().getString("id");
    }

    @Test
    public void testPOSTStory() {
        String storyName = "StoryTest";
        String storyType = "bug";

        //When
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\", \"story_type\": \"" + storyType + "\"," +
                        "\"labels\":[\"story\",\"majorpriority\"]}");
        String storyId = response.jsonPath().getString("id");

        //Then
        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s", projectId, storyId));

        Assert.assertEquals(response.jsonPath().getString("name"), storyName);
        Assert.assertEquals(response.jsonPath().getList("labels.name").size(), 2);
        Assert.assertTrue(response.jsonPath().getList("labels.name").contains("story"));
        Assert.assertEquals(response.jsonPath().getString("story_type"), storyType);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
