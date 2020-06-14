package Bytedance;

/**
 According to a mahjong game called bird soul, simplified the rules of the game, as follows:

There are 36 cards in total, and each card is 1-9.And each number has four cards.

If 14 cards are taken out and the following conditions are satisfied, that is to win in mahjong:

Two of the 14 cards have the same number of cards, known as the Sparrow head.
Remove the above 2 cards, the remaining 12 cards can form 4 shunzi or kezi.
Note: shunzi means an increasing number of three consecutive characters (e.g. 234,567),
 and an kezi means three characters of the same number (e.g. 111,777).

You draw 13 cards out of 36 cards, and then you take another card out of the remaining 23 cards, 
and what kind of number cards can you draw with?
根据一款叫做雀魂的麻将游戏，简化了游戏规则，具体如下：
1.总共有36张牌，每张牌是1~9。每个数字4张牌。
2.如果取出14张牌满足如下条件，即算作和牌：
①14张牌中有2张相同数字的牌，称为雀头。
②除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。

注：顺子的意思是递增的连续3个数字牌（例如234,567等），刻子的意思是相同数字的3个数字牌（例如111,777）。

从36张牌中抽取了13张牌，那么在剩下的23张牌中再取一张牌，取到哪几种数字牌可以和牌？
若满足条件的有多种牌，请按从小到大的顺序返回。若没有满足条件的牌，请返回0。
 
 */

import java.util.ArrayList;
import java.util.List;

public class MajsoulSolution {
    /**
     * 输入: 
[1, 1, 1, 2, 2, 2, 5, 5, 5, 6, 6, 6, 9]
输出: 
[9]
说明：可以组成1,2,5,6的4个刻子和9的雀头。
输入: 
[1, 1, 1, 1, 2, 2, 3, 3, 5, 6, 7, 8, 9]
输出: 
[4, 7]
说明：
用1做雀头，组123,123,567或456,789的四个顺子。
     */
    /**
1.分别枚举数字1-9添加已知的13张牌中，注意如果这13张牌中已经有4张i则不能添加i，因为每个数字最多出现4次。
我们可以用一个数组times[]记录每个数字的出现次数。
2.对于每种14张牌的情况，再分别枚举每个数字j作为雀头(如果j有两张以上)，判断是否可以和牌。
3.然后从1-9开始遍历，如果数字k出现次数times[k]>=3，说明数字k可以组成一个刻子"kkk"，那么优先组成刻子，times[k]-=3。
若判断剩下的k是否可以组成顺子，那么判断k,k+1,k+2如果都存在牌，说明可以组成一个顺子"kk+1k+2"，这三个元素的出现次数都-1；
如果不能组成顺子，说明不可以和牌，因为剩下的所有牌都需要被分配到刻子或顺子中，不能落单。
     */
    /**
     * @param cards: A list of cards.
     * @return: A list of feasible solution.
     */
    // 判断此时的14张牌是否可以和牌
    boolean check(int[] times) {
        int[] tmp = new int[10];
        //i作为雀头
        for (int i = 1; i < 10; i++) {
            if (times[i] < 2) {
                continue;//如果当前13张中没有雀头呢？这不可能，因为数字只有1-9，必须至少有4个重复
            }
            //ok 标记可以和牌的状态
            boolean ok = true;
            for (int k = 1; k < 10; k++) {
                tmp[k] = times[k];
            }
            tmp[i] -= 2;
            for (int j = 1; j < 10 && ok; j++) {
                //刻子
                if (tmp[j] >= 3) {
                    tmp[j] -= 3;
                }
                while (tmp[j] > 0 && ok) {
                    if (j + 2 > 9) {
                        ok = false;
                        break;
                    }
                    //顺子(j, j + 1, j + 2) 
                    if (tmp[j + 1] > 0 && tmp[j + 2] > 0) {
                        tmp[j]--;
                        tmp[j + 1]--;
                        tmp[j + 2]--;
                    } else {
                        ok = false;
                    }
                    
                }
            }
            if (ok) {
                return true;
            }
        }
        return false; //如果遍历了9种雀头都在这副牌面和不了那就算了
    }

    public List<Integer> getTheNumber(int[] cards) {
        //times[i]表示数字i出现的次数
        int[] times = new int[10];
        //List<Integer> ans = new ArrayList<>();
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < 13; i++){   //玩家手上现在只有13张牌
            times[cards[i]]++;
        }    
        //和牌：雀头+4*顺子/刻子
        for (int i = 1; i < 10; i++) {
            if (times[i] < 4) {
                times[i]++; //假设最后一张牌摸到i
                if (check(times)) {
                    ans.add(i);
                }
                times[i]--; //还原到13张牌
            }
        }
        if (ans.size() == 0) {
            ans.add(0);
        }
        return ans;
    }
}