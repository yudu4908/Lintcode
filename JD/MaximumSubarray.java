package JD;
//Given an array of integers, find a contiguous subarray which has the largest sum.
public class MaximumSubarray {
   /**
Input: [−2,2,−3,4,−1,2,1,−5,3]
Output: 6
Explanation: the contiguous subarray [4,−1,2,1] has the largest sum = 6. 
   */ 
  /**
More practice: 
If you have figured out the O(n) solution, 
try coding another solution using the divide and conquer approach, which is more subtle.
   */
  //version1: Greedy
  public int maxSubArray(int[] A) {
      if (A == null || A.length == 0) {
          return 0;
      }
      //max记录全局最大值，sum记录区间和，如果当前sum > 0, 那么可以继续和后面的数求和，否则就从0开始
      int max = Integer.MIN_VALUE, sum = 0;
      for (int i = 0; i < A.length; i++) {
          sum += A[i];
          max = Math.max(max, sum);
          sum = Math.max(sum, 0);
      }
      return max;
  }

  //version 2: prefix sum
  public int maxSubArray2(int[] A) {
      if (A == null || A.length == 0) {
          return 0;
      }
      //max记录全局最大值，sum记录前i个数的和， minSum记录前i个数中0-k的最小值
      int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
      for (int i = 0; i < A.length; i++) {
        sum += A[i];
        max = Math.max(max, sum - minSum);//因为我们求的是连续子序列，它必然从某个位置起始，前面的不用的数字要被从sum中减去
        minSum = Math.min(minSum, sum);
      }
      return max;
  }

  // version 3:  空间复杂度降低为O（1），不过不太好理解，需要画一画。local记录的数字大小是会丢失前面的最大值的，所以需要global全局记录最大值
  public int maxSubArray(int[] A) {
      if (A == null || A.length == 0) {
          return 0;
      }
      int n = A.length;
      int[] global = new int[2];
      int[] local = new int[2];
      global[0] = A[0];
      local[0] = A[0];
      for (int i = 1; i < n; i++) {
          local[i % 2] = Math.max(A[i], local[(i - 1) % 2] + A[i]);
          global[i % 2] = Math.max(local[i % 2], global[(i - 1) % 2]);
      }
      return global[(n - 1) % 2];
  }
}