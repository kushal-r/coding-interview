package datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Given a directed acyclic graph, find all connected components in the graph.
 * We will be using Tarjan’s algorithm for finding all connected components
 * <p>
 * <p>
 * Algorithm:
 * DFS forms tree/forest, scc forms subtrees of the dfs, we need to find head of such subtrees.
 * Do DFS on the graph and find disc[] and low[] for every vertices in the graph.
 * Each visited vertex should also be added to a stack.
 * Whenever we encounter a vertex where low[u] == disc[u], we start pop ing vertices from the
 * stack and printing them.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 28/02/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
 */
public class TarjansSCC {

  private static int time = 0;

  public List<Set<Long>> scc(Graph<Integer> graph) {
    Set<Long> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();
    Map<Long, Integer> disc = new HashMap<>();
    Map<Long, Integer> low = new HashMap<>();
    List<Set<Long>> allSCC = new ArrayList<>();

    graph.getVertices().entrySet().stream().filter(e -> !visited.contains(e.getKey()))
        .forEach(e -> dfs(e.getValue(),
            allSCC,
            visited,
            stack,
            disc,
            low));

    return allSCC;
  }

  public void dfs(Vertex<Integer> u, List<Set<Long>> allSCC, Set<Long> visited,
      Stack<Vertex<Integer>> stack, Map<Long,
      Integer> disc, Map<Long, Integer> low) {
    visited.add(u.getId());
    stack.push(u);
    disc.put(u.getId(), time);
    low.put(u.getId(), time);
    time++;

    for (Vertex<Integer> v : u.getAdjacentVertices()) {
      if (!visited.contains(v.getId())) {
        dfs(v, allSCC, visited, stack, disc, low);
        low.compute(u.getId(), (k, utime) -> Math.min(utime, low.get(v.getId())));
      }
      //Process v only if it is a back edge and not a cross edge
      else if (stack.contains(v)) {
        low.compute(u.getId(), (k, utime) -> Math.min(utime, disc.get(v.getId())));
      }
    }

    //Check if low[u] == disc[u], then this is a root of a scc
    //Keep pop-ing vertices from stack until you find u in the stack
    if (low.get(u.getId()).equals(disc.get(u.getId()))) {
      //Start new scc
      Set<Long> scc = new HashSet<>();
      Vertex<Integer> curr;
      do {
        curr = stack.pop();
        scc.add(curr.getId());

      } while (!u.equals(curr));
      allSCC.add(scc);
    }
  }

  public static void main(String[] args) {
    Graph<Integer> graph = new Graph<>(true);
    graph.addEdge(1, 0);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(2, 1);
    graph.addEdge(3, 4);

    TarjansSCC tarjansSCC = new TarjansSCC();
    System.out.println(tarjansSCC.scc(graph).toString());
  }

}
