package datastructures.array;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * <p>
 * <p>
 * Algorithm:
 * Find maximum height of bar from the left end upto an index i in the array
 * left_max.
 * Find maximum height of bar from the right end upto an index i in the array
 * right_max.
 * Iterate over the height array and update ans:
 * Add min(left_max[i],right_max[i])−height[i]
 * to \text{ans}ans
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * Date 09/10/20
 * <p>
 * References:
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class RainWaterTrapping {

  public int trapExtraSpace(int[] height) {
    if (height == null) {
      throw new IllegalArgumentException("Heights cannot be null");
    }

    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];
    int sum = 0;

    leftMax[0] = height[0];
    rightMax[height.length - 1] = height[height.length - 1];

    for (int i = 1; i < height.length; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], height[i]);
    }

    for (int i = height.length - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], height[i]);
    }

    for (int i = 1; i < height.length - 1; i++) {
      sum += Math.min(rightMax[i], leftMax[i]) - height[i];
    }

    return sum;
  }


  public static void main(String[] args) {
    RainWaterTrapping rwt = new RainWaterTrapping();

    System.out.println("Max Rain (using extra space) " + rwt
        .trapExtraSpace(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
  }
}
