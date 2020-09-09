package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given parent array parent[] = {-1, 0, 0, 0, 3, 1, 1, 2} representing a tree, where value in
 * each cell represents the immediate parent of that node, we need to find the height of such a
 * tree.
 * <p>
 * Algorithm:
 * We need to iterate over the given array of nodes, marking each node as visited, and return the
 * height of the current node = 1 + height (parent) if parent is already visited, else we need
 * to recurse back through the parent till we find a visited node and return its height
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 * <p>
 * Date: 12/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/height-generic-tree-parent-array/
 */
public class HeightOfGenericTree {

  public int calHeight(int[] arr) {
    Map<Integer, Integer> height = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    int maxHeight = 0;

    for (int i = 0; i < arr.length; i++) {
      if (!visited.contains(i)) {
        int currHeight = calHeightRecur(arr, i, height, visited);
        maxHeight = Math.max(maxHeight, currHeight);
      }
    }

    return maxHeight;
  }

  public int calHeightRecur(int[] arr, int idx, Map<Integer, Integer> height,
      Set<Integer> visited) {
    //If root node, arr val will be -1
    if (arr[idx] == -1) {
      visited.add(idx);
      height.put(idx, 0);
      return 0;
    } else if (visited.contains(idx)) {
      return height.get(idx);
    } else {
      int currHeight = 1 + calHeightRecur(arr, arr[idx], height, visited);
      height.put(idx, currHeight);
      visited.add(idx);
      return currHeight;
    }
  }

  public static void main(String[] args) {
    HeightOfGenericTree heightOfGenericTree = new HeightOfGenericTree();

    int[] parent1 = {-1, 0, 0, 0, 3, 1, 1, 2};
    System.out.println("Height of N-ary Tree = " +
        heightOfGenericTree.calHeight(parent1));

    int[] parent2 = {-1, 0, 1, 2, 3};
    System.out.println("Height of N-ary Tree = " +
        heightOfGenericTree.calHeight(parent2));
  }

}
