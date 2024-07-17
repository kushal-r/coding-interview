package datastructures.array;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which
 * has the largest sum and return its sum.
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the
 * divide and conquer approach, which is more subtle.
 * <p>
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: 0
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: -1
 * Example 5:
 * <p>
 * Input: nums = [-2147483647]
 * Output: -2147483647
 * <p>
 * Algorithm:
 * Kadane's Algorithm:
 * Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the
 * array (max_ending_here is used for this). And keep track of maximum sum contiguous segment among
 * all positive segments (max_so_far is used for this). Each time we get a positive sum compare it
 * with max_so_far and update max_so_far if it is greater than max_so_far
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * Date 25/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/maximum-subarray/
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class MaximumSubarraySum {


  public int maxSubArraySum(int[] nums) {
    int maxSum = nums[0];

    //Reset the and set [i] = nums[i-1] + nums[i] if sum till current no. is +ve, else dont do anything
    for (int i = 1; i < nums.length - 1; i++) {
      if (nums[i - 1] > 0) {
        nums[i] = nums[i - 1] + nums[i];
        maxSum = Math.max(maxSum, nums[i]);
      }

    }

    return maxSum;
  }


  /**
   * Without modifying the array
   *
   * @param nums
   *
   * @return
   */
  public int maxSubArraySumNoMod(int[] nums) {
    int maxSum = nums[0];
    int sumTillNow = nums[0];

    for (int i = 1; i < nums.length - 1; i++) {
      if (sumTillNow > 0) {
        sumTillNow = nums[i] + sumTillNow;
        maxSum = Math.max(maxSum, sumTillNow);
      } else {
        sumTillNow = nums[i];
      }
    }

    return maxSum;
  }


  public static void main(String[] args) {
    MaximumSubarraySum ms = new MaximumSubarraySum();

    System.out.println(ms.maxSubArraySum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

    System.out.println(ms.maxSubArraySumNoMod(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

  }
}
