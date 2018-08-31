package base.core.utils;

import java.util.Arrays;

public class StringUtils {

    public static String replaceIntFromString(String target) {
        return target.replaceAll("\\d+", "");
    }

    /**
     * return string with erased values
     *
     * @param target {@link String}
     * @param values {@link String[]} - wanted to erase values
     * @return string w/o values
     */
    public static String replaceFromString(String target, String... values) {

        StringBuilder pattern = new StringBuilder();
        pattern.append("(");
        for (String value : values) {
            pattern.append(value)
                    .append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1);
        pattern.append(")");
        return target.replaceAll(pattern.toString(), "");
    }

    /**
     * is string contains some of values
     *
     * @param target {@link String}
     * @param values searched values
     * @return result
     */
    public static boolean containsValues(String target, String... values) {
        //        System.out.println(str.matches("(.*)(dev|demo)(.*)"));
        StringBuilder pattern = new StringBuilder();
        pattern.append("(.*)(");
        for (String value : values) {
            pattern.append(value)
                    .append("|");
        }
        pattern.deleteCharAt(pattern.length() - 1);
        pattern.append(")(.*)");
        return target.matches(pattern.toString());
    }

    /**
     * is string contains some of values
     *
     * @param target {@link String}
     * @param values searched values
     * @return result
     */
    public static String getContainsValues(String target, String... values) {
        String str = target.replace("carsbookingengine", "");
        return Arrays.stream(values).parallel().filter(str::contains).findFirst().orElse(null);
    }

    public static String firstKeyToUpper(String s) {

        String prepared = s.trim().toUpperCase();
        return prepared.substring(0, 1) + prepared.substring(1).toLowerCase();
    }
}
