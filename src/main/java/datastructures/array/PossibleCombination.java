package datastructures.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers
 * from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Note:
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Example 4:
 * Input: k = 3, n = 2
 * Output: []
 * Example 5:
 * Input: k = 9, n = 45
 * Output: [[1,2,3,4,5,6,7,8,9]]
 * <p>
 * Constraints:
 * <p>
 * 2 <= k <= 9
 * 1 <= n <= 60
 * <p>
 * <p>
 * Algorithm:
 * To implement the algorithm, one could literally follow the steps in the Intuition section.
 * However, we would like to highlight a key trick that we employed, in order to ensure the
 * non-redundancy among the digits within a single combination, as well as the non-redundancy among
 * the combinations.
 * <p>
 * The trick is that we pick the candidates in order. We treat the candidate digits as a list with
 * order, i.e. [1, 2, 3, 4, 5, 6, 7, 8. 9]. At any given step, once we pick a digit, e.g. 6, we
 * will
 * not consider any digits before the chosen digit for the following steps, e.g. the candidates are
 * reduced down to [7, 8, 9].
 * <p>
 * Complexity:
 * Time - O((9! - K)/(9-K)!)s
 * In a worst scenario, we have to explore all potential combinations to the very end, i.e. the sum
 * nn is a large number (n > 9 * 9n>9∗9). At the first step, we have 99 choices, while at the second
 * step, we have 88 choices, so on and so forth.
 * The number of exploration we need to make in the worst case would be P(9, K) =
 * \frac{9!}{(9-K)!}P(9,K)=
 * (9−K)!
 * 9!
 * , assuming that K <= 9K<=9. By the way, K cannot be greater than 9, otherwise we cannot have a
 * combination whose digits are all unique.
 * <p>
 * Each exploration takes a constant time to process, except the last step where it takes
 * \mathcal{O}(K)O(K) time to make a copy of combination.
 * <p>
 * To sum up, the overall time complexity of the algorithm would be \frac{9!}{(9-K)!} \cdot
 * \mathcal{O}(K) = \mathcal{O}(\frac{9! \cdot K}{(9-K)!})
 * (9−K)!
 * 9!
 * ​
 * ⋅O(K)=O(
 * (9−K)!
 * 9!⋅K
 * ​
 * ).
 * <p>
 * <p>
 * Space - (K)
 *
 * @author kushal
 * <p>
 * Date 15/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/combination-sum-iii/
 */
public class PossibleCombination {

  private static final int[] nos = {1, 2, 3, 4, 5, 6, 7, 8, 9};

  public List<List<Integer>> combinationSum(int k, int n) {
    List<List<Integer>> combinations = new ArrayList<>();

    for (int i = 0; i < nos.length; i++) {
      LinkedList<Integer> comb = new LinkedList<>();
      combinationSumHlpr(k, n, i, 0, 0, comb, combinations);
    }

    return combinations;
  }


  public void combinationSumHlpr(int k, int n, int idx, int currCount, int currSum,
      LinkedList<Integer> comb, List<List<Integer>> combinations) {

    if (currSum == n && currCount == k) {
      combinations.add(new ArrayList<>(comb));
      return;
    }

    while (currCount <= k && idx < nos.length && currSum < n) {
      if (currSum + nos[idx] <= n) {
        currSum += nos[idx];
        comb.add(nos[idx]);
        idx++;
        combinationSumHlpr(k, n, idx, currCount + 1, currSum, comb, combinations);
        comb.removeLast();
        currSum -= nos[idx - 1];
      } else {
        return;
      }
    }
  }


  public static void main(String[] args) {
    PossibleCombination pc = new PossibleCombination();

    System.out.println(pc.combinationSum(3, 7));
    System.out.println(pc.combinationSum(3, 9));
    System.out.println(pc.combinationSum(4, 1));
    System.out.println(pc.combinationSum(3, 2));
    System.out.println(pc.combinationSum(9, 45));
  }
}
