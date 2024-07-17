package datastructures.array;

import java.util.Arrays;

/**
 * Given arrival and departure times of all trains that reach a railway station, the task is to
 * find
 * the minimum number of platforms required for the railway station so that no train waits.
 * We are given two arrays which represent arrival and departure times of trains that stop.
 * <p>
 * Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
 * dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
 * Output: 3
 * Explanation: There are at-most three trains at a time (time between 11:00 to 11:20)
 * <p>
 * Input: arr[] = {9:00, 9:40}
 * dep[] = {9:10, 12:00}
 * Output: 1
 * Explanation: Only one platform is needed.
 * <p>
 * <p>
 * Algorithm:
 * We sort both arrival and departure arrays in increasing order. We initialize i and j on arrival
 * and departure arrays respectively. We then compare arr[i] with dep[j], if arr[i] < dep[j], then
 * a
 * train has arrived at the station and we need a platform. so we platform_count++
 * and set i -> i + 1. Now if we encounter arr[i] > dep[j] then we have to do j++ (train has left
 * the station) and do platform_count-- as we have freed a platform. We continue doing j-- until
 * again arr[i] < dep[j], at this point we again inc.  platform_count++ as a new train has arrived.
 * We continue doing this until we reached end of the array.
 * <p>
 * Complexity:
 * Time - O(nlogn) - as we have to sort the arrays
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://www.youtube.com/watch?v=38JLfQGVlkw
 * https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 * <p>
 * <p>
 */

public class CountPlatforms {

  public int getPlatformCount(int[] arr, int[] dep) {
    if (arr == null || dep == null || arr.length != dep.length) {
      throw new IllegalArgumentException("Invalid input");
    }
    int MAX_COUNT = 0;
    Arrays.sort(arr);
    Arrays.sort(dep);

    int i = 0, j = 0;
    int platformCount = 0;

    while (i < arr.length && j < dep.length) {
      if (arr[i] <= dep[j]) {
        platformCount++;
        i++;
      } else if (arr[i] > dep[j]) {
        platformCount--;
        j++;
      }
      MAX_COUNT = Math.max(MAX_COUNT, platformCount);
    }

    return MAX_COUNT;
  }

  public static void main(String[] args) {
    CountPlatforms sol = new CountPlatforms();

    int[] arr = {900, 940, 950, 1100, 1500, 1800};
    int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
    System.out.println(sol.getPlatformCount(arr, dep));

    arr = new int[]{900, 940};
    dep = new int[]{910, 1200};
    System.out.println(sol.getPlatformCount(arr, dep));

  }
}
