package amazon;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * <p>
 * Algorithm:
 * We will be doing conditional sorting based on the criteria
 * <p>
 * Complexity:
 * Time - O(nlogn) - size of logs
 * Space - O(n) - for extra space, adj. char array
 * <p>
 * Date: 21/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderLogs {

  public String[] reorderLogs(String[] logs) {
    Arrays.sort(logs, (log1, log2) -> {
      String[] a = log1.split("\\W", 2);
      String[] b = log2.split("\\W", 2);
      boolean isDigitA = Character.isDigit(a[1].charAt(0));
      boolean isDigitB = Character.isDigit(b[1].charAt(0));

      if (!isDigitA && !isDigitB) {
        int cmp = a[1].compareTo(b[1]);
        return cmp == 0 ? a[0].compareTo(b[0]) : cmp;
      }
      return isDigitA ? isDigitB ? 0 : 1 : -1;
    });

    return logs;
  }

  public static void main(String[] args) {
    String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig",
        "let3 art zero"};

    ReorderLogs rl = new ReorderLogs();
    System.out.println("Re-ordered lpgs : " + Arrays.toString(rl.reorderLogs(logs)));
  }

  //------------------------------------------------------------//

  static class LogComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      String[] a = o1.split("\\W", 2);
      String[] b = o2.split("\\W", 2);

      boolean isDigitA = Character.isDigit(a[1].charAt(0));
      boolean isDigitB = Character.isDigit(b[1].charAt(0));

      if (!isDigitA && !isDigitB) {
        int cmp = a[1].compareTo(b[1]);
        if (cmp == 0) {
          return a[0].compareTo(b[0]);
        } else {
          return cmp;
        }
      }

      if (isDigitA) {
        if (isDigitB) {
          return 0;
        } else {
          return 1;
        }
      } else {
        return -1;
      }
    }
  }
}
