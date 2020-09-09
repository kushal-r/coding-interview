package datastructures.puzzle;

/**
 * Given two jugs of size m and n, where 0< m< n.
 * We have to find the number of operations needed to measure d liters of water using m and n.
 * <p>
 * <p>
 * Algorithm:
 * The problem can be modeled as Diophantine equation of the form mx + ny = d.
 * Which is solvable only if GCD(m,n) divides d.
 * Here x and y represents the number of fill or empty operation needed to be performed on that
 * bucket
 * We can start of with any of the buckets m or n, and completely fill it and pour it onto the
 * other jug or empty it, then we can empty the other jug and perform the required operation on
 * that jug based on the value of x,y
 * Ideally there can be two ways of doing this :
 * 1) Always pour from m to n jug
 * 2) Always pour from n to m jug
 * We need to find for both this cases and get the min steps from this.
 * <p>
 * Date: 05/03/20
 *
 * @author Kushal Roy
 * <p>
 * Referances:
 * https://www.geeksforgeeks.org/two-water-jug-puzzle/
 * https://www.geeksforgeeks.org/water-jug-problem-using-bfs/
 */
public class WaterJug {

  public int gcd(int m, int n) {
    if (n == 0) {
      return m;
    }

    return gcd(n, m % n);
  }

  public int pour(int m, int n, int d) {
    //We will be pouring water from m -> n, so we fill currFrom and currTo is empty
    int currFrom = m;
    int currTo = 0;
    int steps = 1;

    while (currFrom != d && currTo != d) {
      //Either m will be more than size of n, in that case n will be completely filled, else it
      // will be filled till the currTo amt.
      int pouredAmt = Math.min(currFrom, n - currTo);

      //Pour the amt into n
      currTo += pouredAmt;
      currFrom -= pouredAmt;
      steps++;

      if (currTo == d || currFrom == d) {
        break;
      }

      //If m is empty fill it
      if (currFrom == 0) {
        currFrom = m;
        steps++;
      }

      //If n is full empty it
      if (currTo == n) {
        currTo = 0;
        steps++;
      }
    }
    return steps;

  }

  public int findSteps(int m, int n, int d) {

    if (d % gcd(m, n) != 0) {
      return -1;
    }

    if (m > n) {
      int tmp = m;
      m = n;
      n = tmp;
    }

    //d cannot be achieved with m and n
    if (d > n) {
      return -1;
    }

    return Math.min(pour(m, n, d), pour(n, m, d));
  }

  public static void main(String[] args) {
    int n = 3, m = 5, d = 4;
    WaterJug waterJug = new WaterJug();

    System.out.println("Minimum number of steps required is " +
        waterJug.findSteps(m, n, d));
  }

}
