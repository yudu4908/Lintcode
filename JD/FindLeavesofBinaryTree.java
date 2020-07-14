package JD;

import java.util.*;
/**
Given a binary tree, collect a tree's nodes 
as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
Example1
Input: {1,2,3,4,5}
Output: [[4, 5, 3], [2], [1]].
Explanation:

    1
   / \
  2   3
 / \     
4   5    
 */
public class FindLeavesofBinaryTree {
    /**
    @param root: the root of the binary tree
    @return collect and remove all leaves
     */
    int dfs(TreeNode cur, Map<Integer, List<Integer>> depth) {
        if (cur == null) {
            return 0;
        }
        int d = Math.max(dfs(cur.left, depth), dfs(cur.right, depth)) + 1;//结合示例去看，最底层的三个叶子都是深度1，2是深度2， 1是深度3.越往根节点靠近深度越深
        depth.putIfAbsent(d, new ArrayList<>());
        depth.get(d).add(cur.val);
        return d;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> depth = new HashMap<>();
        int max_depth = dfs(root, depth);
        for (int i = 1; i <= max_depth; i++) {
            ans.add(depth.get(i));
        }
        return ans;
    }
}