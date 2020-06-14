package Alibaba;

import java.util.Collections;
import java.util.List;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!难理解，多做做
//https://www.jiuzhang.com/solution/plant-flowers/#tag-highlight-lang-java
/**
小明有n*m个花盆，摆列成矩阵状，有c种颜色的花的种子，每一种颜色的花的种子有c[i]个，
所有种子的数量等于花盆的数量，每个花盆只能种一朵花，小明想让每个花盆的相邻上下左右的花盆的花的颜色不能与这个花盆相同，
请问能按照他的想法种花吗？
1 <= n, m <= 5;
c(i)之和恰好等于n * m的花盆数， 输出“true” or “false”
 */
public class PlantFlowerSolution {
    /**
输入:
1
5
4 1
输出:false
不能按照小明的想法种花
     */
/**
 * 根据题目，种子数量和花盆数目一致，判断每个花盆相邻的四个盆子颜色不一致的方案是否存在，
 * 可以根据抽屉原理（把多于n+1个的物体放到n个抽屉里，则至少有一个抽屉里的东西不少于两件），
 * 我们将花盆一个放一个不放，这样一种颜色的花最多能放（mn+1）/2个才能保证每个花盆周围的颜色不同，
 * 如果最多颜色的种子数量大于（mn+1）/2一定无解（实际上这应该是充要条件了）

为了保证方案是否正确我们再递归搜索判断一边方案是否存在

从左上角向右下角走开始递归
用一个全局变量flag判断是否存在方案可行
用一个矩阵记录mp记录已经放下的种子的颜色是怎么样的
用一个数组cnt记录下标为i的颜色的种子已经用了多少
然后通过枚举种子的颜色，判断位置mpi的上面和左边是否颜色和当前枚举的颜色不同，以及当前颜色的种子是否还有剩余
当符合条件的时候，将这个种子放入这个花盆，然后判断边界的前提下向右边和下面继续递归
当边界已经达到mp右下角时（答案写的左下角是错的，我们自己画个n=3, m = 3, seeds = [4, 5]的场景就可以知道），
假设n=3, m = 3, seeds = [4, 5]，题目的解法是先创建一个4x4的矩阵， 第一行第一列下标为0的充个数最后只填充了-1
从坐标【1，1】的格子开始遍历，先往右走到头（y == m），然后x+1从下一行的【x+1， 1】的格子再开始往右走
有一种一次走完一行的感觉。但是对于整个for (int i = 0; i < c; i++)来说，我们实际上是一次走完了一种颜色从【1，1】开始填充的整个方案，
这也是深度优先搜索的含义。 比如用n=3, m = 3, seeds = [4, 5]的例子我们在i = 0的场景下最后发现0号颜色少了一个，永远实现不了结果。
然后我们就会把每个递归调用的dfs方法后面的语句cnt[i]--; mp[x][y] = -1;执行完把这一边的矩阵mp还原初始状态，然后开始执行下一个for
循环， 也就是把另一种颜色填充在【1，1】看看能否最终走到右下角，也就是填充完n x m的格子
说明种子能按照要求放入花盆，此时flag标记存在方案，返回true
 * 
 * 
 */
 /**
    @param n: a number n
    @param m: a number m
    @param seeds: Number of seeds per color
    @return: give n,m,c and c[i] ,return true or false
   */   
    private int[] cnt = null;
    private int[][] mp = null;
    private boolean flag = false;
    public boolean Plantflowers(int n, int m,List<Integer> seeds) {
        int c = seeds.size();
    	// 对种子数量进行排序
        Collections.sort(seeds);
        // 根据抽屉原理判断方案是否可行
        if (seeds.get(c - 1) > (n * m + 1) / 2) {
            return false;
        }
        // 初始标记种子已经被使用的数量
        cnt = new int[c];
        // 初始化花盆矩阵
        mp = new int[n+1][m+1];
        for (int i = 0; i < n ; i++) { //这里用n+1和m+1更好理解，也能通过代码。只不过因为左下角的格子不插入数据也不影响颜色判断罢了
            for (int j = 0; j < m; j++) {
                mp[i][j] = -1;
            }
        }
        // 标记判断是否存在方案
        flag = false;
        // 从矩阵左上角开始dfs
        dfs(1,1,n,m,c,seeds);
        return flag;
    }
    private void dfs(int x,int y,int n,int m,int c,List<Integer> seeds){
        // 如果当前位置已经到了矩阵右下角，说明花盆可以符合要求种下所有种子
        if (x == n  && y == m + 1) { 
            flag = true;
            return ;
        }
        // 尝试将剩余的种子放进花盆
        for (int i = 0; i < c; i++) {
            if (flag) {
                break;
            }
            // 判断条件，当前选择的种子的颜色不与上面或者左边的种子的颜色相同，且种子数量未用尽
            if (i != mp[x - 1][y] && i != mp[x][y - 1] && cnt[i] < seeds.get(i)) {
                // 放下当前颜色的种子
                mp[x][y] = i;
                // 这种种子数量使用量+1
                cnt[i]++;
                // 判断边界，然后继续往右边和下面递归
                if (x != n && y == m) { // 向下走
                    dfs(x + 1, 1,n,m,c,seeds);
                }
                else { // 向右边走  ；走到右下角终结时x == n， y == m也走到了这里，所以y最终会变成m + 1也就是前面的判断flag为true的条件
                    dfs(x, y + 1,n,m,c,seeds);
                }
                cnt[i]--;
                mp[x][y] = -1;
            }
        }
    }
}