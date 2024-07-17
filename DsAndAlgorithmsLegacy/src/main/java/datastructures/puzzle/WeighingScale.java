package datastructures.puzzle;

import java.util.Arrays;

/**
 * Given a weighing scale, and a list of numbers [7,11] (each can be used infinitely) and numbers
 * of steps, we need to place the weights on the scale in such a way that when ever a new weight
 * is place, the scale tilts in that side.
 * Same weight cannot be placed twice successively.
 * <p>
 * Algorithm:
 * Recursively call findWeight method, passing the difference between the left-right pan value,
 * current step, and weight arr.
 * Each time we need to check if we have reached the pre defined number of steps. If not we need
 * to get a weight whose value is greater than the diff of left-right, and which has not been
 * used previously. Otherwise we need to return False if that is not possible.
 * <p>
 * Assumptions:
 * The weights array is sorted in increasing order
 * <p>
 * Date: 15/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/move-weighting-scale-alternate-given-constraints/
 */
public class WeighingScale {

  public int[] printWeightOnScale(int[] arr, int totalSteps) {
    int[] weights = new int[totalSteps];

    trackPath(0, 0, weights, arr, totalSteps);
    return weights;
  }

  public boolean trackPath(int diff, int stepCount, int[] weights, int[] arr,
      int totalSteps) {

    if (stepCount >= totalSteps) {
      return true;
    }

    //Try all weight, and check which fits
    for (int wt : arr) {
      if (stepCount <= 0 || (weights[stepCount - 1] != wt && wt > diff)) {
        weights[stepCount] = wt;

        if (trackPath(Math.abs(wt - diff), stepCount + 1, weights, arr, totalSteps)) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[] arr = {2, 3, 5, 6};
    int steps = 10;

    WeighingScale weighingScale = new WeighingScale();
    System.out.println(Arrays.toString(weighingScale.printWeightOnScale(arr, steps)));
  }

}
