package org.fundacionjala.coding;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Environment.getInstance().getValue("baseUri"))
                .addHeader("X-TrackerToken", Environment.getInstance().getValue("credentials.owner.token"))
                .build();
    }

}
