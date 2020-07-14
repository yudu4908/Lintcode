package NetEase;
/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
public class SegmentTreeBuild2 {
    /**
The root's start and end is given by build method.
The left child of node A has start=A.left, end=(A.left + A.right) / 2.
The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right.
if start equals to end, there will be no children for this node.
     */
    /**
     Implement a build method with a given array, 
     so that we can create a corresponding segment tree with every node value 
     represent the corresponding interval max value in the array, return the root of this segment tree.
     */
/**
Input: [3,2,1,4]
Explanation:
The segment tree will be
          [0,3](max=4)
          /          \
       [0,1]         [2,3]    
      (max=3)       (max=4)
      /   \          /    \    
   [0,0]  [1,1]    [2,2]  [3,3]
  (max=3)(max=2)  (max=1)(max=4)
 */
//模板题, 递归构造线段树即可. 注意内存要分配在堆上, 而不是栈上. ????
    /**
     *@param A: a list of integer
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        return buildTree(0, A.length - 1, A);
    }

    public SegmentTreeNode buildTree(int start, int end, int[] A) {
        if (start > end) {//这个可以不用写。根据题意不可能有这种情况
            return null;
        }
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]); //说明A的长度就是1，就一个点
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        int mid = start + (end - start) / 2;
        node.left = this.buildTree(start, mid, A);
        node.right = this.buildTree(mid + 1, end, A);

        // if (node.left != null && node.left.max > node.max) {
        //     node.max = node.left.max;
        // }
        // if (node.right != null && node.right.max > node.max) {
        //     node.max = node.right.max;
        // }
        node.max = Math.max(node.left.max, node.right.max);
        return node;
    }

}