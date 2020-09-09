package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents
 * the unique id and the second integer represents a value. Your task is to find an element from
 * a and an element form b such that the sum of their values is less or equal to target
 * and as close to target as possible. Return a list of ids of selected elements.
 * If no pair is possible, return an empty list.
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 * <p>
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 * <p>
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
 * Combined, they add up to 10. Similarly, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 * <p>
 * Output: [[2, 4], [3, 2]]
 * <p>
 * Output: [[2, 1]]
 * <p>
 * Algorithm:
 * The basic idea is to to sort both the lists in ascending order, and then start iterating one
 * from start and other from end, till we reach end of one list, constantly keeping sum of the
 * elements, and update this as soon as we get a value which is more closer to target.
 * <p>
 * Complexity:
 * Time - O(nlogn) - for sorting the lists
 * Space - O(1)
 * <p>
 * Date: 22/04/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/discuss/interview-question/373202
 */
public class OptimalUtilization {

  public List<int[]> findClosestPair(List<int[]> a, List<int[]> b, int target) {
    List<int[]> result = new ArrayList<>();
    a.sort(Comparator.comparingInt(x -> x[1]));
    b.sort(Comparator.comparingInt(x -> x[1]));

    int i = 0, j = b.size() - 1;
    int sum = Integer.MIN_VALUE;
    while (i < a.size() && j >= 0) {
      int currSum = a.get(i)[1] + b.get(j)[1];

      if (currSum > target) {
        j--;
      } else {
        if (currSum > sum) {
          sum = currSum;
          result.clear();
        }
        result.add(new int[]{a.get(i)[0], b.get(j)[0]});
        int idx = i + 1;
        while (idx < a.size() && a.get(idx)[1] == a.get(idx - 1)[1]) {
          result.add(new int[]{a.get(idx)[0], b.get(j)[0]});
          idx++;
        }
        i++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<int[]> a = new ArrayList<>(Arrays.asList(new int[]{1, 3},
        new int[]{2, 5}, new int[]{3, 7}, new int[]{4, 10}));
    List<int[]> b = new ArrayList<>(Arrays.asList(new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4},
        new int[]{4, 5}));
    int target = 10;

    OptimalUtilization ou = new OptimalUtilization();
    ou.findClosestPair(a, b, target)
        .forEach(x -> System.out.println("[" + x[0] + "," + x[1] + "]"));

    System.out.println();
    a = new ArrayList<>(Arrays.asList(new int[]{1, 8},
        new int[]{2, 7}, new int[]{3, 14}));
    b = new ArrayList<>(Arrays.asList(new int[]{1, 5}, new int[]{2, 10}, new int[]{3,
        14}));
    target = 20;
    ou.findClosestPair(a, b, target)
        .forEach(x -> System.out.println("[" + x[0] + "," + x[1] + "]"));

  }
}
