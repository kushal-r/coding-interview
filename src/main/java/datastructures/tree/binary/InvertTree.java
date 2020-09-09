package datastructures.tree.binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Invert a binary tree.
 * <p>
 * Example:
 * <p>Input:
 * <p>      4
 * <p>    /   \
 * <p>   2     7
 * <p>  / \   / \
 * <p> 1   3 6   9
 *
 * <p>
 * Output:
 *
 * <p>     4
 * <p>   /   \
 * <p>  7     2
 * <p> / \   / \
 * <p> 9   6 3   1
 * <p>
 * Algorithm:
 * The inverse of an empty tree is the empty tree. The inverse of a tree with root r, and subtrees
 * {right} and {left}, is a tree with root r, whose right subtree is the inverse of
 * {left}, and whose left subtree is the inverse of {right}.
 * <p>
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(n) [O(h)] - Where h is the height of the tree, and h function calls will be placed o
 * the stack.
 *
 * @author kushal
 */
public class InvertTree {

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);

    root.left = right;
    root.right = left;
    return root;
  }


  public TreeNode invertTreeIterative(TreeNode root) {
    if (root == null) {
      return null;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      TreeNode curr = q.poll();
      TreeNode tmp = curr.left;
      curr.left = curr.right;
      curr.right = tmp;

      if (curr.left != null) {
        q.offer(curr.left);
      }
      if (curr.right != null) {
        q.offer(curr.right);
      }
    }

    return root;
  }

  public static void main(String[] args) {

    InvertTree it = new InvertTree();
    TreeNode node1 = new TreeNode(1, null, null);
    TreeNode node2 = new TreeNode(3, null, null);
    TreeNode node3 = new TreeNode(2, node1, node2);
    TreeNode node4 = new TreeNode(6, null, null);
    TreeNode node5 = new TreeNode(9, null, null);
    TreeNode node6 = new TreeNode(7, node4, node5);
    TreeNode node7 = new TreeNode(4, node3, node6);

    it.invertTreeIterative(node7);

    node7.printLevelOrder();
  }
}
