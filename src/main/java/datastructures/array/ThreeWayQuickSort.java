package datastructures.array;

import java.util.Arrays;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that
 * objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue
 * respectively.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * Complexity:
 * Time - O(n)
 * Space -O(n)
 *
 * @author kushal
 * <p>
 * Date 13/10/20
 * <p>
 * References:
 * https://leetcode.com/problems/sort-colors/
 */
public class ThreeWayQuickSort {

  public int[] sortColors(int[] nums) {
    int p0 = 0, p2 = nums.length - 1;
    int idx = 0;

    while (idx <= p2) {
      if (nums[idx] == 0) {
        int tmp = nums[idx];
        nums[idx++] = nums[p0];
        nums[p0++] = tmp;
      } else if (nums[idx] == 2) {
        int tmp = nums[idx];
        nums[idx] = nums[p2];
        nums[p2--] = tmp;
      } else {
        idx++;
      }
    }

    return nums;
  }


  public static void main(String[] args) {
    ThreeWayQuickSort tqs = new ThreeWayQuickSort();

    System.out.println(Arrays.toString(tqs.sortColors(new int[]{2, 0, 2, 1, 1, 0})));
    System.out.println(Arrays.toString(tqs.sortColors(new int[]{2, 0, 1})));

  }
}
