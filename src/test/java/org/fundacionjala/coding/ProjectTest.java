package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest {


    @Test
    public void testPUTProject() {
        //Given
        String expectedProjectName = "Rest Assured new";
        Response response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", "19d1e5ad024d2f6ed8f5e9e7e1c6f26a")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedProjectName + "\"}")
                .post("/projects");

        String actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedProjectName);

        //When
        String projectId = response.jsonPath().getString("id");
        String expectedNewProjectName = "Test PUT";
        response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", "19d1e5ad024d2f6ed8f5e9e7e1c6f26a")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedNewProjectName + "\"}")
                .put("/projects/" + projectId);

        //Then
        actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedNewProjectName);
    }

}
