package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoryTest {

    private String projectId;
    private String storyId;

    @BeforeTest
    public void setUp() {
        //Given
        String expectedProjectName = "Rest Assured PUT Story";
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectName + "\"}");
        projectId = response.jsonPath().getString("id");

        String storyName = "My Story";
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + storyName + "\"}")
                .post(String.format("/projects/%s/stories", projectId));
        storyId = response.jsonPath().getString("id");
    }

    @Test
    public void testPUTStory() {
        // When
        String expectedNewStoryName= "New Story Name";
        Response response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s", projectId, storyId),
                "{\"name\":\"" + expectedNewStoryName + "\"}");

        //Then
        Assert.assertEquals(response.jsonPath().getString("name"), expectedNewStoryName);
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
