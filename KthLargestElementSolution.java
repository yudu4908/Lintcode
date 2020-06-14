public class KthLargestElementSolution {
    //有点难，理解不透彻
    //方法一：从大大小排序找第k个， O（nlogn）的复杂度+o（K）的扫描
    //方法二： 快排中的partition。
        /**
         * 数组中去一个基准值pivot
         * 以pivot为基准，大的放左边，小的放右边（partition）
         * 两种情况：如果pivot排在的位置p > k, 则在pivot的左边找第K大的数，否则在其右边找
         * 根据你pivot分的好不好，平均复杂度是O（n）， 最差的是O（n2）
         * partition划分玩之后j一定要在i的左边
         */
    public int KthLargestElement(int n, int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, n);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[left];
        int i = left, j = right;
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++; //从左往右找到第一个小于等于pivot的数
            }
            while (i <= j && nums[j] < pivot) {
                j--; //从右到左找到第一个大于等于pivot的数
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;// 交换位置是为了让大于等于pivot的数都放到左边，小于等于的都放到右边去。等于是这里做了排序
                i++;
                j--;
            }
        }

        if (left + k - 1 <= j) { //这里left好像永远是0啊兄弟？？, 不对，left是会变的，每次递归都会让left可能变为i
            return quickSelect(nums, left, j, k);
        }
        if (left + k - 1 >= i) {
            return quickSelect(nums, i, right, k - (i - left));
        }
        return nums[j + 1];
    }
    
}