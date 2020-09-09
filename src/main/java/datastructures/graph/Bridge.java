package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a graph find all bridges in the graph.
 * <p>
 * <p>
 * Bridges are edges in the graph, such that removing the bridges disconnects the graph, this
 * are very similar to articulation points.
 * <p>
 * Algorithm:
 * In a pair of connected vertices u-v, goal is to find a back edge from v or its subtree to
 * vertex x such that x is parent of u or part of parent sub tree.
 * <p>
 * We start with dfs to find discovery time and earliest discovery time of every vertex in the
 * graph and keep on updating disc[] and low[] .
 * Finally we need to compare disc[u] < low[v], if this is true then u-v forms a bridge.
 * <p>
 * Complexity:
 * Runtime - O(V+E) we do dfs
 * Space - O(V)
 * <p>
 * <p>
 * Reference :
 * https://www.geeksforgeeks.org/bridge-in-a-graph/
 * <p>
 * Date: 26/02/20
 *
 * @author Kushal Roy
 */
public class Bridge {

  private static int currTime = 0;

  public Set<Edge<Integer>> findBridges(Graph<Integer> g) {
    Set<Long> visited = new HashSet<>();
    Map<Long, Long> parent = new HashMap<>(); // (k,v) -> (child,parent)
    Map<Long, Integer> discoveryTime = new HashMap<>();
    Map<Long, Integer> lowTime = new HashMap<>();
    Set<Edge<Integer>> bridges = new HashSet<>();

    //Get the first vertex to start the dfs (assuming that graph is not empty)
    Vertex<Integer> startVertex = g.getVertices().entrySet().stream().findFirst().get().getValue();

    //Call recursive dfs to process all the vertices
    dfs(startVertex, visited, parent, discoveryTime, lowTime, bridges);

    return bridges;
  }

  public void dfs(Vertex<Integer> u, Set<Long> visited, Map<Long, Long> parent,
      Map<Long, Integer> disc,
      Map<Long, Integer> low, Set<Edge<Integer>> bridges) {
    visited.add(u.getId());
    disc.put(u.getId(), currTime);
    low.put(u.getId(), currTime);
    currTime++;

    for (Vertex<Integer> v : u.getAdjacentVertices()) {
      if (!visited.contains(v.getId())) {
        parent.put(v.getId(), u.getId());
        dfs(v, visited, parent, disc, low, bridges);

        //After dfs, low[v] should be updated if it encountered a vertex x whose earliest disc
        // time is less than that of u
        low.put(u.getId(), Math.min(low.get(u.getId()), low.get(v.getId())));

        //If discovery time is strictly less than earliest discovery time of v then u-v is a bridge
        if (disc.get(u.getId()) < low.get(v.getId())) {
          bridges.add(new Edge<>(u, v));
        }
      } else if (parent.get(u.getId()) != null && v.getId() != parent.get(u.getId())) {
        low.put(u.getId(), Math.min(low.get(u.getId()), disc.get(v.getId())));
      }
    }

  }

  public static void main(String[] args) {
    Graph<Integer> g1 = new Graph<>();
    g1.addEdge(1, 0);
    g1.addEdge(0, 2);
    g1.addEdge(2, 1);
    g1.addEdge(0, 3);
    g1.addEdge(3, 4);

    Graph<Integer> g2 = new Graph<>();
    g2.addEdge(0, 1);
    g2.addEdge(1, 2);
    g2.addEdge(2, 3);

    Graph<Integer> g3 = new Graph<>();
    g3.addEdge(0, 1);
    g3.addEdge(1, 2);
    g3.addEdge(2, 0);
    g3.addEdge(1, 3);
    g3.addEdge(1, 4);
    g3.addEdge(1, 6);
    g3.addEdge(3, 5);
    g3.addEdge(4, 5);

    Bridge bridge = new Bridge();
    System.out.println("Bridge graph 1 : " + bridge.findBridges(g1).toString());
    System.out.println("Bridge graph 2 : " + bridge.findBridges(g2).toString());
    System.out.println("Bridge graph 3 : " + bridge.findBridges(g3).toString());

  }

}
