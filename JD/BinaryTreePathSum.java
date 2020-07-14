package JD;

import java.util.ArrayList;

/**
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
A valid path is from root node to any of the leaf nodes.
Eg1:
Input:
{1,2,4,2,3}
5
Output: [[1, 2, 2],[1, 4]]
Explanation:
The tree is look like this:
	     1
	    / \
	   2   4
	  / \
	 2   3
For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5

Eg2:
Input:
{1,2,4,2,3}
3
Output: []
Explanation:
The tree is look like this:
	     1
	    / \
	   2   4
	  / \
	 2   3
Notice we need to find all paths from root node to leaf nodes.
1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5 
There is no one satisfying it.
 */

import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * Definition of TreeNode: public class TreeNode { public int val; public
 * TreeNode left, right; public TreeNode(int val) { this.val = val; this.left =
 * this.right = null; } }
 */
public class BinaryTreePathSum {
    //这是最简单的深度优先搜索dfs，将树从根到叶子进行遍历，遍历同时记录当前的和，到达最底部时判断是否等于目标值
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
                // Algorithm: Traverse遍历
        // Use recursion递归 to traverse the tree in preorder, pass with a parameter
        // `sum` as the sum of each node from root to current node.
        // Put the whole path into result if the leaf has a sum equal to target.
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target, result);
        return result;
    }

    private void helper(TreeNode root, ArrayList<Integer> path, int sum, int target, List<List<Integer>> result) {
        //meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                //result.add(path);
                result.add(new ArrayList<Integer>(path)); //不能直接加path，因为path在整个递归过程中不断变化。我们真正要的只是她在此时此刻的值
            }
            return;
        }

        //go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target, result);
            path.remove(path.size() - 1); //移除掉最后一位也就是前面新加的root.left.val
        }

        //go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target, result);
            path.remove(path.size() - 1);
        }
    }
}