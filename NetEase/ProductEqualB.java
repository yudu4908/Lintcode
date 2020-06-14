package NetEase;

import java.util.Arrays;

//未做， 待看！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

public class ProductEqualB {
        /**
     * @param B: the all Ai product equal to B
     * @param A: the positive int array
     * @return: return the minium cost 
     */
    public int getMinCost(int B, int[] A) {
        int n = A.length;
        //存B的因数的数组
        int []factor = new int[200];
        // B因子的数量
        int m = 0;
        //找出B的因子 并存入factor
        for (int i = 1; i <= B; i++) {
            if(B % i == 0) {
                factor[m++] = i;
            }
        }
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < n; i++) {
            //初始化本次循环中，被更新的tmp数组
            //用dp数组去更新tmp数组
            int[] tmp = new int[m];
            for (int j = 0; j < m; j++) {
                tmp[j] = Integer.MAX_VALUE;
            }
            for(int j = 0; j < m; j++) {
                //dp[j]为无穷，无法去更新
                if(dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                //枚举factor[j]的倍数，将前i个数的乘积由factor[j]变换成factor[k]
                for(int k = j; k < m; k++) {
                    //倍数关系约束
                    if (factor[k] % factor[j] != 0) {
                        continue;
                    }
                    //dp方程更新
                    tmp[k] = Math.min(tmp[k], dp[j] + Math.abs(factor[k] / factor[j] - A[i]));
                }
            }
            //本次循环的tmp数组去更新的下次的tmp数组
            dp = tmp;
        }
        return dp[m - 1];

    }
}
