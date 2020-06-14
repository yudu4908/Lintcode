import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//import javax.swing.tree.TreeNode;  //用的不是这个TreeNode，而是自己定义的class

/** 
//Definition of TreeNode
public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
*/

public class BTreeLevelOrderTraversalSolution {
    //给一颗二进制树，让你返回层序遍历的结果
    //使用队列(LinkedList)， 先进先出. 入队的方法名叫做offer或add，offer比add 好（区别在于multithread）；弹出方法叫poll；stack的弹出是pop
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return result;
        }

        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> part = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                part.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            result.add(part);
        }
        return result;
    }
}