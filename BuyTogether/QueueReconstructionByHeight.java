package BuyTogether;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
假设你有一个顺序被随机打乱的列表，代表了站成一列的人群。每个人被表示成一个二元组(h, k)，
其中h表示他的身高，k表示站在他之前的身高高于或等于h的人数。你需要将这个队列重新排列以恢复其原有的顺序。
 */
public class QueueReconstructionByHeight {
 /**
 输入：
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
输出：
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]] 
 */    
   /**
    贪心。
将身高从高到低排序后依次插入即可。对于某个人[h,k]来说，插入答案的第k位。
但是注意[5,0], [5,2]先插入[5,0]是对的， 先插入[5,2]的话会导致[6, 1]在[5, 2]前面。
所以h相同时先插入k小的
    */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o1[1]; //身高相同时按照人数从小到大排序
                } else {
                    return o2[0] - o1[0]; //身高不同时按照身高从高到低排序
                }
            }
        }));
        List<int[]> resultList = new LinkedList<>();
        for (int[] cur : people) {
            resultList.add(cur[1], cur);//在指定index处插入
        }
        return resultList.toArray(new int[people.length][]); //数组和列表的区别，https://zhidao.baidu.com/question/1179645388655257819.html
    }
}