package datastructures.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Given a un directed graph we have to find if a cycle exists in the graph.
 * <p>
 * Algorithm:
 * We can use disjoint sets for detecting cycle in a graph or we can use DFS on the graph.
 * Here we will be using Disjoint sets for detecting cycle.
 * Steps:
 * 1) For each vertex in the graph call makeSet() to create sets.
 * 2) Iterate over edges one by one, and for each vertices of the edge, call findParent() to get
 * the parent vertex and if the parent of both the vertices are same, there is a cycle in the graph.
 * Else we have to call union() of both the vertices.
 * <p>
 * The main logic behind this is that if both ends of the edge exists in the same disjoint set
 * then there must be a previous edge that has connected one of them to this set, and processing
 * this edge would complete the cycle.
 * <p>
 * Complexity:
 * Time - O(m alpha n) -> O(4m) -> O(m)
 * Space - O(n)
 * <p>
 * Date: 10/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.youtube.com/watch?v=n_t0a_8H8VY
 */
public class DetectCycleDisjointSet {

  public boolean detectCycle(Map<String, Set<String>> g) {

    DisjointSet<String> disjointSet = new DisjointSet<>();

    // Call makeSet() to initialize disjoint nodes
    for (Entry<String, Set<String>> e : g.entrySet()) {
      disjointSet.makeSet(e.getKey());
    }

    //Iterate over all the edges of the graph, u-v is the vertices representing the edge
    for (Entry<String, Set<String>> e : g.entrySet()) {
      String u = e.getKey();
      String parentU = disjointSet.findSet(u);

      for (String v : e.getValue()) {
        String parentV = disjointSet.findSet(v);
        if (parentU.equals(parentV)) {
          return true;
        } else {
          disjointSet.union(u, v);
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Map<String, Set<String>> g = new HashMap<>();
    g.put("0", new HashSet<>(Arrays.asList("1", "2")));
    g.put("1", new HashSet<>(Arrays.asList()));
    g.put("2", new HashSet<>(Arrays.asList()));

    DetectCycleDisjointSet detect = new DetectCycleDisjointSet();

    System.out.println("Graph contains cycle ? " + detect.detectCycle(g));
  }
}
