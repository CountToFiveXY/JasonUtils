package Algorithm.Company.Remitly;

import java.util.HashMap;
import java.util.Map;

/**
 * Replace template in string
 *
 * Input:
 * "this is a {foo} {bar}"
 * {foo=template, bar=string}
 *
 * Output:
 * "this is a template string"
 *
 * Follow up:
 * - missing key
 * - nested template
 * - escaped braces
 */
public class ReplaceTemplateInString {

    public static String replace(String text, Map<String, String> map) {
        StringBuilder res = new StringBuilder();
        StringBuilder curr = new StringBuilder();

        for (char c: text.toCharArray()) {

            if (c == '{') {
               res.append(curr);
               curr = new StringBuilder();
            } else if (c == '}') {
                String key = curr.toString();
                String value = map.get(key);
                if (value == null) {
                    res.append("?");
                } else {
                    res.append(value);
                }
                curr = new StringBuilder();
            } else {
                curr.append(c);
            }
        }
        res.append(curr);

        return res.toString();
    }

    public static void main(String[] args) {

        // Normal
        run(
                "this is a {foo} {bar}",
                Map.of("foo", "template", "bar", "string"),
                "this is a template string"
        );

        // Missing key
        run(
                "hello {name}",
                Map.of(),
                "hello {name}"
        );

        // Repeated key
        run(
                "{x} + {x}",
                Map.of("x", "1"),
                "1 + 1"
        );

        // Edge
        run(
                "",
                Map.of(),
                ""
        );
    }

    private static void run(String text, Map<String, String> map, String expected) {
        String actual = replace(text, map);

        System.out.println(
                (expected.equals(actual) ? "✅ " : "❌ ") +
                        "Expected: " + expected +
                        " | Actual: " + actual
        );
    }
}