package datastructures.graph;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are
 * surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum
 * area is 0.)
 * <p>
 * Example 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6
 * <p>
 * Algorithm:
 * Similar to @see {@link Islands} we have to keep tack of the connected islands.
 * <p>
 * <p>
 * Complexity:
 * Time - O(row*col) - As we visit each cel at max once
 * Space - O(row*col)
 *
 * @author kushal
 * <p>
 * Date 14/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/max-area-of-island/
 */
public class IslandsMaxArea {

  public int maxAreaOfIsland(int[][] grid) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int max = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        max = Math.max(max, calArea(grid, i, j, visited));
      }
    }

    return max;
  }

  public int calArea(int[][] grid, int row, int col, boolean[][] visited) {

    if (isSafe(grid, row, col, visited)) {
      visited[row][col] = true;
      return 1 + calArea(grid, row + 1, col, visited) + calArea(grid, row, col + 1, visited)
          + calArea(grid, row, col - 1, visited) + calArea(grid, row - 1, col, visited);

    } else {
      return 0;
    }
  }

  public boolean isSafe(int[][] grid, int row, int col, boolean[][] visited) {
    return row < grid.length && row >= 0 && col < grid[0].length && col >= 0 && !visited[row][col]
        && grid[row][col] == 1;
  }

  public static void main(String[] args) {
    IslandsMaxArea ima = new IslandsMaxArea();

    int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

    System.out.println(ima.maxAreaOfIsland(grid));

  }
}
