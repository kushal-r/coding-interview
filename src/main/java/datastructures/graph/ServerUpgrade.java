package datastructures.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D array representing servers in a grid, we need to upgrade the software of every server.
 * Condition:
 * 1 represents nodes which have updated software, 0 represents nodes which has to be updated
 * Only adjacent nodes (top,right,bottom,left) of an updated servers can only be updated
 * Find the number of days to update all the nodes.
 * <p>
 * Algorithm:
 * We need to do BFS on every node whose value is 1, and add the adj. servers whose value is 0 to
 * the queue for the next iteration. After each iteration we increment days++
 * <p>
 * Complexity:
 * Time - O(m*n)
 * Space - O(m*n)
 * <p>
 * Date: 17/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/discuss/interview-question/411357/
 * https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assess%3C!--%E8%B0%B7%E6%AD%8Coff:%20snippet--%3E%3Cdiv%20class=
 */
public class ServerUpgrade {

  public int countDays(int[][] grid) {
    if (grid == null) {
      return -1;
    }

    int totalCount = grid.length * grid[0].length;
    int days = 0;
    int count = 0;
    Queue<int[]> q = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          q.offer(new int[]{i, j});
          count++;
        }
      }
    }

    //If all servers are already upgraded
    if (count == totalCount) {
      return 0;
    }

    //No servers has data to upgrade
    if (count == 0) {
      return -1;
    }

    while (!q.isEmpty()) {
      int size = q.size();
      if (count == totalCount) {
        return days;
      }

      for (int i = 0; i < size; i++) {
        int[] server = q.poll();
        count += adjServers(grid, server[0], server[1], q);
      }
      days++;
    }

    return -1;
  }

  public int adjServers(int[][] grid, int x, int y, Queue<int[]> q) {
    int[] xCoor = {-1, 0, 1, 0};
    int[] yCoor = {0, 1, 0, -1};
    int c = 0;
    for (int i = 0; i < 4; i++) {
      if (isValid(grid, x + xCoor[i], y + yCoor[i])) {
        grid[x + xCoor[i]][y + yCoor[i]] = 1;
        q.offer(new int[]{x + xCoor[i], y + yCoor[i]});
        c++;
      }
    }
    return c;
  }

  public boolean isValid(int[][] grid, int x, int y) {
    return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 1;
  }

  public static void main(String[] args) {
    int[][] grid = {{0, 1, 1, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 1}, {0, 1, 0, 0, 0}};

    ServerUpgrade su = new ServerUpgrade();
    System.out.println("Total number of days to upgrade " + su.countDays(grid));

    int[][] grid2 = {{0, 1, 1, 0, 1},
        {0, 1, 0, 1, 0},
        {0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0}};

    System.out.println("Total number of days to upgrade " + su.countDays(grid2));

  }
}
