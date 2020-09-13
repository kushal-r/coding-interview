package string;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
 * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are
 * well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits
 * are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 * <p>
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 * <p>
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 * <p>
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 * <p>
 * <p>
 * Algorithm:
 * The main idea is to put all the numbers in numStack, put rest of the "[" and alphabets in the
 * strStack. Whenever we encounter "]" we start popping all the chars from the strStack until we
 * encounter "[" and one number from numStack. We create the new str and put it back in the
 * strStack. After we finish traversing the input str, we have to pop every thing out of the
 * strStack and return the final result.
 * <p>
 * Complexity:
 * Time - (n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * Date 12/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/decode-string/
 * https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
 */
public class DecodeString {


  public String decodeString(String s) {
    Stack<Character> strStack = new Stack<>();
    Stack<Integer> numStack = new Stack<>();
    StringBuilder tmp;
    StringBuilder result = new StringBuilder();
    int idx = 0;
    int count;

    while (idx < s.length()) {
      if (Character.isDigit(s.charAt(idx))) {
        count = 0;

        while (Character.isDigit(s.charAt(idx))) {
          count = count * 10 + (s.charAt(idx) - '0');
          idx++;

        }
        numStack.push(count);
      } else if (s.charAt(idx) == ']') {
        tmp = new StringBuilder();
        count = 0;

        if (!numStack.isEmpty()) {
          count = numStack.pop();
        }

        while (!strStack.isEmpty() && strStack.peek() != '[') {
          tmp.insert(0, strStack.pop());

        }

        if (!strStack.isEmpty() && strStack.peek() == '[') {
          strStack.pop();
        }

        while (count > 0) {
          result.append(tmp);
          count--;

        }

        for (char c : result.toString().toCharArray()) {
          strStack.push(c);
        }
        result = new StringBuilder();
        idx++;
      } else {
        strStack.push(s.charAt(idx));
        idx++;
      }
    }

    while (!strStack.isEmpty()) {
      result.insert(0, strStack.pop());
    }

    return result.toString();
  }


  public static void main(String[] args) {
    DecodeString ds = new DecodeString();

    System.out.println(ds.decodeString("3[a]2[bc]"));
    System.out.println(ds.decodeString("3[a2[c]]"));
    System.out.println(ds.decodeString("2[abc]3[cd]ef"));
    System.out.println(ds.decodeString("abc3[cd]xyz"));

  }
}
