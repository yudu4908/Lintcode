public class DropEggSolution {
    /**
     * 可以用归纳法：
 n=1--1次（坏了是1，不坏就return -1）； n=2--2（最坏的情况要试两次，一层没碎，二层你也不知道碎没碎）; n=3--2（先在2楼试一次，碎了试1，没碎试3）; n=4--3;（先在2楼试，最坏结果是耐摔度是4楼，那就需要3次） ； n=5--3(先在3楼试)； n= 6--4
1 + 2  + 3 + 4 + …. + x >= n那就是x次
如果有n层楼m个鸡蛋就得用动态规划

     */
    public int dropEggs(int n) {
        long ans = 0; //用long是因为这是（1+n）*n/2 的计算题，n是int的极限的话n的平方是会溢出的
        //for (int i = 1; i < n; i++) {
            //https://stackoverflow.com/questions/13366290/why-can-the-condition-of-a-for-loop-be-left-empty
        for (int i = 1; ; i++) {//只能这么写，要不然会强制要求你给整个函数return一个数的； 这里condition为空代表无限，所以也不要求一定return什么
            ans += (long) i;
            if (ans >= (long)n) {
                return i;
            }
        }
        /**
         * for循环也可以写成
         *  public int dropEggs(int n) {
        // Write your code here
                long ans = 0;
                for (int i = 1; i <= n; ++i) { // means i has no limit
                    ans += (long)i;
                    if (ans >= (long)n) {
                        return i;
                    }
                }
                return -1;
            }
         */
    }
}