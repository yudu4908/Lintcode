import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FetchSuppliesSolution {
    //求所有军营到马路的最短距离和
    /**
     * [[-10,0],[0,0],[10,0]]
     * 排序，中位数，根据题意，完全可以无视每个点的y坐标
    所有点到达马路的直线距离为垂直距离，想要让每个点到马路的路程和最小那么马路一定在中位数上
    如果有奇数个点，则马路处于那个电上，如果有偶数个点，马路在中位数的两个端点之间，
    复杂度O(nlogn)
     * @param coordinates: a series of coordinates (x, y)
     * @return: Given a series of coordinates (x, y),return the shortest sum of distance
    */
    public int fetchSupplies(List<List<Integer>> coordinates) {
        //import Collections;
        List<Integer> tmp = new ArrayList<Integer>();
        for (List<Integer> coordinate : coordinates) {
            tmp.add(coordinate.get(0)); //获取x坐标
        }
        Collections.sort(tmp);
        int size = tmp.size();
        double mid = 0;
        if (size % 2 == 0) { //说明是偶数
            int l = size / 2 - 1;
            int r = l + 1;
            mid = (tmp.get(l) + tmp.get(r)) / 2; //默认向下取整
        }
        else {
            mid = tmp.get((size - 1) / 2);
        }
        double ans = 0;
        for (int i = 0; i < size; i++) {
            ans += Math.abs(tmp.get(i) - mid);  //求和每个军营的x坐标减去马路坐标的距离
        }
        return (int)ans;
    }
}