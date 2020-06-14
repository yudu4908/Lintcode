package Kwai;

import java.util.List;
import java.util.Set;

/**
 有 nn 个人正在玩丢手绢。编号为0, 1 ... n-10,1...n−1。
一开始，每个人手上都拿着一块自己手绢。接下来的每一轮，第 ii 个人会把当前手里的手绢丢给一个固定的人to_i。
当有人重新拿到自己的手绢时，游戏就会结束。
请求出游戏会在第几轮结束。
1<=n<=10^3
​​ 
0 <= to_i <= n - 1
to_​i可能和 i 相等，即丢给自己。
保证游戏一定会结束。
 */
/**
样例输入：
to = [1, 2, 0, 4, 3, 0]
样例输出：
2
0,1,2 号都要经过三轮拿到自己的手绢，但 3,4 号只要两轮就能拿到自己的手绢，所以 2 轮后游戏结束。
这个游戏规则是每轮所有人都把自己手绢丢给别人； 三和四号是每轮都互相丢，所以两轮后收到自己的。五号根本没人给他丢，所以永远收不到自己的
 */
public class HandkerchiefThrowingSolution {
    /**
我们可以把N个人抽象成图的节点，
N个丢手绢的方向抽象成边，所以就形成了一个N个节点N条边的有向图，
这道题就成为了有向图上的最小环问题。我们可以用宽度优先搜索来解决。

但是这个图有一个特殊的性质，就是所有节点的出度为1。
也就是说在宽度优先搜索时，队列里只有1个节点。
我们直接模拟每一块手绢在游戏中的轨迹即可，当它的位置回到出发点的话，记录下来最少的轮数。
     */
    /**
     计算某个手绢 i 回到出发点的轮数：

令 position 等于 i，turnCount等于0。
position跳到下一个位置，turnCount加上1。
如果position等于i，即回到出发点的话，返回turnCount并统计。
如果position不等于i：
如果已经访问过当前位置，代表进入死循环，回不到起点。退出。
标记position已访问过，返回第2步。

设游戏人数为N。
时间复杂度
每个节点最多跳N步，时间复杂度为O(N^2)。
空间复杂度
需要O(N)的额外空间记录已访问过的节点，空间复杂度为O(N)
     */
    /**
     * @param to: The target of everyone will throw the handkerchief to.
     * @return: Return the number of turns the game will stop.
     */
    public int gameTurns(List<Integer> to) {
        int n = to.size();
        int minTurn = n; //题目说明了游戏一定能结束的前提下，我们可以推测出最坏情况是n轮结束，比如n=3， 0->1; 1->2; 2->0
        for (int i = 0; i < n; i++) {
            int position = i;
            int turnCount = 0;
            Set<Integer> visited = new HashSet<Integer>();
            position = to.get(position); //position跳到下一个要去的地方
            turnCount++;
            while (position != i) {
                position = to.get(position);
                turnCount++;
                // 如果到了已访问过的点，代表这个手绢进入死循环，到不了出发点
                if (visited.contains(position)) {
                    break; //跳出这个while循环
                }
                visited.add(position);
            }
            if (position == i) { //再次判断是因为当while循环被break时还要往下执行的
                minTurn = Math.min(minTurn, turnCount);
            }
        }
        return minTurn;
    }
}