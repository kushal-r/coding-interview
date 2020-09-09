package string;

/**
 * Given two strings a and b, return the minimum number of times you should repeat string a so that
 * string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after
 * repeating it, return -1.
 * <p>
 * Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is
 * "abcabc".
 * <p>
 * Example 1:
 * <p>
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of
 * it.
 * Example 2:
 * <p>
 * Input: a = "a", b = "aa"
 * Output: 2
 * Example 3:
 * <p>
 * Input: a = "a", b = "a"
 * Output: 1
 * Example 4:
 * <p>
 * Input: a = "abc", b = "wxyz"
 * Output: -1
 * <p>
 * <p>
 * Algorithm:
 * Imagine we wrote S = A+A+A+.... If B is to be a substring of S, we only need to check whether
 * some S[0:], S[1:], ..., S[len(A) - 1:] starts with B, as S is long enough to contain B, and S has
 * period at most len(A).
 * <p>
 * Now, suppose q is the least number for which len(B) <= len(A * q). We only need to check whether
 * B is a substring of A * q or A * (q+1). If we try k < q, then B has larger length than A * q and
 * therefore can't be a substring. When k = q+1, A * k is already big enough to try all positions
 * for B; namely, A[i:i+len(B)] == B for i = 0, 1, ..., len(A) - 1.
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/repeated-string-match/
 * https://www.geeksforgeeks.org/minimum-number-of-times-a-has-to-be-repeated-such-that-b-is-a-substring-of-it/
 */
public class RepeatedStringMatch {

  public int repeatedStringMatch(String a, String b) {
    int q = 0;
    StringBuilder s = new StringBuilder();
    while (s.length() < b.length()) {
      s.append(a);
      q++;
    }

    if (s.indexOf(b) != -1) {
      return q;
    } else if (s.append(a).indexOf(b) != -1) {
      return q + 1;
    }

    return -1;
  }

  public static void main(String[] args) {
    RepeatedStringMatch rsm = new RepeatedStringMatch();
    System.out.println(rsm.repeatedStringMatch("abcd", "cdabcdab"));
    System.out.println(rsm.repeatedStringMatch("a", "aa"));
    System.out.println(rsm.repeatedStringMatch("a", "a"));
    System.out.println(rsm.repeatedStringMatch("abc", "wxyz"));

  }
}
