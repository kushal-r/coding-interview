package datastructures.array;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <=
 * nums[3]....
 * <p>
 * Example:
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 * <p>
 * Algorithm:
 * The idea is to swap even and odd positioned cells by check the condition if [i] < [i+1] or [i]
 * > [i+1]
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/wiggle-sort/
 */
public class WiggleSort {

  public void wiggleSort(int[] nums) {

    boolean isEven = true;
    for (int i = 0; i < nums.length - 1; i++) {
      if (isEven) {
        if (nums[i] > nums[i + 1]) {
          swap(nums, i, i + 1);
        }
      } else {
        if (nums[i] < nums[i + 1]) {
          swap(nums, i, i + 1);
        }
      }
      isEven = !isEven;
    }
  }

  public void swap(int[] nums, int x, int y) {
    int tmp = nums[x];
    nums[x] = nums[y];
    nums[y] = tmp;
  }

  public static void main(String[] args) {

    WiggleSort ws = new WiggleSort();
    int[] nums = {3, 5, 2, 1, 6, 4};
    ws.wiggleSort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
