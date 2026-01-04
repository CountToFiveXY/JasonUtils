package Algorithm.LC.TwoPointer.P271; /**
 * LeetCode 271. Encode and Decode Strings
 *
 * -------------------------
 * Problem Statement:
 * -------------------------
 * Design an algorithm to encode a list of strings into a single string.
 * The encoded string is then decoded back into the original list of strings.
 *
 * The general idea: You need a reversible transformation between:
 *      List<String>  <-->   String
 *
 * Requirements:
 * 1. The encoded format must be **unambiguously decodable**.
 * 2. You cannot rely on simple delimiters like commas or '#'
 *    because strings may contain arbitrary characters.
 * 3. The encoder must handle edge cases:
 *      - Empty strings
 *      - Very long strings
 *      - Strings containing digits, symbols and # characters
 *
 * -----------------------------------------------------
 * ASCII DIAGRAM (Example Encoding With Length Prefixing)
 * -----------------------------------------------------
 * Suppose the list is:
 *      ["hello", "world", "a:b", ""]
 *
 * A common valid encoding scheme (one of several):
 *
 *      5#hello5#world3#a:b0#
 *
 * Breakdown:
 *      "5#hello" → length=5, value="hello"
 *      "5#world" → length=5, value="world"
 *      "3#a:b"   → length=3, value="a:b"
 *      "0#"      → empty string
 *
 * The decoder reads:
 *      5 → read 5 chars → "hello"
 *      5 → read 5 chars → "world"
 *      3 → read 3 chars → "a:b"
 *      0 → read 0 chars → ""
 *
 * -----------------------------------------------------
 * Your task is to implement:
 *
 * class Codec {
 *      public String encode(List<String> strs)
 *      public List<String> decode(String s)
 * }
 *
 * DO NOT include your solution here (this is only skeleton).
 */

import java.util.*;

public class Solution {

    // TODO: implement encode
    public String encode(List<String> strs) {
        // your code here
        return null;
    }

    // TODO: implement decode
    public List<String> decode(String s) {
        // your code here
        return null;
    }

    // ---------------------------------------------------
    // MAIN METHOD WITH BASIC TEST CASES
    // ---------------------------------------------------
    public static void main(String[] args) {
        Solution codec = new Solution();

        List<String> test1 = Arrays.asList("hello", "world");
        List<String> test2 = Arrays.asList("a:b", "x#y", "");
        List<String> test3 = Arrays.asList("", "", "abc");  // multiple empties
        List<String> test4 = Arrays.asList("123", "!@#$%", "中文测试", "🙂 emoji");

        System.out.println("=== Running Encode/Decode Test Cases (Skeleton) ===");

        String enc1 = codec.encode(test1);
        System.out.println("Encoded test1: " + enc1);
        System.out.println("Decoded test1: " + codec.decode(enc1));

        String enc2 = codec.encode(test2);
        System.out.println("Encoded test2: " + enc2);
        System.out.println("Decoded test2: " + codec.decode(enc2));

        String enc3 = codec.encode(test3);
        System.out.println("Encoded test3: " + enc3);
        System.out.println("Decoded test3: " + codec.decode(enc3));

        String enc4 = codec.encode(test4);
        System.out.println("Encoded test4: " + enc4);
        System.out.println("Decoded test4: " + codec.decode(enc4));
    }
}
