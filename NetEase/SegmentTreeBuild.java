package NetEase;
/**
Input：[1,4]
Output："[1,4][1,2][3,4][1,1][2,2][3,3][4,4]"
Explanation：
	               [1,  4]
	             /        \
	      [1,  2]           [3, 4]
	      /     \           /     \
       [1, 1]  [2, 2]     [3, 3]  [4, 4]
       
题目要求Implement a build method with two parameters start and end, 
so that we can create a corresponding segment tree with every node has the correct start and start value, 
return the root of this segment tree.

eg2.

Input：[1,6]
Output："[1,6][1,3][4,6][1,2][3,3][4,5][6,6][1,1][2,2][4,4][5,5]"
Explanation：
	       [1,  6]
             /        \
      [1,  3]           [4,  6]
      /     \           /     \
   [1, 2]  [3,3]     [4, 5]   [6,6]
   /    \           /     \
[1,1]   [2,2]     [4,4]   [5,5]
 */

     /**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end) {
 *         this.start = start, this.end = end;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class SegmentTreeBuild {
    /**
     考点：
线段树
二分
题解：递归过程按照左子树范围为(start, (start + end) / 2)，右子树范围为((start + end) / 2 + 1, end)建树即可。
     */
    public SegmentTreeNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);

        if (start != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid);
            root.right = build(mid + 1, end);
        }
        return root; //最后start == end就是递归的出口
    }

}