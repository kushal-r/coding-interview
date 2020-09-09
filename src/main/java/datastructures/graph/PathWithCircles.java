package datastructures.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a rectangular grid and centre of circles X[] and Y[], find if it is possible to reach
 * the bottom right cell from the top left cell, without touching the circles
 * <p>
 * Algorithm:
 * Iterate through the grid and mark the cell in the grid as -1 if it touching the boundary of
 * the circles. BFS on the grid starting from top left to see is bottom right is reachable
 * <p>
 * Complexity:
 * Time - O(m*n*k) for marking the cell and O(V+E) for bfs
 * Space - O(m*n) for the grid
 * <p>
 * Date: 10/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/path-rectangle-containing-circles/
 */
public class PathWithCircles {

  public boolean isReachable(int m, int n, int k, int r, int[] x, int[] y) {
    int[][] grid = new int[m][n];

    //Fill the grid
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        for (int c = 0; c < k; c++) {
          //Dist. between two pints sqrt((x1-x2)^2 + (y1-y2))
          double dist = Math.sqrt(Math.pow((x[c] - i), 2) + Math.pow((y[c] - j), 2));
          if (dist <= r) {
            grid[i][j] = -1;
            //break;
          }
        }
      }
    }

    //Base case
    if (grid[0][0] == -1) {
      return false;
    }

    Queue<GridIdx> q = new LinkedList<>();
    GridIdx xy = new GridIdx(0, 0);
    q.offer(xy);

    while (!q.isEmpty()) {
      xy = q.poll();
      updateGrid(xy, grid, q);
    }

    return grid[m - 1][n - 1] == 1;
  }


  public void updateGrid(GridIdx xy, int[][] grid, Queue<GridIdx> q) {
    int[] xMove = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] yMove = {-1, 0, 1, 1, 1, 0, -1, -1};

    if (isValidCell(xy.x, xy.y, grid)) {
      grid[xy.x][xy.y] = 1;
      for (int i = 0; i < 8; i++) {
        GridIdx idx = new GridIdx(xy.x + xMove[i], xy.y + yMove[i]);
        q.offer(idx);
        updateGrid(idx, grid, q);
      }
    }
  }


  public boolean isValidCell(int x, int y, int[][] grid) {
    return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0;
  }


  public static void main(String[] args) {
    PathWithCircles pathWithCircles = new PathWithCircles();

    int m1 = 5;
    int n1 = 5;
    int k1 = 2;
    int r1 = 1;
    int[] X1 = {0, 2};
    int[] Y1 = {2, 2};
    System.out.println(pathWithCircles.isReachable(m1, n1, k1, r1, X1, Y1));

    int m2 = 5;
    int n2 = 5;
    int k2 = 2;
    int r2 = 1;
    int[] X2 = {0, 0};
    int[] Y2 = {1, 2};
    System.out.println(pathWithCircles.isReachable(m2, n2, k2, r2, X2, Y2));
  }

  static class GridIdx {

    private int x;
    private int y;

    public GridIdx(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }


}
