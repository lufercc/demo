package org.fundacionjala.coding;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Response> map;

    public ScenarioContext() {
        map = new HashMap<>();
    }

    public Response get(String key) {
        return map.get(key);
    }

    public void set(String key, Response response) {
        map.put(key, response);
    }
}