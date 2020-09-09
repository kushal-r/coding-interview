package datastructures.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Find a the count of paths from src vertex to dest vertex
 * <p>
 * Algorithm:
 * Check if src == dest, then do count++
 * Else check if not already visited, do dfs
 * Once the backtracking call completes, remove the vertex v from visited, so it can be backtracked
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 01/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/count-possible-paths-two-vertices/
 */
public class CountAllPaths {

  private static int count = 0;

  public int countAllPaths(Graph<Integer> g, long s, long d) {
    Set<Long> visited = new HashSet<>();
    Vertex<Integer> src = g.getVertices().get(s);
    Vertex<Integer> dest = g.getVertices().get(d);

    backTrack(src, dest, visited);

    return count;
  }

  public void backTrack(Vertex<Integer> src, Vertex<Integer> dest, Set<Long> visited) {
    visited.add(src.getId());

    if (src.getId() == dest.getId()) {
      count++;
    } else {
      for (Vertex<Integer> v : src.getAdjacentVertices()) {
        if (!visited.contains(v.getId())) {
          backTrack(v, dest, visited);
        }
      }
    }

    visited.remove(src.getId());

  }


  public static void main(String[] args) {
    Graph<Integer> g = new Graph<>(true);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(0, 3);
    g.addEdge(2, 0);
    g.addEdge(2, 1);
    g.addEdge(1, 3);

    CountAllPaths countAllPaths = new CountAllPaths();
    System.out.println("No of paths : " + countAllPaths.countAllPaths(g, 2, 3));

  }

}
