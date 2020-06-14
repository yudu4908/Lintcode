package Kwai;

import java.util.Arrays;
import java.util.Comparator;

/**
运动会要来了，小红作为拉拉队长，要在每个项目中为参赛同学加油助威。
小红需要遵守以下规则：
1.给每个体育项目加油时长必须超过项目时长的一半，每个项目只能加油一次
2.体育项目的开始和结束时间都是整点，小红选择进入项目和离开的时间也必须是整点
3.不考虑往返于各个体育项目比赛场地中花费的时间
现在给出所有项目的起止时间，小红想知道自己能不能在每个项目中都为同学加油呢？
若可以则返回1，或不行则返回-1.
输入：[[3,10],[1,5],[4,6]]
输出：1
说明：1-3时间参加第二个项目，3-4不参加，4-6参加第三个项目，6-10参加第一个项目
 */
public class SportsMeetingSolution {
    /**
     * @param Events: the start and end time
     * @return: return 1 if there is a solution, otherwise resturn -1
     */
    public int CheerAll(int[][] Events) {
        if (Events == null || Events.length == 0 || Events[0].length == 0) {
            return -1;
        }

        Arrays.sort(Events, new Comparator<int[]>() {
            public int compare(int[] t1, int[] t2) {
                int a = (t1[1] - t1[0]) / 2 + 1 + t1[0]; //运动t1加油的最早结束时间点--不对，实际上是按照最迟开始时间从小到大排序的，也就是贪心法
                int b = (t2[1] - t2[0]) / 2 + 1 + t2[0];
                return a - b; //将最早结束拉拉时间的运动从小到大排序--不对，实际上是按照最迟开始时间从小到大排序的，也就是贪心法
            }
        });
        int time = 0;
        int n = Events.length;
        for (int i = 0; i < n; i++) {
            int start = Events[i][0];
            int end = Events[i][1];
            time = Math.max(time, start);
            if (end - time <= (end - start) / 2) { //这个运动我们完不成拉拉就返回-1， 大于就代表至少要大1
                return -1;
            }
            //time = time + (end - time) / 2 + 1; //这个是不对的
            time = time + (end - start) / 2 + 1; 
        }
        return 1;
    }
}