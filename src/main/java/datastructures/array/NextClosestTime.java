package datastructures.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the
 * current
 * digits. There is no limit on how many times a digit can be reused.
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all
 * valid. "1:34", "12:9" are all invalid.
 * Example 1:
 * <p>
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5
 * minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * <p>
 * Example 2:
 * <p>
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed
 * that the returned time is next day's time since it is smaller than the input time numerically.
 * <p>
 * Algorithm:
 * Solution 1:
 * Simulate the clock going forward by one minute. Each time it moves forward, if all the digits are
 * allowed, then return the current time.
 * The natural way to represent the time is as an integer t in the range 0 <= t < 24 * 60. Then the
 * hours are t / 60, the minutes are t % 60, and each digit of the hours and minutes can be found by
 * hours / 10, hours % 10
 * <p>
 * Complexity:
 * Time - O(1) we have only 24*60 possible combinations
 * Space - O(1)
 * <p>
 * <p>
 * Solution 2:
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/next-closest-time/
 */
public class NextClosestTime {

  public String findNextClosestTime(String time) {
    if (time == null || time.isEmpty()) {
      throw new IllegalArgumentException(
          "Time cannot be null or empty and should be of format HH:MM");
    }

    int hr = Integer.parseInt(time.split(":")[0]);
    int min = Integer.parseInt(time.split(":")[1]);

    int totalMins = (hr * 60) + min;

    Set<Integer> allowed = new HashSet<>();
    for (char c : time.toCharArray()) {
      if (c != ':') {
        allowed.add(c - '0');
      }
    }

    while (true) {
      totalMins = (totalMins + 1) % (24 * 60);
      int[] curr = new int[]{totalMins / 60 / 10, totalMins / 60 % 10, (totalMins % 60) / 10,
          (totalMins % 60) % 10};

      search:
      {
        for (int i : curr) {
          if (!allowed.contains(i)) {
            break search;
          }
        }
        return String.format("%02d:%02d", totalMins / 60, totalMins % 60);
      }
    }
  }

  public static void main(String[] args) {

    NextClosestTime nct = new NextClosestTime();
    System.out.println(nct.findNextClosestTime("23:59"));
    System.out.println(nct.findNextClosestTime("00:00"));

  }
}
