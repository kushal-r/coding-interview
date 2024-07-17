package datastructures.graph;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Disjoint set a data structure that supports three operations, namely MakeSet, Union, FindSet
 * MakeSet - Is used to initialize a set containing only one element.
 * Union - Is used to merge two sets.
 * FindSet - Is used to return the identity of a set that contains the searched element.
 * <p>
 * Algorithm:
 * Initialize a individual sets for each vertex by calling make-set method.
 * Merge two sets by calling Union, Find Set is used to fetch the parent element of the set( i.e.
 * the element whose rank is highest) which contains the elements. After merging the two sets the
 * node element with highest rank becomes the parent of the merged set.
 * We will also be doing Path Compression in case of nesting of child nodes.
 * <p>
 * Complexity:
 * m - operations
 * n - no. of vertices
 * Time - O(m alpha n) -> O(4m) -> O(m)
 * Space - O(n)
 * <p>
 * Date: 11/04/20
 *
 * @author Kushal Roy
 * Refereances:
 * https://www.youtube.com/watch?v=ID00PMy0-vE
 */
public class DisjointSet<T> {

  private final Map<T, DisjointNode<T>> disjointSet = new ConcurrentHashMap<>();

  public void makeSet(T data) {
    DisjointNode<T> disjointNode = new DisjointNode<>();
    disjointNode.setData(data);
    disjointNode.setRank(0);
    disjointNode.setParent(disjointNode);

    this.disjointSet.put(data, disjointNode);
  }

  public T findSet(T data) {
    return findParent(disjointSet.get(data)).getData();
  }

  public DisjointNode<T> findParent(DisjointNode<T> node) {
    DisjointNode<T> parent = node.getParent();

    //Recurse until we find a node, whose parent points to itself
    if (parent == node) {
      return parent;
    }

    //Recurse to parent of parent and do path compression
    node.setParent(findParent(parent));
    return node.getParent();
  }

  public void union(T data1, T data2) {
    DisjointNode<T> node1 = disjointSet.get(data1);
    DisjointNode<T> node2 = disjointSet.get(data2);

    DisjointNode<T> parent1 = findParent(node1);
    DisjointNode<T> parent2 = findParent(node2);

    //If both are in same set, do nothing
    if (parent1.equals(parent2)) {
      return;
    }

    if (parent1.getRank() == parent2.getRank()) {
      parent1.setRank(parent1.getRank() + 1);
      parent2.setParent(parent1);
    } else if (parent1.getRank() > parent2.getRank()) {
      parent1.setRank(parent1.getRank());
      parent2.setParent(parent1);
    } else {
      parent1.setParent(parent2);

    }

  }

  public static void main(String[] args) {
    DisjointSet<String> ds = new DisjointSet<>();
    ds.makeSet("1");
    ds.makeSet("2");
    ds.makeSet("3");
    ds.makeSet("4");
    ds.makeSet("5");
    ds.makeSet("6");
    ds.makeSet("7");

    ds.union("1", "2");
    ds.union("2", "3");
    ds.union("4", "5");
    ds.union("6", "7");
    ds.union("5", "6");
    ds.union("3", "7");

    System.out.println(ds.findSet("1"));
    System.out.println(ds.findSet("2"));
    System.out.println(ds.findSet("3"));
    System.out.println(ds.findSet("4"));
    System.out.println(ds.findSet("5"));
    System.out.println(ds.findSet("6"));
    System.out.println(ds.findSet("7"));

  }

  static class DisjointNode<T> {

    private int rank;
    private T data;
    private DisjointNode<T> parent;

    public DisjointNode(int rank, T data, DisjointNode<T> parent) {
      this.rank = rank;
      this.data = data;
      this.parent = parent;
    }

    public DisjointNode() {
    }

    public int getRank() {
      return rank;
    }

    public void setRank(int rank) {
      this.rank = rank;
    }

    public T getData() {
      return data;
    }

    public void setData(T data) {
      this.data = data;
    }

    public DisjointNode<T> getParent() {
      return parent;
    }

    public void setParent(DisjointNode<T> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DisjointNode)) {
        return false;
      }
      DisjointNode<?> that = (DisjointNode<?>) o;
      return getRank() == that.getRank() &&
          Objects.equals(getData(), that.getData()) &&
          Objects.equals(getParent(), that.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getRank(), getData(), getParent());
    }

    @Override
    public String toString() {
      return "DisjointNode{" +
          "rank=" + rank +
          ", data='" + data + '\'' +
          '}';
    }
  }

}


