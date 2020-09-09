package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a undirected graph, find all articulation points in the graph
 * <p>
 * Articulation Points are vertices in the graph, such that removing that vertex disconnects the
 * graph. AP's are mainly used for finding single point of failures in a network.
 * <p>
 * <p>
 * Properties:
 * Vertex is an Articulation point if it satisfies any one of the following conditions:
 * Rule 1:  V1 is the root vertex, and it has at least two children, which are not connected to each
 * other directly, except through the common root vertex
 * Rule 2:  V1 is not root of dfs tree and discovery time of vertex is <= lowTime of any adjacent
 * vertex
 * <p>
 * Leaf nodes cannot be an articulation point
 * <p>
 * <p>
 * Algorithm:
 * The basic idea is to find a "back edge" from the current vertex to vertex that is discovered
 * before the parent of the current vertex. Presence of a back edge means that even if the parent
 * vertex is removed from the graph, the current vertex will still be connected with the graph
 * through this back edge.
 * <p>
 * Do DFS traversal starting from a vertex, for each visited vertex, we need to keep track of the
 * discovery time of the vertex, the dfs parent of the current vertex and
 * the low time of the current vertex, initially low time will be set to the discovery time
 * <p>
 * If vertex u has child vertex v, and the earliest discovered vertex that can be reached from
 * any of the vertices in the subtree of v has low time greater than or equal to discovery
 * time of u, then v does not have a back edge, and u will be an articulation point.
 * <p>
 * The algorithm iterates over all the adjacent vertices, and if it finds a vertex v that has
 * already been visited, it updates the low time low[u] = MIN(low[u],disc[v])
 * If it is not visited, it sets parent of v to u and makes recursive call. When the recursive
 * call ends low[v] will be updated to the discovery time of earliest discovered vertex that is
 * reachable from any vertex in subtree of v. So we can set low[u] = MIN (low[u],low[v])
 * <p>
 * Finally we check if the current vertex is a articulation point by checking the properties of
 * AP on each vertex. If u is not root vertex - check if low[v] >= disc[u] - Then no back edge
 * from v to root, so u is an articulation point
 * <p>
 * <p>
 * Complexity:
 * Runtime - O(V+E) because of DFS,  we visit each vertex and iterate over all its edges
 * Space - O(V) for visited and time arr
 *
 * <p>
 * Date: 23/02/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * https://www.youtube.com/watch?v=2kREIkF9UAs
 * https://www.hackerearth.com/practice/algorithms/graphs/articulation-points-and-bridges/tutorial/
 */
public class ArticulationPoints {

  private int currTime = 0;

  public Set<Vertex<Integer>> findArticulationPoints(Graph<Integer> g) {
    Set<Long> visited = new HashSet<>();
    Map<Long, Long> parent = new HashMap<>(); // (k,v) -> (child,parent)
    Map<Long, Integer> discoveryTime = new HashMap<>();
    Map<Long, Integer> lowTime = new HashMap<>();
    Set<Vertex<Integer>> articulationPoints = new HashSet<>();

    //Get the first vertex to start the dfs (assuming that graph is not empty)
    Vertex<Integer> startVertex = g.getVertices().entrySet().stream().findFirst().get().getValue();

    //Call recursive dfs to process all the vertices
    dfsHlpr(startVertex, articulationPoints, visited, parent, discoveryTime, lowTime);

    return articulationPoints;
  }

  /**
   * @param u                  Current vertex
   * @param articulationPoints Articulation points
   * @param visited
   * @param parent             child-parent mapping
   * @param discovery          Discovery time of each vertex
   * @param low                Earliest discovery time of a vertex to which v or its subtree  has a back edge
   *                           v visited(tree edge) : low[u] = MIN(low[u],disc[v])
   *                           v not visited(back edge) : low[u] = MIN(low[u],low[v])
   */
  public void dfsHlpr(Vertex<Integer> u, Set<Vertex<Integer>> articulationPoints, Set<Long> visited,
      Map<Long, Long> parent,
      Map<Long, Integer> discovery, Map<Long, Integer> low) {

    visited.add(u.getId());
    //Initially disc and low are both set to currTime
    discovery.put(u.getId(), currTime);
    low.put(u.getId(), currTime);
    currTime++;
    //Initial child count of u
    int childCount = 0;

    List<Vertex<Integer>> adjVertices = u.getAdjacentVertices();
    for (Vertex<Integer> v : adjVertices) {

      if (!visited.contains(v.getId())) {
        //Increment child count
        childCount++;
        //Make u the parent of v
        parent.put(v.getId(), u.getId());
        dfsHlpr(v, articulationPoints, visited, parent, discovery, low);
        //low[v] should have now updated to the discovery time of the earliest vertex x that is
        // reachable from any subtree of v, if x exists otherwise low[v] == disc[v]
        //Update low[u] =  MIN(low[v] and low[u]) as we might have discovered a back edge from v
        // to parent of u
        low.put(u.getId(), Math.min(low.get(u.getId()), low.get(v.getId())));

        //Rule 1 : This is the root of the graph
        if (parent.get(u.getId()) == null && childCount > 1) {
          articulationPoints.add(u);
        }
        //Rule 2 :
        if (parent.get(u.getId()) != null && (discovery.get(u.getId()) <= low.get(v.getId()))) {
          articulationPoints.add(u);
        }
      }
      //If v has already been visited we need to updated low[u] to check if we have found a back
      // edge
      else {
        low.put(u.getId(), Math.min(low.get(u.getId()), discovery.get(v.getId())));
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

    ArticulationPoints ap = new ArticulationPoints();
    System.out.println("Articular points graph 1 : " + ap.findArticulationPoints(g1).toString());
    System.out.println("Articular points graph 2 : " + ap.findArticulationPoints(g2).toString());
    System.out.println("Articular points graph 3 : " + ap.findArticulationPoints(g3).toString());

  }

}
