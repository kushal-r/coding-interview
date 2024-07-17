package algorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * Given nXn matrix we have start from (0,0) and reach (n,n), The output should print all
 * possible routes that can be taken to reach the final position.
 * <p>
 * Algorithm:
 * Backtracking - We start from 0,0 and for each new cell visited we try to find out if it is
 * possible to reach the final position.Marking the cell as visited and keeping track of the
 * corresponding move (U,D,L,R). Once we reach the final position we backtrack and mark all the
 * cells in the path as not visited for later traversals.
 * <p>
 * Complexity:
 * Time - O(4^(n^2))
 * Space - O(n^2) for saving the visited path.
 * <p>
 * Date: 22/06/20
 *
 * @author Kushal Roy
 * Referances:
 * https://www.geeksforgeeks.org/rat-in-a-maze-problem-when-movement-in-all-possible-directions-is-allowed/
 */
public class RatInMaze {

  private static final int[] xMove = {0, 1, 0, -1};
  private static final int[] yMove = {1, 0, -1, 0};
  private static final String[] direction = {"R", "D", "L", "U"};

  public boolean isValidMove(int[][] m, boolean[][] visited, int x, int y) {
    //Check if this is inside the matrix and the cell is not previously visited
    if (x >= 0 && x < m.length && y >= 0 && y < m[0].length && m[x][y] != 0 && !visited[x][y]) {
      return true;
    }
    return false;
  }


  public Set<List<String>> path(int[][] m) {
    Set<List<String>> paths = new HashSet<>();
    boolean[][] visited = new boolean[m.length][m[0].length];

    List<String> path = new ArrayList<>();
    pathHelper(m, visited, path, paths, 0, 0);
    return paths;
  }


  public void pathHelper(int[][] m, boolean[][] visited, List<String> path,
      Set<List<String>> paths, int x, int y) {

    //Check if we reached end of the matrix
    if (x == m.length - 1 && y == m[0].length - 1 || m[x][y] == 0) {
      List<String> currPath = new ArrayList<>(path);
      paths.add(currPath);
      return;
    }

    if (!visited[x][y] && m[x][y] != 0) {
      visited[x][y] = true;

      for (int i = 0; i < 4; i++) {
        int nextX = x + xMove[i];
        int nextY = y + yMove[i];
        if (isValidMove(m, visited, nextX, nextY)) {
          path.add(direction[i]);
          pathHelper(m, visited, path, paths, nextX, nextY);

          //Remove this from the path
          path.remove(path.size() - 1);
        }
      }
      //Mark this as not visited so this will be picked up in the next iteration
      visited[x][y] = false;
    }

    return;
  }


  public static void main(String[] args) {
    int[][] m = {{1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 0, 1},
        {0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1}};

    RatInMaze rim = new RatInMaze();
    Set<List<String>> paths = rim.path(m);

    paths.parallelStream().map(x -> StringUtils.join(x, " -> ")).forEach(System.out::println);

  }
}
