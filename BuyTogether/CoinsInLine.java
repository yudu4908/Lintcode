package BuyTogether;
/**
There are n coins in a line. Two players take turns to take one or two coins from right side 
until there are no more coins left. The player who take the last coin wins.

Could you please decide the first player will win or lose?

If the first player wins, return true, otherwise return false.
 */
public class CoinsInLine {
    /**
     Input: 4
Output: true
Explanation:
The first player takes 1 coin at first. Then there are 3 coins left.
Whether the second player takes 1 coin or two, then the first player can take all coin(s) left.
     */
    /**
    可以证明, 当硬币数目是3的倍数的时候, 先手玩家必败, 否则他必胜.

当硬币数目是3的倍数时, 每一轮先手者拿a个, 后手者拿3-a个即可, 后手必胜.

若不是3的倍数, 先手者可以拿1或2个, 此时剩余硬币个数就变成了3的倍数.
这是一个数学题
     */
    public boolean firstWin(int n) {
        if (n % 3 == 0) {  //n mods 3 returns the remainder from division(n divided by 3)
            return false;
        } else {
            return true;
        }
    }
}