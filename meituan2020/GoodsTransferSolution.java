package meituan2020;
/**
 * 有n个背包，每个背包分别装了a[i]的货物，
 * 每个背包的最大容量分别是b[i]，假设转移一件货物需要1秒的时间，那么最少要多少背包能够装下所有货物，以及最少需要转移多少时间？
 * 1<=n<=100
1<=ai<=1000
1<=bi<=1000
ai<=bi
 */
/**
 * 输入
4
[3, 3, 4, 3]
[4, 7, 6, 5]
输出 2 6 
说明:
我们可以把第一个背包中的货物全部挪到第二个背包中,
花费时间3秒,此时第二个背包剩余容量为1,然后把第四个保温箱中的货物转移一件到第二个背包中,
转移最后两件到第三个背包中.花费时间3秒,所以最少保温箱个数是2,最少花费时间为6秒。
 */
/**
 * 本题可以使用背包dp的方法求解
我们用dp[i]来表示当前容量为i时所需要的最少背包数。
状态转移方程为dp[j] = min(dp[j],dp[max(0, j-b[i])]+1)
最短的时间time是由货物的转移来决定的。所以我们用一个额外的数组weight[i]来表示货物的重量。
所以time = sum_good-max_weight
 */
public class GoodsTransferSolution {
     /**
     * @param n: the number of backpacks
     * @param a: the number of goods each backpack carries
     * @param b:  the maximum capacity of each backpack
     * @return: given n, ai and bi,return the minimum number of backpacks and the minimum time
     */
    public int[] goodsTransfer(int n, int[] a, int[] b) {
        final int INF = 0x3f3f3f3f; //表示无穷大
        int k = INF, max_weight = 0;
        int sum_good = 0, sum_cap = 0;


    }
}