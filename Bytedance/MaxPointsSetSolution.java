package Bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
Given a list of coordinate points Points, where Points[i] [0] represents the horizontal axis coordinates,
 Points [i] [1] represents the vertical axis coordinates.
There is a point p that satisfies the contidion that no point in Points is in the upper right area of p 
(both horizontal and vertical coordinates are greater than p), The point p is called the maximum point.
Return all max points, max points in the list are sorted in the order of X axis from small to large.
 */
class Node {
    public int x, y;
    Node() {} //这里为什么要写一个无参数的构造器
    Node(int a, int b) {
        x = a;
        y = b;
    }
}

public class MaxPointsSetSolution {
    /**
     * 首先对于所有的点进行x坐标从大到小排序。
然后用一个变量 max_y 来存储目前最大的y。
当你遍历这个排好序的数组的时候，只要该点的 y 大于 max_y 就证明是最大点。
     */
    /**
     * @param Points: a list of [x, y]
     * @return: return a list of points
     */
    Comparator<Node> comparator = new Comparator<Node>() {  //override
        public int compare(Node a, Node b) {
            return b.x - a.x;  //return a.x - b.x 表示从小到大排序；反过来表示从大到小排序
        }
    };  //这个地方必须加分号，因为这不是一个类或者方法的定义，而是一个语句的结束

    public List<List<Integer>> MaximumPointsSet(int[][] Points) {
        int n = Points.length, max_y = -1; //数组长度直接用length，表示一共有多少个点
        int[] vis = new int[n]; //?
        List<Node> nums = new ArrayList<Node>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(new Node(Points[i][0], Points[i][1]));//为什么要额外创建nums列表？--为了使用下面的sort方法
        }

        Collections.sort(nums, comparator); //按照x坐标大小从大到小排序

        for (int i = 0; i < n; i++) {
            if (nums.get(i).y > max_y) {
                vis[i] = 1; //从x坐标最大的点开始判断， x最大时不可能有点比他x和y同时大，所以他一定是max点，用vis[i]标记
            }
            max_y = Math.max(nums.get(i).y, max_y);//x从大到小进行扫描时只要保证新点的y坐标比所有扫过的点的y的最大值大就也是max
        }

        for (int i = n - 1; i >= 0; i--) {
            if (vis[i] == 1) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(nums.get(i).x);
                temp.add(nums.get(i).y);
                result.add(temp);
            }
        }
        return result;
    }

}