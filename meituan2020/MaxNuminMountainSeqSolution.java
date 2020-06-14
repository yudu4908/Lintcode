package meituan2020;

/**
 * Desc:
 * Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.
 * eg1.
 * Input: nums = [1, 2, 4, 8, 6, 3] 
   Output: 8
 * eg2.
 * Input: nums = [10, 9, 8, 7], 
   Output: 10
 */

public class MaxNuminMountainSeqSolution {
    //二分法，判断山脉趋势，按照取数递归左边或者右边即可。难度： Medium
     /**
     * @param nums a mountain sequence which increase firstly and then decrease
     * @return the mountain top index
     */
    public int mountainSequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2; //防止溢出
            if (nums[mid] > nums[mid + 1]) {
                end = mid; //我们是要找到先上升后下降的点即为峰值，现在mid已经下降说明左侧必有上升. 因为题目已经规定整个数组是先上升后下降了
            } else {
                start = mid;
            }
        }
        return Math.max(nums[start], nums[end]);
    }
}