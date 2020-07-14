package Knapsack;

public class PartitionEqualSubsetSum {
    //use 0-1 package
    /**
        等价与背包问题，能否背到总价值的一半。
        01背包即可。
     */
    /**
     * 
     这题如果不懂背包可以想到枚举法，0，1放进去，2放进去爆了，3放进去可以。。。
     长度为n的数组，子集个数是2的n次方（每个数字可以取或不取），时间复杂度太差了
     01背包就是每个物品只能用1遍，并且有一个限制（重量和为sum/2）
     dp[n][m]考虑前n个物品后，能否刚好装满m的空间
     最后一步： dp[n][m] = dp[n - 1][m] | dp[n - 1][m -A[n]]; //前n - 1个物品中的子集已经可以刚好装满m的空间或者前n - 1个物品
     的子集刚好装满m - A[n]的空间
    转移方程： dp[i][j] = dp[i - 1][j] | dp[i - 1][j - A[i]]
    初始条件：dp[i][0] = true, else false //不管有前几个物品，都一定能装满0的空间

     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[i] = false;
        }
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}