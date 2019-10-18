package org.fundacionjala.coding.rfalconi;

import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.fundacionjala.coding.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoryPutTest {
    // Endpoints used
    private String endpointProjects = "/projects";
    private String endpointProject = "/projects/%s";
    private String endpointStories = "/projects/%s/stories";
    private String endpointStory = "/projects/%s/stories/%s";
    private String endpointLabel = "/projects/%s/labels";

    // Setting info
    String storyName = "First Story";
    String storyType = "bug";
    String storyDescription = "This is the story description.Then a new line";
    String storyState = "delivered";

    // Data set after execute request
    private String projectId;
    private String labelId;
    private String storyId;

    // Declaring response variable
    private Response response;

    @BeforeTest
    public void createProject() {
        //Given
        // Creating new project
        String projectName = "ProjectStory3";
        String labelName = "MyLabel";

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                endpointProjects,
                "{\"name\":\"" + projectName + "\"}");
        projectId = response.jsonPath().getString("id");

        // creating new label for project
        System.out.println("ENDPOINT ::::: " + String.format(endpointLabel, projectId));
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointLabel, projectId),
                "{\"name\":\"" + labelName + "\"}");
        labelId = response.jsonPath().getString("id");

        // creating new story to update
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointStories, projectId),
                "{\"name\": \"Original story\"}");
        storyId = response.jsonPath().getString("id");
    }

    @Test
    public void updateStoryWithOnlyName() {
        //When

        Assert.assertEquals(storyName, response.jsonPath().getString("name"));
    }

    @Test
    public void updateStoryWithLabels() {
        //When
        //System.out.println("BEGIN CREATE STORY");
        String bodyRequest = "{\n" +
                "\t\"name\": \""+storyName+"\",\t\n" +
                "\t\"story_type\": \""+storyType+"\",\n" +
                "\t\"description\": \""+storyDescription+"\",\n" +
                "\t\"current_state\": \""+storyState+"\",\n" +
                "\t\"labels\": [\n" +
                "\t\t{\"id\": "+Integer.parseInt(labelId)+"}\n" +
                "\t]\n" +
                "}";
        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointStory, projectId,storyId),
                bodyRequest);
        Assert.assertEquals(storyName, response.jsonPath().getString("name"));
        Assert.assertEquals(storyType, response.jsonPath().getString("story_type"));
        Assert.assertEquals(storyDescription, response.jsonPath().getString("description"));
        Assert.assertEquals(storyState, response.jsonPath().getString("current_state"));
    }

    @AfterTest
    public void cleanData() {
        //Then
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointProject, projectId));
    }
}
