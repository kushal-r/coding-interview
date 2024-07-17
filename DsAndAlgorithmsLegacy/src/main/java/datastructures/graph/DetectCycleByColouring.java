package datastructures.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Given a directed graph, we need to find if tthe graph contains any cycles.
 * <p>
 * Algorithm:
 * We can use DFS with a visited set to check if we encounter any back edge while doing the dfs.
 * Another approach is using graph colouring.
 * We mark the vertices as WHITE - Not processed, GREY - In process and BLACK - Finished
 * processing, and then we do dfs on the graph, while doing the dfs if we encounter a GREY vertex
 * from the child vertices, we have found a cycle.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 30/03/20
 *
 * @author Kushal Roy
 */
public class DetectCycleByColouring {

  private static final int WHITE = 0, GREY = 1, BLACK = 2;

  //DFS util
  public boolean isCycle(Map<String, Set<String>> g, String v, Map<String, Integer> colour) {
    //Mark the vertex as under processing
    colour.put(v, GREY);

    //Iterate over all the adj. vertices of v
    for (String u : g.get(v)) {
      if (colour.get(u) == GREY) {
        return true;
      }
      if (isCycle(g, u, colour)) {
        return true;
      }
    }
    colour.put(v, BLACK);
    return false;
  }

  public boolean detectCycle(Map<String, Set<String>> g) {

    //Initialize the colours to WHITE - Not processed
    Map<String, Integer> colour = new ConcurrentHashMap<>();
    g.entrySet().parallelStream().forEach(v -> colour.put(v.getKey(), WHITE));

    for (String v : g.keySet()) {
      if (colour.get(v) == WHITE) {
        if (isCycle(g, v, colour)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Map<String, Set<String>> g = new LinkedHashMap<>();
    g.put("0", new HashSet<>(Arrays.asList("1", "2")));
    g.put("1", new HashSet<>(Collections.singletonList("2")));
    g.put("2", new HashSet<>(Arrays.asList("0", "3")));
    g.put("3", new HashSet<>(Arrays.asList("0", "2", "4")));
    g.put("4", new HashSet<>(Collections.singletonList("3")));

    DetectCycleByColouring detectCycleByColouring = new DetectCycleByColouring();
    System.out.println("Graph g has cycle ? " + detectCycleByColouring.detectCycle(g));

    Map<String, Set<String>> g2 = new LinkedHashMap<>();
    g2.put("0", new HashSet<>(Arrays.asList("1", "2")));
    g2.put("1", new HashSet<>(Collections.singletonList("2")));
    g2.put("2", new HashSet<>(Arrays.asList("3")));
    g2.put("3", new HashSet<>(Arrays.asList("4")));
    g2.put("4", new HashSet<>(Arrays.asList()));

    System.out.println("Graph g has cycle ? " + detectCycleByColouring.detectCycle(g2));

  }

}
