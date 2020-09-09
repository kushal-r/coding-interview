package datastructures.array;

import java.util.HashMap;
import java.util.Map;

/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and
 * ask your friend to guess what the number is. Each time your friend makes a guess, you provide a
 * hint that indicates how many digits in said guess match your secret number exactly in both digit
 * and position (called "bulls") and how many digits match the secret number but locate in the
 * wrong
 * position (called "cows"). Your friend will use successive guesses and hints to eventually derive
 * the secret number.
 * <p>
 * Write a function to return a hint according to the secret number and friend's guess, use A to
 * indicate the bulls and B to indicate the cows.
 * Please note that both secret number and friend's guess may contain duplicate digits.
 * <p>
 * Example 1:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * <p>
 * Example 2:
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * <p>
 * Algorithm:
 * First we get the count of the number of chars in the secret string and update the hashmap.
 * <p>
 * Then we iterate over the guess and if the char is equal update bull, else check if char is
 * present in map and count of the letters is more than 0
 * <p>
 * <p>
 * Method 2:
 * Initialize the number of bulls and cows to zero.
 * Initialize the hashmap to count characters. During the iteration, secret string gives a positive
 * contribution, and guess - negative contribution.
 * Iterate over the strings: s is the current character in the string secret and g - the current
 * character in the string guess.
 * If s == g, update bulls counter: bulls += 1.
 * Otherwise, if s != g:
 * Update cows by adding 1 if so far guess contains more s characters than secret: h[s] < 0.
 * <p>
 * Update cows by adding 1 if so far secret contains more g characters than guess: h[g] > 0.
 * <p>
 * Update the hashmap by marking the presence of s character in the string secret: h[s] += 1.
 * <p>
 * Update the hashmap by marking the presence of g character in the string guess: h[g] -= 1.
 * <p>
 * Return the number of bulls and cows.
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {

  public String getHint(String secret, String guess) {
    if (secret == null || guess == null || secret.trim().length() != guess.trim().length()) {
      throw new IllegalArgumentException("Input string is not correct.");
    }

    int bulls = 0, cows = 0;
    Map<Character, Integer> map = new HashMap<>();

    //Get the count of chars at idx i of the secret
    for (int i = 0; i < secret.length(); i++) {
      map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
    }

    for (int i = 0; i < guess.length(); i++) {
      if (guess.charAt(i) == secret.charAt(i)) {
        bulls++;
        map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
      } else if (map.containsKey(guess.charAt(i)) && map.get(guess.charAt(i)) > 0) {
        map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
        cows++;
      }

    }

    return String.format("%dA%dB", bulls, cows);
  }


  public String getHintMethod2(String secret, String guess) {
    if (secret == null || guess == null || secret.trim().length() != guess.trim().length()) {
      throw new IllegalArgumentException("Input string is not correct.");
    }

    int bulls = 0, cows = 0;
    Map<Character, Integer> map = new HashMap<>();

    //Get the count of chars at idx i of the secret
    for (int i = 0; i < guess.length(); i++) {
      char s = secret.charAt(i);
      char g = guess.charAt(i);

      if (s == g) {
        bulls++;
      } else {
        //Update cows by adding 1 if so far guess contains more s characters than secret: h[s] < 0.
        if (map.getOrDefault(s, 0) < 0) {
          cows++;
        }
        //Update cows by adding 1 if so far secret contains more g characters than guess: h[g] > 0.
        if (map.getOrDefault(g, 0) > 0) {
          cows++;
        }
        //Update the hashmap by marking the presence of s character in the string secret: h[s] += 1.
        map.put(s, map.getOrDefault(s, 0) + 1);

        //Update the hashmap by marking the presence of g character in the string guess: h[g] -= 1.
        map.put(g, map.getOrDefault(g, 0) - 1);
      }
    }

    return String.format("%dA%dB", bulls, cows);
  }


  public static void main(String[] args) {
    BullsAndCows bac = new BullsAndCows();

    System.out.println(bac.getHint("1807", "7810"));
    System.out.println(bac.getHint("1123", "0111"));
    System.out.println(bac.getHint("1111", "0000"));
    System.out.println(bac.getHint("1111", "1111"));

    System.out.println();
    System.out.println(bac.getHintMethod2("1807", "7810"));
    System.out.println(bac.getHintMethod2("1123", "0111"));
    System.out.println(bac.getHintMethod2("0111", "1999"));
    System.out.println(bac.getHintMethod2("1111", "1111"));
  }
}
