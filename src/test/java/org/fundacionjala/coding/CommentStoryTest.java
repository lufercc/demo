package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommentStoryTest {
    @Test
    public void testCreationOfProjectWithAttributes() {
        //Given
        String expectedProjectName = "Rest Assured Project1";
        String description = "Project description test";
        String projectType = "public";
        int projectVelocity = 12;
        Boolean isPublic = true;
        String weekStartDay = "Sunday";
        Boolean isTasksEnabled = false;
        Boolean isEmailEnabled = false;

        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"," +
                        "\"description\": \"" + description +"\"," +
                        "\"project_type\": \"" + projectType +"\"," +
                        "\"public\": " + isPublic + "," +
                        "\"initial_velocity\": " + projectVelocity + "," +
                        "\"week_start_day\": \"" + weekStartDay + "\", " +
                        "\"enable_tasks\": " + isTasksEnabled + "," +
                        "\"enable_incoming_emails\": " + isEmailEnabled +"}");

        String projectId = response.jsonPath().getString("id");

        //verification of project attributes from response
        Assert.assertEquals(response.jsonPath().getString("name"), expectedProjectName);
        Assert.assertEquals(response.jsonPath().getString("description"), description);
        Assert.assertEquals(response.jsonPath().getString("project_type"), projectType);
        Assert.assertEquals(response.jsonPath().getInt("initial_velocity"), projectVelocity);
        Assert.assertTrue(response.jsonPath().getBoolean("public"));
        Assert.assertEquals(response.jsonPath().getString("week_start_day"), weekStartDay);
        Assert.assertFalse(response.jsonPath().getBoolean("enable_tasks"));
        Assert.assertFalse(response.jsonPath().getBoolean("enable_incoming_emails"));

        //delete project
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("/projects/%s", projectId));
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void testStoryUpdate() {
        String projectName = "Rest Assured Project2";
        String storyName = "Story1";
        String storyType = "feature";

        //Given
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + projectName + "\"}");
        String projectId = response.jsonPath().getString("id");

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\", \"story_type\": \"" + storyType + "\"}");
        String storyId = response.jsonPath().getString("id");

        //When
        String expectedNewStoryName= "Updated Story Name";
        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s", projectId, storyId),
                "{\"name\":\"" + expectedNewStoryName + "\" , \"labels\":[\"story\",\"majorpriority\"]}");
        Assert.assertEquals(response.jsonPath().getString("name"), expectedNewStoryName);
        Assert.assertEquals(response.jsonPath().getList("labels.name").size(), 2);
        Assert.assertTrue(response.jsonPath().getList("labels.name").contains("story"));
        Assert.assertEquals(response.jsonPath().getString("story_type"), storyType);

        //delete project
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("/projects/%s", projectId));
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void testStoryComment() {
        String projectName = "Rest Assured Project3";
        String storyName = "Story1";

        //Given
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects", "{\"name\":\"" + projectName + "\"}");
        String projectId = response.jsonPath().getString("id");

        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories", projectId),
                "{\"name\":\"" + storyName + "\"}");
        String storyId = response.jsonPath().getString("id");

        //When
        //Add comment
        String expectedStoryComment = "First comment";
        String commitIdentifier = "comment001";
        String commitType = "github";
        response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments", projectId, storyId),
                "{\"commit_identifier\":\"" + commitIdentifier + "\",\"commit_type\": \"" + commitType + "\" , " +
                        "\"text\":\"" + expectedStoryComment + "\" }");
        String commentId = response.jsonPath().getString("id");

        //update comment
        String expectedUpdatedStoryComment = "Updated comment";
        response = RequestManager.put(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId),
                "{\"text\":\"" + expectedUpdatedStoryComment + "\" }");

        //verification of configured attributes from GET response
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId));

        Assert.assertEquals(response.jsonPath().getString("text"), expectedUpdatedStoryComment);
        Assert.assertEquals(response.jsonPath().getString("commit_identifier"), commitIdentifier);
        Assert.assertEquals(response.jsonPath().getString("commit_type"), commitType);

        //delete comment
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("/projects/%s/stories/%s/comments/%s", projectId, storyId, commentId));
        response.then().assertThat().statusCode(204);

        //delete project
        response = RestAssured.given(RequestSpecFactory.getRequestSpec("pivotal"))
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("/projects/%s", projectId));
        response.then().assertThat().statusCode(204);
    }
}
