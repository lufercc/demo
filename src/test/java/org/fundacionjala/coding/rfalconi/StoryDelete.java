package org.fundacionjala.coding.rfalconi;

import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.fundacionjala.coding.RequestSpecFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoryDelete {
    // Endpoints used
    private String endpointProjects = "/projects";
    private String endpointProject = "/projects/%s";
    private String endpointStories = "/projects/%s/stories";
    private String endpointStory = "/projects/%s/stories/%s";

    // Setting info
    private String projectName = "ProjectStory3";
    private String storyName = "First Story";
    private String storyType = "bug";
    private String storyDescription = "This is the story description. Then a new line";
    private String storyState = "delivered";


    // Data set after execute request
    private String projectId;
    private String storyId;

    // Declaring response variable
    private Response response;

    @BeforeTest
    public void createProject() {
        //Given

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                endpointProjects,
                "{\"name\":\"" + projectName + "\"}");
        projectId = response.jsonPath().getString("id");

        String bodyRequest = "{\n" +
                "\t\"name\": \""+storyName+"\",\t\n" +
                "\t\"story_type\": \""+storyType+"\",\n" +
                "\t\"description\": \""+storyDescription+"\",\n" +
                "\t\"current_state\": \""+storyState+"\"\n" +
                "}";

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointStories, projectId),
                bodyRequest);

        storyId = response.jsonPath().getString("id");
    }

    @Test
    public void deleteStory() {
        //When
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointStory, projectId, storyId));

        response = RequestManager.get(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointStory, projectId, storyId)
        );

    }

    @AfterTest
    public void cleanData() {
        //Then
        response = RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format(endpointProject, projectId));
    }
}
