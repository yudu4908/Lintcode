package NetEase;
/**
对于一个数组，我们可以对其建立一棵 线段树, 
每个结点存储一个额外的值 count 来代表这个结点所指代的数组区间内的元素个数. (数组中并不一定每个位置上都有元素)

实现一个 query 的方法，该方法接受三个参数 
root, start 和 end, 分别代表线段树的根节点和需要查询的区间，找到数组中在区间[start, end]内的元素个数。
 */
/**
输入："[0,3,count=3][0,1,count=1][2,3,count=2][0,0,count=1][1,1,count=0][2,2,count=1][3,3,count=1]",[[1, 1], [1, 2], [2, 3], [0, 2]]
输出：[0,1,2,2]
解释：
对应的线段树为：

	                     [0, 3, count=3]
	                     /             \
	          [0,1,count=1]             [2,3,count=2]
	          /         \               /            \
	   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]

Input : query(1,1), Output: 0

Input : query(1,2), Output: 1

Input : query(2,3), Output: 2

Input : query(0,2), Output: 2
 */
public class SegmentTreeQuery2 {
    /**
    线段树
题解：递归实现线段树自上向下的区间查询，自下向上的合并统计即可。
     */
        /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (start > end || root == null || end < root.start || root.end < start) {
            return 0;
        }
        if (start <= root.start && root.end <= end) { //相等
            return root.count;
        }
        int res = 0;

        int mid = root.start + (root.end - root.start) / 2; //用的是root的start和end去划分左右子树的，可不是查询的start和end
        if (end <= mid) {
            res = query(root.left, start, end); //这里可别写成start到mid了
        }
        else if (mid < start) {
            res = query(root.right, start, end);
        }
        else if (start <= mid && mid < end) { //分裂
            res = query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
        return res;
    }

/** 
        //左子区
        if (start <= mid) {
            if (mid < end) {//分裂
                leftsum = query(root.left, start, mid);
            } else {
                leftsum = query(root.left, start, end);
            }
        }

        //右子区
        if (mid < end) { //分裂
            if (start <= mid) {
                rightsum = query(root.right, mid + 1, end);
            } else {
                rightsum = query(root.right, start, end);
            }
        }
        return leftsum + rightsum;

    }   */

}