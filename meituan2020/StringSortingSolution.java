package meituan2020;
/**
 * Given some string splited by ,, please sort them in lexicographical order.
 * String only contains lower cases.
   The number of string is <= 1000 and total length is <= 10^5
   Input: "bb,aa,lintcode,c"
   Output: "aa,bb,c,lintcode"
   Explanation: "aa" < "bb" < "c" < "lintcode" in lexicographical order.
 */
public class StringSortingSolution {
    /**
     * 算法:排序

算法过程:
将字符串以,隔开
排序
将字符串以,连接，并返回答案
时空复杂度
定义nn为字符串数量

时间复杂度：O(nlogn)，如果使用快速排序、归并排序，语言自带排序等的话
时间复杂度：O(n^2 )，如果使用冒泡排序、选择排序、插入排序等的话
时间复杂度：O(n!)，如果使用猴子排序等的话
空间复杂度：O(total length)
     */
    //下面用快排解决
    /**
     * @param s: string
     * @return: sort string in lexicographical order
     */
    public String sorting(String s) {
        String[] strings = s.split(",");
        qsort(0, strings.length - 1, strings);
        String ans = "";
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                ans += ","; //第0个数不用加逗号
            }
            ans += strings[i];
        }
        return ans;
    }

    private void qsort(int n, int m, String[] a) {
        int i = n, j = m;
        String k = a[(i + j) / 2];
        while (i <= j) {
            while (a[i].compareTo(k) < 0) {
                i++;//从左往右找到第一个比中间数大的字符串
            }
            while (a[j].compareTo(k) > 0) {
                j--; //从右往左找出第一个比中间数小的字符串然后和左边那个大于中位数的做交换
            }
            if (i <= j) {
                String tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++; j--;
            }
        }
        if (i < m) {  //最后这俩排序是为啥？？？难点
            /**
             * 这里自己拿纸画一下就知道了， 比如“b, a, l, c”执行完上面的while循环后只实现了中间数左边小于他，右边大于他，但是右边局部还没有有序，也就是快排的先整体后局部的特性
             */
            qsort(i, m, a);
        }
        if (n < j) {
            qsort(n, j, a);
        }
    }
}