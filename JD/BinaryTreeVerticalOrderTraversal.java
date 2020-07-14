package JD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.tree.TreeNode;

/**
 * Given a binary tree, return the vertical order traversal of its nodes'
 * values. (ie, from top to bottom, column by column). If two nodes are in the
 * same row and column, the order should be from left to right.
 * 
 * EG1 Inpurt: {3,9,20,#,#,15,7} Output: [[9],[3,15],[20],[7]] Explanation: 
 * 3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
 * 
 * EG2 Input: {3,9,8,4,0,1,7} Output: [[4],[9],[3,0,1],[8],[7]] Explanation: 
 *   3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
 */
public class BinaryTreeVerticalOrderTraversal {
    /**
    对于一棵树，我们设其根节点的位置为0。
对于任一非叶子节点，若其位置为x，设其左儿子的位置为x-1，右儿子位置为x+1。
按照以上规则bfs遍历整棵树统计所有节点的位置，然后按位置从小到大输出所有节点。
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        Queue<Integer> qCol = new LinkedList<>();//队列是先进先出，栈是后进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //put the root node into queue
        qCol.offer(0); // put the column number 0 into qCol, this is root node's column number

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int col = qCol.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>(Arrays.asList(curr.val)));// why need to convert Array to List?
                //如果直接写map.put(col, curr.val);会报错Integer不能转为list
                //如果写map.put(col, Arrays.asList(curr.val));则asList因为返回的是一个只读列表，不能增删，这会导致后面同一列元素添加时报错
            } else {
                map.get(col).add(curr.val); //get(col) returns a List<Integer>
            }
            if (curr.left != null) {
                queue.offer(curr.left);
                qCol.offer(col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                qCol.offer(col + 1);
            }
        }
        for (int n : map.keySet()) {
            results.add(map.get(n));
        }
        return results;
    }
}