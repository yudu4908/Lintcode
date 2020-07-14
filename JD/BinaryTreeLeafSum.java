package JD;
//Given a binary tree, calculate the sum of leaves.
/**
 * 
Input：{1,2,3,4}
Output：7
Explanation：
    1
   / \
  2   3
 /
4
3+4=7
 */
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class BinaryTreeLeafSum {
    private int sum = 0;
    public int leafSum(TreeNode root) {
        helper(root);
        return sum;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum += root.val;
            return;
        }
        helper(root.left);
        helper(root.right);
    }
}