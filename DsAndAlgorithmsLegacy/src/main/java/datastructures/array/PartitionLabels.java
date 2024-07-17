package datastructures.array;

import java.util.ArrayList;
import java.util.List;

/**
 * A string s is given, we want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * <p>
 * Algorithm:
 * The main idea is to traverse S and get the last occurrence of the each char in the string.
 * Iterate again over the string and keep track of start and end of current partition, if the
 * current characters last occurrence in within this partition then ignore that or else if it is
 * more than the end of current partition, updated current partitions end.
 * If at any time during traversal we encounter idx == current partitions end we have reached end
 * of current partition update result list with the count of chars in this partition and update
 * next partitions start.
 * <p>
 * Complexity:
 * Time - O(n) - number of chars in string
 * Space - O(n) - for storing the tmp chars array
 * <p>
 * Date: 18/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {

  public List<Integer> partition(String s) {
    char[] chars = s.toLowerCase().toCharArray();

    int[] lastIndex = new int[26];

    //Save the last idx of occurrence of each character in the string
    for (int i = 0; i < chars.length; i++) {
      lastIndex[chars[i] - 'a'] = i;
    }

    List<Integer> partitions = new ArrayList<>();
    int partitionStart = 0;
    int partitionEnd = 0;
    for (int i = 0; i < chars.length; i++) {
      //Update the last idx of the current partition
      partitionEnd = Math.max(partitionEnd, lastIndex[chars[i] - 'a']);

      //We have reached the end of the current partition?
      if (i == partitionEnd) {
        //Get actual count of chars in the partition
        partitions.add(partitionEnd - partitionStart + 1);
/*        for (int j = partitionStart; j <= partitionEnd; j++) {
          System.out.print(chars[j]);
        }*/
        System.out.println();
        partitionStart = i + 1;
      }
    }

    return partitions;
  }

  public static void main(String[] args) {
    String s = "ababcbacadefegdehijhklij";
    PartitionLabels pl = new PartitionLabels();

    System.out.println("Partitions: " + pl.partition(s));
  }
}
