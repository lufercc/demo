package org.fundacionjala.coding.benjamin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.fundacionjala.coding.RequestSpecFactory;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

/**
 * @author Benjamin Huanca on 10/16/2019.
 * @version 1.0
 */
public class projectTest {

    @Test
    public void testPUTProject() {
        //Given
        String expectedProjectname = "Rest benjah";
        Response response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", "b903d2286c233a34c2fc3412c25e47dd")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedProjectname + "\"}")
                .post("/projects");
        String actualProjectName = response.jsonPath().getString("name");
        //System.out.println(response.prettyPrint());
        Assert.assertEquals(actualProjectName, expectedProjectname);
        //When
        String projectId = response.jsonPath().getString("id");
        String expectedNewProjectname = "Rest benjah updated";
        response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", "b903d2286c233a34c2fc3412c25e47dd")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"" + expectedNewProjectname + "\"}")
                .put("/projects/" + projectId);
        //Then
        actualProjectName = response.jsonPath().getString("name");
        Assert.assertEquals(actualProjectName, expectedNewProjectname);
    }
}
