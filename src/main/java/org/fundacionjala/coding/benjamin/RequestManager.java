package org.fundacionjala.coding.benjamin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Benjamin Huanca on 10/17/2019.
 * @version 1.0
 */
public class RequestManager {
    public static Response post(final RequestSpecification requestSpec,
                                final String endpoint,
                                final String body){
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(endpoint);
    }

    public static Response put(final RequestSpecification requestSpec,
                                final String endpoint,
                                final String body){
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(endpoint);
    }
}
