package datastructures.tree.binary;

/**
 * Given a binary tree, we need to compute the length of the diameter of the tree. The diameter of
 * a binary tree is the length of the longest path between any two nodes in a tree. This path may
 * or
 * may not pass through the root.
 * <p>
 * <p>    1
 * <p>   / \
 * <p>  2   3
 * <p> / \
 * <p>4   5
 * <p>
 * Diameter is 3 which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Algorithm:
 * Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of
 * node.right) + 1. While we do, a path "through" this node uses 1 + (depth of node.left) + (depth
 * of node.right) nodes. Let's search each node and remember the highest number of nodes used in
 * some path. The desired length is 1 minus this number.
 * <p>
 * Complexity:
 * Time - O(n)
 * Space - O(1)
 *
 * @author kushal
 * <p>
 * References:
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfTree {

  private static int diameter = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    diameter = 0;

    diameterOfBinaryTreeUtil(root);
    return diameter - 1;
  }

  public int diameterOfBinaryTreeUtil(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftSubTreeDia = diameterOfBinaryTreeUtil(root.left);
    int rightSubTreeDia = diameterOfBinaryTreeUtil(root.right);

    diameter = Math.max(diameter, leftSubTreeDia + rightSubTreeDia + 1);

    //We return the max depth i.e. either the left or right branch length,
    // depth of the current path is 1+ either left or right path
    return Math.max(leftSubTreeDia, rightSubTreeDia) + 1;
  }


  public static void main(String[] args) {

    DiameterOfTree dt = new DiameterOfTree();
    TreeNode node4 = new TreeNode(4, null, null);
    TreeNode node5 = new TreeNode(5, null, null);
    TreeNode node2 = new TreeNode(2, node4, node5);
    TreeNode node3 = new TreeNode(3, null, null);
    TreeNode node1 = new TreeNode(1, node2, node3);

    System.out.println(dt.diameterOfBinaryTree(node1));

    System.out.println(dt.diameterOfBinaryTree(node4));

    System.out.println(dt.diameterOfBinaryTree(null));


  }
}
