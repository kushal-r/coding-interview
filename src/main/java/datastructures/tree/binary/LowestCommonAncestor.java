package datastructures.tree.binary;

/**
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a
 * node to be a descendant of itself).”
 * <p>
 * Algorithm:
 * We start from the root of the tree and check if the root itself is one of the nodes, in that
 * case
 * return the root.
 * Else we recursively check if any of the left or right subtrees contains the nodes. if that is
 * the
 * case then the left and right nodes returned by the recursive call will return either any of left
 * of right nodes or only one of them. If both are found in the left and right subtrees then return
 * the current root and LCA, else if both are not found the  return null.
 * Finally return either of the left/right whichever is not null.
 * <p>
 * Complexity:
 * Time - O(n) - We traverse each node at max once
 * Space - O(n)
 *
 * @author kushal
 * <p>
 * References:
 * https://www.youtube.com/watch?v=13m9ZCB8gjw
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if (root == p || root == q) {
      return root;
    }

    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    if (left != null && right != null) {
      return root;
    }

    if (left == null && right == null) {
      return null;
    }

    return left != null ? left : right;
  }


  public static void main(String[] args) {
    LowestCommonAncestor lca = new LowestCommonAncestor();

    TreeNode node1 = new TreeNode(7, null, null);
    TreeNode node2 = new TreeNode(4, null, null);
    TreeNode node3 = new TreeNode(2, node1, node2);
    TreeNode node4 = new TreeNode(6, null, null);
    TreeNode node5 = new TreeNode(5, node4, node3);
    TreeNode node6 = new TreeNode(0, null, null);
    TreeNode node7 = new TreeNode(8, null, null);
    TreeNode node8 = new TreeNode(1, node6, node7);
    TreeNode node9 = new TreeNode(1, node5, node8);

    System.out.println(lca.lowestCommonAncestor(node9, node5, node2).val);
  }
}
