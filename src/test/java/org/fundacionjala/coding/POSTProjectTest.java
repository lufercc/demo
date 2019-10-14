package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class POSTProjectTest {

    private String projectId;

    @Test
    public void testCreationOfProject() {
        String expectedProjectName = "Project POST Project";
        String description = "Project description test";
        String projectType = "public";
        int projectVelocity = 12;
        Boolean isPublic = Boolean.TRUE;
        String weekStartDay = "Sunday";
        Boolean isTasksEnabled = Boolean.FALSE;
        Boolean isEmailEnabled = Boolean.FALSE;

        //When
        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"), "/projects",
                "{\"name\":\"" + expectedProjectName + "\"," +
                        "\"description\": \"" + description +"\"," +
                        "\"project_type\": \"" + projectType +"\"," +
                        "\"public\": " + isPublic + "," +
                        "\"initial_velocity\": " + projectVelocity + "," +
                        "\"week_start_day\": \"" + weekStartDay + "\", " +
                        "\"enable_tasks\": " + isTasksEnabled + "," +
                        "\"enable_incoming_emails\": " + isEmailEnabled +"}");
        projectId = response.jsonPath().getString("id");

        //Then
        Assert.assertEquals(response.jsonPath().getString("name"), expectedProjectName);
        Assert.assertEquals(response.jsonPath().getString("description"), description);
        Assert.assertEquals(response.jsonPath().getString("project_type"), projectType);
        Assert.assertEquals(response.jsonPath().getInt("initial_velocity"), projectVelocity);
        Assert.assertTrue(response.jsonPath().getBoolean("public"));
        Assert.assertEquals(response.jsonPath().getString("week_start_day"), weekStartDay);
        Assert.assertFalse(response.jsonPath().getBoolean("enable_tasks"));
        Assert.assertFalse(response.jsonPath().getBoolean("enable_incoming_emails"));
    }

    @AfterTest
    public void cleanData() {
        RequestManager.delete(RequestSpecFactory.getRequestSpec("pivotal"),
                String.format("/projects/%s", projectId));
    }
}
