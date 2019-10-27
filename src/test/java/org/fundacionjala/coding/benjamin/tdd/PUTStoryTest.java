package org.fundacionjala.coding.benjamin.tdd;

import io.restassured.response.Response;
import org.fundacionjala.coding.benjamin.RequestManager;
import org.fundacionjala.coding.benjamin.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Benjamin Huanca on 10/17/2019.
 * @version 1.0
 */
public class PUTStoryTest {
    @Test
    public void testPUTStory() {
        //Given
        //Step 1: Project is needed.
        String expectedProjectname = "Rest benjah API";

        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectname + "\"}");

        //Step 2: Add an story within a project.
        String projectId = response.jsonPath().getString("id");
        String storyName = "My story";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        String storyId = response.jsonPath().getString("id");

        //When
        String expectedStoryName = "story name updated";
        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s", projectId, storyId),
                "{\"name\":\"" + expectedStoryName + "\"}");

        Assert.assertEquals(response.jsonPath().getString("name"), expectedStoryName);

    }

}
