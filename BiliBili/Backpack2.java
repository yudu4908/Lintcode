package BiliBili;
/**
 * 
Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
Output: 9
Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9 

challenge: O(nm) memory is acceptable, can you do it in O(m) memory?
 */
public class Backpack2 {
    /**
    经典的01背包问题, 资源分配型动态规划.

设定 f[i][j] 表示前 i 个物品装入大小为 j 的背包里, 可以获取的最大价值总和.
 决策就是第i个物品装不装入背包, 所以状态转移方程就是 f[i][j] = max(f[i - 1][j], f[i - 1][j - A[i]] + V[i])

可以使用滚动数组优化空间至 O(m).
     */
    /**
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    //画图理解下，最好还是背下来这个思路，不然看图理解了还是很难写
    public int backPack2(int m, int[] A, int[] V) {
        int[][] dp = new int[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A[i - 1] > j) {//第i件货物对应重量A[i - 1],货物重量大于背包承重时dp[1][1] = dp[0][1] = 0
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }

            }
        }
        return dp[A.length][m];
    }

}