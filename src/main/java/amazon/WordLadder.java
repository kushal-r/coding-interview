package amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a start and an end string sequence and a dictionary of valid words, we need to find the
 * min. number of replacements to make the start word same as end. The intermediate words formed
 * by replacing a single character in the sequence should be valid, i.e the dictionary should
 * contain the word.
 * <p>
 * Algorithm:
 * The basic idea is to do BFS on the start word and traverse all adjacent words that differ by 1
 * character and which is also present in the dictionary, and keep a count of the total number of
 * steps. If at any time we reach the end of the dictionary or we found target we return the count.
 * <p>
 * <p>
 * Complexity:
 * Time - O(n^2 m) - n - dict size, m size of the string
 * Space - O(n) - number of words in the dictionary
 * <p>
 * Date: 15/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/problems/minimum-genetic-mutation/
 * https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/
 */
public class WordLadder {

  public int getChainLength(Set<String> dict, String start, String end) {

    if (!dict.contains(end)) {
      return -1;
    }

    int steps = 0;
    Queue<String> q = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    q.offer(start);

    while (!q.isEmpty()) {
      int size = q.size();

      //We are doing bfs, only one word is valid among all the words in this q, at this moment
      for (int s = 0; s < size; s++) {
        String currToken = q.poll();

        //If start == end
        if (currToken.equals(end)) {
          return steps;
        }

        if (!visited.contains(currToken)) {
          visited.add(currToken);

          //Add elements from dict which are at a diff of 1 from this curr word
          for (String d : dict) {
            if (isDiffOne(d, currToken)) {
              q.offer(d);
            }
          }
        }
      }
      steps++;

    }
    return -1;
  }


  public boolean isDiffOne(String x, String y) {
    if (x.length() != y.length()) {
      return false;
    } else {
      int diff = 0;
      for (int i = 0; i < x.length(); i++) {
        if (x.charAt(i) != y.charAt(i)) {
          diff++;
        }
      }
      return diff == 1;
    }
  }

  public static void main(String[] args) {
    Set<String> dict = new HashSet<>(Arrays.asList("AAAACCCC", "AAACCCCC", "AACCCCCC", "AAAAACCC"));
    String start = "AAAAACCC";
    String end = "AACCCCCC";

    WordLadder wl = new WordLadder();
    System.out.println("Total steps = " + wl.getChainLength(dict, start, end));

    dict = new HashSet<>(Arrays.asList("AACCGGTA", "AACCGCTA", "AAACGGTA", "AACCGGTT"));
    start = "AACCGGTT";
    end = "AAACGGTA";
    System.out.println("Total steps = " + wl.getChainLength(dict, start, end));

    dict = new HashSet<>(Arrays.asList("AACCGGTA", "AACCGGTT"));
    start = "AACCGGTT";
    end = "AACCGGTA";
    System.out.println("Total steps = " + wl.getChainLength(dict, start, end));

    dict = new HashSet<>(Arrays.asList("AACCGGTA", "AACCGCTA", "AAACGGTA"));
    start = "AACCGGTT";
    end = "AACCGCTA";
    System.out.println("Total steps = " + wl.getChainLength(dict, start, end));

  }

}
