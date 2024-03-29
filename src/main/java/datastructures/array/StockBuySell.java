package datastructures.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share
 * of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * <p>
 * Algorithm:
 * If we plot the numbers of the given array on a graph.
 * The points of interest are the peaks and valleys in the given graph. We need to find the largest
 * peak following the smallest valley. We can maintain two variables - minprice and maxprofit
 * corresponding to the smallest valley and maximum profit (maximum difference between selling price
 * and min price) obtained so far respectively.
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - o(1)
 *
 * @author kushal
 * <p>
 * Date 22/09/20
 * <p>
 * References:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class StockBuySell {

  public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int minPrice = Integer.MAX_VALUE;

    for (int price : prices) {
      minPrice = Math.min(minPrice, price);

      maxProfit = Math.max(maxProfit, price - minPrice);
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    StockBuySell sb = new StockBuySell();

    System.out.println(sb.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    System.out.println(sb.maxProfit(new int[]{7, 6, 4, 3, 1}));

  }
}
