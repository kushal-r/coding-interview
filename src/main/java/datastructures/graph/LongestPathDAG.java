package datastructures.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

/**
 * Given a DAG we have to find the longest path between a giver vertex and any other vertex.
 * <p>
 * Algorithm:
 * We need to calculate the distance of each and every vertex from the source vertex. For doing
 * this we have to find the topological order of the vertices in the graph, doing so we can
 * represent the given graph in a linear form and in order of there reachability from the source
 * vertex. Next we initialize a dist[] initialized to MIN_VALUE for all the other vertices and 0 for
 * the
 * source vertex.
 * We traverse the vertices in topological order and the adj. vertices, for every vertex u set the
 * adj.
 * dist[v] =
 * MAX(dist[u] + wt[u-v], dist[v])
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 *
 * <p>
 * Date: 04/04/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
 */
public class LongestPathDAG {

  public Optional<Integer> longestDist(Map<String, Map<String, Integer>> g, String src) {
    Map<String, Integer> dist = new HashMap<>();

    //Set all the distances as MIN value
    g.keySet().parallelStream().forEach(v -> {
      dist.put(v, Integer.MIN_VALUE);
    });

    //Set src dist as 0
    dist.put(src, 0);

    //Do topological sort
    Set<String> visited = new HashSet<>();
    Stack<String> stack = new Stack<>();
    topologicalSort(g, visited, stack);

    while (!stack.isEmpty()) {
      String v = stack.pop();

      if (dist.get(v) != Integer.MIN_VALUE) {
        //Iterate over all the adj. vertices of the source
        for (Map.Entry<String, Integer> e : g.get(v).entrySet()) {
          //Compare with the adj. vertex edge weight
          int d = Math.max(dist.get(e.getKey()), dist.get(v) + e.getValue());
          dist.put(e.getKey(), d);
        }
      }

    }

    //System.out.println(dist.toString());

    //Return the max from the dist list
    return dist.values().parallelStream().max(Comparator.naturalOrder());
  }

  public void topologicalSort(Map<String, Map<String, Integer>> g, Set<String> visited,
      Stack<String> stack) {

    for (String u : g.keySet()) {
      topologicalSortUtil(g, u, visited, stack);
    }
  }

  public void topologicalSortUtil(Map<String, Map<String, Integer>> g, String u,
      Set<String> visited, Stack<String> order) {
    if (!visited.contains(u)) {
      visited.add(u);

      for (String v : g.get(u).keySet()) {
        topologicalSortUtil(g, v, visited, order);
      }

      order.push(u);
    }
  }

  public static void main(String[] args) {
    Map<String, Map<String, Integer>> g = new LinkedHashMap<>();
    g.put("0", new HashMap<String, Integer>() {{
      put("1", 5);
      put("2", 3);
    }});
    g.put("1", new HashMap<String, Integer>() {{
      put("3", 6);
      put("2", 2);
    }});
    g.put("2", new HashMap<String, Integer>() {{
      put("4", 4);
      put("5", 2);
      put("3", 7);
    }});
    g.put("3", new HashMap<String, Integer>() {{
      put("5", 1);
      put("4", -1);
    }});
    g.put("4", new HashMap<String, Integer>() {{
      put("5", -2);
    }});
    g.put("5", new HashMap<String, Integer>() {{
    }});

    LongestPathDAG longestPathDAG = new LongestPathDAG();

    System.out.println(longestPathDAG.longestDist(g, "1").orElse(0));


  }


}
