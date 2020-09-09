package datastructures.graph;

/**
 * Goal is to search the given graph and return true if the searched element is present at a
 * vertex, false otherwise.
 * <p>
 * Algorithm:
 * This algorithm is mainly used for checking weather the given node is present in the tree or
 * graph, so it always returns true/false.
 * The basic idea is that we need to limit our dfs to search till the pre defined level, and
 * return when it hits the node or reached pre defined level, we do this multiple times till we
 * reach the leaf nodes of this tree or graph.
 * We start from the 0th level(root) and iteratively increase the depth of our search.
 * The top levels are visited multiple times, whereas the bottom or leaf level is visited only
 * once, this is still an optimization in case the tree is hugs as the maximum nodes resides on
 * the bottom levels compared to the top level.
 * <p>
 * Complicity:
 * This is mainly used if we need to process a huge tree in limited amount of memory and where
 * there is no restriction the processing time.
 * Time - O(b^d) b -branching factor, d - depth
 * Space - O(d) d - depth
 *
 *
 * <p>
 * Date: 26/03/20
 *
 * @author Kushal Roy
 * Referances:
 * https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
 */
public class IDDFS {

  public boolean iddfs(Graph<Integer> g, long root, long val, int maxDepth) {

    Vertex<Integer> rootNode = g.getVertices().get(root);
    for (int i = 0; i < maxDepth; i++) {
      if (dls(rootNode, val, i)) {
        return true;
      }
    }

    return false;
  }

  //Depth limited search
  public boolean dls(Vertex<Integer> v, long val, int depth) {
    if (v.getId() == val) {
      return true;
    }

    if (depth <= 0) {
      return false;
    }

    for (Vertex<Integer> u : v.getAdjacentVertices()) {
      if (dls(u, val, depth - 1)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Graph<Integer> g = new Graph<>();
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 5);
    g.addEdge(2, 6);

    IDDFS iddfs = new IDDFS();

    if (iddfs.iddfs(g, 0, 6L, 3)) {
      System.out.println("Target is reachable from source within max depth");
    } else {
      System.out.println("Target is not reachable from source within max depth");
    }
  }


}
