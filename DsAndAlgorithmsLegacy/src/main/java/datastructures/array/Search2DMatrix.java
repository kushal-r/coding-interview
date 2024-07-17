package datastructures.array;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the
 * following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 * <p>
 * <p>
 * Algorithm:
 * First, we initialize a (row, col)(row,col) pointer to the bottom-left of the matrix.[1] Then,
 * until we find target and return true (or the pointer points to a (row, col)(row,col) that lies
 * outside of the dimensions of the matrix), we do the following: if the currently-pointed-to value
 * is larger than target we can move one row "up". Otherwise, if the currently-pointed-to value is
 * smaller than target, we can move one column "right". It is not too tricky to see why doing this
 * will never prune the correct answer; because the rows are sorted from left-to-right, we know that
 * every value to the right of the current value is larger. Therefore, if the current value is
 * already larger than target, we know that every value to its right will also be too large. A very
 * similar argument can be made for the columns, so this manner of search will always find target in
 * the matrix (if it is present).
 * <p>
 * <p>
 * Complexity:
 * Time - O(n+m)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * Date 13/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/search-a-2d-matrix-ii/solution/
 */
public class Search2DMatrix {

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null) {
      return false;
    }

    int row = matrix.length - 1;
    int col = 0;

    while (row >= 0 && col <= matrix[0].length - 1) {
      if (matrix[row][col] == target) {
        return true;
        //If matrix > tgt move up ^
      } else if (matrix[row][col] > target) {
        row--;
        //If matrix < tgt move right ->
      } else if (matrix[row][col] < target) {
        col++;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Search2DMatrix sm = new Search2DMatrix();

    int[][] matrix = {
        {1, 4, 7, 11, 15},
        {2, 5, 8, 12, 19},
        {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };

    System.out.println(sm.searchMatrix(matrix, 5));
    System.out.println(sm.searchMatrix(matrix, 20));

  }

}
