package NetEase;

import java.util.Arrays;
//！！！！！！！！！！！！！！！！！！！！！！！！！！！！！未掌握，待看！！！！！！！！！
/**
 * 有一个长度为n的序列a，在序列上进行q次操作。 每次操作有一个查询的数字bi，将序列中所有大于等于bi的数字减一。
 *  返回每次操作后有多少数字被减一。
 * 
 */

public class SequenceMaintenance {
   //1≤n,q≤10000,1≤ai,x≤n  空间复杂度最好是n logn， 不然n * q勉强是10^8，只是一个勉强过的答案
   /**
输入
4
3
[1,2,3,4]
[4,3,1]
输出
[1,2,4]
输入
3
2
[1,2,3]
[3,3]
输出
[1,0]
    */
    /**
     首先将数组排序,因为修改的限制,所以数组只会是一个不减的数组.
那么我们就可以二分确定第一个>=b[i]的位置pos
*如果不存在这个位置,就说明没有一个元素符合条件.
*否则输出n-l+1.然后更新线段树即可.
<<      :     左移运算符，num << 1,相当于num乘以2

>>      :     右移运算符，num >> 1,相当于num除以2

>>>    :     无符号右移，忽略符号位，空位都以0补齐
 1010      十进制：10     原始数         number
10100      十进制：20     左移一位       number = number << 1;
 1010      十进制：10     右移一位       number = number >> 1;
     */
    /**
        /**
     * @param n: length of sequence
     * @param q: Operating frequency
     * @param a: the sequence
     * @param b: the standard of each operation
     * @return: How many numbers are subtracted by one after each operation
     */
    public static void build(int[] sum, int[] a, int l, int r, int rt) {
        if (l == r) {
            sum[rt] = a[l - 1];
            return;
        }
        int m = (l + r) >> 1; //右移一位相当于除以2，等于是二分
        build(sum, a, l, m, rt << 1);
        build(sum, a, m + 1, r, rt << 1 | 1); // '|'表示按位或， '^'表示异或
    }

    void pushDown(int[] sum, int[] add, int rt, int ln, int rn) {
        if (add[rt] != 0) {
            add[rt << 1] += add[rt];
            sum[rt << 1] += add[rt] * ln;
            sum[rt << 1 | 1] += add[rt] * rn;
            add[rt] = 0;
        }
    }

    void update(int[] sum, int[] add, int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            sum[rt] += C * (r - l + 1);
            add[rt] += C;
            return; 
        }
        int m = (l + r) >> 1;
        pushDown(sum, add, rt, m - l + 1, r - m);
        if (L <= m) {
            update(sum, add, L, R, C, l, m, rt << 1);
        }
        if (R > m) {
            update(sum, add, L, R, C, m + 1, r, rt << 1 | 1);
        }
    }

    int query(int[] sum, int[] add, int L, int R, int l, int r, int rt) {//求arr中L到R区间的数字和，这里sum相当于tree数组，add相当于arr原始数组
        if (L <= l && r <= R) { //如果所求的L-R比如2-5区间正好包含了某一段l-r比如3-5之间的话，就不用往下递归了，直接返回3-5区间对应的tree node的值即可
            return sum[rt]; //相当于return tree[node]
        }
        int m = (l + r) >> 1;
        pushDown(sum, add, rt, m - l + 1, r - m);
        int ans = 0;
        if (L <= m) {
            ans += query(sum, add, L, R, l, m, rt << 1);          
        }
        if (R > m) {
            ans += query(sum, add, L, R, m + 1, r, rt << 1 | 1);
        }
        return ans;
    }

    //下面是核心逻辑
    public int[] sequenceMaintenance(int n, int q, int[] a, int[] b) {
        Arrays.sort(a); //先把真个数组从小到大排序
        int[] sum = new int[n << 2]; //序列长度n * 4 比如4 * 4 = 16
        int[] add = new int[n << 2];
        int[] ans = new int[q]; //每次操作返回a中不小于标准b[i]的元素个数
        int cnt = 0;
        build(sum, a, 1, n, 1); //前面的移位运算不会改变n本身， 比如n还是4
        for (int i = 0; i < q; i++) {
            int l = 1, r = n, pos = -1;
            while (l <= r) {
                int m =  (l + r) >> 1; //求左右指针的中间位置， 除以2进行二分
                if (query(sum, add, m, m, l, n, 1) >= b[i]) {
                    r = m - 1; //第一个不小于b[i]的点在m的左边
                    pos = m;
                } else {
                    l = m + 1; //第一个不小于b[i]的点在m的右边
                }
            }
            if (pos == -1) {
                ans[cnt++] = 0;
            } else {
                ans[cnt++] = n - pos + 1;
                update(sum, add, pos, n, -1, 1, n, 1);
            }
        }
        return ans;      
    }

}