package datastructures.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a
 * specific
 * target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * Algorithm:
 * While we iterate and inserting elements into the table, we also look back to check if current
 * element's complement already exists in the table. If it exists, we have found a solution and
 * return immediately.
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length <= 1) {
      return null;
    }
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        return new int[]{map.get(nums[i]), i};
      } else {
        int rem = target - nums[i];
        map.put(rem, i);
      }
    }

    throw new IllegalArgumentException("No pairs found whose sum equals target");
  }

  public static void main(String[] args) {
    TwoSum ts = new TwoSum();

    int[] nums = {2, 7, 11, 15};
    int target = 9;
    System.out.println(Arrays.toString(ts.twoSum(nums, target)));

    nums = new int[]{2, 7, 11, 15};
    target = 18;
    System.out.println(Arrays.toString(ts.twoSum(nums, target)));
  }
}
