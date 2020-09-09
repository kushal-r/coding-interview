package datastructures.graph;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers. If the
 * answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string,
 * string>> queries , where equations.size() == values.size(), and the values are positive. This
 * represents the equations. Return vector<double>.
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * <p>
 * <p>
 * Algorithm:
 * Simple Directed Graph and DFS
 * based on equations and value, we need to construct directed graph along with weight.
 * E.g a/b=2 then we a-->b with weight 2 and b--->a with weight 1/2. the same for b/c=3 and the
 * rest. when we need to query a/c we need do a/c=a/b * b/c. use regular DFS ways to do it.
 * <p>
 * Note: a/a and x/x are special cases.
 * <p>
 * Complexity:
 * Time - O(e+v) = O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/evaluate-division/
 */
public class EvaluateDivision {

  private double v = 0;

  public double[] calcEquation(List<List<String>> equations, double[] values,
      List<List<String>> queries) {
    Map<String, List<Map<String, Double>>> graph = buildGraph(equations, values);

    double[] ret = new double[queries.size()];

    int i = 0;
    for (List<String> query : queries) {
      Set<String> visited = new HashSet<>();
      v = 1.0;
      if (dfsHelper(graph, query.get(0), query.get(1), visited)) {
        ret[i++] = v;
      } else {
        ret[i++] = -1.0;
      }
    }

    return ret;
  }

  public boolean dfsHelper(Map<String, List<Map<String, Double>>> graph, String start, String end,
      Set<String> visited) {

    if (!graph.containsKey(start) || !graph.containsKey(end)) {
      return false;
    }

    if (start.equals(end)) {
      return true;
    }

    visited.add(start);
    for (Map<String, Double> adjVertices : graph.get(start)) {
      String vertex = adjVertices.entrySet().stream().findFirst().get().getKey();
      double wt = adjVertices.entrySet().stream().findFirst().get().getValue();

      if (!visited.contains(vertex)) {
        v = v * wt;
        if (dfsHelper(graph, vertex, end, visited)) {
          return true;
        }
        v = v / wt;
      }
    }

    return false;
  }


  public Map<String, List<Map<String, Double>>> buildGraph(List<List<String>> equations,
      double[] values) {
    Map<String, List<Map<String, Double>>> graph = new HashMap<>();

    for (int i = 0; i < equations.size(); i++) {
      List<String> eq = equations.get(i);
      double val = values[i];

      if (graph.containsKey(eq.get(0))) {
        List<Map<String, Double>> vertex = graph.get(eq.get(0));
        vertex.add(new HashMap<String, Double>() {{
          put(eq.get(1), val);
        }});
      } else {
        List<Map<String, Double>> vertex = new ArrayList<>();
        vertex.add(new HashMap<String, Double>() {{
          put(eq.get(1), val);
        }});
        graph.put(eq.get(0), vertex);
      }

      if (graph.containsKey(eq.get(1))) {
        List<Map<String, Double>> vertex = graph.get(eq.get(1));
        vertex.add(new HashMap<String, Double>() {{
          put(eq.get(0), (1.0 / val));
        }});
      } else {
        List<Map<String, Double>> vertex = new ArrayList<>();
        vertex.add(new HashMap<String, Double>() {{
          put(eq.get(0), (1.0 / val));
        }});
        graph.put(eq.get(1), vertex);
      }
    }
    return graph;
  }


  public static void main(String[] args) {
    EvaluateDivision ed = new EvaluateDivision();
    Gson gson = new Gson();
    TypeToken<List<List<String>>> eqType = new TypeToken<List<List<String>>>() {
    };

    List<List<String>> equations =
        gson.fromJson("[ [\"a\", \"b\"], [\"b\", \"c\"] ]", eqType.getType());
    double[] values = gson.fromJson("[2.0, 3.0]", double[].class);
    List<List<String>> queries = gson.fromJson(
        "[ [\"a\", \"c\"], [\"b\", \"a\"], [\"a\", \"e\"], [\"a\", \"a\"], [\"x\", \"x\"] ]",
        eqType.getType());
    System.out.println(Arrays.toString(ed.calcEquation(equations, values, queries)));
  }
}
