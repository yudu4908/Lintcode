package NetEase;
/**
 * 
对于一个有n个数的整数数组，在对应的线段树中,
 根节点所代表的区间为0-n-1, 每个节点有一个额外的属性max，值为该节点所代表的数组区间start到end内的最大值。

为SegmentTree设计一个 query 的方法，
接受3个参数root, start和end，线段树root所代表的数组中子区间[start, end]内的最大值。
 */
public class SegmentTreeQuery {
    /**
    考点：

线段树
题解：
每次分别对当前区间的左右子树搜索区间最大值。
如果查询起点位于区间左侧，终点位于右侧，左子树查询区间为(start,mid)。如果终点位于左侧，左子树查询区间为(start,end)。
如果查询终点位于区间右侧，起点位于左侧，右子树查询区间为(mid+1,end)。如果起点位于右侧，右子树查询区间为(start,end)。
/**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The maximum number in the interval [start, end]

     */

     // int max = Integer.MIN_VALUE; //注意：方法一的max定义在query函数外面或里面都可以
    public int query(SegmentTreeNode root, int start, int end) {
        // if (root == null || start > end || root.start > end || root.end < start) {
        //     return 0;
        // }
        // if (start == root.start && end == root.end) {
        //     return root.max;
        // }

        // int mid = (root.start + root.end) / 2;
        

        // if (end <= mid) { //必在左子区
        //     max = query(root.left, start, end);
        // }

        // else if (mid < start) { //必在右子区
        //     max = query(root.right, start, end);
        // }
        // else if (start <= mid && mid < end) {//分裂
        //     int leftmax = query(root.left, start, mid);
        //     int rightmax = query(root.right, mid + 1, end);
            //max = Math.max(leftmax, rightmax);
        // }
        // return max;

        //其实上面我自己写的方法一思路更清晰易懂，上面的方法是基于start和end所在的区段为出发点去考虑所有场景
        //下面的方法二则是以每个节点遍历左孩子和右孩子去cover所有场景的
        if (root == null || start > end || root.start > end || root.end < start) {
            return Integer.MIN_VALUE;
        }
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;

        if (start == root.start && end == root.end) { //递归的出口
            return root.max;
        }

        int mid = (root.start + root.end) / 2; //tree根节点区间进行二分
        //左子区
        if (start <= mid) {
            if (end > mid) { //拆分1
                leftMax = query(root.left, start, mid);
            } else {//这里必须加else， 不然的话就成了if执行完一定执行后面的语句了。 但我们想要的是非A即B的执行
                leftMax = query(root.left, start, end); 
            }
        }

        //右子区
        if (mid < end) { //这里不能加=， 
            if (start <= mid) {//拆分2
                rightMax = query(root.right, mid + 1, end);
            } else {
                rightMax = query(root.right, start, end);
            }
        }
        //不存在不满足上述两个条件的情景，上面两个if是隐式互斥的
        return Math.max(leftMax, rightMax);

    }
}