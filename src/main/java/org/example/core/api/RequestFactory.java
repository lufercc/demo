package org.example.core.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.core.Environment;

import java.sql.SQLOutput;

public class RequestFactory {
    private static final Environment ENV = Environment.getInstance();

    private static RequestSpecification getRequestWithLogger(final RequestSpecification requestSpecification) {
        return requestSpecification
                .log().method()
                .log().uri()
                .log().params()
                .log().body();
    }

    public static RequestSpecification buildRequestSpecification(final String service) {
        RequestSpecification requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .setBaseUri(ENV.getValue(service + ".baseUri"))
                .addHeaders(ENV.getValuesAsMap(service + ".header"))
                .build();
        return getRequestWithLogger(requestSpecification);
    }
}
