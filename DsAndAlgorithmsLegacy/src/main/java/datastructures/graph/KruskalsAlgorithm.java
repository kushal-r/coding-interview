package datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a undirected connected graph G(V,E), a spanning tree of the graph is a tree which
 * connects all the vertices of G, and is a sub-graph of G.
 * A minimal spanning tree(MST) is a spanning tree where the cost or sum of weights of the edges
 * is minimum among all the spanning trees of the graph.
 * <p>
 * MST's are primarily used in network analysis.
 * <p>
 * Algorithm:
 * Kruskal's algorithm  builds the spanning tree in a greedy approach where we keep on adding the
 * edges based on increasing weights in each iteration.
 * <p>
 * 1)Sort edges based on increasing weight.
 * 2)Start adding edge with the lowest weight.
 * 3)Keep adding edges until it forms a cycle - We will be using disjoint sets or union-find for
 * checking
 * cycle in a graph
 * In disjoint set we see if both the vertices has the same parent then it means that they belong
 * to the same disjoint set and hence there is a cycle in the graph. We will be excluding this
 * type of edges and move onto the next edges.
 * <p>
 * If we have n vertices, then MST will have n-1 edges.
 * <p>
 * Complexity:
 * Time - O(ElogV) - for sorting the edges
 * Space - O(V)
 * <p>
 * Date: 09/04/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.youtube.com/watch?v=fAuF0EuZVCk
 * https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/tutorial/
 * https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
 */
public class KruskalsAlgorithm {

  public List<Edge<Integer>> sortEdges(Graph<Integer> g) {
    g.getEdges().sort(new WeightComparator());

    return g.getEdges();
  }

  public List<Edge<Integer>> findMST(Graph<Integer> g) {
    DisjointSet<Long> ds = new DisjointSet<>();
    List<Edge<Integer>> mst = new ArrayList<>();

    //Initialize the disjoint set
    for (Long id : g.getVertices().keySet()) {
      ds.makeSet(id);
    }

    //Sort the edges based on the weight
    List<Edge<Integer>> edges = sortEdges(g);

    for (Edge<Integer> e : edges) {
      long parent1 = ds.findSet(e.getVertex1().getId());
      long parent2 = ds.findSet(e.getVertex2().getId());

      //Check if we have encountered a cycle by adding this edge, otherwise union both the vertices
      if (parent1 != parent2) {
        ds.union(e.getVertex1().getId(), e.getVertex2().getId());
        mst.add(e);
      }
    }

    return mst;
  }

  public static void main(String[] args) {
    Graph<Integer> g = new Graph<>();
    g.addEdge(0, 1, 10);
    g.addEdge(1, 3, 15);
    g.addEdge(3, 2, 4);
    g.addEdge(0, 2, 6);
    g.addEdge(0, 3, 5);

    KruskalsAlgorithm ka = new KruskalsAlgorithm();
    System.out.println("MST of the graph : " + ka.findMST(g).toString());
  }

}


