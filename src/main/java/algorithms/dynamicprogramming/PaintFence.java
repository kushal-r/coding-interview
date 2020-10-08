package algorithms.dynamicprogramming;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * <p>
 * You have to paint all the posts such that no more than two adjacent fence posts have the same
 * color.
 * <p>
 * Return the total number of ways you can paint the fence.
 * <p>
 * Note:
 * n and k are non-negative integers.
 * <p>
 * Example:
 * <p>
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 * <p>
 * Algorithm:
 * diff = no of ways when color of last
 * two posts is different
 * same = no of ways when color of last
 * two posts is same
 * total ways = diff + sum
 * <p>
 * for n = 1
 * diff = k, same = 0
 * total = k
 * <p>
 * for n = 2
 * diff = k * (k-1) //k choices for
 * first post, k-1 for next
 * same = k //k choices for common
 * color of two posts
 * total = k +  k * (k-1)
 * <p>
 * for n = 3
 * diff = [k +  k * (k-1)] * (k-1)
 * (k-1) choices for 3rd post
 * to not have color of 2nd
 * post.
 * same = k * (k-1)
 * c'' != c, (k-1) choices for it
 * <p>
 * Hence we deduce that,
 * total[i] = same[i] + diff[i]
 * same[i]  = diff[i-1]
 * diff[i]  = (diff[i-1] + diff[i-2]) * (k-1)
 * = total[i-1] * (k-1)
 *
 * @author kushal
 * <p>
 * Date 06/10/20
 * <p>
 * References:
 * https://leetcode.com/problems/paint-fence/
 */
public class PaintFence {

  public int numWays(int n, int k) {
    if (n < 0 || k < 0) {
      return 0;
    }

    if (n == 1) {
      return k;
    }

    //Case : First 2 posts have same colour
    int sameColour = k;

    //Case : First 2 posts have diff. colour
    int diffColour = k * (k - 1);

    // Total ways to colour 2 posts = sameColour + diffColour

    for (int i = 3; i <= n; i++) {
      int tmp = diffColour;

      /*
       * Current Case: To every samecolor and diffcolor we can add a new post with different color as the last one. We have k-1 color
       * options for the last one.
       *
       * Ways to colour this post with diff colour= Total ways to colour prev post [sameColour + diffColour] * (k-1) remaining colours
       */
      diffColour = (sameColour + diffColour) * (k - 1);

      /*
       * To every diffCase we can add a new post with the same color as the last one to not generate violation - no
       * more than 2 adjacent fence posts have the same color.
       *
       * Ways to colour this post with same colour = sameColour + diffColour
       */
      sameColour = tmp;

    }

    return sameColour + diffColour;
  }

  public static void main(String[] args) {
    PaintFence pf = new PaintFence();
    System.out.println(pf.numWays(3, 2));
  }
}
