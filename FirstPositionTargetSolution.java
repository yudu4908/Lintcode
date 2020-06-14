public class FirstPositionTargetSolution {
    //二分查找的模板，找第一次出现的位置，但注意可能出现多次，所以找到数字后不能直接结束，而要将end移动到mid处
    //直到最后缩小到只有一个或两个数字时，优先判断start再判断end
    //O(logn)
      /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if(nums.length == 0 || nums == null) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2; //这样不用担心end太大溢出, /表示取整数
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}