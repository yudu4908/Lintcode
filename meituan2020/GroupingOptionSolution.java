package meituan2020;
/**
 * 现在有nn个人在排成一排。他们必须从左到右分成连续的mm组。如果每个组的人数都必须大于等于其左边的组的人数，
 * (each group must be at least as large as the group to its left)
 * 请问一共有多少种不同的分组方式。对于两个分组而言，
 * 当且仅当按递增序排列以后是不同的，他才是不同的分组。例如，[1, 1, 1, 3]和[1, 1, 1, 2]不同，但和[1, 3, 1, 1]相同。
 * 1 <= n, m <= 200
 */
/**
 * 输入:
8
4
输出: 5
说明: [1, 1, 1, 5], [1, 1, 2, 4], [1, 1, 3, 3], [1, 2, 2, 3], [2, 2, 2, 2]
 */
public class GroupingOptionSolution {
    /**
     * 动态规划，dp[i][j]表示i个人分成j组的方案数，则dp如下：
     * 边界条件：dp[i][i] = 1, dp[i][j] = 0(i < j)
        转移方程：dp[i][j] = ∑(i到j)dp[i - j][k]// ydu：我觉得答案给的转移方程不对，我自己画了一下
        //dp[i][j] = ∑(k=1到j)dp[i - j][k]才是对的，比如dp[8][4] = dp[4][1](值为1) + dp[4][2](转移为dp[2][1]+dp[2][2]=2) + dp[4][3](转移为dp[1][1]+dp[1][2]+dp[1][3] = 1) + dp[4][4](=1)
        答案：dp[n][m]
     */
        /**
     * @param n: the number of people
     * @param m: the number of groups
     * @return: the number of grouping options
     */
    public long groupingOptions(int n, int m) {
        if (m > n) {
            return 0;
        }
        long[][] dp = new long[n + 1][n + 1]; //？为啥用long； 长度为n + 1表示最后一个下标是n，我们时要用到dp[n][n] = 1的
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                for (int k = 1; k <= j; k++) {//看起来最坏可能有n的三次方的for循环，但是每个循环里的dp还有自己的循环的，所以可能会很大
                    //test case dp[200][15] = 20992222004超过整形范围2的31次方-1了（21亿多次）
                    dp[i][j] += dp[i - j][k];
                }
            }
        }
        return dp[n][m];
    }
}