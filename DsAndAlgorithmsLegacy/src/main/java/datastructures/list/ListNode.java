package datastructures.list;

/**
 * @author kushal
 */
public class ListNode {


  int val;
  ListNode next;

  public void setVal(int val) {
    this.val = val;
  }

  public int getVal() {
    return val;
  }

  public ListNode getNext() {
    return next;
  }

  public void setNext(ListNode next) {
    this.next = next;
  }

  ListNode() {
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  @Override
  public String toString() {
    return val + " " + (next != null ? next : "");

  }
}

