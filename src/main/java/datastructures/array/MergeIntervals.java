package datastructures.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Algorithm:
 * First, we sort the list as described. Then, we insert the first interval into our merged list and
 * continue considering each interval in turn as follows: If the current interval begins after the
 * previous interval ends, then they do not overlap and we can append the current interval to
 * merged. Otherwise, they do overlap, and we merge them by updating the end of the previous
 * interval if it is less than the end of the current interval.
 * <p>
 * A simple proof by contradiction shows that this algorithm always produces the correct answer.
 * First, suppose that the algorithm at some point fails to merge two intervals that should be
 * merged. This would imply that there exists some triple of indices ii, jj, and kk in a list of
 * intervals \text{ints}ints such that i < j < ki<j<k and (\text{ints[i]}ints[i],
 * \text{ints[k]}ints[k]) can be merged, but neither (\text{ints[i]}ints[i], \text{ints[j]}ints[j])
 * nor (\text{ints[j]}ints[j], \text{ints[k]}ints[k]) can be merged. From this scenario follow
 * several inequalities:
 * <p>
 * Complexity:
 * Time - (nlogn) [For sorting]
 * Space - (1)
 *
 * @author kushal
 * <p>
 * Date 13/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, new IntervalComparator());

    LinkedList<int[][]> mergedInterval = new LinkedList<>();
    int[][] prevInterval = new int[][]{intervals[0]};
    mergedInterval.add(prevInterval);
    int idx = 0;

    for (int i = 1; i < intervals.length; i++) {
      int[][] currInterval = new int[][]{intervals[i]};
      if (mergedInterval.get(idx)[0][0] <= currInterval[0][0]
          && currInterval[0][0] <= mergedInterval.get(idx)[0][1]) {
        mergedInterval.get(idx)[0][0] = Math.min(mergedInterval.get(idx)[0][0], currInterval[0][0]);
        mergedInterval.get(idx)[0][1] = Math.max(mergedInterval.get(idx)[0][1], currInterval[0][1]);
      } else {
        mergedInterval.add(currInterval);
        idx++;
      }
    }

    int[][] mergedArr = new int[mergedInterval.size()][2];
    int j = 0;

    for (int[][] m : mergedInterval) {
      mergedArr[j][0] = m[0][0];
      mergedArr[j][1] = m[0][1];
      j++;
    }

    return mergedArr;
  }

  public static void main(String[] args) {
    MergeIntervals mi = new MergeIntervals();

    System.out.println(
        Arrays.deepToString(mi.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));

    System.out.println(
        Arrays.deepToString(mi.merge(new int[][]{{1, 4}, {4, 5}})));


  }

  private static class IntervalComparator implements Comparator<int[]> {

    @Override
    public int compare(int[] o1, int[] o2) {
      return Integer.compare(o1[0], o2[0]);
    }
  }
}
