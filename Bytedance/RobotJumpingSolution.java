package Bytedance;
/**
机器人正在玩一个古老的基于DOS的游戏。游戏中有N+1座建筑——从0到N编号，从左到右排列。
编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位。
起初， 机器人在编号为0的建筑处。
每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，且它现在的能量值是E, 下一步它将跳到第个k+1建筑。
它将会得到或者失去正比于与H(k+1)与E之差的能量。
如果 H(k+1) > E 那么机器人就失去 H(k+1) - E 的能量值，否则它将得到 E - H(k+1) 的能量值。
游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位。现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏？
 */
/**
1≤height.size()≤10^5
​​ 
1≤height[i]≤10^5
输入:
height:[3,4,3,2,4]
输出: 4
解释:
初始能量为4才能从0走到4
 */
//下面这种解法的memory消耗太多，实际上没有被AC， python相同思路的解法通过了，这个待问助教
public class RobotJumpingSolution {
//     逆递推
// 假设跳跃前能力为E,要跳的高度为H，那么跳跃后的能量就是2E-H，  (E + E - H = 2E - H)
// 那么跳跃后的能量加上高度就是跳跃前的两倍，然后从后往前逆推可以获得答案
    /**
     * @param height: the Building height
     * @return: The minimum unit of initial energy required to complete the game
     */
    public int LeastEnergy(List<Integer> height) {
        int E = 0;
        for (int i = height.size() - 1; i >= 0; i--) {
            E = (E + height.get(i) + 1) / 2; //不加1的话因为除法是向下取整的，以题目中的例子来看不加1最后得到的数字就是3是不行的
        }
        return E;
    }
}