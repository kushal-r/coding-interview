package datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Given a 2D matrix containing 1's and 0's, we need to find count of all possible connected 1's
 * in the matrix.
 * <p>
 * Algorithm:
 * We can use either BFS or DFS for solving this.
 * In case of BFS, for each vertex if it is not yet visited we do bfs on that vertex and increment
 * count++, we
 * find all the connected cells
 * whose value is 1, and which is not yet visited, and add it to the queue. We continue doing
 * this until all the vertices are visited. At the end the count will return the total number of
 * islands in the matrix
 * <p>
 * Complexity:
 * Time - O(m*n)
 * Space - O(m*n)
 * <p>
 * Date: 25/03/20
 *
 * @author Kushal Roy
 * References:
 * https://www.geeksforgeeks.org/islands-in-a-graph-using-bfs/
 * https://www.geeksforgeeks.org/find-number-of-islands/
 */
public class Islands {

  //-----------------BFS solution------------------//

  public int countIslandsBFS(int[][] mat) {
    Set<Cell> visited = new HashSet<>();

    int count = 0;

    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        Cell tmp = new Cell(i, j);
        if (mat[i][j] == 1 && !visited.contains(tmp)) {
          bfs(mat, tmp, visited);
          count++;

        }
      }
    }
    return count;
  }

  public void bfs(int[][] mat, Cell init, Set<Cell> visited) {
    Queue<Cell> q = new LinkedList<>();
    int[] xMove = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] yMove = {0, 1, 1, 1, 0, -1, -1, -1};

    q.offer(init);

    while (!q.isEmpty()) {
      Cell curr = q.poll();
      if (!visited.contains(curr)) {
        visited.add(curr);

        for (int i = 0; i < 8; i++) {
          Cell tmp = new Cell(curr.x + xMove[i], curr.y + yMove[i]);
          if (isSafe(mat, tmp, visited)) {
            q.offer(tmp);
          }
        }
      }
    }

  }

  //---------------DFS solution--------------//

  public int countIslandsDFS(int[][] mat) {
    Set<Cell> visited = new HashSet<>();

    int count = 0;

    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        Cell tmp = new Cell(i, j);

        if (mat[i][j] == 1 && !visited.contains(tmp)) {
          dfsHlpr(mat, tmp, visited);
          count++;

        }
      }
    }
    return count;
  }

  public void dfsHlpr(int[][] mat, Cell curr, Set<Cell> visited) {
    int[] xMove = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] yMove = {0, 1, 1, 1, 0, -1, -1, -1};

    if (!visited.contains(curr)) {
      visited.add(curr);

      for (int i = 0; i < 8; i++) {
        Cell tmp = new Cell(curr.x + xMove[i], curr.y + yMove[i]);
        if (isSafe(mat, tmp, visited)) {
          dfsHlpr(mat, tmp, visited);
        }
      }
    }
  }


  public boolean isSafe(int[][] mat, Cell curr, Set<Cell> visited) {

    return curr.x < mat.length && curr.x >= 0 && curr.y < mat[0].length && curr.y >= 0 && !visited
        .contains(curr) && mat[curr.x][curr.y] == 1;
  }

  public static void main(String[] args) {
    int[][] mat = {{1, 1, 0, 0, 0},
        {0, 1, 0, 0, 1},
        {1, 0, 0, 1, 1},
        {0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1}};

    Islands islands = new Islands();

    System.out.println("Island count using BFS : " + islands.countIslandsBFS(mat));
    System.out.println("Island count using DFS : " + islands.countIslandsDFS(mat));

  }

  //-----------------------------------------------//

  private static class Cell {

    private int x;
    private int y;

    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Cell)) {
        return false;
      }
      Cell cell = (Cell) o;
      return x == cell.x &&
          y == cell.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
