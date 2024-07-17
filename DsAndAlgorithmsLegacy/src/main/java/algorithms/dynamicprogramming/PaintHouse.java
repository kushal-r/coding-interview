package algorithms.dynamicprogramming;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue
 * or
 * green. The cost of painting each house with a certain color is different. You have to paint all
 * the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For
 * example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of
 * painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Example:
 * <p>
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 * <p>
 * <p>
 * <p>
 *
 * @author kushal
 * <p>
 * Date 22/09/20
 * <p>
 * Reference:
 * https://leetcode.com/problems/paint-house/
 */
public class PaintHouse {

  public int minCostExtraSpace(int[][] costs) {
    if (costs == null) {
      return 0;
    }

    int[][] memoization = new int[costs.length][costs[0].length];

    for (int i = 0; i < costs[0].length; i++) {
      memoization[costs.length - 1][i] = costs[costs.length - 1][i];
    }

    for (int i = costs.length - 2; i >= 0; i--) {
      memoization[i][0] = costs[i][0] + Math.min(memoization[i + 1][1], memoization[i + 1][2]);
      memoization[i][1] = costs[i][1] + Math.min(memoization[i + 1][0], memoization[i + 1][2]);
      memoization[i][2] = costs[i][2] + Math.min(memoization[i + 1][0], memoization[i + 1][1]);

    }

    return Math.min(memoization[0][0], Math.min(memoization[0][1], memoization[0][2]));
  }


  public static void main(String[] args) {
    PaintHouse ph = new PaintHouse();

    System.out
        .println(ph.minCostExtraSpace(
            new int[][]{new int[]{17, 2, 17}, new int[]{16, 16, 5}, new int[]{14, 3, 19}}));
  }
}
