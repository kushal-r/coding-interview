package datastructures.tree.binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kushal
 */
public class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }




  public void printLevelOrder() {
    Queue<TreeNode> q = new LinkedList<>();
    TreeNode head = new TreeNode(val, left, right);
    q.offer(head);
    q.offer(null);

    while (!q.isEmpty()) {
      TreeNode curr = q.poll();
      if (curr == null) {
        if (!q.isEmpty()) {
          q.add(null);
        }
        System.out.println();
      } else {
        if (curr.left != null) {
          q.offer(curr.left);
        }
        if (curr.right != null) {
          q.offer(curr.right);
        }

        System.out.print(String.format(" %d ", curr.val));
      }
    }

  }

  @Override
  public String toString() {
    return "TreeNode{" +
        "val=" + val +
        ", left=" + left +
        ", right=" + right +
        '}';
  }
}
