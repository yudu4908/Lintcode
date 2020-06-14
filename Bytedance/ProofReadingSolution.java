package Bytedance;

import java.util.ArrayList;
import java.util.List;

public class ProofReadingSolution {
    /**
     有一些单词中有拼写错误，请编写一个程序，能够自动校对单词的拼写，并且返回正确值。
校对的规则如下：

如果有三个相同的字符连在一起，一定是拼写错误，去掉其中一个，如：ooops -> oopsooops−>oops。
如果有两对一样的字符(AABB的形式)连在一起,，一定是拼写错误，去掉第二对中的一个字符，如：helloo -> hellohelloo−>hello。
以上两条规则要优先从左往右处理，如：aabbccaabbcc 中 aabbaabb 和 bbccbbcc 都是拼写错误，应该优先考虑修复aabbaabb，结果是aabccaabcc。
输入字符串的长度为 nn，1<=n<=10^5
字符串均由小写字符组成。
     */
    /**
     样例输入 2:
    str = "woooow"
    样例输出 2:
    "woow"
    "oooo"拼写错误，先删去一个'o'，"ooo"还是拼写错误，再删去一个'o'。
     */
    /**
     * 令栈result为一个空栈。
    遍历字符串，每次将当前字符加入result中。
    检查栈的最后3个字符，如果相同，弹出栈顶的字符。
    检查栈的最后4个字符，如果相同，弹出栈顶的字符。
    最后返回结果的字符串。
     */
    /**
     * 复杂度分析
设校对前的字符串长为N。

时间复杂度
遍历一遍字符串，复杂度为O(N)。
空间复杂度
维护栈的空间复杂度为O(N)
     */
        /**
     * @param str: The string before proofreading.
     * @return: Return the string after proofreading.
     */
    public String automaticProofReadingProgram(String str) {
        List<Character> result = new ArrayList<Character>(); //用数组列表模拟栈
        for (int i = 0; i < str.length(); i++) {
            result.add(str.charAt(i));
            int n = result.size();
            if (n < 3) {
                continue;
            }
            if (result.get(n - 1) == result.get(n - 2)) {
                //rule 1
                if (n >= 3) {
                    if (result.get(n - 1) == result.get(n - 3)) {
                        result.remove(n - 1);
                        continue; //跳出for循环插入下一个字母
                    }
                }
                n = result.size(); //为什么又要赋值一遍？不赋值也能测试通过
                //rule 2
                if (n >= 4) {
                    if (result.get(n - 3) == result.get(n - 4)) {
                        result.remove(n - 1);
                    }
                }
            }
        }
        StringBuffer resultStr = new StringBuffer();// 利用StringBuffer将arraylist转为string
        for (int i = 0; i < result.size(); i++) {
            resultStr.append(result.get(i));
        }
        return resultStr.toString();
    }
}