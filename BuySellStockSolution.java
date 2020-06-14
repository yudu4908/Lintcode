public class BuySellStockSolution {
    //根据买卖股票的最佳时机算出能获得的最大利润， Medium难度   0521
    //用动态规划
    // 思路一是转换为固定第i天卖出能获得的最大profit： dp[i]: max profit when we sell the stock at ith day ==> dp[k], 1 <= k <= n
    //dp[i] = prices[i] - minimum price before i
    //思路二：通过前面的子状态获得， 如果在第i-1天卖的最大profit是dp[i - 1],那么
    //  第i天卖出的价格只要加上差价即可， 那么还有种情况是恰好在第i - 1天买入则是“或”运算后面的算法：
    //  dp[i] = dp[i - 1] + a[i] -a[i - 1] || a[i] - a[i - 1]
    public int maxProfit(int[] a) {
        if (a.length == 0) {
            return 0;
        } 
        int[] dp = new int[a.length];
        int res = 0;
        for (int i = 1; i < a.length; i++) {//i必须从1开始， 不然java中数组下标为负会抛出异常
            dp[i] = Math.max(dp[i - 1] + a[i] - a[i - 1], a[i] - a[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}