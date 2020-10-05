package algorithms.backtracking;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain
 * amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Algorithm:
 * <p>
 * Let us look at the case n = 1, clearly f(1) = A1.
 * Now, let us look at n = 2, which f(2) = max(A1, A2).
 * For n = 3, you have basically the following two options:
 * Rob the third house, and add its amount to the first house's amount.
 * Do not rob the third house, and stick with the maximum amount of the first two houses.
 * Clearly, you would want to choose the larger of the two options at each step.
 * Therefore, we could summarize the formula as following:
 * f(k) = max(f(k – 2) + Ak, f(k – 1))
 * We choose the base case as f(–1) = f(0) = 0, which will greatly simplify our code as you can
 * see.
 * The answer will be calculated as f(n). We could use an array to store and calculate the result,
 * but since at each step you only need the previous two maximum values, two variables are
 * suffice.s
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * Date 05/10/20
 * <p>
 * References:
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

  public int rob(int[] nums) {
    int prevMax = 0;
    int currMax = 0;

    for (int i = 0; i < nums.length; i++) {
      int tmp = currMax;
      currMax = Math.max(prevMax + nums[i], currMax);
      prevMax = tmp;
    }

    return currMax;
  }


  public static void main(String[] args) {
    HouseRobber hr = new HouseRobber();
    System.out.println(hr.rob(new int[]{2, 7, 9, 3, 1}));
  }
}
