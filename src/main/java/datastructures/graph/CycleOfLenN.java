package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given an undirected connected graph and a number n, we have to find all the cycles in the
 * graph of length n (number of vertices and edges should be n)
 * <p>
 * Algorithm:
 * We do DFS for every vertex and find all the paths of length (n-1), and check if this path ends
 * with the vertex that it started from. If so we have found a cycle.
 * We have to check for (n-1) vertices because nth vertex will the starting vertex it self.
 * <p>
 * Complicity:
 * Time - O(V - (n-1))
 * Space - O(V)
 * <p>
 * Date: 28/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/cycles-of-length-n-in-an-undirected-and-connected-graph/
 */
public class CycleOfLenN {

  private static int count = 0;

  public int countCycleOfN(Map<String, Set<String>> g, int n) {
    Set<String> visited = new HashSet<>();

    for (String u : g.keySet()) {
      List<String> path = new ArrayList<>();
      detectCycle(g, u, u, n - 1, visited, path);

      //We have already found all cycles through this, so this can be skipped
      visited.add(u);
    }

    // We have duplicate cycles as this a undirected graph, unique cycles should be half of the
    // total count
    return count / 2;
  }

  public void detectCycle(Map<String, Set<String>> g, String u, String start, int n,
      Set<String> visited,
      List<String> path) {

    visited.add(u);
    path.add(u);

    if (n == 0) {
      if (g.get(u).contains(start)) {
        //Start is in u's adj. vertices, so print this path
        count++;
        System.out.println(path.toString());
      }

      //We need to look for other paths that might go through this vertex
      visited.remove(u);
      //Remove this from path as we will try to check for the other paths as well starting from u
      path.remove(path.size() - 1);
      return;
    }

    //Iterate over all the adj. vertices of u and call detectCycle()
    for (String v : g.get(u)) {
      if (!visited.contains(v)) {
        detectCycle(g, v, start, n - 1, visited, path);
      }
    }

    //If we have reached the terminal vertex, no cycle, remove this vertex and continue with
    // other vertices
    visited.remove(u);
    path.remove(u);
  }

  public static void main(String[] args) {
    Map<String, Set<String>> g = new LinkedHashMap<>();
    g.put("0", new HashSet<>(Arrays.asList("1", "3")));
    g.put("1", new HashSet<>(Arrays.asList("0", "2", "4")));
    g.put("2", new HashSet<>(Arrays.asList("1", "3")));
    g.put("3", new HashSet<>(Arrays.asList("0", "2", "4")));
    g.put("4", new HashSet<>(Arrays.asList("1", "3")));

    CycleOfLenN cycleOfLenN = new CycleOfLenN();
    System.out
        .println("Total number of unique cycles in the graph = " + cycleOfLenN.countCycleOfN(g, 4));
  }

}
