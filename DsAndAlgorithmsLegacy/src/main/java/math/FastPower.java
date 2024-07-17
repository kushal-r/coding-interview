package math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * <p>
 * Algorithm:
 * Simple solution is to multiply x, n-times, considering all the edge cases like n = -ve.
 * <p>
 * Another solution is as follows:
 * <p>
 * Assume we have got the result of x ^ {n / 2}, and now we want to get the result of x ^ n. Let A
 * be result of x ^ {n / 2}, we can talk about x ^ n
 * based on the parity of n respectively. If n is even, we can use the formula (x^n)^2=x^(2∗n)
 * to get x ^ n = A * A
 * If n is odd, then A * A = x ^ {n - 1}. Intuitively, We need to multiply another x to the result,
 * so x ^ n = A * A * x. This approach can be easily implemented using recursion. We call this
 * method "Fast Power", because we only need at most O(log n) computations to get x ^ n
 * n
 * <p>
 * Complexity:
 * Time - O(log n)
 * Space - O(log n)
 *
 * @author kushal
 * <p>
 * Date 11/09/20
 * <p>
 * Referances:
 * https://leetcode.com/problems/powx-n/
 */
public class FastPower {

  public double myPow(double x, int n) {
    if (n < 0) {
      x = 1 / x;
      n = -n;
    }
    return calculatePow(x, n);
  }

  public double calculatePow(double x, int n) {
    if (n == 0) {
      return 1;
    }

    double pow = calculatePow(x, n / 2);
    if (n % 2 == 0) {
      return pow * pow;
    } else {
      return pow * pow * x;
    }
  }

  public static void main(String[] args) {
    FastPower fp = new FastPower();

    System.out.println(fp.myPow(2, 10));
    System.out.println(fp.myPow(2.1, 3));

  }
}
