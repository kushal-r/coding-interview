package algorithms.dynamicprogramming;

/**
 * Given a string, we have to find the length of the longest palindromic substring in this string.
 * <p>
 * Algorithm:
 * Dynamic programming: Create a boolean array of size nXn where n is the length of the string.
 * Mark all the single length palindromes as true , for string of length 2 mark the cells as true
 * which ever satisfies the condition. For string of length 3, mark the cell as true if diagonal
 * cell  [i+1,j-1]
 * is true and s[i] == s[j]
 * <p>
 * Complexity:
 * Time - O(n^2)
 * Space - O(n^2)
 * <p>
 * Date: 14/07/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.ideserve.co.in/learn/longest-palindromic-substring
 * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * https://www.youtube.com/watch?v=UflHuQj6MVA
 */
public class LongestPalindromicSubString {

  public void longestPalindrome(String str) {
    int len = str.length();
    boolean[][] palindrome = new boolean[len][len];
    int startIdx = 0;
    int maxLen = 1;

    //Palindrome of size 1
    for (int i = 0; i < len; i++) {
      palindrome[i][i] = true;
    }

    //Find palindromes of size more than 2
    for (int i = 2; i < len - 1; i++) {
      if (str.charAt(i) == str.charAt(i + 1)) {
        palindrome[i][i + 1] = true;
        startIdx = i;
        maxLen = 2;
      }
    }

    //Find palindromes of size more than 3
    //k is length of substring
    for (int k = 3; k <= len; k++) {
      //Get the left character from the str
      for (int i = 0; i < len - k + 1; i++) {
        //Get the right character from the starting idx i to the length k
        int j = i + k - 1;

        if (palindrome[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
          palindrome[i][j] = true;

          if (k > maxLen) {
            startIdx = i;
            maxLen = k;
          }
        }
      }
    }

    System.out.println(str.substring(startIdx, startIdx + maxLen));

  }

  public static void main(String[] args) {

    LongestPalindromicSubString lps = new LongestPalindromicSubString();
    lps.longestPalindrome("forgeeksskeegfor");
  }
}
