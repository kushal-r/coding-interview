package datastructures.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater
 * permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie,
 * sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in
 * the right-hand column.
 * <p>
 * 1,2,3 -> 1,3,2
 * 3,2,1 -> 1,2,3
 * 1,1,5 -> 1,5,1
 * <p>
 * Algorithm:
 * We need to find the first pair of two successive numbers a[i]a[i] and a[i-1]a[i-1], from the
 * right, which satisfy a[i] > a[i-1]a[i]>a[i-1]. Now, no rearrangements to the right of
 * a[i-1]a[i-1] can create a larger permutation since that subarray consists of numbers in
 * descending order. Thus, we need to rearrange the numbers to the right of a[i-1]a[i-1] including
 * itself.
 * <p>
 * Now, what kind of rearrangement will produce the next larger number? We want to create the
 * permutation just larger than the current one. Therefore, we need to replace the number
 * a[i-1]a[i−1] with the number which is just larger than itself among the numbers lying to its
 * right section, say a[j]a[j].
 * We swap the numbers a[i-1]a[i−1] and a[j]a[j]. We now have the correct number at index i-1i−1.
 * But still the current permutation isn't the permutation that we are looking for. We need the
 * smallest permutation that can be formed by using the numbers only to the right of a[i-1]a[i−1].
 * Therefore, we need to place those numbers in ascending order to get their smallest permutation.
 * <p>
 * But, recall that while scanning the numbers from the right, we simply kept decrementing the index
 * until we found the pair a[i]a[i] and a[i-1]a[i−1] where, a[i]>a[i−1]. Thus, all
 * numbers to the right of a[i-1] were already sorted in descending order. Furthermore,
 * swapping a[i-1] and a[j] didn't change that order. Therefore, we simply need to reverse
 * the numbers following a[i-1] to get the next smallest lexicographic permutation.
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

  public void nextPermutation(int[] nums) {
    int i = nums.length - 1;

    //Find a pair such that num[i] > num[i-1]
    while (i - 1 >= 0 && nums[i] < nums[i - 1]) {
      i--;
    }

    int j = i;

    //If we found a pair in the set above, otherwise we have reached the
    // start of the arr and we have to sort the entire array
    if (i > 0) {
      //find a number whic is just greater than num[i-1]
      while (j < nums.length - 1 && nums[j] <= nums[i - 1]) {
        j++;
      }

      //Swap num[i-1] with num[j]
      int tmp = nums[i - 1];
      nums[i - 1] = nums[j];
      nums[j] = tmp;
    }

    //Sort the rest of the elements[in case we were unable to find pair num[i]> num[i-1]],
    // we are going to sort the entire array
    j = nums.length - 1;
    while (j > i) {
      int tmp = nums[j];
      nums[j] = nums[i];
      nums[i] = tmp;
      j--;
      i++;
    }
  }

  public static void main(String[] args) {
    NextPermutation np = new NextPermutation();

    int[] nums = {1, 2, 3};
    np.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));

    nums = new int[]{3, 2, 1};
    np.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));

    nums = new int[]{1, 1, 5};
    np.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));

    nums = new int[]{1};
    np.nextPermutation(nums);
    System.out.println(Arrays.toString(nums));
  }

}
