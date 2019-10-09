package org.fundacionjala.coding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestManager {

    public static Response post(final RequestSpecification requestSpec, final String endpoint,
                                final String body) {
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(endpoint);
    }

    public static Response put(final RequestSpecification requestSpec, final String endpoint,
                                final String body) {
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(endpoint);
    }

    public static Response get(final RequestSpecification requestSpec, final String endpoint) {
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
    }

    public static Response delete(final RequestSpecification requestSpec, final String endpoint) {
        return RestAssured.given(requestSpec)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint);
    }

}
