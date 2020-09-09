package datastructures.array;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is
 * fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * <p>
 * Algorithm:
 * Liner Scan - In this approach, we make use of the fact that two consecutive numbers
 * nums[j] and nums[j + 1] are never equal. Thus, we can traverse over the nums
 * array starting from the beginning. Whenever, we find a number nums[i], we only need to
 * check if it is larger than the next number nums[i+1] for determining if nums[i]
 * is the peak element. The reasoning behind this can be understood by taking the following three
 * cases which cover every case into which any problem can be divided.
 * Case 1. All the numbers appear in a descending order. In this case, the first element
 * corresponds
 * to the peak element. We start off by checking if the current element is larger than the next
 * one.
 * The first element satisfies this criteria, and is hence identified as the peak correctly. In
 * this
 * case, we didn't reach a point where we needed to compare nums[i]nums[i] with nums[i-1]nums[i−1]
 * also, to determine if it is the peak element or not.
 * Case 2. All the elements appear in ascending order. In this case, we keep on comparing
 * nums[i]nums[i] with nums[i+1]nums[i+1] to determine if nums[i]nums[i] is the peak element or
 * not.
 * None of the elements satisfy this criteria, indicating that we are currently on a rising slope
 * and not on a peak. Thus, at the end, we need to return the last element as the peak element,
 * which turns out to be correct. In this case also, we need not compare nums[i]nums[i] with
 * nums[i-1]nums[i−1], since being on the rising slope is a sufficient condition to ensure that
 * nums[i]nums[i] isn't the peak element.
 * Case 3. The peak appears somewhere in the middle. In this case, when we are traversing on the
 * rising edge, as in Case 2, none of the elements will satisfy nums[i] > nums[i +
 * 1]nums[i]>nums[i+1]. We need not compare nums[i]nums[i] with nums[i-1]nums[i−1] on the rising
 * slope as discussed above. When we finally reach the peak element, the condition nums[i] > nums[i
 * + 1]nums[i]>nums[i+1] is satisfied. We again, need not compare nums[i]nums[i] with
 * nums[i-1]nums[i−1]. This is because, we could reach nums[i]nums[i] as the current element only
 * when the check nums[i] > nums[i + 1]nums[i]>nums[i+1] failed for the previous((i-1)^{th}(i−1)
 * th
 * element, indicating that nums[i-1] < nums[i]nums[i−1]<nums[i]. Thus, we are able to identify the
 * peak element correctly in this case as well.
 * <p>
 * <p>
 * Algorithm 2:Recursive Binary Search
 * We can view any given sequence in numsnums array as alternating ascending and descending
 * sequences. By making use of this, and the fact that we can return any peak as the result, we can
 * make use of Binary Search to find the required peak element.
 * <p>
 * In case of simple Binary Search, we work on a sorted sequence of numbers and try to find out the
 * required number by reducing the search space at every step. In this case, we use a modification
 * of this simple Binary Search to our advantage. We start off by finding the middle element, midmid
 * from the given numsnums array. If this element happens to be lying in a descending sequence of
 * numbers. or a local falling slope(found by comparing nums[i]nums[i] to its right neighbour), it
 * means that the peak will always lie towards the left of this element. Thus, we reduce the search
 * space to the left of midmid(including itself) and perform the same process on left subarray.
 * <p>
 * If the middle element, midmid lies in an ascending sequence of numbers, or a rising slope(found
 * by comparing nums[i]nums[i] to its right neighbour), it obviously implies that the peak lies
 * towards the right of this element. Thus, we reduce the search space to the right of midmid and
 * perform the same process on the right subarray.
 * <p>
 * In this way, we keep on reducing the search space till we eventually reach a state where only one
 * element is remaining in the search space. This single element is the peak element.
 * <p>
 * <p>
 * Complexity:
 * Time -O(n) - Liner search/ O(log n)- Binary search
 * Space - O(1)/O(log n)
 *
 * @author kushal
 * Date - 07/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/find-peak-element/
 */
public class PeakElement {

  public int findPeakElement(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] > nums[i + 1]) {
        return i;
      }
    }

    return nums.length - 1;
  }

  public int findPeakElementBinarySearch(int[] nums) {
    return binarySearch(nums, 0, nums.length - 1);
  }

  public int binarySearch(int[] nums, int l, int r) {

    //If this is the last cell return
    if (l == r) {
      return l;
    }

    int mid = (r + l) / 2;

    //If mid > mid+1 then we have to search the left side of the array, to see if this is the peak element
    if (nums[mid] > nums[mid + 1]) {
      return binarySearch(nums, l, mid);
    } else {
      return binarySearch(nums, mid + 1, r);
    }
  }

  public static void main(String[] args) {
    PeakElement pe = new PeakElement();

    System.out.println(pe.findPeakElement(new int[]{1, 2, 3, 1}));
    System.out.println(pe.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));

    System.out.println("Using modified binary search");
    System.out.println(pe.findPeakElementBinarySearch(new int[]{1, 2, 3, 1}));
    System.out.println(pe.findPeakElementBinarySearch(new int[]{1, 2, 1, 3, 5, 6, 4}));

  }
}
