package meituan2020;
/**
 * 小美是团队的负责人，需要为团队制定工作的计划，来帮助团队产生最大的价值。每周团队都会有两项候选的任务,其中一项为简单任务
 * 一项为复杂任务,两项任务都能在一周内完成。在第i周,团队完成简单任务的价值为low_i, 完成复杂任务的价值为high_i
 * 由于复杂任务本身的技术难度较高，团队如果在第i周选择执行复杂任务的话,需要在i-1周不做任何任务来专心准备。
 * 如果团队在第i周选择执行简单任务的话,不需要提前做任何准备。现在小美的团队收到了未来n周的候选任务列表,
 * 请帮助小美确定每周的工作安排使得团队的工作价值最大。
 */
/**
 * 输入:low=[4,2,3,7],hard=[3,5,6,9]
输出:17
解释:在第一周的时候挑选简单任务，价值=4
在第二周做准备，第三周挑选复杂任务，价值=4+6=10
在第四周挑选简单任务，价值=10+7=17
 */
public class WorkPlanSolution {
    /**
     * 较为简单的dp。
       dp[i]代表做完第i天的任务后，所能获得的最大价值。
        所以可以在前一天的基础上挑选简单任务，或者在倒数第二天的基础上挑选复杂任务
        dp[i]=max(dp[i-1]+low[i],dp[i-2]+high[i])
     */
    /**
     * @param low: the simple task
     * @param high: the complex task
     * @return: the most value
     */
    public int workPlan(int[] low, int[] high) {
        //int[] dp = new int[10500]; //?
        int[] dp = new int[10001]; //dp长度至少是n+1，最后一个下标是n，因为dp[0]没意义
        int n = low.length;
        int i;
        for (i = 0; i <= n; i++) { //?
            dp[i] = 0;
        }
        dp[1] = low[0];
        for (i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + low[i - 1], dp[i - 2] + high[i - 1]);
            // i = 4时因为dp[0]是没意义的，所以dp长度需要为5
            //这样一来dp[4]就会超过low和high的下标范围， 所以要用low[i - 1]
            //;也就是dp[3]表示第三周后最大收益和，lowp[2]表示第三周
        }
        return dp[n];
    }
}