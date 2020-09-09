package datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * Given a connected graph, we have to find if there exists a path starting from a vertex V to
 * any of the other vertices, such that the sum of the weights of the edges connecting them is
 * more than or equal to K.
 * <p>
 * <p>
 * Algorithm:
 * Start at the vertex V and iterate over all the connected vertices, and recursively go through
 * each of the connected vertices and keeping track if we already reached sum of K.
 * Also keeping track of already visited vertices to avoid cycles.
 * <p>
 * Complexity:
 * Time - O(V!)
 * Space - O(V)
 * <p>
 * Date: 01/06/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://www.geeksforgeeks.org/find-if-there-is-a-path-of-more-than-k-length-from-a-source/
 */
public class PathWithLenK {

  public void detectEdgeSum(Map<Long, Map<Long, Integer>> g, long origin, int k) {
    Set<Long> visited = new LinkedHashSet<>();
    List<Long> path = new ArrayList<>();

    LinkedList<Long> list = new LinkedList<>();
    list.add(origin);

    printPath(g, visited, list, origin, k, 0);

  }

  public void printPath(Map<Long, Map<Long, Integer>> g, Set<Long> visited,
      LinkedList<Long> path,
      long v, int k, int sum) {

    visited.add(v);
    for (Entry<Long, Integer> e : g.get(v).entrySet()) {
      if (!visited.contains(e.getKey())) {
        path.add(e.getKey());

        if (sum + e.getValue() <= k) {
          printPath(g, visited, path, e.getKey(), k, sum + e.getValue());
          path.pollLast();
        } else {
          System.out.println(StringUtils.join(path, " -> ") + " = " + (sum + e.getValue()));
          return;
        }
      }
    }
    visited.remove(v);
  }

  public static void main(String[] args) {
    Map<Long, Map<Long, Integer>> g = new LinkedHashMap<>();

    g.put(0L, new HashMap<Long, Integer>() {{
      put(1L, 4);
      put(7L, 8);
    }});

    g.put(1L, new HashMap<Long, Integer>() {{
      put(2L, 8);
      put(7L, 11);
    }});

    g.put(2L, new HashMap<Long, Integer>() {{
      put(3L, 7);
      put(5L, 4);
      put(8L, 2);
      put(1L, 8);
    }});

    g.put(3L, new HashMap<Long, Integer>() {{
      put(2L, 7);
      put(4L, 9);
      put(5L, 14);
    }});

    g.put(4L, new HashMap<Long, Integer>() {{
      put(3L, 9);
      put(5L, 10);
    }});

    g.put(5L, new HashMap<Long, Integer>() {{
      put(4L, 10);
      put(3L, 14);
      put(2L, 4);
      put(6L, 2);
    }});

    g.put(6L, new HashMap<Long, Integer>() {{
      put(5L, 2);
      put(8L, 6);
      put(7L, 1);
    }});

    g.put(7L, new HashMap<Long, Integer>() {{
      put(6L, 1);
      put(8L, 7);
      put(1L, 11);
      put(0L, 8);
    }});

    g.put(8L, new HashMap<Long, Integer>() {{
      put(7L, 7);
      put(6L, 6);
      put(2L, 2);
    }});

    PathWithLenK pk = new PathWithLenK();
    pk.detectEdgeSum(g, 0, 58);
  }
}
