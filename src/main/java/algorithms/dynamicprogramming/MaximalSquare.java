package algorithms.dynamicprogramming;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's
 * and
 * return its area.
 * Example:
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 [1 1] 1
 * 1 1 [1 1] 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 * <p>
 * <p>
 * Algorithm:
 * We initialize another matrix (dp) with the same dimensions as the original one initialized with
 * all 0’s.
 * dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell
 * with index (i,j) in the original matrix.
 * Starting from index (0,0), for every 1 found in the original matrix, we update the value of the
 * current element as
 * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1.
 * <p>
 * Basically we have to find if current cell is part of the square whose right bottom element is the
 * current cell
 * <p>
 * Complexity:
 * Time - O(mn)
 * Space - O(msn)
 *
 * @author kushal
 * <p>
 * Date 21/09/20
 * <p>
 * Reference:
 * https://leetcode.com/problems/maximal-square/
 * https://www.youtube.com/watch?v=_Lf1looyJMU&ab_channel=TusharRoy-CodingMadeSimple
 */
public class MaximalSquare {

  public int maximalSquare(int[][] matrix) {
    int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

    int maxArea = 0;
    for (int i = 1; i <= matrix.length; i++) {
      for (int j = 1; j <= matrix[0].length; j++) {
        if (matrix[i - 1][j - 1] == 1) {
          dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
          maxArea = Math.max(maxArea, dp[i][j]);
        }
      }
    }

    return maxArea * maxArea;
  }

  public static void main(String[] args) {
    MaximalSquare ms = new MaximalSquare();

    int[][] matrix = {{1, 0, 1, 0, 0},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 0, 0, 1, 0}};
    System.out.println(ms.maximalSquare(matrix));
  }
}
