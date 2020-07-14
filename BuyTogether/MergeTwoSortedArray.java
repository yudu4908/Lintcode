package BuyTogether;
/**
Input:  A=[1,2,3,4], B=[2,4,5,6]
Output: [1,2,2,3,4,4,5,6]	
Explanation: return array merged.
 */

 /**
使用两个指针分别对数组从小到大遍历，每次取二者中较小的放在新数组中。
直到某个指针先到结尾，另一个数组中剩余的数字直接放在新数组后面。

时间复杂度O(n), 因为小的数组走的快，走完之后就不比较了，另一个数组剩下的数一定比这个小的数组最后一个也是最大的数还要大，
直接把另一个数组剩下的数添加在result的尾巴即可
  */
public class MergeTwoSortedArray {
    public int[] mergeSortedArray(int[] A, int[] B) {
        if (A == null || B == null) {
            return null;
        }
        int[] res = new int[A.length + B.length];
        int idx_a = 0, idx_b = 0, idx_res = 0;
        while (idx_a < A.length && idx_b < B.length) {
            if(A[idx_a] < B[idx_b]) {
                res[idx_res++] = A[idx_a++];
            } else {
                res[idx_res++] = B[idx_b++];
            }
        }
        while (idx_a < A.length) {
            res[idx_res++] = A[idx_a];
        }
        while (idx_b < B.length) {
            res[idx_res++] = B[idx_b];
        }
        return res;
    }
}