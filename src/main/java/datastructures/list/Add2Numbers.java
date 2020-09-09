package datastructures.list;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
 * return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p>
 * Algorithm:
 * Create a dummy head and start iterating over the lists, until both have completed.
 * If carry is not 0, updated the value of carry in a new node add it to the dummy heads tail
 * Return dummy.next()
 * <p>
 * Complexity:
 * Time - O(n) [n -size of the longest list]
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/add-two-numbers/
 */
public class Add2Numbers {

  public ListNode calculateSum(ListNode num1, ListNode num2) {
    if (num1 == null) {
      return num2;
    } else if (num2 == null) {
      return num1;
    }

    ListNode dummySum = new ListNode();
    ListNode start1 = num1;
    ListNode start2 = num2;
    ListNode itr = dummySum;
    int carry = 0;

    while (start1 != null || start2 != null) {
      int x = start1 != null ? start1.val : 0;
      int y = start2 != null ? start2.val : 0;

      int currSum = x + y + carry;
      carry = currSum / 10;
      currSum = currSum % 10;

      itr.next = new ListNode(currSum, null);
      itr = itr.next;
      if (start1 != null) {
        start1 = start1.next;
      }

      if (start2 != null) {
        start2 = start2.next;
      }
    }

    if (carry != 0) {
      itr.next = new ListNode(carry, null);
    }

    return dummySum.next;
  }

  public static void main(String[] args) {
    Add2Numbers add2Numbers = new Add2Numbers();
    ListNode num1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
    ListNode num2 = new ListNode(5, new ListNode(6, new ListNode(4, null)));
    System.out.println("Expected [708] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

    num1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
    num2 = new ListNode(5, null);
    System.out.println("Expected [743] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

    num1 = new ListNode(5, null);
    num2 = new ListNode(5, null);
    System.out.println("Expected [01] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

    num1 = new ListNode(5, null);
    num2 = new ListNode(0, null);
    System.out.println("Expected [5] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

    num1 = new ListNode(1, null);
    num2 = new ListNode(9, new ListNode(9, null));
    System.out.println("Expected [001] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

    num1 = new ListNode(9, null);
    num2 = new ListNode(9, null);
    System.out.println("Expected [81] : Output [" + add2Numbers.calculateSum(num1, num2) + "]");

  }
}
