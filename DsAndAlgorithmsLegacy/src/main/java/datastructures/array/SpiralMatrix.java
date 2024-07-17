package datastructures.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in
 * spiral
 * order.
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * Algorithm:
 * For each outer layer, we want to iterate through its elements in clockwise order starting from
 * the top left corner. Suppose the current outer layer has top-left coordinates \text{(r1, c1)}(r1,
 * c1) and bottom-right coordinates \text{(r2, c2)}(r2, c2).
 * <p>
 * Then, the top row is the set of elements \text{(r1, c)}(r1, c) for \text{c = c1,...,c2}c =
 * c1,...,c2, in that order. The rest of the right side is the set of elements \text{(r, c2)}(r, c2)
 * for \text{r = r1+1,...,r2}r = r1+1,...,r2, in that order. Then, if there are four sides to this
 * layer (ie., \text{r1 < r2}r1 < r2 and \text{c1 < c2}c1 < c2), we iterate through the bottom side
 * and left side
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * Date 13/10/20
 * <p>
 * References:
 * https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {

  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    //Top left corner
    int r1 = 0, c1 = 0;

    //Bottom right corner
    int r2 = matrix.length - 1, c2 = matrix[0].length - 1;

    while (r1 <= r2 && c1 <= c2) {

      for (int i = c1; i <= c2; i++) {
        ans.add(matrix[r1][i]);
      }

      for (int i = r1 + 1; i <= r2; i++) {
        ans.add(matrix[i][c2]);
      }

      //Do ot print the common corner element from here
      if (r1 < r2 && c1 < c2) {
        for (int i = c2 - 1; i > c1; i--) {
          ans.add(matrix[r2][i]);
        }

        for (int i = r2; i > r1; i--) {
          ans.add(matrix[i][c1]);
        }
      }

      r1++;
      c1++;
      r2--;
      c2--;
    }

    return ans;
  }

  public static void main(String[] args) {
    SpiralMatrix sm = new SpiralMatrix();

    System.out.println(sm.spiralOrder(new int[][]{{1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}}));

    System.out.println(sm.spiralOrder(new int[][]{{1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}}));
  }
}
