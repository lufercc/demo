package org.fundacionjala.coding.benjamin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Benjamin Huanca on 10/27/2019.
 * @version 1.0
 */
public final class EndpointHelper {
    private EndpointHelper(){

    }
    public static String endpointBuilder(final ScenarioContext context,
                                         final String endpoint){
        if(!endpoint.contains("{")){
            return endpoint;
        }
        Matcher matches = Pattern.compile("(?<=\\{)(.*?)(?=\\})").matcher(endpoint);
        StringBuilder newEndpoint = new StringBuilder();
        while (matches.find()){
            String[] parametersPart=matches.group().split("\\.");
            String key =parametersPart[0];
            String value =parametersPart[1];
            String replaceParameters = context.get(key).jsonPath().getString(value);
            matches.appendReplacement(newEndpoint,replaceParameters);
        }
        return newEndpoint.toString().replaceAll("[\\{\\}]","");
    }
}
