
/**
 * O(n​2) 解法：

Dp[i] 表示以第i个数字为结尾的最长上升子序列的长度。
对于每个数字，枚举前面所有小于自己的数字 j，Dp[i] = max{Dp[j]} + 1. 如果没有比自己小的，Dp[i] = 1;

O(nlogn)O(nlogn)解法：

使用一个辅助空间B数组。
B[i]存储Dp值为i的最小的数字。（有多个位置，以这些位置为结尾的LIS长度都为i， 则这些数字中最小的一个存在B[i]中）
则B数组严格递增。且下标表示LIS长度，也是严格递增，可以在B数组中进行二分查找。

对于每个位置i，我们要找，所有小于A[i], 且Dp值最大的那个。这个操作在B数组中二分查找。
 */

public class LongestIncreasingSubseqSolution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    //method 1: O(n2)
     public int longestIncreasingSubsequence(int[] nums) {
         int[] f = new int[nums.length];
         int max = 0;
         for (int i = 0; i < nums.length; i++) {
             f[i] = 1;
             for (int j = 0; j < i; j++) {
                 if (nums[j] < nums[i]) {
                     f[i] = f[i] > f[j] + 1 ? f[i] : f[j] + 1;
                 }
             }
             if (f[i] > max) {
                 max = f[i];
             }
         }
         return max;
     }

    //method 2: O(nlogn)

}