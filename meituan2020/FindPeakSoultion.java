package meituan2020;
/**
 * There is an integer array which has the following features:

   The numbers in adjacent positions are different.
   A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
   We define a position P is a peak if:
   A[P] > A[P-1] && A[P] > A[P+1]
   It's guaranteed the array has at least one peak.
   The array may contain multiple peeks, find any of them.
   The array has at least 3 numbers in it.
 */
/**
 *  Example 1:
	Input:  [1, 2, 1, 3, 4, 5, 7, 6]
	Output:  1 or 6
	
	Explanation:
	return the index of peek.


    Example 2:
	Input: [1,2,3,4,1]
	Output:  3
 */

public class FindPeakSoultion {
    /**
     * 这个题 LintCode 和 LeetCode 的 find peak element 是有区别的。
    数据上，LintCode 保证数据第一个数比第二个数小，倒数第一个数比到倒数第二个数小。
    因此 start, end 的范围要取 1, A.length - 2

    二分法。
    每次取中间元素，如果大于左右，则这就是peek。
    否则取大的一边，两个都大，可以随便取一边。最终会找到peek。

    正确性证明：

    A[0] < A[1] && A[n-2] > A[n-1] 所以一定存在一个peek元素。
    二分时，选择大的一边, 则留下的部分仍然满足1 的条件，即最两边的元素都小于相邻的元素。所以仍然必然存在peek。
    二分至区间足够小，长度为3, 则中间元素就是peek。
     */
    /** 
    * @param A: An integers array.
    * @return: return any of peek positions.
    */
    public int findPeak(int[] A) {
        int start = 1, end = A.length - 2; // 1.答案在之间，2.不会出界
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid - 1]) {
                end = mid;
            } else if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid; //这里是不是直接return mid；就可以？现在感觉是要找到第一个peek而不是任意一个了
                //return mid;   //经过测试这种写法也没问题
            }
        }
        if (A[start] < A[end]) {
            return end;
        } else {
            return start;
        }
    }
}