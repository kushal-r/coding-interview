package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Given a DAG, we need to find the topological order of the vertices.
 * <p>
 * Algorithm:
 * We will be using Kahn's algorithm for finding the order.
 * A DAG has at least one vertex whose in degree is 0, and one vertex whose out degree is 0.
 * The main logic behind this is, suppose we have the longest path between u-v, since u-v is the
 * longest path there can be no incoming edge to u, and no outgoing edge from v. In that case
 * this wont be the longest path.
 * <p>
 * Compute the in degree of all the vertices, simply by iterating over the adj. vertices list for
 * each vertex and incrementing the count by 1.
 * <p>
 * Enqueue the vertices to a queue whose in degree is 0. Iterate over its adj. list and decrement
 * the in degree by 1, if the count of incident vertices becomes 0, enqueue this vertex to the
 * queue.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 *
 * <p>
 * Date: 02/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
 */
public class KahnsAlgorithmTopoSort {

  public Map<String, Integer> countInDegree(Map<String, Set<String>> g) {
    Map<String, Integer> inDegree = new ConcurrentHashMap<>();

    //This step takes O(V+E) amount of time
    g.keySet().parallelStream().forEach(v -> inDegree.put(v, 0));
    g.entrySet().parallelStream().forEach(v -> v.getValue().parallelStream().forEach(e -> {
      int count = inDegree.get(e) + 1;
      inDegree.put(e, count);
    }));

    return inDegree;
  }

  public ArrayList<String> topologicalSort(Map<String, Set<String>> g) {
    Map<String, Integer> inDegree = countInDegree(g);
    int visited = 0;
    Queue<String> q = new LinkedList<>();
    ArrayList<String> order = new ArrayList<>();

    //Enqueue all vertices whose in degree is 0
    for (Map.Entry<String, Integer> e : inDegree.entrySet()) {
      if (e.getValue() == 0) {
        q.offer(e.getKey());
      }
    }

    while (!q.isEmpty()) {
      String u = q.poll();
      visited++;

      for (String v : g.get(u)) {
        int count = inDegree.get(v) - 1;
        inDegree.put(v, count);

        //If any of the adj. vertices in-degree becomes 0, add it to the queue
        if (count == 0) {
          q.offer(v);
        }
      }
      order.add(u);
    }

    //If all the vertices are not visited then topological sort is not possible
    if (visited != g.size()) {
      return null;
    }

    return order;
  }

  public static void main(String[] args) {
    Map<String, Set<String>> g = new LinkedHashMap<>();
    g.put("0", new HashSet<>());
    g.put("1", new HashSet<>());
    g.put("2", new HashSet<>(Arrays.asList("3")));
    g.put("3", new HashSet<>(Arrays.asList("1")));
    g.put("4", new HashSet<>(Arrays.asList("1", "0")));
    g.put("5", new HashSet<>(Arrays.asList("2", "0")));

    KahnsAlgorithmTopoSort ka = new KahnsAlgorithmTopoSort();
    System.out.println("Topological Sort :" + ka.topologicalSort(g));
  }
}
