package org.fundacionjala.coding.benjamin;

import io.restassured.response.Response;
import org.fundacionjala.coding.RequestManager;
import org.testng.annotations.Test;

/**
 * @author Benjamin Huanca on 10/22/2019.
 * @version 1.0
 */
public class DELETEProjectTest {
    @Test
    public void testDELETEProject() {
        //Given
        //Step 1: A Project already created is needed.
        String expectedProjectname = "Rest project API";

        Response response = RequestManager.post(RequestSpecFactory.getRequestSpec("pivotal"),
                "/projects",
                "{\"name\":\"" + expectedProjectname + "\"}");

        String actualProjectName = response.jsonPath().getString("name");

    }
}
