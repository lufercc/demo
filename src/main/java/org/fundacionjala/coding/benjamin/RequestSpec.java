package org.fundacionjala.coding.benjamin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * @author Benjamin Huanca on 10/17/2019.
 * @version 1.0
 */
public class RequestSpec {
    public static RequestSpecification getRequestSpec() {
    return new RequestSpecBuilder()
            .setBaseUri(Environment.getInstance().getValue("baseUri"))
            .addHeader("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
            .build();
    }
}
