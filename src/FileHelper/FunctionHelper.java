package FileHelper;

public class FunctionHelper {
    private static final String COMMA = ",";

    public static String duplicate(String line) {
        return line + COMMA + line;
    }

    public static String addPrefix(String line, String prefix) {
        return prefix + line;
    }

    public static String addSuffix(String line, String suffix) {
        return line + suffix;
    }

    public static String getSubStringBeforeIndex(String line, String index) {
        return line.substring(0, line.lastIndexOf(index));
    }

    public static String getSubStringAfterIndex(String line, String index) {
        return line.substring(line.lastIndexOf(index) + 1);
    }

    public static String replaceString(String line, String old, String candidate) {
        return line.replace(old, candidate);
    }

    public static String to(String line, String old, String candidate) {
        return line.replace(old, candidate);
    }
}
