package meituan2020;

import java.util.Stack;

/**
 * 一位店主需要完成一项销售任务，他将要出售的物品排成一排。 从左侧开始，店主以其全价减去位于该物品右侧的第一个价格较低或价格相同的商品的价格。
 * 如果右侧没有价格低于或等于当前商品价格的商品，则以全价出售当前商品。 你需要返回每一个物品实际售出价格。 数组 Prices 的长度范围是: [1,
 * 100000] Prices[i] 的整数范围是: [1, 1000000] 示例 1: 输入: Prices = [2, 3, 1, 2, 4, 2]
 * 输出: [1, 2, 1, 0, 2, 2] 解释：第0个和第1个物品右边第一个更低的价格都是1，所以实际售价需要在全价上减去1，
 * 第3个物品右边第一个更低的价格是2，所以实际售价要在全价上面减去2。 示例 2: 输入: Prices = [1, 2, 3, 4, 5] 输出: [1,
 * 2, 3, 4, 5] 解释: 每一个物品都保持原价，他们的右边都没有等于或者更低价格的物品
 */
public class FinalDiscountedPriceSolution {
    //单调非递减栈来存储之前的值，当出现比栈顶所存值更小的值则可以更新之前的价格。stack是后进先出，queue是先进先出
        /**
     * @param prices: a list of integer
     * @return: return the actual prices
     */
    public int[] finalDiscountedPrice(int[] prices) {
        int[] res = new int[prices.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            res[i] = prices[i]; //应该是不可以直接让res = prices这样去给列表赋值的，只能遍历
        } 
        for (int i = 0; i < prices.length; i++) {
            while (! s.isEmpty() && prices[s.peek()] >= prices[i]) { //stack.peek()查看栈顶对象而不移除它
                int index = s.pop(); //i = 1时栈顶元素是0
                //当stack中有两个下标比如1和2的时候，假如下标2对应的价格比下标3高那么把下标2弹出后还要判断下标1是否比下标3的价格高，如果一直到for循环结束都找不到那么下标1就保持原价出售
                res[index] = prices[index] - prices[i];
            }
            s.push(i);
        }
        return res;
    }
}