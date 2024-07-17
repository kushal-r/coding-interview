package datastructures.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a directed acyclic graph, find all connected components in the graph.
 * We will be using Kosaraju’s algorithm for finding all connected components
 * <p>
 * <p>
 * Algorithm:
 * Do DFS on the graph - mark the vertex as visited, move to the adjacent vertices and do
 * recursive dfs, AFTER that from the last vertex in dfs, push the visited vertex into a stack
 * <p>
 * Reverse the graph - transpose
 * <p>
 * Do DFS on the reversed graph, by pop-ing vertices from the stack
 * and marking them as visited, the vertices that are accessible from this vertex are part of the
 * same scc
 *
 * <p>
 * Complexity:
 * Runtime - O(V+E) for DFS
 * Space - O(V) for the stack and visited vertices
 * <p>
 * <p>
 * Date: 16/02/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://en.wikipedia.org/wiki/Strongly_connected_component
 * https://www.youtube.com/watch?v=RpgcYiky7uw
 * https://www.geeksforgeeks.org/strongly-connected-components/
 */
public class StronglyConnectedComponents {

  public void dfs(Set<Long> visited, Stack<Vertex<Integer>> stack, Graph<Integer> g) {
    //Iterate over all the vertices of the graph, and process if it has not been already
    // visited
    g.getVertices().entrySet().parallelStream().forEach(e -> {
      if (!visited.contains(e.getKey())) {
        dfsHlper(visited, stack, e.getValue());
      }
    });
  }

  public void dfsHlper(Set<Long> visited, Stack<Vertex<Integer>> stack, Vertex<Integer> v) {
    visited.add(v.getId());
    for (Vertex<Integer> av : v.getAdjacentVertices()) {
      //If this vertex has not been visited before, do dfs on this vertex
      if (!visited.contains(av.getId())) {
        dfsHlper(visited, stack, av);
      }
    }
    stack.push(v);
  }

  public void dfsHlperForReverse(Set<Long> visited, Set<Long> scc, Vertex<Integer> v) {
    visited.add(v.getId());
    for (Vertex<Integer> av : v.getAdjacentVertices()) {
      //If this vertex has not been visited before, do dfs on this vertex
      if (!visited.contains(av.getId())) {
        dfsHlperForReverse(visited, scc, av);
      }
    }
    scc.add(v.getId());
  }

  public void findSCC(Graph<Integer> g) {
    // DFS on original graph and fill up the stack
    Set<Long> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();
    dfs(visited, stack, g);

    //Reverse the graph
    Graph<Integer> reverse = g.transpose();
    visited.clear();
    List<Set<Long>> allSCC = new ArrayList<>();

    //Do dfs on the reversed graph
    while (!stack.isEmpty()) {
      Vertex<Integer> v = stack.pop();
      if (!visited.contains(v.getId())) {
        Set<Long> scc = new HashSet<>();
        dfsHlperForReverse(visited, scc, reverse.getVertices().get(v.getId()));
        allSCC.add(scc);
      }
    }
    System.out.println("Strongly connected components : " + allSCC.toString());
  }


  public static void main(String[] args) {
    Graph<Integer> graph = new Graph<>(true);
    graph.addEdge(1, 0);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(2, 1);
    graph.addEdge(3, 4);

    StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents();
    stronglyConnectedComponents.findSCC(graph);
  }

}
