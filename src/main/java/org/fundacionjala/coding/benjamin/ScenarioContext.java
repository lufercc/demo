package org.fundacionjala.coding.benjamin;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Benjamin Huanca on 10/27/2019.
 * @version 1.0
 */
public class ScenarioContext {
    private Map<String, Response> map;

    public ScenarioContext() {
        map=new HashMap<>();
    }

    public Response get(String key) {
        return map.get(key);
    }

    public void set(String key, Response response) {
        map.put(key,response);
    }
}
