package org.fundacionjala.coding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EndpointHelper {

	private EndpointHelper() {
	}

	public static String buildEndpoint(final ScenarioContext context, final String endPoint) {
		if (!endPoint.contains("{")) {
			return endPoint;
		}
		Matcher matches = Pattern.compile("(?<=\\{)(.*?)(?=\\})").matcher(endPoint);
		StringBuilder newEndPoint = new StringBuilder();
		while (matches.find()) {
			String[] parametersParts = matches.group().split("\\.");
			String key = parametersParts[0];
			String value = parametersParts[1];
			String replaceParameter = context.get(key).jsonPath().getString(value);
			matches.appendReplacement(newEndPoint, replaceParameter);
		}
		matches.appendTail(newEndPoint);
		return newEndPoint.toString().replaceAll("[\\{\\}]", "");
	}

}
