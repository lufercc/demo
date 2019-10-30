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
                                         final String endPoint){
//        if(!endpoint.contains("{")){
//            return endpoint;
//        }
//        Matcher matches = Pattern.compile("(?<=\\{)(.*?)(?=\\})").matcher(endpoint);
//        StringBuilder newEndpoint = new StringBuilder();
//        while (matches.find()){
//            String[] parametersPart=matches.group().split("\\.");
//            String key =parametersPart[0];
//            String value =parametersPart[1];
//            String replaceParameters = context.get(key).jsonPath().getString(value);
//            matches.appendReplacement(newEndpoint,replaceParameters);
//        }
//        return newEndpoint.toString().replaceAll("[\\{\\}]","");
        String[] endPointSplit = endPoint.split("/");
        for (int i = 0; i < endPointSplit.length; i++) {
            Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
            Matcher matcher = pattern.matcher(endPointSplit[i]);
            if (matcher.find()) {
                endPointSplit[i] = getElementResponse(context, matcher.group(1));
            }
        }
        return String.join("/", endPointSplit);
    }
    private static String getElementResponse(final ScenarioContext context, final String element) {
        String[] elementSplit = element.split("\\.");
        return context.get(elementSplit[0]).jsonPath().getString(elementSplit[1]);
    }
}
