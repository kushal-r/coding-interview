package datastructures.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Given a undirected connected graph G(V,E), a spanning tree of the graph is a tree which
 * connects all the vertices of G, and is a sub-graph of G containing (V-1) edges
 * A minimal spanning tree(MST) is a spanning tree where the cost or sum of weights of the edges
 * is minimum among all the spanning trees of the graph.
 * <p>
 * Algorithm:
 * This algorithm is very much similar to Kruskal's algorithm for finding the MST, but instead of
 * adding edges to the disjoint set we need to start with a random vertex add to the list of MST,
 * and we will be iterating over all the adj. vertices and pick the next smallest connected
 * vertex which has
 * the edge with lowest weight for the next iteration, suppose we find a vertex whose weight is
 * less than the current assigned weight we will need to update this with the lowest weight. We
 * then keep on processing the new vertex and all its adj.
 * vertices until we have (V-1) vertices in the MST set. While traversing the graph, we have to
 * keep in mind that we will have to avoid cycles, for this we keep a visited set containing
 * vertices that we have already visited.
 * <p>
 * Complexity:
 * Time - O(V + ElogV)
 * Space - O(V)
 * <p>
 * Date: 14/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/tutorial/
 * https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
 * https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/
 */
public class PrimsAlgorithm {

  public Map<Long, Long> mst(Graph<Long> g) {
    Set<Long> visited = new HashSet<>();
    Queue<VertexWeight<Long>> q =
        new PriorityQueue<>(
            Comparator.comparingInt(VertexWeight::getWeight));
    Map<Long, Integer> vertexWeightMap = new HashMap<>();
    Map<Long, Long> mst = new HashMap<>();

    //Fill the queue and initialize vertexWeightMap
    g.getVertices().forEach((key, value) -> {
      VertexWeight<Long> vw;
      if (key != 0) {
        vw = new VertexWeight<>(Integer.MAX_VALUE, value);
      } else {
        vw = new VertexWeight<>(0, value);
      }

      vertexWeightMap.put(vw.getV().getId(), vw.getWeight());
      q.offer(vw);
    });

    while (!q.isEmpty()) {
      VertexWeight<Long> u = q.poll();
      visited.add(u.getV().getId());

      //Iterate over all adj. vertices v of u
      u.getV().getEdges().forEach(x -> {
        Vertex<Long> v = x.getVertex2();
        int wt = x.getWeight();

        if (!visited.contains(v.getId())) {
          //If the adj. vertex v weight is less than the older value of v, update v weight in vertexWeightMap
          if (vertexWeightMap.get(v.getId()) > wt) {
            vertexWeightMap.put(v.getId(), wt);
            mst.put(v.getId(), u.getV().getId());
          }
        }
      });

    }

    return mst;
  }

  public static void main(String[] args) {
    Graph<Long> g = new Graph<>();
    g.addEdge(0, 1, 10);
    g.addEdge(0, 2, 6);
    g.addEdge(0, 3, 5);
    g.addEdge(1, 3, 15);
    g.addEdge(3, 2, 4);

    PrimsAlgorithm pa = new PrimsAlgorithm();
    System.out.println(pa.mst(g));
  }

  private static class VertexWeight<T> {

    int weight;
    Vertex<T> v;

    public VertexWeight(int weight, Vertex<T> v) {
      this.weight = weight;
      this.v = v;
    }

    public int getWeight() {
      return weight;
    }

    public Vertex<T> getV() {
      return v;
    }

    @Override
    public String toString() {
      return "VertexWeight{" +
          "weight=" + weight +
          ", v=" + v +
          '}';
    }
  }


}

