package NetEase;
/**
For a Maximum Segment Tree, which each node has an extra value max to store the maximum value in this node's interval.

Implement a modify function with three parameter root, 
index and value to change the node's value with [start, end] = [index, index] 
to the new given value. Make sure after this change, every node in segment tree s
till has the max attribute with the correct value.
 */
/**
Input："[1,4,max=3][1,2,max=2][3,4,max=3][1,1,max=2][2,2,max=1][3,3,max=0][4,4,max=3]",2,4
Output："[1,4,max=4][1,2,max=4][3,4,max=3][1,1,max=2][2,2,max=4][3,3,max=0][4,4,max=3]"
Explanation：
For segment tree:

	                      [1, 4, max=3]
	                    /                \
	        [1, 2, max=2]                [3, 4, max=3]
	       /              \             /             \
	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]

if call modify(root, 2, 4), we can get:

	                      [1, 4, max=4]
	                    /                \
	        [1, 2, max=4]                [3, 4, max=3]
	       /              \             /             \
    [1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
    ········································································
    eg2
    Input："[1,4,max=3][1,2,max=2][3,4,max=3][1,1,max=2][2,2,max=1][3,3,max=0][4,4,max=3]",4,0
Output："[1,4,max=4][1,2,max=4][3,4,max=0][1,1,max=2][2,2,max=4][3,3,max=0][4,4,max=0]"
Explanation：
For segment tree:

	                      [1, 4, max=3]
	                    /                \
	        [1, 2, max=2]                [3, 4, max=3]
	       /              \             /             \
	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
if call modify(root, 4, 0), we can get:
	
	                      [1, 4, max=2]
	                    /                \
	        [1, 2, max=2]                [3, 4, max=0]
	       /              \             /             \
	[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]
 */
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

public class SegmentTreeModify {
    //这个题就是改变root tree节点区间中下标为index的节点的值
    /**
     *考点：
线段树
题解：按照区间自上向下查询区间，然后修改叶子节点，再自下向上合并修改各节点信息。
     */
/**
     *@param root, index, value: The root of segment tree and 
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) { //查找到要改的节点
            root.max = value;
            return;
        }
        //查找
        int mid = (root.start + root.end) / 2;
        if (root.start <= index && index <= mid) {
            modify(root.left, index, value);
        }
        if (mid < index && index <= root.end) {
            modify(root.right, index, value);
        } 
        //更新
        root.max = Math.max(root.left.max, root.right.max);
    }
}