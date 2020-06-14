import javax.swing.tree.TreeNode;

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
//注意每个node都可以是负数

public class BTMaxPathSumSolution {
    /**
     * 给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和）
     */
    //分治法,需要考虑的是，子树中最大的路不一定必过根节点。所以要记录single Path的值。
    private class ResultType {
        //singlePath:从root往下走到任意点的最大路径，这条路径可以不包含任何点
        //maxPath:从树中任意点到任一点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        //Divide
        ResultType left = heper(root.left);
        ResultType right = heper(root.right);
        //Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0); //如果子树的singlePath是负数的话那root自己肯定数值大于加上子树singlePath的

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType res = helper(root);
        return res.maxPath;
    }

}