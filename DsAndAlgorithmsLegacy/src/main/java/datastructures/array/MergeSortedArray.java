package datastructures.array;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional
 * elements from nums2.
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 * <p>
 * Algorithm:
 * We start to overwrite nums1 from the end, where there is no information yet. Then no additional
 * space is needed.
 * <p>
 * We start comparing the elements from the m, n idx and update the idx's.
 * We repeat this until we reach the start of the array
 * <p>
 * Complexity:
 * Time - O(m+n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArray {

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int mIdx = m - 1;
    int nIdx = n - 1;
    for (int i = nums1.length - 1; i >= 0; i--) {
      int x = mIdx >= 0 ? nums1[mIdx] : Integer.MIN_VALUE;
      int y = nIdx >= 0 ? nums2[nIdx] : Integer.MIN_VALUE;

      if (x > y) {
        nums1[i] = x;
        mIdx--;
      } else {
        nums1[i] = y;
        nIdx--;
      }
    }
  }

  public static void main(String[] args) {
    MergeSortedArray msa = new MergeSortedArray();

    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] nums2 = {2, 5, 6};
    int n = 3;
    msa.merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));

    nums1 = new int[]{1, 2, 3, 0, 0};
    m = 3;
    nums2 = new int[]{2, 6};
    n = 2;
    msa.merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));

    nums1 = new int[]{1, 3, 5, 0, 0};
    m = 3;
    nums2 = new int[]{2, 4};
    n = 2;
    msa.merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));

    nums1 = new int[]{1, 0};
    m = 1;
    nums2 = new int[]{2};
    n = 1;
    msa.merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));

    nums1 = new int[]{1, 0};
    m = 1;
    nums2 = new int[]{1};
    n = 1;
    msa.merge(nums1, m, nums2, n);
    System.out.println(Arrays.toString(nums1));
  }
}
