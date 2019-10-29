package org.fundacionjala.pivotal;

import static org.apache.commons.lang3.StringUtils.trim;

public final class ArrayHelper {

    private ArrayHelper() {
        // private constructor
    }

    public static String getArrayId(final String component, final String string) {
        int index = 0;
        String[] extractArray = string.split("[\\\\.$|,|;|:]");
        for (int count = 0; count < extractArray.length; count++) {
            if (component.contains(trim(extractArray[count]))) {
                index = count + 1;
            }
        }
        return extractArray[index];
    }
}
