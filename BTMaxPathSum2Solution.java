import java.util.LinkedList;

import javax.swing.tree.TreeNode;

import sun.misc.Queue;

public class BTMaxPathSum2Solution {
    //Given a binary tree, find the maximum path sum from root. 区别在于必须经过root
    //可以使用BFS
    public int maxpathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //int res = 0;//如果树就一个节点{-2}那返回值就成了0而不是期望的-2，所以这里要用负无穷
        int res = Integer.MIN_VALUE;
        Queue<NewNode> queue = new LinkedList<>();
        queue.add(new NewNode(root, root.val));
        while (!queue.isEmpty()) {
            NewNode cur = queue.poll();
            if (cur.sum > res) {
                res = cur.sum;
            }
            if (cur.treeNode.left != null) {
                queue.offer(new NewNode(cur.treeNode.left, cur.sum + cur.treeNode.left.val));
            }
            if (cur.treeNode.right != null) {
                queue.offer(new NewNode(cur.treeNode.right, cur.sum + cur.treeNode.right.val));
            }
        }
        return res;
    }

    public class NewNode {
        public TreeNode treeNode;
        public int sum;
        public NewNode(TreeNode treeNode, int sum) {
            this.treeNode = treeNode;
            this.sum = sum;
        }
    }
}

//BFS:
/**
 * high code complexity
 * non-recursive
 * shortest steps
 */
//Recursion: traverse, divide&conquer
/**
 * low code complexity
 * recursive
 */