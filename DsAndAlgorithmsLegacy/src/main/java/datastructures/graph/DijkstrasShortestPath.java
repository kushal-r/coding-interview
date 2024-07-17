package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Given a connected graph and a source vertex u, we have to find the shortest path starting from u
 * and
 * covering all the other vertices exactly once.
 * <p>
 * Algorithm:
 * Greedy approach - We maintain two sets of vertices - sfSet and nonSfSet, we fill nonSfSet with
 * all the vertices and set their distance as INF except the starting vertex whose distance is
 * set to 0. We iterate over nonSfSet and pull the vertex with minimal distance. In first case it
 * would be the starting vertex. We add this to the sfSet and iteratively update the distance of
 * all the adj. vertices with the distance from the currently selected vertex if the current
 * distance is less than he distance that is set in nonSfSet we update this distance. We do this
 * until the nonSfSet is empty and all the vertices are added to the sfSet.
 * <p>
 * Complexity:
 * Time - O(E log V) in case we use Heap, otherwise it will be O(V^2)
 * Space - O(V)
 * <p>
 * Date: 02/07/20
 *
 * @author Kushal Roy
 * <p>
 * Referrance:
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * https://www.youtube.com/watch?v=lAXZGERcDf4&t=2s
 */
public class DijkstrasShortestPath {

  public void dijkstraSP(int[][] graph, Map<Integer, Integer> parent,
      Map<Integer, Integer> dist) {
    Queue<VertexWeight> minHeap = new PriorityQueue<>(
        (o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
    Set<Integer> visited = new HashSet<>();

    for (int i = 1; i < graph.length; i++) {
      VertexWeight vw = new VertexWeight(i, Integer.MAX_VALUE);
      minHeap.add(vw);
    }

    //minHeap.offer(new VertexWeight(0, 0));
    VertexWeight curr = new VertexWeight(0, 0);

    //Parent of src vertex is -1
    parent.put(0, -1);

    //Dist of src vertex from src is 0
    dist.put(0, 0);

    visited.add(0);

    while (!minHeap.isEmpty()) {
      //Iterate over all the adj. vertices of the current vertex
      for (int i = 0; i < graph[0].length; i++) {

        //If there is an edge between the vertices which is not processed
        if (graph[curr.getVertex()][i] != 0 && !visited.contains(i)) {
          int distFrmSrc =
              (dist.get(curr.getVertex()) == null ? 0 : dist.get(curr.getVertex())) + graph[curr
                  .getVertex()][i];

          for (VertexWeight vw : minHeap) {
            if (vw.getVertex() == i && vw.getWeight() > distFrmSrc) {
              minHeap.remove(vw);
              vw.setWeight(distFrmSrc);
              minHeap.offer(vw);
              parent.put(i, curr.getVertex());
              break;
            }
          }
        }
      }

      curr = minHeap.poll();
      visited.add(curr.getVertex());

      dist.put(curr.getVertex(), curr.getWeight());

    }

  }


  public void printShortestPath(int[][] graph, int src) {
    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Integer> dist = new HashMap<>();
    dijkstraSP(graph, parent, dist);

    for (int i = 1; i < graph[0].length; i++) {
      System.out.print(src + " -> " + i + "   ");
      System.out.print(dist.get(i) + "   ");

      int x = i;
      System.out.print("[");
      while (x >= 0) {
        System.out.print(x + " ");
        x = parent.get(x);
      }
      System.out.print("]");
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[][] graph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
        {4, 0, 8, 0, 0, 0, 0, 11, 0},
        {0, 8, 0, 7, 0, 4, 0, 0, 2},
        {0, 0, 7, 0, 9, 14, 0, 0, 0},
        {0, 0, 0, 9, 0, 10, 0, 0, 0},
        {0, 0, 4, 0, 10, 0, 2, 0, 0},
        {0, 0, 0, 14, 0, 2, 0, 1, 6},
        {8, 11, 0, 0, 0, 0, 1, 0, 7},
        {0, 0, 2, 0, 0, 0, 6, 7, 0}};

    DijkstrasShortestPath dsp = new DijkstrasShortestPath();
    dsp.printShortestPath(graph, 0);
  }


  static class VertexWeight {

    private int vertex;
    private Integer weight;

    public VertexWeight(int vertex, Integer weight) {
      this.vertex = vertex;
      this.weight = weight;
    }

    public int getVertex() {
      return vertex;
    }

    public Integer getWeight() {
      return weight;
    }

    public void setVertex(int vertex) {
      this.vertex = vertex;
    }

    public void setWeight(Integer weight) {
      this.weight = weight;
    }

  }
}
