package Tencent;
/**
Given an array of A, a non-negative integer target. 
Each decimal in A needs to be operated by ceil(向上取整) or floor（向下取整）, and finally an integer array is obtained, 
requiring the sum of all integers that are in the array to be equal to target, 
and requiring adjustments sum of the decimal array is minimum.
Such as ceil(1.2),adjustment is 0.8,floor(1.2),adjustment is 0.2. return the integer array.
 */
//！！！！！！！！！！！！！！！！！背包题还是不好做呀。。。。没太理解
public class FloatCombinationSum {
    /**
     1.1<=A.length<=300

     2.0<=target<=15000

     3.0.0<=A[i]<=100.0

     4.Datas guarantees the existence of answers.
     */
    /**
    Input：A=[1.2,1.3,2.3,4.2],target=9
    Output：[1,1,3,4]
    Explanation：1.2->1,1.3->1,2.3->3,4.2->4.
    考点：
        dp
        题解:类比于分组背包问题，每个数字可以看成是包含两个互斥的物品放入即可。
        对于每个小数，看作是向上取整和向下取整的两个物品，必须选择一个，考虑分组背包。
        在第二层循环即背包容量的循环中同时考虑两个物品，则可保证选择具有互斥性。
     */
    public int[] getArray(double[] A, int target) {
        double[] dp = new double[15500];
        int[][] path = new int[15500][500];
        int n = A.length;
        int[] res = new int[n];
        for (int i = 1; i <= target; i++) { //比如target = 3； dp = [0, 100000, 100000, 100000]
            dp[i] = 100000;
        }
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = target; ; j--) {
                int x = (int)Math.floor(A[i]);
                int y = (int)Math.ceil(A[i]);
                if (j < x && j < y) { //放不下第i件物品我就不放，换下一件试试
                    break;
                }
                if (j >= x && j >= y) { //两个物品均可放入，必选其一
                    if (dp[j - x] + A[i] - x < dp[j - y] + y - A[i]) {// A[i] - x和 y - A[i]是两种情况分别的adjustment，取小的
                        dp[j] = dp[j - x] + A[i] - x;
                        path[j][i] = 1;
                    } else {
                        dp[j] = dp[j - y] + y - A[i];
                        path[j][i] = 2;
                    }
                }
                else if (j >= x) { //只能放入向下取整整数，直接放入
                    dp[j] = dp[j - x] + A[i] - x;
                    path[j][i] = 1;
                }
                else if (j >= y) { //Math.ceil(-0.65)=-0.0 //只能放入向上取整整数，直接放入 ？？？？哪里存在这样的数？
                    dp[j] = dp[j - y] + y - A[i];
                    path[j][i] = 2;
                }
            }
        }
        if (dp[target] >= 10000) {
            return res; //空数组
        } else {
            int sum = target;
            for (int i = n - 1; i >= 0; i--) { //答案的记录此处通过对背包状态回溯完成还原(同样可以参考背包路径问题)。
                if (path[sum][i] == 1) {
                    res[i] = (int)Math.floor(A[i]);
                    sum -= (int)Math.floor(A[i]);
                }
                else if (path[sum][i] == 2) {
                    res[i] = (int)Math.ceil(A[i]);
                    sum -= (int)Math.ceil(A[i]);
                }
            }
            return res;
        }
    }

}