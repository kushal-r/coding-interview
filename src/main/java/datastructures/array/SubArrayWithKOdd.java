package datastructures.array;

/**
 * Given an array of n elements and an integer m, we need to write a program to find the number of
 * contiguous sub arrays in the array which contains exactly m odd numbers.
 * <p>
 * Input : arr = {2, 5, 6, 9},  m = 2
 * Output : 2
 * Explanation: subarrays are [2, 5, 6, 9]
 * and [5, 6, 9]
 * <p>
 * Input : arr = {2, 2, 5, 6, 9, 2, 11},  m = 2
 * Output : 8
 * Explanation: subarrays are [2, 2, 5, 6, 9],
 * [2, 5, 6, 9], [5, 6, 9], [2, 2, 5, 6, 9, 2],
 * [2, 5, 6, 9, 2], [5, 6, 9, 2], [6, 9, 2, 11]
 * and [9, 2, 11]
 * <p>
 * Algorithm:
 * The main idea is to keep track of the even numbers before encountering the odd nos.
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * Date 22/09/20
 * <p>
 * References:
 * https://www.geeksforgeeks.org/number-subarrays-m-odd-numbers/
 */
public class SubArrayWithKOdd {

  public int countSubarrays(int[] arr, int k) {
    int[] prefix = new int[arr.length];
    int count = 0;
    int startIdx = 0;

    for (int j : arr) {
      prefix[startIdx]++;

      if (j % 2 >= 1) {
        startIdx++;
      }

      if (startIdx >= k) {
        count += prefix[startIdx - k];
      }
    }

    return count;
  }


  public static void main(String[] args) {
    SubArrayWithKOdd sk = new SubArrayWithKOdd();

    System.out.println(sk.countSubarrays(new int[]{2, 5, 6, 9}, 2));
    System.out.println(sk.countSubarrays(new int[]{2, 2, 5, 6, 9, 2, 11}, 2));

  }
}
