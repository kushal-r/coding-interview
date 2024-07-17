package datastructures.array;

/**
 * Given an array, which is sorted and then rotated from a particular point. We have to search for
 * an element in this array
 * <p>
 * Algorithm: We will be doing binary search on this array, first we find the mid point and check if
 * the element is equal to the [mid],
 * <p>
 * Else if [l...mid] is sorted we check  if x lies in range of [l...mid] and binary search on this
 * otherwise will do binary search on [mid...r].
 * <p>
 * Else the other side must be sorted, and have to do the same search on the other side.
 * <p>
 * Complexity: Time - O(log n) Space - O(1)
 * <p>
 * Date: 01/08/20
 *
 * @author Kushal Roy
 * <p>
 * References: https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 */
public class SearchRotatedArray {

  public int search(int[] arr, int l, int r, int x) {
    if (l > r) {
      return -1;
    }

    int mid = (l + r) / 2;

    if (arr[mid] == x) {
      return mid;
      //If left side of array is sorted
    } else if (arr[mid] > arr[l]) {
      if (x >= arr[l] && x < arr[mid]) {
        return search(arr, l, mid - 1, x);
      }
      return search(arr, mid + 1, r, x);
      //The right side is sorted
    } else {
      if (x >= arr[mid + 1] && x <= arr[r]) {
        return search(arr, mid + 1, r, x);
      }
      return search(arr, l, mid - 1, x);
    }
  }

  public int search(int[] nums, int target) {
    int l = 0, r = nums.length - 1;

    while (l < r) {
      int mid = l + (r - l) / 2;

      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > nums[l]) {
        //If left side is sorted
        if (nums[mid] > target && target >= nums[l]) {
          r = mid - 1;
        } else {
          l = mid + 1;
        }
      } else {
        //Right side is sorted
        if (nums[mid] < target && nums[r] >= target) {
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] arr = {4, 5, 6, 7, 8, 9, 1, 2, 3};
    SearchRotatedArray sra = new SearchRotatedArray();
    System.out.println(sra.search(arr, 0, arr.length, 6));
    System.out.println(sra.search(arr, 6));

  }
}
