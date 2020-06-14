package NetEase;

/**
小易有n根柱子，第i根柱子的高度为hi, 一开始小易站在第一根柱子上。小易可以从i根柱子跳到第j根柱子，当且仅当 hj ≤ hi
​​  并且1 ≤ j−i ≤ k, 其中k为指定的一个数字。另外小易拥有一次放超能力的机会,这个超能力能让小易从柱子i跳到任意满足1 ≤ j−i ≤ k
的柱子而无视柱子高度的限制。
现在小易想知通,小易是否能到达第n根柱子。
 */
public class JumpPillar {
    /**
    2≤n,k≤1000
    1≤h[i]≤10^9
     */
    /**
    输入:h=[3,2,7,4],k=2
    输出:true
    解释:你可以使用超能力，从第一根跳到第三根。然后按照规则，从第三根跳到第四根
    输入:h=[3,4,5,6],k=2
    输出:false
     */
    /**
    利用dp[i][2]来保存状态。
dp[i][0]代表未使用超能力能到达的位置，dp[i][1]代表已使用过一次。
当满足跳跃的两个条件的时候，直接转移即可。
dp[j][0] = max(dp[j][0], dp[i][0])
dp[j][1]=max(dp[j][1],dp[i][0])
或者使用超能力，
dp[j][1] = max(dp[j][1], dp[i][0])
    */
    /**
        /**
     * @param h: the height of n pillar
     * @param k: the limit
     * @return: Xiao Yi can or can't reach the n-th pillar
     */
    public boolean jumpPillar(int[] h, int k) {
        int[][] dp = new int[10500][2]; //为什么是10500， int[1000][2]即可
        int n = h.length; //n根柱子
        for (int i = 0; i < n + 1; i++) { // i < n即可
            dp[i][0] = 0;
            dp[i][1] = 0;
        }
        dp[0][0] = 1; // 不使用超能力能到达第0根柱子肯定是true 
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= Math.min(n - 1, i + k); j++) {  //不能超过柱子最后一个坐标n - 1， 也不能超过i + k
                if (h[i] >= h[j]) {
                    dp[j][0] = Math.max(dp[j][0], dp[i][0]);
                    dp[j][1] = Math.max(dp[j][1], dp[i][0]); //虽然不需要用能力，但是我用了肯定也是能跳到的，要把状态设对
                }
                dp[j][1] = Math.max(dp[i][0], dp[j][1]);
            }
        }
        if (dp[n - 1][0] +  dp[n - 1][1] != 0) {
            return true;
        }
        return false;
    }
}