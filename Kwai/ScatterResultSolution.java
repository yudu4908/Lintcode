package Kwai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 本题可以利用双队列进行模拟。
 * 利用队列分别存储 P, V元素，按要求每 n 个元素需有 1 个 P 元素，
 * 因此对于每 n 个元素，弹出 1 个 P 元素，弹出 n-1 个 V 元素即可。
需要注意第一个 P 元素的特殊处理。

时间复杂度：O(N), N 为元素个数
空间复杂度：O(N), N 为元素个数
 */
public class ScatterResultSolution {
    /**
某个推荐系统会推荐出一个由视频和图片元素组成的列表。x 表示元素编号，视频用 V_x 表示，图片用 P_x 表示。现在需要对这些元素进行打散，打散规则如下：

第一个[图片P]出现的位置不变；
从第一个[图片P]后开始，每 n 个元素里面恰好出现1个[图片P]；
图片之间的相对顺序不变；
不能满足打散规则的元素需要舍去。给出元素列表和 n 的值，请返回打散后的元素列表。
     */
    /**
     * 1 ≤元素个数 ≤ 10000
2 ≤ n ≤ 元素个数
保证元素中至少有 1 个 [图片P]
     */
    /**
样例1：
输入样例：
elements=["V_0", "V_1", "V_2", "P_3", "P_4", "P_5", "V_6", "P_7", "V_8", "V_9"] , n=3
输出样例：
["V_0", "V_1", "V_2", "P_3", "V_6", "V_8", "P_4", "V_9"]
样例解释：
P_3 需出现在从0开始的第3位，由于每3个元素中恰好一个 P，P_5 和 P_7 满足不了打散要求，被删除。

样例2：
输入样例：
elements=["V_0", "P_1", "V_2", "V_3", "V_4"] , n=2
输出样例：
["V_0", "P_1", "V_2"]
样例解释：
P_1 需出现在从0开始的第1位，由于此后每2个元素中恰好一个 P，V_3 和 V_4 满足不了打散要求，被删除。
     */

     /**
本题可以利用双队列进行模拟。利用队列分别存储 P, V元素，
按要求每 n 个元素需有 1 个 P 元素，因此对于每 n 个元素，弹出 1 个 P 元素，弹出 n-1 个 V 元素即可。
需要注意第一个 P 元素的特殊处理。

时间复杂度：O(N), N 为元素个数
空间复杂度：O(N), N 为元素个数
     */
    /**
     * @param elements: A list of recommended elements.
     * @param n:        [picture P] can appear at most 1 in every n
     * @return: Return the scattered result.
     */
    public List<String> scatter(List<String> elements, int n) {
        //定义双队列，分别存储图片元素p和视频元素v
        Queue<String> queueP = new LinkedList<String>();
        Queue<String> queueV = new LinkedList<String>();
        
        //第一个图片P出现的位置
        int firstP = -1;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).charAt(0) == 'P') {
                if (firstP == -1) {
                    firstP = i;
                }
                //该元素加入P队列
                queueP.offer(elements.get(i));
            } else {
                //该元素加入V队列
                queueV.offer(elements.get(i));
            }
        }
        //定义打散后的结果序列
        List<String> result = new ArrayList<>();
         // 将出现第 1 个 P 前的 V 元素存入结果序列
         while (firstP > 0) {
             firstP--;
             result.add(queueV.element());
             queueV.poll();
         }
         //辅助变量
         int step = 0;
         while (!queueP.isEmpty()) {
             //每n个元素中恰好有一个p
             result.add(queueP.element());
             queueP.poll();
             step = n;

             //将n - 1个V元素存入结果序列
             while (!queueV.isEmpty() && step > 1) {
                 result.add(queueV.element());
                 queueV.poll();
                 step--;
             }
             //若当前已经无法满足打散要求(V元素不足n - 1个)，直接结束
             if (step > 1) {
                 break;
             }
         }
         return result;
    }
}