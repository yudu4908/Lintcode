public class ArrayScoreSolution {
    /*
    You are given an array numsnums, and three postive integers k,u,lk,u,l.
    For every subarray whose length is kk of nums, if its sum is less than uu, you'll get 11 score, if its sum is greater than ll, you'll lose 11 score.
    Please calculate the score you'll get in the end.
    时间复杂度
    遍历一遍数组，复杂度为 O(N)O(N)。
    空间复杂度
    只需额外 O(1)O(1) 的空间记录分数和子段和。
    */
    /**
     * @param nums: the array to be scored.
     * @param k: the requirement of subarray length.
     * @param u: if the sum is less than u, get 1 score.
     * @param l: if the sum is greater than l, lose 1 score.
     * @return: return the sum of scores for every subarray whose length is k.
     */
    public int arrayScore(List<Integer> nums, int k, long u, long l) {
        // 子段的和，这里用long是因为和最大为10^5 * 10^5，用int会溢出
        long sumOfSubarray = 0;
        int score = 0;
        int n = nums.size();
        //calculate the sum of the first k numbers
        for (int i = 0; i < k; i++) {
            sumOfSubarray += nums.get(i);
        }
        if (sumOfSubarray < u) {
            score++;
        }
        if (sumOfSubarray > l) {
            score--;
        }
        // 剩下的，窗口每次向右移动，加入第 i 个数，弹出第 i - k 个.这题挺有意思，就是保证窗口中始终有k个元素不断变化sum做判断即可
        for (int i = k; i < n; i++) {
            sumOfSubarray += nums.get(i);
            sumOfSubarray -= nums.get(i - k);
            if(sumOfSubarray < u) {
                score++;
            }
            if (sumOfSubarray > l) {
                score--;
            }
        }
        return score;

    }


}