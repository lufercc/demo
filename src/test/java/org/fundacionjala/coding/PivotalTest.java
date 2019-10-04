package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PivotalTest {


    @Test
    public void testPOSTProject() {

        Response response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken", "19d1e5ad024d2f6ed8f5e9e7e1c6f26a")
                .contentType(ContentType.JSON)
                .when()
                .body("{\"name\":\"Project created by Rest Assured\"}")
                .post("/projects");
        System.out.println(response.prettyPrint());
    }

}
