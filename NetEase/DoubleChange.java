package NetEase;
/**
给定数字 A 和 B 以及系数 p 和 q 。
每次操作可以将 A 变成 A + p 或者将 p 变成 p * q。
返回最少变化次数使得 B <= A
 */
/**
1≤A≤1e9
1≤B≤1e​9 
1≤p≤1e​9   (e = 2.71828无限不循环)
​​2≤q≤10
 */
public class DoubleChange {
    /**
    示例 1:
输入:
A = 2
B = 10
p = 1
q = 2
输出: 4
解释：通过三次变换之后，p = 8, 然后 A + p = 10，所以最终返回4.
示例 2:
输入:
A = 2
B = 13
p = 8
q = 4
输出: 2
解释：通过一次变换之后，p = 32, 然后 A + p = 34，所以最终返回2.
     */
        /**
     * @param A: an Integer
     * @param B: an Integer
     * @param p: an Integer
     * @param q: an Integer
     * @return: Return the minimum time
     */
    public int doubleChange(int A, int B, int p, int q) {
        /**
        当A + p < B的时候：p = p * q 的变化次数是小于 A = A + p的变化次数。
        所以在A + p < B的时候只需要做 p = q * p 的操作就好了
        注意 p ,A可能会超出 int 的范围
         */
        int times = 0;
        Long res = new Long(A);
        Long pchange = new Long(p);

        while (res < B) {
            if ((res + pchange) >= B) {
                res += pchange;
            } else {
                pchange = pchange * q;
            }
            times++;
        }
        return times;
    }
}