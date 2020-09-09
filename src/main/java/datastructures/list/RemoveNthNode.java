package datastructures.list;

/**
 * Given a list, remove the n-th node from the end return the head
 * <p>
 * Algorithm: The main idea is to maintain a fast and a slow pointer having a difference of n+1 in
 * between them. So as whenever the fast pointer reaches the end the slow will be pointing to the
 * n-th node from the end.
 * <p>
 * Always use dummy node while performing operation on list. This is crucial for handling corner
 * cases like removing the head of the list, etc
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNode {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    ListNode dummy = new ListNode(-1, null);
    dummy.setNext(head);

    ListNode slowPtr = dummy;
    ListNode fastPtr = dummy;

    for (int i = 1; i <= n + 1; i++) {
      fastPtr = fastPtr.getNext();
    }

    while (fastPtr != null) {
      fastPtr = fastPtr.getNext();
      slowPtr = slowPtr.getNext();
    }

    slowPtr.setNext(slowPtr.getNext().getNext());

    //Returning dummy.next is crucial to fix corner cases
    return dummy.getNext();
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(5, null);
    ListNode node2 = new ListNode(4, node1);
    ListNode node3 = new ListNode(3, node2);
    ListNode node4 = new ListNode(2, node3);
    ListNode node5 = new ListNode(1, node4);

    RemoveNthNode rnn = new RemoveNthNode();
    System.out.println("[]" + rnn.removeNthFromEnd(node1, 1));

    node1 = new ListNode(5, null);
    node2 = new ListNode(4, node1);
    System.out.println("[5]" + rnn.removeNthFromEnd(node2, 2));

    node1 = new ListNode(5, null);
    node2 = new ListNode(4, node1);
    node3 = new ListNode(3, node2);
    node4 = new ListNode(2, node3);
    node5 = new ListNode(1, node4);
    System.out.println("[1235]" + rnn.removeNthFromEnd(node5, 2));

  }
}


