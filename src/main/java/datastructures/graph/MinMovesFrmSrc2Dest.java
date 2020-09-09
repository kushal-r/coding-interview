package datastructures.graph;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Given a 2D matrix representing start (1), end (2), empty (3)  and wall (0), find a way to
 * traverse the grid starting at 1 and ending at 2. We can traverse only via cells marked as
 * empty(3)
 * <p>
 * Algorithm:
 * Start BFS from node marked 1, using a queue, enqueue all the neighbour vertices into the queue.
 * Validate if the neighbour is valid matrix cell and not visited before, and increment the
 * value on the neighbour vertex by +1
 * Check if we reached the dest, if so check if update min
 * After the bfs the destination cell marked 2 will have the lowest count of moves
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 17/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/find-minimum-numbers-moves-needed-move-one-cell-matrix-another/
 */
public class MinMovesFrmSrc2Dest {

  public int doBfs(int[][] arr, MatrixIdx src, MatrixIdx dest) {

    boolean[][] visited = new boolean[arr.length][arr[0].length];
    Queue<MatrixIdx> q = new LinkedList<>();
    int min = Integer.MAX_VALUE;

    q.offer(src);
    visited[src.getX()][src.getY()] = true;

    while (!q.isEmpty()) {
      MatrixIdx curr = q.poll();

      if (curr.equals(dest)) {
        min = Math.min(min, curr.getVal());
      } else if (arr[curr.getX()][curr.getY()] != 0) {
        checkNeighbours(arr, curr, q, visited);
      }

    }
    return min;
  }

  public void checkNeighbours(int[][] arr, MatrixIdx curr, Queue<MatrixIdx> q,
      boolean[][] visited) {
    // left, right, bottom top
    int[] moveX = {0, 0, -1, 1};
    int[] moveY = {-1, 1, 0, 0};

    for (int i = 0; i < 4; i++) {
      //Build the next neighbour to check
      MatrixIdx neighbour = new MatrixIdx(moveX[i] + curr.getX(), moveY[i] + curr.getY(),
          curr.getVal() + 1);

      //If valid grid cell and not visited
      if (isSafe(arr, neighbour.getX(), neighbour.getY()) && !visited[neighbour.getX()][neighbour
          .getY()]) {
        visited[neighbour.getX()][neighbour.getY()] = true;
        q.add(neighbour);
      }
    }

  }

  public boolean isSafe(int[][] arr, int x, int y) {
    return x >= 0 && x < arr.length && y >= 0 && y < arr[0].length && arr[x][y] != 0;
  }

  public int findMinMoves(int[][] arr) {
    MatrixIdx src = null;
    MatrixIdx dest = null;

    //Find the src and dest cells
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == 1) {
          src = new MatrixIdx(i, j, 0);
        }
        if (arr[i][j] == 2) {
          dest = new MatrixIdx(i, j, 0);
        }
      }
    }

    return doBfs(arr, src, dest);
  }

  public static void main(String[] args) {
    int[][] grid = {{3, 3, 1, 0}, {3, 0, 3, 3},
        {2, 3, 0, 3}, {0, 3, 3, 3}};

    MinMovesFrmSrc2Dest minMovesFrmSrc2Dest = new MinMovesFrmSrc2Dest();
    System.out.println("Min moves = " + minMovesFrmSrc2Dest.findMinMoves(grid));

    int[][] grid2 = {{0, 3, 1, 0}, {3, 0, 3, 3},
        {2, 3, 0, 3}, {0, 3, 3, 3}};
    System.out.println("Min moves = " + minMovesFrmSrc2Dest.findMinMoves(grid2));

  }

  private static class MatrixIdx {

    private int x;
    private int y;

    private int val;

    public MatrixIdx(int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getVal() {
      return val;
    }

    public void setX(int x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public void setVal(int val) {
      this.val = val;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MatrixIdx)) {
        return false;
      }
      MatrixIdx matrixIdx = (MatrixIdx) o;
      return getX() == matrixIdx.getX() &&
          getY() == matrixIdx.getY();
    }

    @Override
    public int hashCode() {
      return Objects.hash(getX(), getY());
    }
  }
}


