package org.fundacionjala.coding.benjamin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * @author Benjamin Huanca on 10/16/2019.
 * @version 1.0
 */
public class PivotalTest {
    @Test
    public void testPOSTProject(){
        Response response = RestAssured.given()
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .header("X-TrackerToken","600fff04d9c041106e24b2ee17261424")
                .contentType(ContentType.JSON)
                .when()
                .body("'{\"name\":\"Project created by Rest assured\"}'")
                .post("/projects");
        System.out.println(response.prettyPrint());
    }
}
