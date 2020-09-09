package datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Given a sorted list of alien words find the order of the charters in the words.
 * <p>
 * Algorithm:
 * We have a represent each character as a vertex in the graph. Following that we have to add an
 * edge between the vertices based on the order of encounter while traversing two words from the
 * list at a time. Finally we have to do topological sorting on this graph to get the order of
 * characters.
 * <p>
 * Complexity:
 * Time - O(V+E)
 * Space - O(V)
 * <p>
 * Date: 08/04/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
 */
public class AlienDictionary {

  public void findOrder(List<String> words) {
    Map<Character, Set<Character>> g = constructGraph(words);
    Set<Character> visited = new HashSet<>();
    Stack<Character> stack = new Stack<>();

    g.keySet().parallelStream().forEach(u -> {
      if (!visited.contains(u)) {
        topologicalSort(g, u, visited, stack);
      }
    });

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
  }

  public Map<Character, Set<Character>> constructGraph(List<String> words) {
    Map<Character, Set<Character>> g = new ConcurrentHashMap<>();

    //Add vertices to the graph
    words.stream().map(String::toCharArray).forEach(c -> {
      for (char ch : c) {
        g.put(ch, new HashSet<>());
      }
    });

    for (int i = 0; i < words.size() - 1; i++) {
      //Iterate over two adjacent words at a time, string from [0] and [1] words
      String u = words.get(i);
      String v = words.get(i + 1);

      for (int j = 0; j < Math.min(u.length(), v.length()); j++) {
        if (u.charAt(j) != v.charAt(j)) {
          //Adds an edge from the character at word1 to that of the character in word2
          g.get(u.charAt(j)).add(v.charAt(j));
          //List of words are already sorted, so break out as soon we found mis match
          break;
        }
      }
    }

    return g;
  }

  public void topologicalSort(Map<Character, Set<Character>> g, Character u, Set<Character> visited,
      Stack<Character> stack) {

    visited.add(u);
    for (Character v : g.get(u)) {
      if (!visited.contains(v)) {
        topologicalSort(g, v, visited, stack);
      }
    }
    stack.push(u);
  }

  public static void main(String[] args) {
    AlienDictionary alienDictionary = new AlienDictionary();
    List<String> words1 = new ArrayList<>(Arrays.asList("caa", "aaa", "aab"));
    List<String> words2 = new ArrayList<>(Arrays.asList("baa", "abcd", "abca", "cab", "cad"));

    alienDictionary.findOrder(words1);
    System.out.println();
    alienDictionary.findOrder(words2);

  }
}
