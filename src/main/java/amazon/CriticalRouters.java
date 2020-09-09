package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of edges [u-v], we have to find all the articulation points/ cut vertices in the
 * graph.
 * <p>
 * Algorithm:
 * A given vertex is an articulation point if it has any of the following properties:
 * 1. The vertex is the root vertex and it has at least 2 adj. vertices which are connected only
 * via this parent vertex.
 * 2. The vertex is a non root vertex and the discovery time of the vertex disc[u] <= low[v] low
 * time of adj. vertices. i.e. there are no back edges from any of the adj. sub graph to the
 * parent of the vertex u.
 * <p>
 * We will be doing DFS from a vertex u, and for each of its adj. sub graphs, we keep track of
 * the initial discovery time disc and the latest low time, initially low == disc time, but after
 * traversing the adj. sub graphs v, we will be updating the low[u] as follows.
 * <p>
 * If adj. vertex is not visited we will traversing the sub graph with start from v, once the
 * traversal is complete will be having updated low[] for all the adj. vertices of the child
 * vertex low[u] = MIN(low[u], low[v]) and if there is back edge we will be having a low[] value
 * smaller than disc[u].
 * If adj. vertex is already visited, we have found a back edge, update low[u] = MIN(low[u],
 * disc[v]). In case of back edge this disc[v] will be smaller than disc[u].
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * Date: 18/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assess%3C!--%E8%B0%B7%E6%AD%8Coff:%20snippet--%3E%3Cdiv%20class=
 * https://leetcode.com/discuss/interview-question/436073/
 */
public class CriticalRouters {

  private static int time = 1;

  public List<Integer> findCriticalRouters(int numNodes, int numEdges, List<Integer[]> edges) {
    Map<Integer, Set<Integer>> g = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> disc = new HashMap<>();
    Map<Integer, Integer> low = new HashMap<>();
    Map<Integer, Integer> parent = new HashMap<>();
    List<Integer> ap = new ArrayList<>();

    //Construct a adjacency list from the edges
    for (int i = 0; i < numNodes; i++) {
      g.put(i, new HashSet<>());
    }

    edges.parallelStream().forEach(e -> {
      g.get(e[0]).add(e[1]);
      g.get(e[1]).add(e[0]);
    });

    dfs(g, g.keySet().stream().findAny().orElse(0), parent, visited, disc, low, ap);
    return ap;
  }

  public void dfs(Map<Integer, Set<Integer>> g, int u, Map<Integer, Integer> parent,
      Set<Integer> visited,
      Map<Integer, Integer> disc, Map<Integer, Integer> low, List<Integer> ap) {

    visited.add(u);
    //Update low and discovery time
    disc.put(u, time);
    low.put(u, time);
    time++;
    int children = 0;

    for (int v : g.get(u)) {
      if (!visited.contains(v)) {
        children++;

        //Make u the parent of v
        parent.put(v, u);
        dfs(g, v, parent, visited, disc, low, ap);
        low.put(u, Math.min(low.get(v), low.get(u)));

        //Rule 1:
        if (children > 1 && parent.get(u) == null) {
          ap.add(u);
        }
        //Rule 2:
        if (disc.get(u) <= low.get(v) && parent.get(u) != null) {
          ap.add(u);
        }
      } else {
        low.put(u, Math.min(disc.get(v), low.get(u)));
      }
    }

  }

  public static void main(String[] args) {
    List<Integer[]> edges = new ArrayList<>(
        Arrays.asList(new Integer[]{0, 1}, new Integer[]{0, 2}, new Integer[]{1, 3},
            new Integer[]{2, 3}, new Integer[]{2, 5}, new Integer[]{5, 6}, new Integer[]{3, 4}));

    CriticalRouters cr = new CriticalRouters();

    System.out.println("Critical Routers : " + cr.findCriticalRouters(7, edges.size(), edges));

  }
}
