package datastructures.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Graph representation using adjacency list
 * <p>
 * Date: 16/02/20
 *
 * @author Kushal Roy
 */
public class Graph<T> {

  private Map<Long, Vertex<T>> vertices;
  private List<Edge<T>> edges;
  private boolean isDirected = false;

  /**
   * Create a undirected graph
   */
  public Graph() {
    this.vertices = new LinkedHashMap<>();
    this.edges = new ArrayList<>();
  }

  /**
   * Create a directed graph
   *
   * @param isDirected
   */
  public Graph(boolean isDirected) {
    this.isDirected = isDirected;
    this.vertices = new LinkedHashMap<>();
    this.edges = new ArrayList<>();
  }

  public Map<Long, Vertex<T>> getVertices() {
    return vertices;
  }

  public List<Edge<T>> getEdges() {
    return edges;
  }

  public void addEdge(long id1, long id2) {
    addEdge(id1, id2, 0);
  }

  public void addEdge(long id1, long id2, int w) {
    Vertex<T> v1 = null;
    if (vertices.containsKey(id1)) {
      v1 = vertices.get(id1);
    } else {
      v1 = new Vertex<>(id1);
      vertices.put(id1, v1);
    }

    Vertex<T> v2 = null;
    if (vertices.containsKey(id2)) {
      v2 = vertices.get(id2);
    } else {
      v2 = new Vertex<>(id2);
      vertices.put(id2, v2);
    }

    Edge<T> edge = new Edge<>(v1, v2, w, isDirected);
    edges.add(edge);
    v1.addAdjacentVertex(edge, v2);

    // If this is a un-directed graph then v1 is reachable from v2
    if (!isDirected) {
      v2.addAdjacentVertex(edge, v1);
    }
  }

  public void addEdge(long id1, long id2, T data) {
    addEdge(id1, id2, 0, data);
  }

  public void addEdge(long id1, long id2, int w, T data) {
    Vertex<T> v1 = null;
    if (vertices.containsKey(id1)) {
      v1 = vertices.get(id1);
    } else {
      v1 = new Vertex<>(id1, data);
      vertices.put(id1, v1);
    }

    Vertex<T> v2 = null;
    if (vertices.containsKey(id2)) {
      v2 = vertices.get(id2);
    } else {
      v2 = new Vertex<>(id2, data);
      vertices.put(id2, v2);
    }

    Edge<T> edge = new Edge<>(v1, v2, w, isDirected);
    edges.add(edge);
    v1.addAdjacentVertex(edge, v2);

    // If this is a un-directed graph then v1 is reachable from v2
    if (!isDirected) {
      v2.addAdjacentVertex(edge, v1);
    }
  }

  // ------------------- Traversals -------------------//


  /**
   * Complexity :
   * Time - O(E+V) We visit each vertex and iterate over all its edges
   * Space - O(V) For using the Queue and the visited set
   */
  public void doBFS(Long id) {
    Set<Long> visited = new HashSet<>();
    Queue<Long> q = new LinkedList<>();
    q.offer(id);
    visited.add(id);

    while (!q.isEmpty()) {
      Long currVertex = q.poll();
      Vertex<T> curr = this.vertices.get(currVertex);
      System.out.print(curr.getId() + " ");

      for (Vertex<T> ver : curr.getAdjacentVertices()) {
        if (!visited.contains(ver.getId())) {
          q.offer(ver.getId());
          visited.add(ver.getId());
        }
      }
    }
  }

  /**
   * Complexity :
   * Time - O(E+V) We visit each vertex and iterate over all its edges
   * Space - O(V) For storing the visited vertices
   */
  public void doDFS() {
    Set<Long> visited = new HashSet<>();

    // Loop through all the vertices, so that in case of disconnected graph we would still print
    // all the vertices
    for (Entry<Long, Vertex<T>> e : vertices.entrySet()) {
      if (!visited.contains(e.getKey())) {
        dfsHelper(e.getValue(), visited);
      }
    }
  }

  public void dfsHelper(Vertex<T> vertex, Set<Long> visited) {
    visited.add(vertex.getId());
    System.out.print(vertex.getId() + " ");

    List<Vertex<T>> adjacentVertices = vertex.getAdjacentVertices();
    for (Vertex<T> v : adjacentVertices) {
      if (!visited.contains(vertex.getId())) {
        dfsHelper(v, visited);
      }
    }
  }

