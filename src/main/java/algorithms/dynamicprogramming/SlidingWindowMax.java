package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving
 * from
 * the very left of the array to the very right. You can only see the k numbers in the window. Each
 * time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * Algorithm:
 * The idea is to create two separate arrays left[] and right[] to save the max element in each
 * window from left and right side, after this we just have to find the max of the left and right
 * value in each window.
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * Date 30/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMax {

  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null) {
      throw new IllegalArgumentException("[] cannot be null");
    }

    int n = nums.length;
    if (n * k == 0) {
      return new int[0];
    }

    if (k == 1) {
      return nums;
    }

    int[] left = new int[n];
    int[] right = new int[n];
    int[] op = new int[n - k + 1];

    left[0] = nums[0];
    right[n - 1] = nums[n - 1];

    for (int i = 1; i < n; i++) {
      //If this is the last element of the window, set the left[] as the start of the window
      if (i % k == 0) {
        left[i] = nums[i];
      } else {
        //Else compare the largest element and set it to left[]
        left[i] = Math.max(nums[i], left[i - 1]);
      }

      //We have to do the same for the right[] as well
      int j = n - i - 1;
      if (j % k == 0) {
        right[j] = nums[j];
      } else {
        right[j] = Math.max(nums[j], right[j + 1]);
      }
    }

    for (int i = 0; i < n - k + 1; i++) {
      //Compare the start and the end max elements in the window
      op[i] = Math.max(left[i + k - 1], right[i]);
    }

    return op;
  }

  public static void main(String[] args) {
    SlidingWindowMax sm = new SlidingWindowMax();

    System.out
        .println(Arrays.toString(sm.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
  }

}
