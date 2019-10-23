package org.fundacionjala.coding;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Response> map;

    public ScenarioContext() {
        map = new HashMap<>();
    }

    public Map<String, Response> getMap() {
        return map;
    }

    public void setMap(Map<String, Response>map) {
        this.map = map;
    }

}
