package datastructures.array;

/**
 * Given an array arr, which is sorted in ascending order except in between some of the elements
 * are unsorted. We have to find the min. length sub array sorting which the array becomes sorted.
 * <p>
 * Algorithm:
 * The goal is to find the start and end idx in the array, so sorting this sub array makes the array
 * sorted. We start iterating from the left to right and try to find the first element which is
 * greater than the next element.
 * Iterate from right to left and find an element which is less than the element before it in the
 * entire array.
 * To set the start and end idx to the correct position in the array, we start from left and try
 * to find an element which is just greater than start. Similarly we traverse from the right and
 * try to find an element which is just smaller than end, so we update our start and end idx to
 * point to this idx.
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 * <p>
 * Date: 14/07/20
 *
 * @author Kushal Roy
 * <p>
 * References:
 * https://leetcode.com/articles/shortest-unsorted-continous-subarray/
 */
public class MinLengthUnsortedArray {

  public void findMinLength(int[] arr) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        min = Math.min(min, arr[i + 1]);
      }
    }

    for (int i = arr.length - 1; i > 0; i--) {
      if (arr[i] < arr[i - 1]) {
        max = Math.max(max, arr[i - 1]);
      }
    }

    int l, r;
    for (l = 0; l < arr.length; l++) {
      if (min <= arr[l]) {
        break;
      }
    }

    for (r = arr.length - 1; r >= 0; r--) {
      if (max >= arr[r]) {
        break;
      }
    }

    System.out.println("Start idx :" + l + " End idx :" + r);
  }

  public static void main(String[] args) {
    int[] arr = {2, 6, 4, 8, 10, 9, 15};

    MinLengthUnsortedArray minLengthUnsortedArray = new MinLengthUnsortedArray();
    minLengthUnsortedArray.findMinLength(arr);

    arr = new int[]{10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
    minLengthUnsortedArray.findMinLength(arr);

  }
}
