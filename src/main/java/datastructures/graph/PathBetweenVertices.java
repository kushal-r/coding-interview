package datastructures.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given a graph src and dest vertices,  we need to print all paths from src to dest.
 * <p>
 * Algorithm:
 * Using either DFS or BFS to traverse through the graph.
 * DFS:
 * Start from the src vertex, keep storing the vertices in a tmp list and mark the vertices as
 * visited, until we reach the dest vertex.
 * <p>
 * BFS:
 * Iterate over the graph using a queue, push path to queue, compare the last node in the path,
 * if they match print the current path, else iterate over all the adj. vertices of the last node
 * in the path, and clone the current path into a new list and add it to the queue.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 13/03/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.geeksforgeeks.org/print-paths-given-source-destination-using-bfs/
 * https://www.geeksforgeeks.org/find-paths-given-source-destination/
 */
public class PathBetweenVertices {

  public void printPathDFS(Graph<Integer> g, Long s, Long d) {
    Set<Long> visited = new HashSet<>();
    List<Long> path = new ArrayList<>();
    Vertex<Integer> src = g.getVertices().get(s);
    Vertex<Integer> dest = g.getVertices().get(d);

    dfsUtil(src, dest, visited, path);


  }

  public void dfsUtil(Vertex<Integer> src, Vertex<Integer> dest, Set<Long> visited,
      List<Long> path) {
    visited.add(src.getId());
    path.add(src.getId());

    if (src.getId() == dest.getId()) {
      System.out.println(path);
    } else {
      for (Vertex<Integer> v : src.getAdjacentVertices()) {
        if (!visited.contains(v.getId())) {
          dfsUtil(v, dest, visited, path);

          //Remove the current vertex from path
          path.remove(path.size() - 1);
          visited.remove(v.getId());
        }
      }
    }
  }

  public void printPathBFS(Graph<Integer> g, Long s, Long d) {
    Set<Long> visited = new HashSet<>();
    List<Long> path = new ArrayList<>();
    Vertex<Integer> src = g.getVertices().get(s);
    Queue<List<Long>> q = new LinkedList<>();

    //Check if src and dest are same vertex
    if (s.equals(d)) {
      System.out.println(s);
      return;
    }

    //Add a path to q
    path.add(src.getId());
    q.offer(path);

    while (!q.isEmpty()) {
      //Pop the first path from q
      List<Long> tmpPath = q.poll();
      Long lastNode = tmpPath.get(tmpPath.size() - 1);

      if (lastNode.equals(d)) {
        System.out.println(tmpPath);
      } else {
        for (Vertex<Integer> v : g.getVertices().get(lastNode).getAdjacentVertices()) {
          if (isNotVisited(tmpPath, v.getId())) {
            //Add a new path
            List<Long> newPath = new ArrayList<>(tmpPath);
            newPath.add(v.getId());
            q.add(newPath);
          }
        }
      }
    }
  }

  public boolean isNotVisited(List<Long> path, Long v) {
    Long u = path.parallelStream().filter(e -> e.equals(v)).findFirst().orElse(null);

    return u == null;
  }

  public static void main(String[] args) {
    PathBetweenVertices pbv = new PathBetweenVertices();

    Graph<Integer> g = new Graph<>(true);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(0, 3);
    g.addEdge(2, 0);
    g.addEdge(2, 1);
    g.addEdge(1, 3);

    System.out.println("Following are all different paths using DFS");
    pbv.printPathDFS(g, 2L, 3L);

    System.out.println("Following are all different paths using BFS");
    pbv.printPathBFS(g, 2L, 3L);
  }
}