  public void topologicalSort() {
    Set<Long> visited = new HashSet<>();
    Stack<Vertex<T>> stack = new Stack<>();

    //In case of dis-connected graph, all the vertices will still be processed
    for (Entry<Long, Vertex<T>> e : this.vertices.entrySet()) {
      topologicalHlpr(e.getValue(), visited, stack);
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.pop().getId() + " ");
    }

  }

  public void topologicalHlpr(Vertex<T> vertex, Set<Long> visited, Stack<Vertex<T>> stack) {
    if (!visited.contains(vertex.getId())) {
      visited.add(vertex.getId());

      //Loop through all the adjacent vertices and mark them as visited
      for (Vertex<T> v : vertex.getAdjacentVertices()) {
        topologicalHlpr(v, visited, stack);
      }

      //After all adjacent vertices has been been processed, push the current vertex into stack
      stack.push(vertex);
    }
  }

  public Graph<T> transpose() {
    Graph<T> transpose = new Graph<>(true);
    //Iterate over all the vertices of the graph
    for (Entry<Long, Vertex<T>> e : this.vertices.entrySet()) {
      //Reverse the direction of the adjacent vertices (v1 -> v2 convert to v2 -> v1)
      e.getValue().getAdjacentVertices().parallelStream()
          .forEach(v -> transpose.addEdge(v.getId(), e.getKey()));
    }

    return transpose;
  }

  public void printGraph() {
    for (Entry<Long, Vertex<T>> e : this.vertices.entrySet()) {
      System.out.print(e.getKey() + " --> ");
      e.getValue().getAdjacentVertices().forEach(v -> System.out.print(v.getId() + " "));
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Graph<Integer> g = new Graph<>(true);
    g.addEdge(0, 1);
    g.addEdge(0, 4);
    g.addEdge(0, 3);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    g.addEdge(4, 1);
    g.addEdge(4, 3);

    System.out.println("\nBFS traversal of the graph:");
    g.doBFS(4L);
    System.out.println("\nDFS traversal of the graph:");
    g.doDFS();
    System.out.println("\nTopological sort of the graph:");
    g.topologicalSort();
    System.out.println("\nOriginal graph:");
    g.printGraph();
    Graph<Integer> transpose = g.transpose();
    System.out.println("\nTranspose graph:");
    transpose.printGraph();
  }

}

// ------------------- Vertex -------------------//

class Vertex<T> {

  private long id;
  private T data;

  /**
   * List of incident edges or degree of the vertex
   */
  private List<Edge<T>> edges = new ArrayList<>();
  private List<Vertex<T>> adjacentVertices = new ArrayList<>();

  public Vertex(long id) {
    this.id = id;
  }

  public Vertex(long id, T data) {
    this.id = id;
    this.data = data;
  }

  public long getId() {
    return id;
  }

  public T getData() {
    return data;
  }

  public List<Edge<T>> getEdges() {
    return edges;
  }

  public List<Vertex<T>> getAdjacentVertices() {
    return adjacentVertices;
  }

  /**
   * List of adjacent vertices, If this vertex is accessible from another vertex there must be an
   * edge connecting them Always update <code>edges</code> list if an entry is being made to this
   * list of vertices
   */
  public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
    edges.add(e);
    adjacentVertices.add(v);
  }

  public int getDegree() {
    return this.edges.size();
  }

  @Override
  public String toString() {
    return "Vertex{" +
        "id=" + id +
        ", data=" + data +
        '}';
  }
}

// ------------------- Edge -------------------//

class Edge<T> {

  private boolean isDirected = false;
  private Vertex<T> vertex1;
  private Vertex<T> vertex2;
  private int weight;

  public Edge() {
  }

  public Edge(Vertex<T> v1, Vertex<T> v2) {
    this.vertex1 = v1;
    this.vertex2 = v2;
  }

  public Edge(Vertex<T> v1, Vertex<T> v2, int w) {
    this.vertex1 = v1;
    this.vertex2 = v2;
    this.weight = w;
  }

  public Edge(Vertex<T> v1, Vertex<T> v2, int w, boolean isDirected) {
    this.vertex1 = v1;
    this.vertex2 = v2;
    this.weight = w;
    this.isDirected = isDirected;
  }

  public Edge(Vertex<T> v1, Vertex<T> v2, boolean isDirected) {
    this.vertex1 = v1;
    this.vertex2 = v2;
    this.isDirected = isDirected;
  }

  public boolean isDirected() {
    return isDirected;
  }

  public Vertex<T> getVertex1() {
    return vertex1;
  }

  public Vertex<T> getVertex2() {
    return vertex2;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return "Edge{" +
        "isDirected=" + isDirected +
        ", vertex1=" + vertex1 +
        "<--> vertex2=" + vertex2 +
        ", weight=" + weight +
        "}\n";
  }
}

class WeightComparator implements Comparator<Edge<Integer>> {

  @Override
  public int compare(Edge<Integer> o1, Edge<Integer> o2) {
    Integer w1 = o1.getWeight();
    return w1.compareTo(o2.getWeight());
  }
}
