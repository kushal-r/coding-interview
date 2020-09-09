package algorithms.dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are assigned to travel to different villages and generate some profit based on the
 * conditions:
 * From a village i you can only move to village j, if j > i, and v[j] is a multiple of v[i]
 * Find the maximum profit that can be generated.
 * <p>
 * Ex: v[1,2,3,4,9,8], soln : 15 -> [ 1, 2, 4, 8 ]
 * <p>
 * <p>
 * Algorithm:
 * This can be solved by BFS, starting at each cell and find all the possible connected nodes,
 * and finally checking for the maximum sum.
 * <p>
 * This can be solved in an optimized way using dynamic programming, we start for each node
 * keeping track of the sum so far in a tmp arr dp[], at each stage we check if j is a multiple
 * of i, if so we update the value of the dp[] based on whether it is more than the existing value.
 * <p>
 * Complexity:
 * Time - O()
 * Space - O(n) for the auxiliary array
 * <p>
 * Date: 21/03/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/avatar-and-his-quest-d939b13f/
 */
public class MaximizeProfit {

  /**
   * BFS solution
   *
   * @param a
   * @return
   */
  public int calMaxProfit(int[] a) {

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < a.length; i++) {
      int currMax = bfs(a, i);

      max = Math.max(max, currMax);
    }

    return max;
  }

  public int bfs(int[] a, int idx) {

    Queue<Integer> q = new LinkedList<>();
    int max = 0;

    q.offer(idx);

    while (!q.isEmpty()) {

      int currIdx = q.poll();
      isValidVillage(a, currIdx, q);
      max += a[currIdx];
    }

    return max;

  }

  public void isValidVillage(int[] a, int currIdx, Queue<Integer> q) {
    int nextIdx = currIdx + 1;

    while (nextIdx < a.length) {
      if (a[nextIdx] % a[currIdx] == 0) {
        q.offer(nextIdx);
        return;
      }

      nextIdx = nextIdx + 1;
    }
  }

  /**
   * Dynamic programming solution
   *
   * @param arr
   * @return
   */
  public int calculateMaxProfit(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int[] dp = new int[arr.length];
    System.arraycopy(arr, 0, dp, 0, arr.length);

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] % arr[j] == 0) {
          dp[i] = Math.max(dp[i], arr[i] + dp[j]);
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for (int value : dp) {
      max = Math.max(max, value);
    }

    return max;
  }


  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 9, 8};

    MaximizeProfit t = new MaximizeProfit();
    System.out.println(t.calculateMaxProfit(a));
  }

}
