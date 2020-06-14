package Bytedance;

import java.util.ArrayList;
import java.util.List;

/** A country's currency s
 * stem contains 4 kinds coins,   * their values are 1, 4, 
 * 6, 64, and a kind paper money, values 1024. ou now used a paper money
 * values 1024to buy a product values N, 0 < N <= 1024
 please calculate how many coins you'll received at least, for change.
 */
public class GiveChangeSolution { //找零钱问题
    /** 
     * Sample Input1:
        amount = 1014

        Sample Output1:
            4
        there will be 2 coins value 4, and 2 coins value 1.
    */
    /**
     * 我们可以算出要找的钱为 1024 - amount​，由于所有的硬币的面值两两成倍数关系，
     * 所以一定尽量每次优先用最大的面值去找零，如果最大的面值大于剩下的金额数，再用第二大的面值。
     * 利用贪心思想，从大到小进行计算。

代码思路
令常量 VALUES = [64, 16, 4, 1]。
从大到小遍历，将 number 加上 change / value，代表需要多少个面值 value的硬币，
change \mod value 代表用这种面值后剩下多少金额。
时间复杂度
只需常量次数的计算，时间复杂度为O(1)。
空间复杂度
需要常量空间进行计算，空间复杂度为O(1)。
     */
     /**
     * @param amount: The amount you should pay.
     * @return: Return the minimum number of coins for change.
     */
    public int giveChange(int amount) {
        final List<Integer> VALUES = new ArrayList<Integer>();//用final的好处是？
        VALUES.add(64);
        VALUES.add(16);
        VALUES.add(4);
        VALUES.add(1);

        int change = 1024 - amount;
        int number = 0;

        for (int i = 0; i < 4; i++) {
            int value = (int)VALUES.get(i); //这里Interger到int是有隐式转化的，只不过代码习惯这里最好是显式转化
            number += change / value; //比如66 / 64 = 1
            change %= value; //余数2
        }
        return number;
    }

}