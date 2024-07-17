package algorithms.backtracking;

/**
 * Given a graph find the Hamiltonian circuit in the graph.
 * <p>
 * Algorithm:
 * We start from vertex 0, add it to the hamiltonian path[] and traverse the graph. For each of
 * the adj. vertices we check if this has not been already added to the path[]. If it is added we
 * discard this vertex and move on to the the other adj. vertices. We repeat this till we
 * have traversed all the vertices and either we found if the last vertex added connects back to
 * the starting vertex.
 * <p>
 * Complexity:
 * Time - O(n!) - Recursion can be thought of as n nested loops, and in each loop the number of
 * iterations are reduced by 1(as it gets added to the path[]) - T(N) = N* (TT(N-1) + T(N-2)...)
 * = O(N!)
 *
 * <p>
 * Date: 25/06/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/
 */
public class HamiltonianCycle {

  public boolean isSafe(int[][] graph, int[] path, boolean[] visited, int v, int idx) {

    //Check if this vertex is not connected with the last added vertex in path[]
    if (graph[path[idx - 1]][v] == 0) {
      return false;
    }

    //Check if this vertex has already been visited
    if (visited[v]) {
      return false;
    }

    return true;
  }

  public void hamiltonianCycle(int[][] graph) {
    int[] path = new int[graph.length];
    boolean[] visited = new boolean[graph.length];

    path[0] = 0;
    visited[0] = true;
    if (hamiltonianCycleHelper(graph, path, 1, visited, 0)) {
      for (int value : path) {
        System.out.print(String.format("%d", value) + "  ");
      }
    } else {
      System.out.println("No Hamiltonian cycle detected");
    }
  }

  public boolean hamiltonianCycleHelper(int[][] graph, int[] path, int idx, boolean[] visited,
      int v) {

    //If we already added all the vertices return
    if (idx == path.length) {

      //If there is an edge from last to first vertex
      return graph[path[idx - 1]][path[0]] == 1;
    }

    for (int i = 0; i < graph.length; i++) {
      if (isSafe(graph, path, visited, i, idx)) {
        path[idx] = i;
        //Mark this vertex as already visited
        visited[i] = true;

        if (hamiltonianCycleHelper(graph, path, idx + 1, visited, i)) {
          return true;
        }

        path[idx] = -1;
        visited[i] = false;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] graph1 = {{0, 1, 0, 1, 0},
        {1, 0, 1, 1, 1},
        {0, 1, 0, 0, 1},
        {1, 1, 0, 0, 1},
        {0, 1, 1, 1, 0},
    };

    HamiltonianCycle hc = new HamiltonianCycle();
    hc.hamiltonianCycle(graph1);
  }

}
