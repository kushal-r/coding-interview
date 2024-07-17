package datastructures.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Find mother vertex in a connected directed graph g
 * A vertex through which all other vertices of the graph can be visited is known as a mother vertex
 * There can be more than one mother vertex in a graph.
 * <p>
 * Algorithm:
 * If a mother vertex exists the graph, then this will be the last vertex to processed while
 * doing dfs on the graph.
 * While doing dfs, keep track of the last finished vertex.
 * Do dfs/bfs again, starting from this vertex and all the vertices are reachable from this
 * vertex then this is a mother vertex
 * We use the visited[] to keep tack of the visited vertices.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 29/02/20
 *
 * @author Kushal Roy
 * <p>
 * Reference:
 * https://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/
 */
public class MotherVertex {

  private static long mother = 0;

  public long findMother(Graph<Integer> g) {
    Set<Long> visited = new HashSet<>();

    g.getVertices().entrySet().stream().filter(e -> !visited.contains(e.getKey()))
        .forEach(e -> {
          dfsHelpr(e.getValue(),
              visited
          );
          //Keep track of the last visited vertex in the dfs
          mother = e.getKey();
        });
    visited.clear();

    dfsHelpr(g.getVertices().get(mother), visited);

    if (visited.size() == g.getVertices().size()) {
      return mother;
    } else {
      return -1;
    }

  }

  public void dfsHelpr(Vertex<Integer> u, Set<Long> visited) {
    visited.add(u.getId());
    for (Vertex<Integer> v : u.getAdjacentVertices()) {
      if (!visited.contains(v.getId())) {
        dfsHelpr(v, visited);
      }
    }
  }

  public static void main(String[] args) {
    Graph<Integer> graph = new Graph<>(true);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(4, 1);
    graph.addEdge(6, 4);
    graph.addEdge(5, 6);
    graph.addEdge(5, 2);
    graph.addEdge(6, 0);

    MotherVertex mv = new MotherVertex();
    System.out.println("Mother vertex : " + mv.findMother(graph));

  }

}
