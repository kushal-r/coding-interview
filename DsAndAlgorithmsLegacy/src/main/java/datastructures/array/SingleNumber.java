package datastructures.array;

import java.util.Arrays;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other
 * elements appear exactly twice. Find the two elements that appear only once. You can return the
 * answer in any order.
 *
 * <p>Input: nums = [1,2,1,3,2,5] Output: [3,5] Explanation: [5, 3] is also a valid answer.
 *
 * <p>Algorithm:
 * We will have to use XOR, XOR of the same number = 0, XOR of any number with 0 is
 * that number. We XOR all the numbers in the list, the resultant is X XOR Y, X Y we need to find.
 * XOR is 1 if two bits are not same, i.e. one of them should be 1. If the ith bit of the resultant
 * is 0, the other should be 1. If we divide all this nums, in two groups based on this ith bit, and
 * then we XOR each group we will get one number from each group.
 *
 * <p>Complexity:
 * Time - O(n) Space - O(1)
 *
 * <p>References:
 * https://leetcode.com/problems/single-number-iii/
 * https://medium.com/@gurupad93/two-numbers-that-appear-once-b89e92a9334b
 *
 * @author kushal
 */
public class SingleNumber {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 1, 3, 2, 5};
    System.out.println(Arrays.toString(singleNumber(nums)));

    nums = new int[]{-1, 0};
    System.out.println(Arrays.toString(singleNumber(nums)));
  }

  public static int[] singleNumber(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }

    int xor = nums[0];
    for (int i = 1; i < nums.length; i++) {
      xor = xor ^ nums[i];
    }

    int differentiatingBit = 0;
    for (int i = 0; i < 32; i++) {
      if ((xor & (1 << i)) != 0) {
        differentiatingBit = i;
        break;
      }
    }

    int unique1 = 0, unique2 = 0;
    for (int num : nums) {
      if ((num & (1 << differentiatingBit)) == 0) {
        unique1 = unique1 ^ num;
      } else {
        unique2 = unique2 ^ num;
      }
    }

    return new int[]{unique1, unique2};
  }
}
