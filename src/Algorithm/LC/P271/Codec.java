package Algorithm.LC.P271;

import java.util.*;

/**
 * LeetCode 271 - Encode and Decode Strings
 *
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and decoded
 * back to the original list of strings.
 *
 * You are NOT allowed to use built-in serialization methods.
 *
 * Example:
 * Input: ["lint","code","love","you"]
 * Encoded: ?
 * Decoded: ["lint","code","love","you"]
 *
 * Constraints:
 * - Strings may contain any characters
 * - Empty strings are allowed
 *
 * ----------------------------------------
 * Key design considerations:
 * - How to separate strings safely?
 * - How to handle empty strings?
 * - How to handle special characters?
 * - How to ensure decoding is unambiguous?
 *
 * ----------------------------------------
 * Diagram (conceptual):
 *
 * ["lint", "code", "", "you"]
 *
 *   encode()
 *     ↓
 * "????"
 *     ↓
 *   decode()
 *
 * ["lint", "code", "", "you"]
 *
 */
public class Codec {

    /**
     * Encodes a list of strings to a single string.
     *
     * @param strs List of strings
     * @return Encoded string
     */
    public String encode(List<String> strs) {
        // TODO: implement encoding logic
        StringBuilder sb = new StringBuilder();

        for (String s:  strs) {
            int len = s.length();
            sb.append(len).append("#").append(s);
        }
        return sb.toString();
    }

    /**
     * Decodes a single string to a list of strings.
     *
     * @param s Encoded string
     * @return Decoded list of strings
     */
    public List<String> decode(String s) {
        // TODO: implement decoding logic
        List<String> res = new ArrayList<>();

        int i = 0, len = 0;

        while(i < s.length()) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                len = len * 10 + (c - '0');
                i++;
            }

            if (c == '#') {
                res.add(s.substring(i + 1, i + 1 + len));
                i = i + len + 1;
                len = 0;
            }
        }

        return res;
    }

    // ------------------------------
    // Test Harness
    // ------------------------------
    public static void main(String[] args) {
        Codec codec = new Codec();

        List<String> input1 = Arrays.asList("lint", "code", "love", "you");
        String encoded1 = codec.encode(input1);
        List<String> decoded1 = codec.decode(encoded1);

        System.out.println("Input:   " + input1);
        System.out.println("Encoded: " + encoded1);
        System.out.println("Decoded: " + decoded1);

        List<String> input2 = Arrays.asList("", "a", "", "bc");
        String encoded2 = codec.encode(input2);
        List<String> decoded2 = codec.decode(encoded2);

        System.out.println("Input:   " + input2);
        System.out.println("Encoded: " + encoded2);
        System.out.println("Decoded: " + decoded2);

        List<String> input3 = Arrays.asList("abcdefghij", "k");
        String encoded3 = codec.encode(input3);
        List<String> decoded3 = codec.decode(encoded3);

        System.out.println("Input:   " + input3);
        System.out.println("Encoded: " + encoded3);
        System.out.println("Decoded: " + decoded3);
    }
}
